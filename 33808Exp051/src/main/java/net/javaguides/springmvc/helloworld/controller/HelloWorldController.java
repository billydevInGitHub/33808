package net.javaguides.springmvc.helloworld.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springmvc.helloworld.model.HelloWorld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ramesh Fadatare
 */
@Controller
public class HelloWorldController {

	@RequestMapping("/helloworld")
	public String handler(Model model) {
		
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setMessage("Hello World Example Using Spring MVC 5!!!");
		helloWorld.setDateTime(LocalDateTime.now().toString());
		model.addAttribute("helloWorld", helloWorld);
		return "helloworld";
	}


	@RequestMapping("/pdf/{fileName:.+}")
	public void downloadPDFResource( HttpServletRequest request,
									 HttpServletResponse response,
									 @PathVariable("fileName") String fileName)
	{
		//If user is not authorized - he should be thrown out from here itself

		//Authorized user will download the file
		String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/");
		Path file = Paths.get(dataDirectory, fileName);
		if (Files.exists(file))
		{
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename="+fileName);
			try
			{
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}