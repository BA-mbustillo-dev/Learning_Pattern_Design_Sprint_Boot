
package com.patron.patron.chain.responsability.method.validator;

import com.patron.patron.chain.responsability.method.domain.order.AbstractOrder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mbustillo
 */
public abstract class AbstractOrderValidator {
    protected List<AbstractOrderValidator> validators = new ArrayList<>();
    
    public abstract void validate(AbstractOrder order) throws ValidationException;
    
    public void addValidator(AbstractOrderValidator validator){
        validators.add(validator);
    }
}
