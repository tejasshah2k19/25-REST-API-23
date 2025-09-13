package com.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.ProductEntity;

@RestController
public class ProductController {

	//@GETmapping 
	
	
	@PostMapping("products")
	public String addProduct(@RequestBody ProductEntity productEntity) {
		//json read 
		
		//print data 
		System.out.println(productEntity.getProductName());
		System.out.println(productEntity.getCategory());
		System.out.println(productEntity.getPrice());
		System.out.println(productEntity.getQty());
		//validate 
		
		//db insert 
		
		return "Product added";
	}
	
}
