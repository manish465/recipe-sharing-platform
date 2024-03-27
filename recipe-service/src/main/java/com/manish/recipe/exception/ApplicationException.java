package com.manish.recipe.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationException extends RuntimeException{
    public ApplicationException(String message){
        super(message);
    }
}
