package com.example.webshop.controller;

import com.example.webshop.model.WebShop;
import com.example.webshop.model.exception.NotFoundException;
import com.example.webshop.service.WebShopService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web-shop")
public class WebShopController {

    private final WebShopService webShopService;

    public WebShopController(WebShopService webShopService) {
        this.webShopService = webShopService;
    }

    @GetMapping
    public String getAllWebShop(final Model model){
        final List<WebShop> webShops = webShopService.getAllWebShops();
        model.addAttribute("webShops",webShops);
        return"webshops/list";
    }

    @GetMapping("/{id}")
    public String getWebShop(final Model model, final @PathVariable Long id){
        final WebShop webShop = webShopService.getWebShop(id);
        model.addAttribute("webShop",webShop);
        return"webshops/edit";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createWebShop(final Model model,
                                final @RequestParam(value = "id", required = false) Long id,
                                final WebShop webShopChange) {
        final WebShop webShop = webShopService.updateWebShop(id, webShopChange);
        model.addAttribute("webShop",webShop);
        return"webshops/edit";
    }

    @GetMapping("/create")
    public String createWebShopForm(final Model model){
        return"webshops/create";
    }

    @PostMapping("/create")
    public String createWebShop(final Model model, final WebShop webShop) {
        final WebShop savedWebShop = webShopService.createWebShop(webShop);
        model.addAttribute("webShop",savedWebShop);
        return "webshops/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteWebShop(final Model model, final @PathVariable("id") Long id){
        try {
            webShopService.deleteWebShop(id);
        } catch (NotFoundException e) {

        }
        final List<WebShop> webShops = webShopService.getAllWebShops();
        model.addAttribute("webShops",webShops);
        return"webshops/list";
    }
}
