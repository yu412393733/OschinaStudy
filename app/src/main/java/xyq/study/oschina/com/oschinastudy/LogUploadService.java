package xyq.study.oschina.com.oschinastudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.kymjs.kjframe.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;

import cz.msebera.android.httpclient.Header;
import xyq.study.oschina.com.oschinastudy.api.remote.OSChinaApi;
import xyq.study.oschina.com.oschinastudy.utill.StringUtil;
import xyq.study.oschina.com.oschinastudy.utill.TLog;

public class LogUploadService extends Service {
    public LogUploadService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.print("sdfsdfsdfsdfsdfsdf");
        TLog.log("LogUploadService");
        //获取log日志文件
        final File logFile= FileUtils.getSaveFile(AppConfig.LogFileFolde,AppConfig.LogFileName);
        String data=null;
        //读取log日志文件
        try {
            FileInputStream is=new FileInputStream(logFile);
            data= StringUtil.toConvertString(is);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!StringUtil.isEmpty(data)){
                //上传日志
                OSChinaApi.uploadLog(data, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        //上传成功，删除日志
                        logFile.delete();
                        //停止服务运行
                        LogUploadService.this.stopSelf();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        LogUploadService.this.stopSelf();
                    }
                });
            }else {
                this.stopSelf();
        }


        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }




}
