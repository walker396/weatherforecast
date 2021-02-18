package com.complexica.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception tool
 * @author Li He
 * @date 2019-01-06
 */
public class ThrowableUtil {

    /**
     * Get stack information
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
