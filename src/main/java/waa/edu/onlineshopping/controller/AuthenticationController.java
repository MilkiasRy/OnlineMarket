package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.CredentialService;
import waa.edu.onlineshopping.service.RoleService;
import waa.edu.onlineshopping.service.SellerService;
import waa.edu.onlineshopping.util.EmailNotification;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Controller
@SessionAttributes({ "buyerReset", "credentialReset" })
public class AuthenticationController {

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CredentialService credentialService;

	@Autowired
	private EmailNotification emailNotification;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signupPage() {
		//@ModelAttribute("buyer") Buyer buyer
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("buyer", new Buyer());
		modelAndView.addObject("seller", new Seller());
		modelAndView.setViewName("signup"); // resources/template/home.html
		return modelAndView;

	}

	@RequestMapping(value = "/buyer/signup", method = RequestMethod.POST)
	public String buyerSignup(@Valid @ModelAttribute("buyer") Buyer buyer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		System.out.println("INSIDE BUYER/SIGNUP ");
		if(bindingResult.hasErrors()){
			System.out.println(bindingResult.getAllErrors().toString());
			model.addAttribute("seller", new Seller());
			return "signup";
		}
		buyer.getCredential().setPassword(bCryptPasswordEncoder.encode(buyer.getCredential().getPassword()));

		Role buyerRole = roleService.findByRole("ROLE_BUYER");
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(buyerRole);
		buyer.getCredential().setRoles(roleSet);

		Buyer b = buyerService.save(buyer);

//		Credential credential = b.getCredential();
//		credential.setBuyer(b);
//		credentialService.save(credential);

		System.out.println("**********BUYER SIGNUP INFO************");
		System.out.println(b);
		redirectAttributes.addFlashAttribute("success", "Signup Successful. Pleas login to continue shopping");
		return "redirect:/signup";
	}

	@RequestMapping(value = "/seller/signup", method = RequestMethod.POST)
	public String sellerSignup(@Valid @ModelAttribute("seller") Seller seller, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()){
			return "signup";
		}
		seller.getCredential().setPassword(bCryptPasswordEncoder.encode(seller.getCredential().getPassword()));
		seller.getCredential().setEnabled(false);

		Role sellerRole = roleService.findByRole("ROLE_SELLER");
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(sellerRole);
		seller.getCredential().setRoles(roleSet);

		Seller s = sellerService.save(seller);
		System.out.println("**********SELLER SIGNUP INFO************");
		System.out.println(s);
		redirectAttributes.addFlashAttribute("success", "We will email once your account is approved and activated");
		return "redirect:/signup";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(Principal user) {
		System.out.println("CURRENT USER " + user.getName());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}

	@RequestMapping(value = "/reset/password", method = RequestMethod.GET)
	public String resetPassword() {

		return "resetpassword";
	}

	@RequestMapping(value = "/security/question/{email}", method = RequestMethod.GET)
	public @ResponseBody Buyer getSecurityQuestion(@PathVariable("email") String email, Model model) {
		Credential credential = credentialService.findByEmail(email);
		Buyer buyer = buyerService.findByCredential(credential);

		model.addAttribute("buyerReset", buyer);
		model.addAttribute("credentialReset", credential);


		return buyer;
	}

	@RequestMapping(value = "/security/question/{answer}", method = RequestMethod.POST)
	public @ResponseBody String securityQuestionAnswer(@PathVariable("answer") String answer, Model model) {
		Credential credential = (Credential) model.asMap().get("credentialReset");
		Buyer buyer = (Buyer) model.asMap().get("buyerReset");

		if(answer.equalsIgnoreCase(buyer.getSecurityAnswer())){
			String newPassword = UUID.randomUUID().toString();
			System.out.println("NEW PASSWORD " + newPassword);
			credential.setPassword(bCryptPasswordEncoder.encode(newPassword));
			credentialService.save(credential);
			emailNotification.sendEmail(credential.getEmail(), "Password Resetted" ,
					"Your new password is: <strong style=\"color:red;\">" + newPassword
							+ "</strong> <p>Login Page: <a href=\"http://localhost:8080/login\">Online Shopping</a></p>");

			return "{\"success\": \"Password reset successful. Check your email for the new password \"}";
		}

		System.out.println("WRONG ANSWER");
		return "{\"failure\": \"Incorrect answer \"}";
	}

	@ExceptionHandler(Exception.class)
	public void  exc(Exception ex) {
		System.out.println(ex.getMessage());
	}
}
