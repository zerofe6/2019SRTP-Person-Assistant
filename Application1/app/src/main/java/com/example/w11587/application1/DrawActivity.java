package com.example.w11587.application1;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DrawActivity extends AppCompatActivity{

    private AlertDialog dialog = null;
    private TextView tvDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        //showDialog();// 对话框提示


    }

    /**
     * 对话框提示
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showDialog() {

        // 构建dialog显示的view布局
        View view_dialog = getLayoutInflater().from(this).inflate(R.layout.view_dialog, null);

        if (dialog == null){
            // 创建AlertDialog对象
            dialog = new AlertDialog.Builder(this)
                    .create();
            dialog.show();
            // 设置点击可取消
            dialog.setCancelable(true);

            // 获取Window对象
            Window window = dialog.getWindow();



            //改变对话框的宽度
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = 500;// 调整该值可以设置对话框显示的宽度
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            // 设置显示视图内容
            window.setContentView(view_dialog);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        }else {
            dialog.show();
        }
    }



}
