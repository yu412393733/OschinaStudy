package xyq.study.oschina.com.oschinastudy.utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/12/3.
 */
public class StringUtil {

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer sb=new StringBuffer();
        InputStreamReader isr=new InputStreamReader(is);
        BufferedReader br=new BufferedReader(isr);
        try{
            String line;
            line=br.readLine();
            while(line!=null){
                sb.append(line+"<br>");
                line=br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(br!=null){
                    br.close();
                    br=null;
                }
                if(isr!=null){
                    isr.close();
                    isr=null;
                }
                if(is!=null){
                    is.close();
                    is=null;
                }
            }catch (Exception e){

            }
        }
        return sb.toString();
    }

}
