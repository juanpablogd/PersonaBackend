package com.alphai.persona.web.controlador;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
/*
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alphai.persona.dominio.Product;
import com.alphai.persona.dominio.servicio.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
	
	@GetMapping()
	@Operation(summary = "Consigue la lista de todos los productos")
	public ResponseEntity<List<Product>> getAll(){
		System.out.println("Todos");
		//productService.getAll(),HttpStatus.OK
		return ResponseEntity.ok(productService.getAll());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obtiene un porgucto en especifico")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200",description = "OK"),
		@ApiResponse(responseCode = "404",description = "Producto no encontrado"),
	})
	public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
		System.out.println(productId);
		//si es Optional se usa un map para trabajar en su interior
		return productService.getProduct(productId)
				.map(product -> ResponseEntity.ok(product))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") int categoryId){
		return productService.getByCategory(categoryId)
				.map( p -> ResponseEntity.ok(p))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable("id")int productId) {
		return new ResponseEntity(productService.delete(productId) ? HttpStatus.OK : HttpStatus.NOT_FOUND) ;
	}
}
