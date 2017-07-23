package com.jun.roise.pdfviewer.pdf.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Roise on 2017. 7. 17..
 */

public class PdfFileUtil {
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    private static final String SAVE_FOLDER = "/pdfDownload";

    //다운로드 경로를 지정
    public static String SAVE_PDF_PATH = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;
    public static String ENVIRONMENT_PATH = Environment.getExternalStorageDirectory().toString();

    public static String getPdfFilePath(String fileName) {
        String filePath = SAVE_PDF_PATH + "/" + fileName + ".pdf";
        return filePath;
    }

    public static String deletePdfFile(String fileName) {
        String filePath = null;
        try {
            filePath = SAVE_PDF_PATH + "/" + fileName + ".pdf";
            File pdfFile = new File(filePath);
            if(pdfFile.exists()) {
                pdfFile.delete();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return filePath;
    }
}
