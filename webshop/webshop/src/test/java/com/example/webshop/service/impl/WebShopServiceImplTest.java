package com.example.webshop.service.impl;

import com.example.webshop.model.Category;
import com.example.webshop.model.Material;
import com.example.webshop.model.WebShop;
import com.example.webshop.model.exception.NotFoundException;
import com.example.webshop.service.WebShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class WebShopServiceImplTest {

    private static final WebShop EZÜST_FÜLBEVALÓ_ÉKSZER = new WebShop(1L,"Ezüst fülbevaló", Material.SILVER, Category.EARRING);
    private static final WebShop ARANY_GYŰRŰ_GYÉMÁNTTAL_ÉKSZER = new WebShop(2L, "Arany gyűrű gyémánttal",Material.GOLD,Category.RING);
    private static final WebShop PLATINUM_SMARAGDKÖVES_FÜLBEVALÓ = new WebShop(3L,"Platinum smaragdköves fülbevaló",Material.PLATINUM,Category.EARRING);
    private static final List<WebShop> WEB_SHOPS = List.of(
            EZÜST_FÜLBEVALÓ_ÉKSZER,
            ARANY_GYŰRŰ_GYÉMÁNTTAL_ÉKSZER,
            PLATINUM_SMARAGDKÖVES_FÜLBEVALÓ
    );
    public static final Long UNKNOWN_WEBSHOP_ID = -1L;
    public static final String EZÜST_NYAKLÁNC_ÉKSZER_NAME = "Ezüst nyaklánc";

    private WebShopService underTest;

    @BeforeEach
    void setUp(){
        underTest = new WebShopServiceImpl(WEB_SHOPS);
    }

    @Test
    void getAllWebShopsShouldReturnAllWebShops() {
        final List<WebShop> actual= underTest.getAllWebShops();
        assertThat(actual).isEqualTo(WEB_SHOPS);
    }

    @Test
    void getWebShopShouldReturnWebShopWhenGivenId() {
        final WebShop actual = underTest.getWebShop(EZÜST_FÜLBEVALÓ_ÉKSZER.getId());
        assertThat(actual).isEqualTo(EZÜST_FÜLBEVALÓ_ÉKSZER);
    }

    @Test
    void getWebShopShouldThrow() {
        assertThrows(NotFoundException.class, () -> underTest.getWebShop(UNKNOWN_WEBSHOP_ID));
    }

    @Test
    void createWebShopShouldReturnWebShop() {
        final WebShop ezustekszer = new WebShop(null,EZÜST_NYAKLÁNC_ÉKSZER_NAME,Material.SILVER,Category.NECKLACE);
        final WebShop expectedezustekszer = new WebShop(4L,EZÜST_NYAKLÁNC_ÉKSZER_NAME,Material.SILVER,Category.NECKLACE);
        final WebShop actual = underTest.createWebShop(ezustekszer);
        assertThat(actual).isEqualTo(expectedezustekszer);
    }

    @Test
    void updateWebShopShouldReturnUpdatedWebShopExistingWebShop() {
        final WebShop ezustekszer = new WebShop(null,EZÜST_NYAKLÁNC_ÉKSZER_NAME,Material.SILVER,Category.NECKLACE);
        final WebShop expectedwebshop = new WebShop(EZÜST_FÜLBEVALÓ_ÉKSZER.getId(),EZÜST_NYAKLÁNC_ÉKSZER_NAME,Material.SILVER,Category.NECKLACE);
        final WebShop actual = underTest.updateWebShop(EZÜST_FÜLBEVALÓ_ÉKSZER.getId(),ezustekszer);
        assertThat(actual).isEqualTo(expectedwebshop);

    }

    @Test
    void updateWebShopShouldReturnUpdatedWebShopNotExistingWebShop() {
        assertThrows(NotFoundException.class, () -> underTest.getWebShop(UNKNOWN_WEBSHOP_ID));
    }

    @Test
    void deleteWebShopShouldDeleteWebShop() {
        final List<WebShop> expectedwebshops = List.of(ARANY_GYŰRŰ_GYÉMÁNTTAL_ÉKSZER,PLATINUM_SMARAGDKÖVES_FÜLBEVALÓ);
        underTest.deleteWebShop(EZÜST_FÜLBEVALÓ_ÉKSZER.getId());
        final List<WebShop> actual = underTest.getAllWebShops();
        assertThat(actual).isEqualTo(expectedwebshops);
    }
}