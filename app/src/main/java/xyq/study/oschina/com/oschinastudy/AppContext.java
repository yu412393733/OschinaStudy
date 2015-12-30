package xyq.study.oschina.com.oschinastudy;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.UUID;

import xyq.study.oschina.com.oschinastudy.api.ApiHttpClient;
import xyq.study.oschina.com.oschinastudy.base.BaseApplication;
import xyq.study.oschina.com.oschinastudy.utill.SharedPreferencesUtil;
import xyq.study.oschina.com.oschinastudy.utill.TLog;

/**
 * Created by Administrator on 2015/12/3.
 */
public class AppContext extends BaseApplication {

    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        init();
    }

    private void init() {
        //初始化网络连接
        AsyncHttpClient httpClient=new AsyncHttpClient();
        PersistentCookieStore cookieStore=new PersistentCookieStore(this);
        httpClient.setCookieStore(cookieStore);
        ApiHttpClient.setClient(httpClient);
        ApiHttpClient.setCookie(ApiHttpClient.getCookie(this));
        // Log控制器
        KJLoger.openDebutLog(true);
        TLog.DEBUG = BuildConfig.DEBUG;
        //BitmapConfig.CACHEPATH = ImageCasheSaveFolde; //这个就是我们图片缓存的路径了;
    }
    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }
    /**
     * 获取App唯一标识
     *
     * @return
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }
    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }
    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /***
     * 获取是否是黑夜模式
     */
    public static boolean getNightModeSwitch(){
        return SharedPreferencesUtil.getInstance(context()).get(AppConfig.KEY_NIGHT_MODE_SWITCH,false);
    }
    /***
     * 设置是否是黑夜模式
     */
    public static void setNightModeSwitch(Boolean value){
        SharedPreferencesUtil.getInstance(context()).set(AppConfig.KEY_NIGHT_MODE_SWITCH, value);
    }
}
