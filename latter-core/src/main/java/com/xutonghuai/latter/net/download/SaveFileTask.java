package com.xutonghuai.latter.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.xutonghuai.latter.app.Latter;
import com.xutonghuai.latter.net.callback.IRequest;
import com.xutonghuai.latter.net.callback.ISuccess;
import com.xutonghuai.latter.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by xutonghuai on 2018-03-20.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File>{

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        REQUEST = request;
        SUCCESS = success;
    }


    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        ResponseBody responseBody = (ResponseBody) params[2];
        InputStream is = responseBody.byteStream();
        String name = (String) params[3];

        if(downloadDir==null||downloadDir.equals("")){
            downloadDir = "down_loads";
        }
        if(extension ==null||extension.equals("")){
            extension = "";
        }
        if(name == null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else{
            return FileUtil.writeToDisk(is,downloadDir,name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        //完成
        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getParent());
        }
        //请求结束
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {

        if(FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latter.getApplicationContext().startActivity(install);
        }
    }

}
