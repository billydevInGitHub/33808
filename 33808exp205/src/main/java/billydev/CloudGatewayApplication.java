package billydev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;
import reactor.tools.agent.ReactorDebugAgent;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
public class CloudGatewayApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

	@GetMapping(value = "/")
	public String getIndex() {
		return "index";
	}

	@GetMapping(value = "/home")
	public String getHome() {
		return "home";
	}

	@GetMapping("/whoami")
	@ResponseBody
	public Map<String, Object> index(
			@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
			@AuthenticationPrincipal OidcUser oidcUser) {
		Map<String, Object> model = new HashMap<>();
		model.put("clientName", authorizedClient.getClientRegistration().getClientName());
		model.put("userName", oidcUser.getName());
		model.put("userAttributes", oidcUser.getAttributes());
		/*  uncomment the following lines when need break a reactive chain
		HashMap hashMap = new HashMap();
		hashMap.put("Error", Mono.error(new RuntimeException("Mimic error")));
		return hashMap;
 		*/
		return model;
	}

}
