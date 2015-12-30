package xyq.study.oschina.com.oschinastudy.utill;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by Administrator on 2015/12/11.
 */
public class SharedPreferencesUtil {
    protected static String fileName="oschina";
    private static SharedPreferencesUtil instance;
    private SharedPreferences sharedPreferences;

    public static SharedPreferencesUtil getInstance(Context context){
        if(instance==null){
            instance=new SharedPreferencesUtil(context);
        }
        return instance;
    }
    public static SharedPreferencesUtil getInstance(Context context,String fileNames){
        fileName=fileNames;
        if(instance==null){
            instance=new SharedPreferencesUtil(context);
        }
        return instance;
    }

    public SharedPreferencesUtil(Context context){
        sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }

    /**
     * 获取字符串信息
     * */
    public String get(String key,String defValue){
        return sharedPreferences.getString(key,defValue);
    }
    /**
     * 获取boolean信息
     * */
    public boolean get(String key,boolean defValue){
        return sharedPreferences.getBoolean(key, defValue);
    }
    /**
     * 获取float信息
     * */
    public float get(String key,float defValue){
        return sharedPreferences.getFloat(key, defValue);
    }
    /**
     * 获取int信息
     * */
    public int get(String key,int defValue){
        return sharedPreferences.getInt(key, defValue);
    }
    /**
     * 获取long信息
     * */
    public long get(String key,long defValue){
        return sharedPreferences.getLong(key, defValue);
    }
    /**
     * 获取String集合信息
     * */
    public Set get(String key,Set defValue){
        return sharedPreferences.getStringSet(key, defValue);
    }

    /**
     * 删除操作
     * */
    public void remove(String key){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
    /**
     * 添加String值
     * */
    public void  set(String key,String value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    /**
     * 添加String集合值
     * */
    public void  set(String key,Set value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }
    /**
     * 添加int集合值
     * */
    public void  set(String key,int value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    /**
     * 添加Boolean集合值
     * */
    public void  set(String key,Boolean value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    /**
     * 添加Float集合值
     * */
    public void  set(String key,Float value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    /**
     * 添加Long集合值
     * */
    public void  set(String key,Long value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }
}
