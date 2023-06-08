
package com.moyportillo.entity;

/**
 *
 * @author mbustillo
 */
public class Product {
    private Long idProductos;
    private String productosName;
    private double price;

    public Product(Long idProductos, String productosName, double price) {
        this.idProductos = idProductos;
        this.productosName = productosName;
        this.price = price;
    }

    public Long getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Long idProductos) {
        this.idProductos = idProductos;
    }

    public String getProductosName() {
        return productosName;
    }

    public void setProductosName(String productosName) {
        this.productosName = productosName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
}
