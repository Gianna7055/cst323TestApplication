package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessInterface;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController
{
	// Inject and auto-wire OrdersBusinessInterface
	@Autowired
	private OrdersBusinessInterface service;
	// Inject and auto-wire SecurityBusinessService
	@Autowired
	private SecurityBusinessService security;

	@GetMapping("/")
	public String display(Model model)
	{
		// Display Login Form View
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}

	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		// Check for validation errors
		if (bindingResult.hasErrors())
		{
			model.addAttribute("title", "Login Form");
			return "login";
		}

		// Get the list of orders
		List<OrderModel> orders = service.getOrders();

		// Set model attributes
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);

		security.authenticate(loginModel.getUsername(), loginModel.getPassword());

		// Navigate to the "orders" view
		return "orders";
	}
}
