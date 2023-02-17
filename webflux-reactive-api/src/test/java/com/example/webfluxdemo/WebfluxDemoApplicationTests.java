package com.example.webfluxdemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.react.api.model.Product;
import com.react.api.repository.ProductRepository;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Collections;

@SpringBootConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebfluxDemoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
    ProductRepository productRepository;

	@Test
	public void testCreateproduct() {
		Product product = new Product("1","Test","Type",BigDecimal.ONE,BigDecimal.TEN,null);

		webTestClient.post().uri("/products")
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(product), Product.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.text").isEqualTo("This is a Test product");
	}

	@Test
    public void testGetAllproducts() {
	    webTestClient.get().uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Product.class);
    }

    @Test
    public void testGetSingleproduct() {
        Product product = productRepository.save(new Product("2","Test2","Type2",BigDecimal.ONE,BigDecimal.TEN,null)).block();

        webTestClient.get()
                .uri("/products/{id}", Collections.singletonMap("id", product.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testUpdateproduct() {
        Product product = productRepository.save(new Product("3","Test3","Type3",BigDecimal.ONE,BigDecimal.TEN,null)).block();

        Product newproductData = new Product("4","Test4","Type4",BigDecimal.ONE,BigDecimal.TEN,null);

        webTestClient.put()
                .uri("/products/{id}", Collections.singletonMap("id", product.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(newproductData), Product.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.text").isEqualTo("Updated product");
    }

    @Test
    public void testDeleteproduct() {
	    Product product = productRepository.save(new Product("2","Test","Type",BigDecimal.ONE,BigDecimal.TEN,null)).block();

	    webTestClient.delete()
                .uri("/products/{id}", Collections.singletonMap("id",  product.getId()))
                .exchange()
                .expectStatus().isOk();
    }
}
