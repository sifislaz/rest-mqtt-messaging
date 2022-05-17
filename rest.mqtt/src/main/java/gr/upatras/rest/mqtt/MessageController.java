package gr.upatras.rest.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MessageController {
	@Autowired
	private IMessageService messageService;

	private static final Logger log = LoggerFactory.getLogger(MessageController.class);

	@ApiOperation(value = "Gets message", notes = "This operation retrieves message", response = Message.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/message/", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Message getMessage() {
		Message msg = messageService.findMessage();
		return msg;
	}

	@ApiOperation(value = "Creates a Message", notes = "This operation creates a Message entity.", response = Message.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = Message.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
	})
	@RequestMapping(value = "/message/", produces = { "application/json;charset=utf-8" }, consumes = {
			"application/json;charset=utf-8" }, method = RequestMethod.POST)
	public ResponseEntity<Message> createProduct(
			@ApiParam(value = "The Message to be transmitted", required = true) @RequestBody Message m) {
		log.info("Will add a new message");
		Message message = messageService.addMessage(m);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
