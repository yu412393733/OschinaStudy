package xyq.study.oschina.com.oschinastudy;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2015/12/12.
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;
    private AppManager(){}

    /**
     * 获取AppManager单一模式
     * */
    public static AppManager getInstance(){
        if(instance==null){
            instance=new AppManager();
        }
        return instance;
    }
    /**
     * 添加Activity
     * */
    public static void AddActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<>();
        }
        activityStack.add(activity);
    }
    /**
     * 获取当前Activity(即最后压人进栈的)
     * */
    public static Activity GetActivity(){
        return activityStack.lastElement();
    }

    /**
     * 删除所有Activity
     * */
    public static void finishAllActivity(){
        if (activityStack!=null){
            for (Activity a:activityStack) {
                a.finish();
            }
        }
        activityStack.clear();
    }
    /**
     * 结束指定Activity
     * */
    public static void finishActivity(Activity activity){
        if(activity!=null&&!activity.isFinishing()){
            activityStack.remove(activity);
            activity.finish();
        }
    }
    /**
     * 结束当前Activity(即最后压人进栈的)
     * */
    public static void finishActivity(){
        Activity activity=activityStack.lastElement();
        finishActivity(activity);
    }
    /**
     * 获取指定activity
     * */
    public static Activity GetActivity(Class cls){
        if(activityStack!=null){
            for (Activity a: activityStack) {
                if(a.getClass().equals(cls)){
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * 退出系统
     * */
    public static void Exit(){
        finishAllActivity();
        //杀死进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
