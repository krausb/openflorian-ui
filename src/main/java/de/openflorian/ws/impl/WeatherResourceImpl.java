package de.openflorian.ws.impl;

import de.openflorian.weather.Weather;
import de.openflorian.weather.WeatherProvisioningService;
import de.openflorian.ws.WeatherResource;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import javax.persistence.EntityNotFoundException;

/**
 * Impl of {@link WeatherResource}
 * 
 * @author Bastian Kraus <bofh@k-hive.de>
 */
@Api(value = "/weather", description = "Current weather for the default screen")
@RestController
@RequestMapping("weather")
public class WeatherResourceImpl implements WeatherResource {

	private static final Logger log = LoggerFactory.getLogger(OperationResourceImpl.class);

	@Autowired
	private WeatherProvisioningService weatherService;

	public WeatherResourceImpl() {
		log.info("Registering " + this.getClass().getName() + " to Spring context.");
	}

	@Override
	public Weather current() {
		try {
			final Weather w = weatherService.getCurrentWeather();
			if (w != null)
				return w;
			else
				throw new EntityNotFoundException();
		}
		catch (final EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No current weather available.");
		}
		catch (final Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}

}
