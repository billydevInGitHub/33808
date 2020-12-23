package pl.piomin.services.caller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/caller")
public class CallerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallerController.class);

	@Autowired
	Environment environment;
	@Autowired
	RestTemplate template;

	@GetMapping
	public String call(@RequestHeader(value="Accept") String acceptHeader,
					   @RequestHeader(value="Authorization") String authorizationHeader) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", acceptHeader);
		headers.set("Authorization", authorizationHeader);
		String url = "http://hotels-service-33808exp206//hotels//test";

		HttpEntity entity = new HttpEntity(headers);

		ResponseEntity<String> response = template.exchange(
				url, HttpMethod.GET, entity, String.class);


		LOGGER.info("Response: {}", response);
		return "I'm Caller running on port " + environment.getProperty("local.server.port")
				+ " calling-> " + response;
	}

	@GetMapping("/slow")
	public String slow() {
		String url = "http://callme-service/callme/slow";
		String callmeResponse = template.getForObject(url, String.class);
		LOGGER.info("Response: {}", callmeResponse);
		return "I'm Caller running on port " + environment.getProperty("local.server.port")
				+ " calling-> " + callmeResponse;
	}
}