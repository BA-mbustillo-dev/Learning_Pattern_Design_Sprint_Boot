
package com.patron.patron.chain.responsability.method.validator;

import com.patron.patron.chain.responsability.method.domain.Contributor;
import com.patron.patron.chain.responsability.method.domain.Status;
import com.patron.patron.chain.responsability.method.domain.order.AbstractOrder;

/**
 *
 * @author mbustillo
 */
public class ContributorValidator extends AbstractOrderValidator{

    @Override
    public void validate(AbstractOrder order) throws ValidationException {
        for(AbstractOrderValidator validator: validators){
            validator.validate(order);
        }
        Contributor contributor = order.getContributor();
        if(Status.ACTIVO != contributor.getStatus()){
            throw new ValidationException("El contribuyente no esta activo");
        }
    }
}
