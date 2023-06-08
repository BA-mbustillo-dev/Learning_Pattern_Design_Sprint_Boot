
package com.patron.patron.chain.responsability.method.validator;

import com.patron.patron.chain.responsability.method.domain.Address;
import com.patron.patron.chain.responsability.method.domain.order.AbstractOrder;

/**
 *
 * @author mbustillo
 */
public class AddressValidator extends AbstractOrderValidator{

    @Override
    public void validate(AbstractOrder order) throws ValidationException {
        Address address = order.getContributor().getAddres();
        if(address.getAddress1()== null || address.getAddress1().length()==0){
            throw new ValidationException("la direccion 1 es obligatoria");
        }else if(address.getCP()==null || address.getCP().length()==0){
            throw new ValidationException("El CP dede ser de 4 digitos");
        }else if(address.getCountry()== null || address.getCountry().length()==0){
            throw new ValidationException("El pais es obligatorio");
        }
    }

    
}
