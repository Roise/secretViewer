package com.jun.roise.pdfviewer.pdf;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jun.roise.pdfviewer.pdf.model.PdfListAdapter;
import com.jun.roise.pdfviewer.pdf.util.ItemOffsetDecoration;
import com.jun.roise.pdfviewer.pdf.util.PdfFileUtil;

import java.io.File;
import java.util.ArrayList;


public class FileFolderActivity extends AppCompatActivity {
    private TextView textView;
    private ArrayList<String> items;
    private String rootPath = "";
    private String nextPath = "";
    private String prevPath = "";
    private String currentPath = "";
    private TextView messageView;

    private int GRID_VIEW_MODE = 0;
    private int LINEAR_VIEW_MODE = 1;
    private int CURRENT_VIEW_MODE = 0;

    //>> Book List
    private RecyclerView mPdfListView = null;
    private LinearLayoutManager mLayoutManager = null;
    private GridLayoutManager mGridLayoutManager = null;
    private PdfListAdapter mPdfListAdapter = null;
    //<<


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_folder);

        initView();

        ArrayList<String> pdfFileList = new ArrayList<>();
        File root = new File(PdfFileUtil.ENVIRONMENT_PATH);
        File[] filelist = root.listFiles();
        for(int i = 0; i < filelist.length; i++){
            if(filelist[i].toString().contains(".png")){
                pdfFileList.add(filelist[i].toString());
            }
        }

        for(String fileName : pdfFileList) {
            Log.e("JUN", "File Name = " + fileName);
        }

        // 루트 경로 가져오기
        rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        boolean result = initFolderData(rootPath);
//        if ( result == false )
//            return;



    }

//    private void setView() {
//        mPdfListAdapter = new mPdfListAdapter(this, mBookDataList);
//        mBookListView.setAdapter(mPdfListAdapter);
//        mPdfListAdapter.setOnItemClickListener(mBookListItemClickListener);
//    }
//
//    mPdfListAdapter.OnItemClickListener mBookListItemClickListener = new mPdfListAdapter.OnItemClickListener() {
//
//        @Override
//        public void onItemClick(View view, int position) {
////            Log.e(TAG,"onItemClick = " + position);
//        }
//    };

    public boolean initFolderData(String rootPath)    {
        // 파일 객체 생성
        File fileRoot = new File(rootPath);
        if(fileRoot.isDirectory() == false)        {
            Toast.makeText(this, "Not Directory" , Toast.LENGTH_SHORT).show();
            return false;
        }
        textView.setText(rootPath);

        // 파일 리스트 가져오기
        String[] fileList = fileRoot.list();
        if ( fileList == null )        {
            Toast.makeText(this, "Could not find List" , Toast.LENGTH_SHORT).show();
            return false;
        }

        // 아이템 리스트 전부 삭제
        items.clear();

        // 리스트의 첫 항목은 뒤로가기 위해 ".." 세팅
        items.add("..");
        for ( int i = 0; i < fileList.length; i++ )        {
            items.add(fileList[i]);
        }

        // 리스트 뷰에 적용
//        mPdfListAdapter.notifyDataSetChanged();
        return true;
    }

    public void nextPath(String str)    {
        prevPath = currentPath;

        // 현재 경로에서 / 와 다음 경로 붙이기
        nextPath = currentPath + "/" + str;
        File file = new File(nextPath);
        if ( file.isDirectory() == false )        {
            Toast.makeText(this, "Not Directory" , Toast.LENGTH_SHORT).show();
            return;
        }

        String[] fileList = file.list();
        items.clear();
        items.add("..");

        for ( int i = 0; i < fileList.length; i++ )        {
            items.add(fileList[i]);
        }

        textView.setText(nextPath);
//        mPdfListAdapter.notifyDataSetChanged();

    }

    public void prevPath(String str)    {
        nextPath = currentPath;
        prevPath = currentPath;


        // 마지막 / 의 위치 찾기
        int lastSlashPosition = prevPath.lastIndexOf("/");

        // 처음부터 마지막 / 까지의 문자열 가져오기
        prevPath = prevPath.substring(0, lastSlashPosition);
        File file = new File(prevPath);

        if ( file.isDirectory() == false)        {
            Toast.makeText(this, "Not Directory" , Toast.LENGTH_SHORT).show();
            return;
        }

        String[] fileList = file.list();
        items.clear();
        items.add("..");

        for( int i = 0; i < fileList.length; i++ )        {
            items.add(fileList[i]);
        }

        textView.setText(prevPath);
//        mPdfListAdapter.notifyDataSetChanged();
    }



    private void initView() {
        mPdfListView = (RecyclerView) findViewById(R.id.pdf_list_view);
        mPdfListView.setHasFixedSize(true);

        if(CURRENT_VIEW_MODE == LINEAR_VIEW_MODE) {
            //>> Linear Mode
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mPdfListView.setLayoutManager(mLayoutManager);
            //<<
        } else if(CURRENT_VIEW_MODE == GRID_VIEW_MODE) {
            //>> Grid Mode
            mGridLayoutManager = new GridLayoutManager(this, 3);
            mPdfListView.setLayoutManager(mGridLayoutManager);

            ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_side_offset, R.dimen.item_top_offset);
            mPdfListView.addItemDecoration(itemDecoration);
            //<<
        }
    }
}
