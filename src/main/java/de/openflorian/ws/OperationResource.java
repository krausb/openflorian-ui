package de.openflorian.ws;

import de.openflorian.data.model.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Resource for Operation Handlinng
 * 
 * @author Bastian kraus <bofh@k-hive.de>
 */
public interface OperationResource {

	/**
	 * REST Resource to load a {@link List}<{@link Operation}>
	 * 
	 * @return {@link List}<{@link Operation}>
	 */
	@ApiOperation(httpMethod="GET", value = "List all operations", response = Operation.class, responseContainer = "List")
	@GetMapping(path="/all", produces= MediaType.APPLICATION_JSON_VALUE)
	List<Operation> getAllOperations();

	/**
	 * REST Resource to load a {@link List}<{@link Operation}>
	 * 
	 * @return {@link List}<{@link Operation}>
	 */
	@ApiOperation(httpMethod="GET", value = "List all operations sorted by id descending with given limit", response = Operation.class, responseContainer = "List")
	@GetMapping(path="/last", produces= MediaType.APPLICATION_JSON_VALUE)
	List<Operation> getLastOperations(@RequestParam("limit") int limit);

}
