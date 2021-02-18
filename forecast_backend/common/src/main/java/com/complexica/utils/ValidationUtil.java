package com.complexica.utils;

import com.complexica.exception.BadRequestException;

import java.util.Optional;

/**
 * Verification tool
 * @author Li He
 * @date 2018-11-23
 */
public class ValidationUtil{

    /**
     * Verify empty
     * @param optional
     */
    public static void isNull(Optional optional, String entity,String parameter, Object value){
        if(!optional.isPresent()){
            String msg = entity
                    + "Does not exist"
                    +"{ "+ parameter +":"+ value.toString() +" }";
            throw new BadRequestException(msg);
        }
    }

    /**
     * Verify whether it is a mail
     * @param string
     * @return
     */
    public static boolean isEmail(String string) {
        if (string == null){
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[ a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return string.matches(regEx1);
    }
}
