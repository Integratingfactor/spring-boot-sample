package com.integratingfactor.authn.mvc.welcome;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.integratingfactor.idp.lib.client.model.IdpTokenValidation;

@Controller
@RequestMapping(value="/")
public class WelcomeController {
	private static Logger LOG = Logger.getLogger(WelcomeController.class.getName());
	
	@RequestMapping(method=RequestMethod.GET, value="")
    public String getLandingPage(HttpServletRequest request) {
		LOG.info("Landing page for app");
		String page = "index";
        IdpTokenValidation auth;
        try {
            auth = (IdpTokenValidation) SecurityContextHolder.getContext().getAuthentication();
            request.setAttribute("user", auth);
        } catch (ClassCastException e) {
            LOG.info("User is unauthenticated");
        }
		LOG.info("Sending destination: " + page);
		return page;
	}
}