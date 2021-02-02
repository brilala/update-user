package com.hcl.updateuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private UsersService service;

	@RequestMapping("/")
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<Users> listUsers = service.listAll(keyword);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("keyword", keyword);

		return "index.html";
	}

	@RequestMapping("/newUser")
	public String addNewUserForm(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);

		return "new_user.html";

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("users") Users users) {
		service.save(users);

		return "redirect:/";
	}

	@RequestMapping("/editUser/{id}")
	public ModelAndView editUserForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_user");

		Users users = service.get(id);
		mav.addObject("users", users);

		return mav;

	}
	
	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(name="id")Long id) {
		service.delete(id);
		
		return "redirect:/";
	}

}
