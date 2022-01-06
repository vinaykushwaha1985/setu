package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gov.nha.bis.server.model.UserForm;

@CrossOrigin
@Controller
public class UserLogOutController {
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView  signOut(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
	    new SecurityContextLogoutHandler()
	            .logout(request, response, null);
	    new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
	            .logout(request, response, null);
	    
	    return new ModelAndView("logout", "command", new UserForm());
	
	}
	
	@RequestMapping(value = "/sessionExpire", method = RequestMethod.GET)
	public ModelAndView  sessionExpired(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
	    new SecurityContextLogoutHandler()
	            .logout(request, response, null);
	    new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY)
	            .logout(request, response, null);
	    
	    return new ModelAndView("sessionExpire", "command", new UserForm());
	
	}

}
