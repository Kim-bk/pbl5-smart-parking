package com.parkingsmart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingsmart.dto.CustomerDTO;
import com.parkingsmart.dto.DistrictDTO;
import com.parkingsmart.dto.ProvinceDTO;
import com.parkingsmart.dto.WardDTO;
import com.parkingsmart.service.impl.CustomerService;
import com.parkingsmart.service.impl.DistrictService;
import com.parkingsmart.service.impl.ProvinceService;
import com.parkingsmart.service.impl.WardService;

@Controller
public class UserController {
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private WardService wardService;
	
	@Autowired
	private CustomerService customerService;
	
	
	
	@GetMapping("/user/home")
	public String getAdminHome(Model model) {
		CustomerDTO customer = new CustomerDTO();
		model.addAttribute("customer",customer);
		List<ProvinceDTO> provinces = provinceService.findAll();
		model.addAttribute("provinces", provinces);
		return "user/index";
	}
	
	@GetMapping("/user/district")
	@ResponseBody
	public List<DistrictDTO> getDistrict(@RequestParam(name="province_id") Optional<String> id){
		if (id.isPresent()) {
			List<DistrictDTO> districts = districtService.findByIdProvince(id.get()); 
			return districts;
		}
		return null;
	}
	
	@GetMapping("/user/ward")
	@ResponseBody
	public List<WardDTO> getWard(@RequestParam(name="district_id") Optional<String> id){
		if (id.isPresent()) {
			List<WardDTO> wards = wardService.findByDistrict(id.get());
			return wards;
		}
		return null;
	}
	
	@PostMapping(value="/user/add")
	public String addCustomer(@ModelAttribute("customer") CustomerDTO customer) {
		
		customerService.save(customer);
		return "redirect:/user/home";
	}

}
