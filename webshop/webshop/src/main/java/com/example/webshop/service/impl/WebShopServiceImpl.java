package com.example.webshop.service.impl;

import com.example.webshop.model.Category;
import com.example.webshop.model.Material;
import com.example.webshop.model.WebShop;
import com.example.webshop.model.exception.NotFoundException;
import com.example.webshop.service.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WebShopServiceImpl implements WebShopService {

    private final List<WebShop> DATA_BASE = new ArrayList<>();

    @Autowired
    public WebShopServiceImpl() {
        DATA_BASE.add(new WebShop(1L, "Ezüst fülbevaló", Material.SILVER, Category.EARRING));
        DATA_BASE.add(new WebShop(2L, "Arany gyűrű gyémánttal", Material.GOLD, Category.RING));
        DATA_BASE.add(new WebShop(3L, "Platinum smaragdköves fülbevaló", Material.PLATINUM, Category.EARRING));
    }


    public WebShopServiceImpl(final List<WebShop> webShops) {
        DATA_BASE.addAll(webShops);
    }

    @Override
    public List<WebShop> getAllWebShops() {
        return Collections.unmodifiableList(DATA_BASE);
    }

    @Override
    public WebShop getWebShop(Long id) {
        return DATA_BASE.stream()
                .filter(webShop -> webShop.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);

    }

    @Override
    public WebShop createWebShop(WebShop webShop) {
        webShop.setId(getNextId());
        DATA_BASE.add(webShop);
        return webShop;
    }

    @Override
    public WebShop updateWebShop(Long id, WebShop webShopChange) {
        final WebShop webShop = getWebShop(id);
        webShop.setName(webShopChange.getName());
        webShop.setCategory(webShopChange.getCategory());
        webShop.setMaterial(webShopChange.getMaterial());
        return webShop;
    }

    @Override
    public void deleteWebShop(Long id) {
       final WebShop webShop = getWebShop(id);
       DATA_BASE.remove(webShop);
    }

    private long getNextId(){
        return getLastId() + 1L;
    }

    private long getLastId(){
        return DATA_BASE.stream()
                .mapToLong(WebShop::getId)
                .max()
                .orElse(0);
    }
}
