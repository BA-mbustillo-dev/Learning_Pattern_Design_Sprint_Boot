
package com.patron.patron.chain.responsability.method.validator;

import com.patron.patron.chain.responsability.method.domain.order.AbstractOrder;
import com.patron.patron.chain.responsability.method.domain.order.SalesOrder;

/**
 *
 * @author mbustillo
 */
public class SalesOrderValidator extends AbstractOrderValidator{

    @Override
    public void validate(AbstractOrder order) throws ValidationException {
        if(!(order instanceof SalesOrder)){
            throw new ValidationException("Se esperaba una orden de venta");
        }
        
        for(AbstractOrderValidator validator: validators){
            validator.validate(order);
        }
    }
    
}
