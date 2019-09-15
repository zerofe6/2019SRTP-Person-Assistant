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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.w11587.application1.BottomFragment.FriendFragment;
import com.example.w11587.application1.BottomFragment.MsgFragment;
import com.example.w11587.application1.BottomFragment.MyFragment;
import com.example.w11587.application1.Friend_List.SideBar;
import com.example.w11587.application1.Friend_List.SortAdapter;
import com.example.w11587.application1.Friend_List.User;
import com.example.w11587.application1.SetMemo.MemoActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private MsgFragment fragment1;
    private FriendFragment fragment2;
    private MyFragment fragment3;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private ListView listView;
    private SideBar sideBar;
    private ArrayList<User> list;
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

        //好友列表设置
        initView();
        initData();



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


    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        sideBar = (SideBar) findViewById(R.id.side_bar);
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        listView.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new User("亳州")); // 亳[bó]属于不常见的二级汉字
        list.add(new User("大娃"));
        list.add(new User("二娃"));
        list.add(new User("三娃"));
        list.add(new User("四娃"));
        list.add(new User("五娃"));
        list.add(new User("六娃"));
        list.add(new User("七娃"));
        list.add(new User("喜羊羊"));
        list.add(new User("美羊羊"));
        list.add(new User("懒羊羊"));
        list.add(new User("沸羊羊"));
        list.add(new User("暖羊羊"));
        list.add(new User("慢羊羊"));
        list.add(new User("灰太狼"));
        list.add(new User("红太狼"));
        list.add(new User("孙悟空"));
        list.add(new User("黑猫警长"));
        list.add(new User("舒克"));
        list.add(new User("贝塔"));
        list.add(new User("海尔"));
        list.add(new User("阿凡提"));
        list.add(new User("邋遢大王"));
        list.add(new User("哪吒"));
        list.add(new User("没头脑"));
        list.add(new User("不高兴"));
        list.add(new User("蓝皮鼠"));
        list.add(new User("大脸猫"));
        list.add(new User("大头儿子"));
        list.add(new User("小头爸爸"));
        list.add(new User("蓝猫"));
        list.add(new User("淘气"));
        list.add(new User("叶峰"));
        list.add(new User("楚天歌"));
        list.add(new User("江流儿"));
        list.add(new User("Tom"));
        list.add(new User("Jerry"));
        list.add(new User("12345"));
        list.add(new User("54321"));
        list.add(new User("_(:з」∠)_"));
        list.add(new User("……%￥#￥%#"));
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        SortAdapter adapter = new SortAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
