package xyq.study.oschina.com.oschinastudy.api;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Locale;

import cz.msebera.android.httpclient.client.params.ClientPNames;
import xyq.study.oschina.com.oschinastudy.AppContext;
import xyq.study.oschina.com.oschinastudy.utill.TLog;

/**
 * Created by Administrator on 2015/12/3.
 *
 * 对AsyncHttpClient进行封装
 */
public class ApiHttpClient {
    public static AsyncHttpClient client;
    public final static String HOST = "www.oschina.net";
    private static String API_URL = "http://www.oschina.net/%s";
    private static String appCookie;
    public ApiHttpClient() {
    }

    public static AsyncHttpClient getClient() {
        return client;
    }

    public static void setClient(AsyncHttpClient as) {
        client = as;
        client.addHeader("Accept-Language", Locale.getDefault().toString());
        client.addHeader("Host", HOST);
        client.addHeader("Connection", "Keep-Alive");
        client.getHttpClient().getParams()
                .setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        setUserAgent(ApiClientHelper.getUserAgent(AppContext.getInstance()));
    }

    public static void setUserAgent(String userAgent) {
        client.setUserAgent(userAgent);
    }
    public static void setCookie(String cookie) {
        client.addHeader("Cookie", cookie);
    }
    public static String getCookie(AppContext appContext) {
        if (appCookie == null || appCookie == "") {
            appCookie = appContext.getProperty("cookie");
        }
        return appCookie;
    }


    public static void post(String partUrl, RequestParams params,
                            AsyncHttpResponseHandler handler) {
        client.post(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("POST ").append(partUrl).append("&")
                .append(params).toString());
    }

       public static void log(String log) {
        Log.d("BaseApi", log);
        TLog.log("Test", log);
    }

    public static String getAbsoluteApiUrl(String partUrl) {
        String url = partUrl;
        if (!partUrl.startsWith("http:") && !partUrl.startsWith("https:")) {
            url = String.format(API_URL, partUrl);
        }
        Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }

}
