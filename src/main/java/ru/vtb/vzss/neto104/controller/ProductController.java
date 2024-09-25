package ru.vtb.vzss.neto104.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.vzss.neto104.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/fetch-product")
    public ResponseEntity<List<String>> fetchProduct(@RequestParam("name") String name) {
        return ResponseEntity.ok(productRepository.getProductsByCustomer(name));
    }
}
