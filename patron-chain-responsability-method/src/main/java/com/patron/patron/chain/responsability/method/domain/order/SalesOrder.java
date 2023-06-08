
package com.patron.patron.chain.responsability.method.domain.order;

import java.util.Calendar;

/**
 *
 * @author mbustillo
 */
public class SalesOrder extends AbstractOrder{
    protected Calendar deliveryDate;

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
   
}
