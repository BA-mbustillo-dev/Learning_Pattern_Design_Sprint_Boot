
package com.moyportillo.dao;

import com.moyportillo.entity.Product;
import com.moyportillo.patron_factory_method.DBFactory;
import com.moyportillo.patron_factory_method.IDBAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mbustillo
 */
public class ProductDAO {
    private IDBAdapter dbAdapter;
    
    public ProductDAO(){
        dbAdapter = DBFactory.getDefaultDBAdapter();
    }
    
    public List<Product> finAllProducts(){
        Connection connection = dbAdapter.getConnection();
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT  idProductos, productoName, productoPrice FROM Productos");
            ResultSet results = statement.executeQuery();
            while(results.next()){
                productList.add(new Product(results.getLong(1), results.getString(2), results.getDouble(3)));
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally{
            try{
                connection.close();
            }catch (Exception e){}
        }
    }
    
    public boolean saveProduct(Product product){
        Connection connection = dbAdapter.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Productos(idProductos, productosName, productosPrice) VALUES (?,?,?)");
            statement.setLong(1, product.getIdProductos());
            statement.setString(2, product.getProductosName());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {}
        }
    }
}
