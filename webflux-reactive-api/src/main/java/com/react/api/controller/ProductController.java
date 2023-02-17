package com.react.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.react.api.exception.ProductNotFoundException;
import com.react.api.model.Product;
import com.react.api.payload.ErrorResponse;
import com.react.api.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/listProducts")
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/createProduct")
    public Mono<Product> createProducts(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/getProductById/{id}")
    public Mono<ResponseEntity<Product>> getproductById(@PathVariable(value = "id") String productId) {
        return productRepository.findById(productId)
                .map(savedproduct -> ResponseEntity.ok(savedproduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/updateProductById/{id}")
    public Mono<ResponseEntity<Product>> updateproduct(@PathVariable(value = "id") String productId,
                                                   @Valid @RequestBody Product product) {
        return productRepository.findById(productId)
                .flatMap(existingproduct -> {
                    existingproduct.setName(product.getName());
                    return productRepository.save(existingproduct);
                })
                .map(updateproduct -> new ResponseEntity<>(updateproduct, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleteProductById/{id}")
    public Mono<ResponseEntity<Void>> deleteproduct(@PathVariable(value = "id") String productId) {

        return productRepository.findById(productId)
                .flatMap(existingproduct ->
                        productRepository.delete(existingproduct)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Products are Sent to the client as Server Sent Events
	@GetMapping(value = "/stream/products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> streamAllProducts() {
        return productRepository.findAll();
    }

    /* Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)- */

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A product with the same text already exists"));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
