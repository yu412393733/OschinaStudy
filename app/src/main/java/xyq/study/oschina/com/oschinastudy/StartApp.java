package xyq.study.oschina.com.oschinastudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import org.kymjs.kjframe.http.KJAsyncTask;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.PreferenceHelper;

import java.io.File;

import xyq.study.oschina.com.oschinastudy.ui.MainActivity;
import xyq.study.oschina.com.oschinastudy.utill.TDevice;

/**
 * 应用的启动页面
 *
 * @author xyr
 * @created 2015-12-3
 * */

public class StartApp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view =View.inflate(this, R.layout.activity_start_app, null);
        setContentView(view);
        //渐变展示图片
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(800);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            //动画结束时候执行
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int casheVersion= PreferenceHelper.readInt(this, "first_install", "first_install", -1);
        int currentVersion= TDevice.GetCurrentVersion();
        if(casheVersion<currentVersion){
            //当缓冲中的版本小于当前版本，将当前版本写入缓冲中
            PreferenceHelper.write(this,"first_install","first_install",currentVersion);
            //清空图片信息
            cleanImagerCashe();
        }
    }

    private void cleanImagerCashe() {
        //获取图片的目录
        final File folder= FileUtils.getSaveFolder(AppConfig.ImageCasheSaveFolde);
        KJAsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for(File file:folder.listFiles()){
                    file.delete();
                }
            }
        });
    }

    /**
     * 跳转到主页
     * */
    private void redirectTo() {
        //第一部分上传上次的日志
        Intent intent=new Intent(this,LogUploadService.class);
        startService(intent);
        //第二部分跳转到主页面
        Intent intent1=new Intent(this, MainActivity.class);
        startActivity(intent1);
        finish();

    }

}
