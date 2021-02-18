package com.complexica.utils;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * File tool class, extend the hutool toolkit
 * @author Li He
 * @date 2018-12-27
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    /**
     * Define the calculation constant for GB
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * Define the calculation constant of MB
     */
    private static final int MB = 1024 * 1024;
    /**
     * Define the calculation constant of KB
     */
    private static final int KB = 1024;

    /**
     * Format decimal
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * MultipartFile to File
     * @param multipartFile
     * @return
     */
    public static File toFile(MultipartFile multipartFile){
        // Get the file name
        String fileName = multipartFile.getOriginalFilename();
        // Get the file suffix
        String prefix="."+getExtensionName(fileName);
        File file = null;
        try {
            // Use uuid as the file name to prevent duplication of generated temporary files
            file = File.createTempFile(IdUtil.simpleUUID(), prefix);
            // MultipartFile to File
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Delete
     * @param files
     */
    public static void deleteFile(File... files) {
        for (File file: files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * Get file extension
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length()> 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot <(filename.length()-1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java file operation Get the file name without extension
     * @param filename
     * @return
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length()> 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot <(filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * File size conversion
     * @param size
     * @return
     */
    public static String getSize(int size){
        String resultSize = "";
        if (size / GB >= 1) {
            //If the current Byte value is greater than or equal to 1GB
            resultSize = DF.format(size / (float) GB) + "GB ";
        } else if (size / MB >= 1) {
            //If the current Byte value is greater than or equal to 1MB
            resultSize = DF.format(size / (float) MB) + "MB ";
        } else if (size / KB >= 1) {
            //If the current Byte value is greater than or equal to 1KB
            resultSize = DF.format(size / (float) KB) + "KB ";
        } else {
            resultSize = size + "B ";
        }
        return resultSize;
    }
}
