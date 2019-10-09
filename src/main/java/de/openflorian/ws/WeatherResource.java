package de.openflorian.ws;

import de.openflorian.data.model.Operation;
import de.openflorian.weather.Weather;
import de.openflorian.weather.WeatherProvisioningService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * REST Resource for Weather Handlinng
 * 
 * @author Bastian kraus <bofh@k-hive.de>
 */
public interface WeatherResource {

	/**
	 * REST Resource to access the current {@link Weather} provided by {@link WeatherProvisioningService}<br/>
	 * <br/>
	 * HTTP Method: GET
	 *
	 * @return
	 */
	@ApiOperation(httpMethod="GET", value = "Get current weather", response = Weather.class)
	@GetMapping(path="/current")
	Weather current();

}
