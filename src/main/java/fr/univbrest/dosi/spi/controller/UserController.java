package fr.univbrest.dosi.spi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Authentification;
import fr.univbrest.dosi.spi.bean.User;
import fr.univbrest.dosi.spi.exception.SPIException;
import fr.univbrest.dosi.spi.service.AuthentificationService;
import fr.univbrest.dosi.spi.service.UserService;

@RestController
public class UserController {

	@Autowired
	AuthentificationService authoService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST, headers = "Accept=application/json")
	public void authentifier(final HttpServletRequest request,
			@RequestBody final User user) {
		/*
		 * final User users = userService.authentifier(user.getUsername(),
		 * user.getPwd());
		 */
		final Authentification auth = authoService.logIn(user.getUsername(),
				user.getPwd());
		if (auth != null) {
			request.getSession().setAttribute("user", auth);
		} else {
			request.getSession().removeAttribute("user");
			throw new SPIException("impossible de s'authentifier");
		}

	}

	@RequestMapping(value = "/user")
	public Authentification users(final HttpServletRequest request,
			final HttpServletResponse response) {
		final Authentification user = (Authentification) request.getSession()
				.getAttribute("user");
		return user;

	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public void authentifier(final HttpServletRequest request) {
		request.getSession().removeAttribute("user");
	}

}
