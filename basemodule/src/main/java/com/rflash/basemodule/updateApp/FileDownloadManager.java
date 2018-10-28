package com.rflash.basemodule.updateApp;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

/**
 * Created by yangfan on 2017/11/30.
 * 文件下载管理
 */

public class FileDownloadManager {

    private DownloadManager downloadManager ;
    private Context mContext;
    private static FileDownloadManager instance;

    private FileDownloadManager(Context context) {
        //获取下载器
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        this.mContext = context;

    }

    public static FileDownloadManager getInstance(Context context) {
        if (instance == null) {
            instance = new FileDownloadManager(context);
        }
        return instance;
    }

    /**
     * 开始下载
     * @param uri
     * @param title
     * @param description
     * @param appName
     * @return
     */
    public long startDownload(String uri, String title, String description,String appName){


        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
        //设置用于下载时的网络类型，默认任何网络都可以下载，提供的网络常量有：NETWORK_BLUETOOTH、NETWORK_MOBILE、NETWORK_WIFI。
        request.setAllowedNetworkTypes(DownloadManager.PAUSED_WAITING_FOR_NETWORK);
        //用于设置漫游状态下是否可以下载
        request.setAllowedOverRoaming(false);
        //用于设置下载时时候在状态栏显示通知信息
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //设置Notification的title信息
        request.setTitle(title);
        //设置Notification的message信息
        request.setDescription(description);
        //设置app下载路径
        //file:///storage/emulated/0/Android/data/your-package/files/Download/update.apk
        request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, appName+".apk");
        return downloadManager.enqueue(request);

    }

    /**
     * 获取文件保存路径
     * @param downloadId
     * @return
     */
    public String getDownloadPath(long downloadId) {

        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor cursor = downloadManager.query(query);
        if (cursor != null){
            try{

                if (cursor.moveToFirst()){
                    return  cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_URI));
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                cursor.close();
            }
        }
        return "";
    }

    public Uri getDownloadUri(long downloadId) {
        return downloadManager.getUriForDownloadedFile(downloadId);
    }

    public DownloadManager getDownloadManager() {
        return downloadManager;
    }

    /**
     * 获取下载状态
     * @param downloadId
     * @return
     */
    public int getDownloadStatus(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = downloadManager.query(query);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    return c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                }
            } finally {
                c.close();
            }
        }
        return -1;
    }

}
