package xyq.study.oschina.com.oschinastudy.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;


import xyq.study.oschina.com.oschinastudy.AppManager;
import xyq.study.oschina.com.oschinastudy.R;

/**
 * Created by Administrator on 2015/12/12.
 */
public class DoubleClickExitHelper {
    public static Activity activity;
    public static boolean isOnKeyBacking;
    public static Handler mHandler;
    public static Toast mToast;
    public DoubleClickExitHelper(Activity activity){
        this.activity=activity;
        mHandler=new Handler(Looper.getMainLooper());
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode!=KeyEvent.KEYCODE_BACK){
            //不为返回情况
            return false;
        }
        if(isOnKeyBacking){
            //进行退出
            mHandler.removeCallbacks(onBackTimeRunnable);

            AppManager.getInstance().Exit();
            return true;
        }else{
            isOnKeyBacking=true;
            if(mToast==null){
                mToast=Toast.makeText(activity, R.string.double_click_exit,Toast.LENGTH_LONG);
            }
            mToast.show();
            mHandler.postDelayed(onBackTimeRunnable,2000);
            return true;
        }
    }
    private Runnable onBackTimeRunnable =new Runnable(){

        @Override
        public void run() {
        isOnKeyBacking=false;
            if(mToast!=null){
                mToast.cancel();
            }
        }
    };

}
