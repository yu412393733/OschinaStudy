package xyq.study.oschina.com.oschinastudy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyq.study.oschina.com.oschinastudy.AppContext;
import xyq.study.oschina.com.oschinastudy.R;
import xyq.study.oschina.com.oschinastudy.base.FragmentBase;

public class NavigationDrawerFragment extends FragmentBase {


    @Bind(R.id.menu_item_quests)
    LinearLayout menuItemQuests;
    @Bind(R.id.menu_item_opensoft)
    LinearLayout menuItemOpensoft;
    @Bind(R.id.menu_item_blog)
    LinearLayout menuItemBlog;
    @Bind(R.id.menu_item_gitapp)
    LinearLayout menuItemGitapp;
    @Bind(R.id.tv_set)
    TextView tvSet;
    @Bind(R.id.tv_night)
    TextView tvNight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mDrawerListView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView.setOnClickListener(this);
        ButterKnife.bind(this, mDrawerListView);
        initview(mDrawerListView);
        initData();
        return mDrawerListView;
    }

    @Override
    public void initview(View view) {
        if (AppContext.getNightModeSwitch()) {
            tvNight.setText("日间");
        } else {
            tvNight.setText("夜间");
        }
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick(R.id.tv_night)
    public void SetNight(){
        if(AppContext.getNightModeSwitch()){
            AppContext.setNightModeSwitch(false);
            getActivity().setTheme(R.style.AppBaseTheme_Light);
        }else{
            AppContext.setNightModeSwitch(true);
            getActivity().setTheme(R.style.AppBaseTheme_Night);
        }
        getActivity().recreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
