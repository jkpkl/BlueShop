package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BlueShopAPI {
	@Autowired
	private ProductRepository repo;
	@RequestMapping(value="/products")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> plist = repo.findAll();
		return new ResponseEntity<List<Product>>(plist,HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}")
	public ResponseEntity<Product> getOneProduct(@PathVariable int id){
		Product p = repo.findOne(id);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}

	@RequestMapping(value="/products",method=RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody Product p){
		Product p1 = repo.save(p);
		
		return new ResponseEntity<Product>(p1,HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Product> editProduct(@RequestBody Product p,@PathVariable int id){
		p.setPid(id);
		Product p1 = repo.save(p);
		return new ResponseEntity<Product>(p1,HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		repo.delete(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
}
