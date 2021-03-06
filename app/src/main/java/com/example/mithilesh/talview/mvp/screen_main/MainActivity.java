package com.example.mithilesh.talview.mvp.screen_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.mvp.BaseActivity;
import com.example.mithilesh.talview.utils.AppConstants;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private BottomNavigationView mNavigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void init() {
        initView();
        initMembers();
        initListeners();
        initData();
    }

    @Override
    protected void initView() {
        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

    }

    @Override
    protected void initMembers() {

        showFragment(AppConstants.Screens.SCREEN_MAIN_POST, null);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void initListeners() {
        mNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_post:
                showFragment(AppConstants.Screens.SCREEN_MAIN_POST, null);
                break;
            case R.id.navigation_album:
                showFragment(AppConstants.Screens.SCREEN_MAIN_ALBUM, null);
                break;
        }

        return true;
    }
}
