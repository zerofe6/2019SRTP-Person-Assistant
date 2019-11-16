package com.wangj.testproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wangj.baselibrary.activity.BaseActivity;
import com.wangj.baselibrary.http.bean.CommonRequest;
import com.wangj.baselibrary.http.bean.CommonResponse;
import com.wangj.baselibrary.http.interf.ResponseHandler;
import com.wangj.baselibrary.util.DialogUtil;
import com.wangj.baselibrary.util.LoadingDialogUtil;

public class MainActivity extends BaseActivity {

    //private String URL_LOGIN = "http://26826h283j.zicp.vip:24369/MyWorld_Service/LoginServlet";
    private String URL_LOGIN = "http://277673f6x5.wicp.vip:32311/personassint/login";
    private String URL_REGISTER = "http://277673f6x5.wicp.vip:32311/personassint/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = (EditText) findViewById(R.id.et_name);
        final EditText etPassword = (EditText) findViewById(R.id.et_password);

        final EditText reName = (EditText) findViewById(R.id.re_name);
        final EditText rePassword = (EditText) findViewById(R.id.re_password);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etName.getText().toString(), etPassword.getText().toString());
            }
        });

        Button btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(reName.getText().toString(), rePassword.getText().toString(),"newuser");
                //注册应该为三个参数,这里用户名为中文时服务端抛异常 解码出错 待解决
            }
        });

    }

    private void login(String name, String password) {
        final TextView tvRequest = (TextView) findViewById(R.id.tv_request);
        final TextView tvResponse = (TextView) findViewById(R.id.tv_response);

        final CommonRequest request = new CommonRequest();
        request.addRequestParam("name", name);
        request.addRequestParam("password", password);
        sendHttpPostRequest(URL_LOGIN, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                LoadingDialogUtil.cancelLoading();
                tvRequest.setText(request.getJsonStr());
                tvResponse.setText(response.getResCode() + "\n" + response.getResMsg());
                DialogUtil.showHintDialog(MainActivity.this, "登陆成功！", false);
            }

            @Override
            public void fail(String failCode, String failMsg) {
                tvRequest.setText(request.getJsonStr());
                tvResponse.setText(failCode + "\n" + failMsg);
                DialogUtil.showHintDialog(MainActivity.this, true, "登陆失败", failCode + " : " + failMsg, "关闭对话框", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoadingDialogUtil.cancelLoading();
                        DialogUtil.dismissDialog();
                    }
                });
            }
        }, true);

    }

    private void register(String name, String password,String username) {
        final TextView tvRequest = (TextView) findViewById(R.id.tv_request);
        final TextView tvResponse = (TextView) findViewById(R.id.tv_response);

        final CommonRequest request = new CommonRequest();
        request.addRequestParam("account", name);
        request.addRequestParam("password", password);
        request.addRequestParam("username",username);
        sendHttpPostRequest(URL_REGISTER, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                LoadingDialogUtil.cancelLoading();
                tvRequest.setText(request.getJsonStr());
                tvResponse.setText(response.getResCode() + "\n" + response.getResMsg());
                DialogUtil.showHintDialog(MainActivity.this, "注册成功！", false);
            }

            @Override
            public void fail(String failCode, String failMsg) {
                tvRequest.setText(request.getJsonStr());
                tvResponse.setText(failCode + "\n" + failMsg);
                DialogUtil.showHintDialog(MainActivity.this, true, "注册失败", failCode + " : " + failMsg, "关闭对话框", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoadingDialogUtil.cancelLoading();
                        DialogUtil.dismissDialog();
                    }
                });
            }
        }, true);

    }
}
