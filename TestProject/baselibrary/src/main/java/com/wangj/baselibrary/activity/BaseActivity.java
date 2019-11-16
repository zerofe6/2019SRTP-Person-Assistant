package com.wangj.baselibrary.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wangj.baselibrary.http.Constant;
import com.wangj.baselibrary.http.HttpPostTask;
import com.wangj.baselibrary.http.bean.CommonRequest;
import com.wangj.baselibrary.http.interf.ResponseHandler;
import com.wangj.baselibrary.util.DialogUtil;
import com.wangj.baselibrary.util.LoadingDialogUtil;
import com.wangj.baselibrary.util.LogUtil;

/**
 * 基类
 * <p/>
 * Created by WangJie on 2017-03-14.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void sendHttpPostRequest(String url, CommonRequest request, ResponseHandler responseHandler, boolean showLoadingDialog) {
        new HttpPostTask(request, mHandler, responseHandler).execute(url);
        if (showLoadingDialog) {
            LoadingDialogUtil.showLoadingDialog(BaseActivity.this);
        }
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == Constant.HANDLER_HTTP_SEND_FAIL) {
                LogUtil.logErr(msg.obj.toString());

                LoadingDialogUtil.cancelLoading();
                DialogUtil.showHintDialog(BaseActivity.this, "请求发送失败，请重试", true);
            } else if (msg.what == Constant.HANDLER_HTTP_RECEIVE_FAIL) {
                LogUtil.logErr(msg.obj.toString());

                LoadingDialogUtil.cancelLoading();
                DialogUtil.showHintDialog(BaseActivity.this, "请求接受失败，请重试", true);
            }
        }
    };

    /*@Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Base Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.wangj.baselibrary.activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Base Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.wangj.baselibrary.activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }*/
}
