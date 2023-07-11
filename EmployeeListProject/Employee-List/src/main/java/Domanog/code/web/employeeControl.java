package Domanog.code.web;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.JavaInfo.JavaRuntimeEnvironmentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Domanog.code.model.Employee;
import Domanog.code.model.repositoryUser.employeeRepo;


@Controller
public class employeeControl {
	
	@Autowired
	private employeeRepo empRepo;
	
	@GetMapping("/")
	public ModelAndView displayList() {
		ModelAndView mav = new ModelAndView("display-employee");
		List<Employee> list = empRepo.findAll();
		mav.addObject("employee", list);
		return mav;
		}
	@GetMapping("/add-employee")
	public ModelAndView addEmployee() {
		ModelAndView mav = new ModelAndView("add-employee");
		Employee newEmployee = new Employee();
		mav.addObject("employee", newEmployee);
		return mav;
	}
	
	@PostMapping("/save-employee")  
	public String saveEmployee(Model model, @ModelAttribute Employee employee) {
		if(empRepo.findByEmail(employee.getEmail()) != null) throw new RuntimeException("Email Already Exist");		
		empRepo.save(employee);
		return "redirect:/";
	}
	
	@GetMapping("/display-form")
	public ModelAndView displayForm(@RequestParam Long employeeId) {
		ModelAndView mav = new ModelAndView("add-employee");
		Employee employee = empRepo.findById(employeeId).get();
		mav.addObject("employee", employee);
		return mav;
	}
	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		empRepo.deleteById(employeeId);
		return "redirect:/";
	}
	@GetMapping("/login")
	public String login() {
		return"login";
	}
	
}
