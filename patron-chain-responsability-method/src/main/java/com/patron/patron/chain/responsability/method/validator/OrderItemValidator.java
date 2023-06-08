
package com.patron.patron.chain.responsability.method.validator;

import com.patron.patron.chain.responsability.method.domain.Product;
import com.patron.patron.chain.responsability.method.domain.order.AbstractOrder;
import com.patron.patron.chain.responsability.method.domain.order.OrderItem;
import java.util.List;

/**
 *
 * @author mbustillo
 */
public class OrderItemValidator extends AbstractOrderValidator{

    @Override
    public void validate(AbstractOrder order) throws ValidationException {
        List<OrderItem> orderItems = order.getOrderItems();
        for(OrderItem item: orderItems){
            Product product = item.getProduct();
            if(item.getQuantity() <= 0){
                throw new ValidationException("El producto "+product.getName()+" no tiene cantidad");
            }
            double listPrice = item.getProduct().getListPrice();
            double price = item.getPrice();
            double priceLimit = listPrice - (listPrice*0.20d);
            if(price < priceLimit){
                throw new ValidationException("");
            }
        }
    }
    
}
