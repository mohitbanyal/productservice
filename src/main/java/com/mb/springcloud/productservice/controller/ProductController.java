package com.mb.springcloud.productservice.controller;

import com.mb.springcloud.productservice.datatransferobject.Coupon;
import com.mb.springcloud.productservice.model.Product;
import com.mb.springcloud.productservice.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("productapi")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${couponService.url}")
    private String couponServiceURL;

    @PostMapping("products")
    public Product create(@RequestBody Product product){
        Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
        BigDecimal priceAfterDiscount = product.getPrice().subtract(coupon.getDiscount());
        product.setPrice(priceAfterDiscount);
        return productRepository.save(product);
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProducts(@PathVariable("id") long id){
        return productRepository.findById(id);
    }


}
