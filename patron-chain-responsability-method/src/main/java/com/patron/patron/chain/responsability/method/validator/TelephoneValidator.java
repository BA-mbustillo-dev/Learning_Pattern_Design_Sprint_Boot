
package com.patron.patron.chain.responsability.method.validator;

import com.patron.patron.chain.responsability.method.domain.Telephone;
import com.patron.patron.chain.responsability.method.domain.order.AbstractOrder;

/**
 *
 * @author mbustillo
 */
public class TelephoneValidator extends AbstractOrderValidator{

    @Override
    public void validate(AbstractOrder order) throws ValidationException {
        Telephone tel = order.getContributor().getTelephone();
        if(null == tel){
            throw new ValidationException("El telefono del contribuyente es requerido");
        }else if(tel.getNumber().length()!= 7){
            throw new ValidationException("Numero de telefono invalido");
        }else if(tel.getLada().length()!=3){
            throw new ValidationException("Lada Invalida");
        }
    }
    
}
