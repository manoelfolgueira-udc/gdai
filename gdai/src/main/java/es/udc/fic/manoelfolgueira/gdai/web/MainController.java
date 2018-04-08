package es.udc.fic.manoelfolgueira.gdai.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.udc.fic.manoelfolgueira.gdai.model.entities.User;
import es.udc.fic.manoelfolgueira.gdai.model.entities.UserRepository;

@Controller
public class MainController implements ErrorController {
	
	private final static String PATH = "/error";
		 
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = PATH)
	public @ResponseBody String error() {
		return "Error handling.";
	}
	
	@Override
	public String getErrorPath() {
		return PATH;
	}

	@RequestMapping(value = "/")
	@CrossOrigin(origins = "*") 
	public String index() {
		return "index.html";
	}
	
	@RequestMapping(value = "/all")
	public @ResponseBody String getAll() {
		return userRepository.findAll().toString();
	}

	@RequestMapping("/add")
	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
		User n = new User(name, email);
		userRepository.save(n);
		return "Saved";
	}

}