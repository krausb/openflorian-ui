package de.openflorian.ws;

import de.openflorian.data.model.Operation;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * REST Resource for Alarm Handlinng
 * 
 * @author Bastian kraus <bofh@k-hive.de>
 */
public interface AlarmResource {

	/**
	 * REST Resource to incurre an {@link Operation} with given <code>operationId</code><br/>
	 * <br/>
	 * HTTP Method: POST
	 * 
	 * @param operationId
	 * @return
	 */
	@ApiOperation(httpMethod="POST", value = "Incurre alarm")
	@PostMapping(path="/")
	void incurre(@RequestParam("operationId") long operationId);

	/**
	 * REST Resource to incurre the last incurred operation
	 * 
	 * HTTP Method: POST
	 * 
	 * @return
	 */
	@ApiOperation(httpMethod="POST", value = "Re-incurre last incurred alarm")
	@PostMapping(path="/last")
	void incurreLast();

	/**
	 * REST Resource to load all running content crawler available<br/>
	 * <br/>
	 * HTTP Method: GET
	 * 
	 * @return {@link Operation}
	 */
	@ApiOperation(httpMethod="GET", value = "Get the current incurred operation", response = Operation.class)
	@GetMapping(path="/current", produces= MediaType.APPLICATION_JSON_VALUE)
	public Operation getCurrentOperation();

	/**
	 * REST Resource to takeOver the current {@link Operation}<br/>
	 * <br/>
	 * HTTP Method: PUT
	 *
	 * @return
	 */
	@ApiOperation(httpMethod = "PUT", value = "Takeover current incurred operation")
	@PutMapping(path="/current")
	public void takeOver();

	/**
	 * REST Resource to takeOver an {@link Operation} with given <code>operationId</code><br/>
	 * <br/>
	 * HTTP Method: DELETE <br/>
	 * HTTP Response:<br/>
	 * 200 - Successfuly dispatched 404 - Currently no operation
	 *
	 * @return
	 */
	@ApiOperation(httpMethod = "DELETE", value = "Dispatch current incurred operation")
	@DeleteMapping("/current")
	public void dispatch();

}
