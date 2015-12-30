package xyq.study.oschina.com.oschinastudy.api.remote;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import xyq.study.oschina.com.oschinastudy.api.ApiHttpClient;

/**
 * Created by Administrator on 2015/12/3.
 */
public class OSChinaApi {

    /**
     * BUG上报
     *
     * @param data
     * @param handler
     */
    public static void uploadLog(String data, AsyncHttpResponseHandler handler) {
        uploadLog(data, "1", handler);
    }

    private static void uploadLog(String data, String report,
                                  AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("app", "1");
        params.put("report", report);
        params.put("msg", data);
        ApiHttpClient.post("action/api/user_report_to_admin", params, handler);
    }

}
