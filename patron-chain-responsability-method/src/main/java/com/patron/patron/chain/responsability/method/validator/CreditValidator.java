
package com.patron.patron.chain.responsability.method.validator;

import com.patron.patron.chain.responsability.method.domain.CreditData;
import com.patron.patron.chain.responsability.method.domain.order.AbstractOrder;

/**
 *
 * @author mbustillo
 */
public class CreditValidator extends AbstractOrderValidator{

    @Override
    public void validate(AbstractOrder order) throws ValidationException {
        double total = order.getTotal();
        CreditData creditData = order.getContributor().getCreditData();
        double newBalance = creditData.getBalance() + total;
        if(newBalance > creditData.getCreditLimit()){
            throw new ValidationException("el monto de la orden excede el limite de credito dispone");
        }
    }
    
}
