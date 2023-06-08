
package com.patron.patron.chain.responsability.method.validator;

/**
 *
 * @author mbustillo
 */
public class ValidationException extends Exception{
    
    public ValidationException(String message) throws ValidationException{
        super(message);
    }
    
}
