package com.example.webshop.service;

import com.example.webshop.model.WebShop;

import java.util.List;

public interface WebShopService {

    List<WebShop> getAllWebShops();

    WebShop getWebShop(Long id);

    WebShop createWebShop(WebShop webShop);

    WebShop updateWebShop(Long id, WebShop webShopChange);

    void deleteWebShop(Long id);

}
