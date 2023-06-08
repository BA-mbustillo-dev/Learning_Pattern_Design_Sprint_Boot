/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.moyportillo.patron_factory_method;

import com.moyportillo.dao.ProductDAO;
import com.moyportillo.entity.Product;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mbustillo
 */
public class Patron_Factory_Method {

    public static void main(String[] args) throws SQLException{
        Product productA = new Product(1L, "Producto A", 100);
        Product productB = new Product(2L, "Producto B", 200);
        
        ProductDAO productDAO = new ProductDAO();
        
        productDAO.saveProduct(productA);
        productDAO.saveProduct(productB);
        
        List<Product> products = productDAO.finAllProducts();
        System.out.println("Product size ==> " + products.size());
        for(Product product: products){
            System.out.println(product);
        }
    }
}
