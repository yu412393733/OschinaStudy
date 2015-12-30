package xyq.study.oschina.com.oschinastudy.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyq.study.oschina.com.oschinastudy.AppContext;
import xyq.study.oschina.com.oschinastudy.AppManager;
import xyq.study.oschina.com.oschinastudy.R;
import xyq.study.oschina.com.oschinastudy.fragment.MainFragment;

public class MainActivity extends ActionBarActivity {

    @Bind(R.id.fl_main_context)
    FrameLayout flMainContext;
    @Bind(R.id.navigation_drawer)
    FrameLayout navigationDrawer;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private DoubleClickExitHelper doubleClickExitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppContext.getInstance().getNightModeSwitch()){
            //是黑夜模式
            this.setTheme(R.style.AppBaseTheme_Night);
        }else{
            this.setTheme(R.style.AppBaseTheme_Light);
        }
        super.onCreate(savedInstanceState);
        AppManager.getInstance().AddActivity(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        //双击退出
        doubleClickExitHelper = new DoubleClickExitHelper(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_main_context, new MainFragment(), "FMCONTEMT");
        fragmentTransaction.add(R.id.navigation_drawer,new NavigationDrawerFragment(),"NAVIGATION");
        fragmentTransaction.commit();



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            doubleClickExitHelper.onKeyDown(keyCode,event);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
