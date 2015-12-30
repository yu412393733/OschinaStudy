package xyq.study.oschina.com.oschinastudy.utill;

import xyq.study.oschina.com.oschinastudy.base.BaseApplication;

/**
 * Created by Administrator on 2015/12/3.
 */
public class TDevice {

    public TDevice() {
    }

    public static int GetCurrentVersion(){
        int versionCode=0;
        try {
            versionCode= BaseApplication.context()
                    .getPackageManager()
                    .getPackageInfo(BaseApplication.context().getPackageName(),0)
                    .versionCode;
        }catch (Exception e){
            versionCode=0;
        }
        return versionCode;
    }
}
