package com.example.w11587.application1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.w11587.application1.BottomFragment.FriendFragment;
import com.example.w11587.application1.BottomFragment.MsgFragment;
import com.example.w11587.application1.BottomFragment.MyFragment;
import com.example.w11587.application1.SetMemo.MemoActivity;

public class MainActivity extends AppCompatActivity {

    private MsgFragment fragment1;
    private FriendFragment fragment2;
    private MyFragment fragment3;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    //private FragmentManager manager;
    //private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //全屏显示，显示时间和电量
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        //解决打开输入法时导航栏上移的问题
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开启事务管理，主要处理Fragment
        //manager = getFragmentManager();
        // transaction = manager.beginTransaction();

        //设置切换Fragment
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        RadioGroupList radigGroupList = new RadioGroupList();
        radioGroup.setOnCheckedChangeListener(radigGroupList);

        //设置默认按钮为选中状态
        radioButton =(RadioButton) findViewById(R.id.btn_0);
        radioButton.setChecked(true);


        //开始处理Fragment
        fragment1 = new MsgFragment();
        fragment2 = new FriendFragment();
        fragment3 = new MyFragment();


        findViewById(R.id.fragment_main).setVisibility(View.VISIBLE);
        findViewById(R.id.fragment_msg).setVisibility(View.INVISIBLE);
        findViewById(R.id.fragment_friend).setVisibility(View.INVISIBLE);
        findViewById(R.id.fragment_my).setVisibility(View.INVISIBLE);

        Button btn1 = (Button) findViewById(R.id.btn_board_share);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(i);
            }
        });

        Button btn2 = (Button) findViewById(R.id.btn_memo);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MemoActivity.class);
                startActivity(i);
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.min, menu);
        //return true;
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.settings:
                //onAdd();
                break;
            default:
        }
        return true;
    }*/

    public class RadioGroupList implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            if(group.getId() == R.id.radiogroup)
            {
                switch (checkedId)
                {
                    case R.id.btn_0:
                        findViewById(R.id.fragment_main).setVisibility(View.VISIBLE);
                        findViewById(R.id.fragment_msg).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_friend).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_my).setVisibility(View.INVISIBLE);
                        Log.d("主页", "提示");
                        break;
                    case R.id.btn_1:
                        findViewById(R.id.fragment_main).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_msg).setVisibility(View.VISIBLE );
                        findViewById(R.id.fragment_friend).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_my).setVisibility(View.INVISIBLE);
                        Log.d("消息", "提示");
                        break;
                    case R.id.btn_2:
                        findViewById(R.id.fragment_main).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_msg).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_friend).setVisibility(View.VISIBLE);
                        findViewById(R.id.fragment_my).setVisibility(View.INVISIBLE );
                        Log.d("好友", "提示");
                        break;
                    case R.id.btn_3:
                        findViewById(R.id.fragment_main).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_msg).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_friend).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_my).setVisibility(View.VISIBLE );

                        Log.d("我的", "提示");
                        break;
                    default :
                        break;
                }
            }
        }
    }
}
