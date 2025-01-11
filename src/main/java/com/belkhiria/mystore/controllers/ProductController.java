package com.belkhiria.mystore.controllers;

import com.belkhiria.mystore.dtos.ProductRequest;
import com.belkhiria.mystore.dtos.ProductResponse;
import com.belkhiria.mystore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/{user-id}")
	public ResponseEntity<ProductResponse> addProduct(
			@PathVariable("user-id") Integer userId,
			@RequestBody ProductRequest productRequest
	) {
		return new ResponseEntity<>(productService.addProduct(userId, productRequest), HttpStatus.CREATED);
	}

	@PutMapping("/{product-id}")
	public ResponseEntity<ProductResponse> updateProduct(
			@PathVariable("product-id") Integer productId,
			@RequestBody ProductRequest productRequest
	) {
		return ResponseEntity.ok(productService.updateProduct(productId, productRequest));
	}

	@GetMapping("/{product-id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("product-id") Integer productId) {
		return ResponseEntity.ok(productService.getProductById(productId));
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@DeleteMapping("/{product-id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("product-id") Integer productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}
}
