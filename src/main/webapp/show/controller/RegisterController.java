package main.webapp.show.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import main.webapp.ecsa.dao.Login;
import main.webapp.ecsa.hibernate.Users;
import main.webapp.show.util.MessageData;
import main.webapp.show.util.SessionFactoryUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class RegisterController {
	 static Logger LOG = Logger.getLogger( RegisterController.class.toString());
	
	 @RequestMapping(value="/register", method = RequestMethod.POST, consumes="application/json", headers = {"Content-type=application/json"})
		public @ResponseBody MessageData registerPost(@RequestBody Users user) {
		 MessageData message = new MessageData();
		 
		 message =  SessionFactoryUtil.saveObject(user,"User");
		 LOG.info(user.getMail());
		 return message;
	 }
	 
	 @RequestMapping(value="/login", method = RequestMethod.POST, consumes="application/json", headers = {"Content-type=application/json"})
		public @ResponseBody MessageData loginPost(@RequestBody Users user) {
		 MessageData message = new MessageData();

		 message = SessionFactoryUtil.userExist(user);
		 LOG.info(user.getMail());
		 return message;
	 }
	 

}
