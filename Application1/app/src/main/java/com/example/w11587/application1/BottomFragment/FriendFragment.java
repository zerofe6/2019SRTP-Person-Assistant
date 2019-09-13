package com.example.w11587.application1.BottomFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.w11587.application1.R;

/**
 * Created by 11587 on 2019/8/20.
 */
public class FriendFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //mxl文件
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }
}
