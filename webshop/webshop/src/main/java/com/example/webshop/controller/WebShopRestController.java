package com.example.webshop.controller;

import com.example.webshop.model.WebShop;
import com.example.webshop.service.WebShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/web-shop")
public class WebShopRestController {

    private final WebShopService webShopService;

    public WebShopRestController(WebShopService webShopService) {
        this.webShopService = webShopService;
    }

    @GetMapping
    public List<WebShop> getallWebShops(){
        return webShopService.getAllWebShops();
    }

    @GetMapping("/{id}")
    WebShop getWebShop(final @PathVariable("id") Long id){
        return webShopService.getWebShop(id);
    }

    @PostMapping
    WebShop createWebShop(final @RequestBody WebShop webShop) {

        return webShopService.createWebShop(webShop);
    }

//    @PostMapping
//    WebShop createWebShop(final @RequestBody WebShop webShop){
//        return webShopService.createWebShop(webShop);
//    }
//    @PutMapping("/{id}")
//    WebShop updateWebShop(final @PathVariable Long id, final @RequestBody WebShop webShopChange){
//        return webShopService.updateWebShop(id,webShopChange);
//    }

    @PutMapping("/{id}")
    WebShop updateWebShop(final @PathVariable Long id, final @RequestBody WebShop webShopChange) {
        return webShopService.updateWebShop(id, webShopChange);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWebShop(final @PathVariable Long id){
        webShopService.deleteWebShop(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
