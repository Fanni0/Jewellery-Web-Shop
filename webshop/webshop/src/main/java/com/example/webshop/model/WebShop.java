package com.example.webshop.model;

import java.util.Objects;

public class WebShop {
    private Long id;
    private String name;
    private Material material;
    private Category category;

    public WebShop() {
    }

    public WebShop(final Long id, final String name, final Material material, final Category category) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebShop)) return false;
        WebShop webShop = (WebShop) o;
        return Objects.equals(id, webShop.id) && Objects.equals(name, webShop.name) && material == webShop.material && category == webShop.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, material, category);
    }

    @Override
    public String toString() {
        return "WebShop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", material=" + material +
                ", category=" + category +
                '}';
    }

}
