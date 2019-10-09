package de.openflorian.ws.impl;

import de.openflorian.data.dao.OperationDao;
import de.openflorian.data.dao.OperationRepository;
import de.openflorian.data.model.Operation;
import de.openflorian.weather.WeatherProvisioningService;
import de.openflorian.ws.OperationResource;
import io.swagger.annotations.Api;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

/**
 * Impl of {@link OperationResource}
 * 
 * @author Bastian Kraus <bofh@k-hive.de>
 */
@Api(value = "/operation", description = "API functions for managing operations")
@RestController
@RequestMapping("operation")
public class OperationResourceImpl implements OperationResource {

	private static final Logger log = LoggerFactory.getLogger(OperationResourceImpl.class);

	@Autowired
	private OperationRepository operationRepository;

	public OperationResourceImpl() {
		log.info("Registering " + this.getClass().getName() + " to Spring context.");
	}

	@Override
	public List<Operation> getAllOperations() {
		try {
			return IterableUtils.toList(operationRepository.findAll());
		}
		catch (final Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}

	@Override
	public List<Operation> getLastOperations(int limit) {
		try {
			List<Operation> operations = IterableUtils.toList(operationRepository.findAll());
			if(operations.size() > 0) {
				if(limit >= operations.size())
					return operations.subList(0, operations.size() - 1);
				else if(limit < operations.size())
					return operations.subList(operations.size() - limit - 1, operations.size() - 1);
				else
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "limit out of bounds ( <= 0 ).");
			} else
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No operation found.");
		}
		catch (final Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}

}
