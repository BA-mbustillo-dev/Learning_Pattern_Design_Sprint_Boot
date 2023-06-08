
package com.patron.patron.chain.responsability.method.domain.order;

import com.patron.patron.chain.responsability.method.domain.Contributor;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author mbustillo
 */
public abstract class AbstractOrder {
    private Calendar createDate;
    private Contributor contributor;
    private List<OrderItem> orderItems;
    
    public Calendar getCreateDate(){
        return createDate;
    }
    
    public double getTotal(){
        double total = 0d;
        for(OrderItem abstractOrderItem: orderItems){
            total += abstractOrderItem.getTotal();
        }
        return total;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    
    
}
