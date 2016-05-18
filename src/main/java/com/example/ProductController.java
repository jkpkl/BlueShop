package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	private ProductRepository repo;
	
	@Autowired
	public ProductController(ProductRepository repo) {
		this.repo = repo;
	}
	
	@RequestMapping(value="/")
	public String listProduct(Model model) {
		List<Product> plist = repo.findAll();
		model.addAttribute("plist", plist);
		return "main";
	}
	
	@RequestMapping(value="/delete/{pid}")
	public String deleteProduct(@PathVariable("pid") int pid, Model model) {
		repo.delete(pid);
		return "redirect:/";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addProduct(@RequestParam("pname") String pname, @RequestParam("pdetail") String pdetail, @RequestParam("price") int price) {
		Product p = new Product();
		p.setPname(pname);
		p.setPdetail(pdetail);
		p.setPrice(price);
		repo.save(p);
		return "redirect:/";
	}
	
}
