
package com.patron.patron.chain.responsability.method.validator;

import com.patron.patron.chain.responsability.method.domain.Customer;
import com.patron.patron.chain.responsability.method.domain.order.AbstractOrder;

/**
 *
 * @author mbustillo
 */
public class CustomerValidator extends AbstractOrderValidator{

    @Override
    public void validate(AbstractOrder order) throws ValidationException {
        for(AbstractOrderValidator validator: validators){
            validator.validate(order);
        }
        
        if(!(order.getContributor() instanceof Customer)){
            throw new ValidationException("El contribuyente no es un cliente");
        }
    }
    
}
