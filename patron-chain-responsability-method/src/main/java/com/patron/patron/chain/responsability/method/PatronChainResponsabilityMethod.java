
package com.patron.patron.chain.responsability.method;

import com.patron.patron.chain.responsability.method.domain.Address;
import com.patron.patron.chain.responsability.method.domain.CreditData;
import com.patron.patron.chain.responsability.method.domain.Customer;
import com.patron.patron.chain.responsability.method.domain.Product;
import com.patron.patron.chain.responsability.method.domain.Status;
import com.patron.patron.chain.responsability.method.domain.Telephone;
import com.patron.patron.chain.responsability.method.domain.order.OrderItem;
import com.patron.patron.chain.responsability.method.domain.order.SalesOrder;
import com.patron.patron.chain.responsability.method.validator.AbstractOrderValidator;
import com.patron.patron.chain.responsability.method.validator.OrderValidatorBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author mbustillo
 */
public class PatronChainResponsabilityMethod {

    public static void main(String[] args) {
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setDeliveryDate(Calendar.getInstance());
        Customer customer = new Customer();
        customer.setName(null);
        customer.setRfc("XX000000000X0");
        customer.setStatus(Status.ACTIVO);
        
        Telephone phone = new Telephone();
        phone.setExt("123");
        phone.setLada("9991");
        phone.setNumber("1234567");
        customer.setTelephone(phone);
        
        Address address = new Address();
        address.setAddress1("Col. Carrizal");
        address.setAddress2("secto 2");
        address.setCP("1234");
        address.setCountry("Honduras");
        customer.setAddres(address);
        
        CreditData creditData = new CreditData();
        creditData.setBalance(100);
        creditData.setCreditLimit(13000);
        customer.setCreditData(creditData);
        
        salesOrder.setContributor(customer);
        
        List<OrderItem> orderItems = new ArrayList<>();
        for(int c = 0;c < 10; c++){
            OrderItem item = new OrderItem();
            item.setPrice((c+1)*30);
            Product product = new Product();
            product.setListPrice((c+1)*32);
            product.setName("Product "+ (c+1));
            item.setProduct(product);
            item.setQuantity(c+1);
            orderItems.add(item);
        }
        
        salesOrder.setOrderItems(orderItems);
        System.out.println("Total Orden > "+salesOrder.getTotal());
        try {
            AbstractOrderValidator validator = OrderValidatorBuilder.buildSalesOrderValidator();
            System.out.println("Validacion Exitosa");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
        
    }
}
