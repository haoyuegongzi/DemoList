package com.haoyue.demo_list.download;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chen1 on 2017/8/1.
 */

public class DownLoadTask extends AsyncTask<String, Integer, Integer> {
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSE = 2;
    public static final int TYPE_CANCEL = 3;
    private boolean isCancel = false;
    private boolean isPause = false;
    private int lastProgress = 0;

    private DownLoadListener mDownLoadListener;

    public DownLoadTask (DownLoadListener mListene){
        mDownLoadListener = mListene;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        InputStream is = null;
        RandomAccessFile saveFile = null;
        File file = null;
        try {
            long downLoadLength = 0;
            String downLoadUrl = strings[0];
            String fileName = downLoadUrl.substring(downLoadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if(file.exists()){
                downLoadLength = file.length();
            }
            long contentLength = getContentLength(downLoadUrl);
            if(contentLength == 0){
                return TYPE_FAILED;
            }else if(contentLength == downLoadLength){
                //已经下载的字节数和文件总字节数相等，说明下载成功
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().addHeader("RANGE", "bytes=" + downLoadLength + "-")
                                         .url(downLoadUrl).build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                saveFile = new RandomAccessFile(file, "rw");
                saveFile.seek(downLoadLength);//跳过已下载的字节数
                byte[] by = new byte[1024];
                int total = 0;
                int length = 0;
                while ((length = is.read(by)) != -1){
                    if (isCancel){
                        return TYPE_CANCEL;
                    }else if( isPause){
                        return TYPE_PAUSE;
                    }else {
                        total +=length;
                        saveFile.write(by, 0, length);
                        int progress = (int)((total + downLoadLength) *100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
                if(saveFile != null){
                    saveFile.close();
                }
                if(isCancel && file != null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        if (progress > lastProgress){
            mDownLoadListener.onProcess(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer){
            case TYPE_SUCCESS:
                mDownLoadListener.onSuccess();
                break;
            case TYPE_FAILED:
                mDownLoadListener.onFailed();
                break;
            case TYPE_PAUSE:
                mDownLoadListener.onPause();
                break;
            case TYPE_CANCEL:
                mDownLoadListener.onCancel();
                break;
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPause = true;
    }

    public void cancelDownload(){
        isCancel = true;
    }

    private long getContentLength(String downLoadUrl) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downLoadUrl).build();
        Response response = client.newCall(request).execute();
        if(response != null && response.isSuccessful()){
            long contentLength = response.body().contentLength();
            return contentLength;
        }
        return 0;
    }
}
