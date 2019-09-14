package com.example.w11587.application1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


        //自定义toolbar
        Toolbar toolbar_main=(Toolbar) findViewById(R.id.toolbar_main);
        toolbar_main.setTitle("个人助手");
        setSupportActionBar(toolbar_main);

        Toolbar toolbar_friend=(Toolbar) findViewById(R.id.toolbar_friend);
        toolbar_friend.setTitle("好友");
        setSupportActionBar(toolbar_friend);

        Toolbar toolbar_msg=(Toolbar) findViewById(R.id.toolbar_msg);
        toolbar_msg.setTitle("消息");
        setSupportActionBar(toolbar_msg);

        Toolbar toolbar_my=(Toolbar) findViewById(R.id.toolbar_my);
        toolbar_my.setTitle("个人中心");
        setSupportActionBar(toolbar_my);


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
        getMenuInflater().inflate(R.menu.main_toolbar,menu);
        getMenuInflater().inflate(R.menu.friend_toolbar,menu);
        getMenuInflater().inflate(R.menu.msg_toolbar,menu);
        getMenuInflater().inflate(R.menu.my_toolbar,menu);
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
