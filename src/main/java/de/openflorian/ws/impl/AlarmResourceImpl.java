package de.openflorian.ws.impl;

import javax.persistence.EntityNotFoundException;

import de.openflorian.EventBusAddresses;
import de.openflorian.OpenflorianApplication;
import de.openflorian.alarm.AlarmContextService;
import de.openflorian.data.dao.OperationDao;
import de.openflorian.data.model.Operation;
import de.openflorian.ws.AlarmResource;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

/**
 * Impl of {@link AlarmResource}
 * 
 * @author Bastian Kraus <bofh@k-hive.de>
 */
@Api(value = "/alarm", description = "Operations for alarm handling")
@RestController
@RequestMapping("alarm")
public class AlarmResourceImpl implements AlarmResource {

	private static final Logger log = LoggerFactory.getLogger(OperationResourceImpl.class);

	public AlarmResourceImpl() {
		log.info("Registering " + this.getClass().getName() + " to Spring context.");
	}

	@Override
	public Operation getCurrentOperation() {
		return AlarmContextService.getInstance().getCurrentOperation();
	}

	@Override
	public void incurre(long operationId) {
		try {
			final Operation o = new OperationDao().getById(operationId);
			if (o != null)
				OpenflorianApplication.vertx().eventBus().publish(EventBusAddresses.ALARM_INCURRED, o);
			else
				throw new EntityNotFoundException();
		}
		catch (final EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No operation with ID " + operationId + " found.");
		}
		catch (final Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}

	@Override
	public void incurreLast() {
		try {
			final Operation o = new OperationDao().getLastOperation();
			if (o != null)
				OpenflorianApplication.vertx().eventBus().publish(EventBusAddresses.ALARM_INCURRED, o);
			else
				throw new EntityNotFoundException();
		}
		catch (final EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No operation found.");
		}
		catch (final Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}

	@Override
	public void takeOver() {
		try {
			final Operation o = AlarmContextService.getInstance().getCurrentOperation();
			if (o != null)
				OpenflorianApplication.vertx().eventBus().publish(EventBusAddresses.ALARM_TAKENOVER, o);
			else
				throw new EntityNotFoundException();
		}
		catch (final EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No operation in progress.");
		}
		catch (final Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}

	@Override
	public void dispatch() {
		try {
			final Operation o = AlarmContextService.getInstance().getCurrentOperation();
			if (o != null)
				OpenflorianApplication.vertx().eventBus().publish(EventBusAddresses.ALARM_DISPATCHED, o);
			else
				throw new EntityNotFoundException();
		}
		catch (final EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No operation in progress.");
		}
		catch (final Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}

}
