package com.jun.roise.pdfviewer.pdf.util;

import android.content.Context;
import android.content.Intent;

import com.jun.roise.pdfviewer.pdf.FileFolderActivity;
import com.jun.roise.pdfviewer.pdf.PdfViewerActivity;

/**
 * 이동하는 액티비티 정의
 * Created by Roise on 2017. 7. 20..
 */

public class WarpUtil {

    // Pdf Viewer
    public static void warpPdfView(Context context) {
        Intent intent = new Intent(context, PdfViewerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    // 파일 탐색기
    public static void warpFileFolderView(Context context) {
        Intent intent = new Intent(context, FileFolderActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
