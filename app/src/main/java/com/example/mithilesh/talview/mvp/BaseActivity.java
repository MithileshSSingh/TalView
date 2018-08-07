package com.example.mithilesh.talview.mvp;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.mvp.screen_album.AlbumFragment;
import com.example.mithilesh.talview.mvp.screen_main.MainAlbumFragment;
import com.example.mithilesh.talview.mvp.screen_main.MainPostFragment;
import com.example.mithilesh.talview.mvp.screen_post.PostFragment;
import com.example.mithilesh.talview.utils.ActivityUtils;
import com.example.mithilesh.talview.utils.AppConstants;

public abstract class BaseActivity extends AppCompatActivity {

    public static String TAG = "TAG";
    public Resources mResources;
    public Activity mActivity;
    private BaseFragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mActivity = this;
        mResources = this.getResources();
    }


    public void showFragment(int screenId, Bundle data) {

        String tag = null;
        mFragment = null;
        boolean addToBackStack = false;

        if (getIntent() != null) {
            data = getIntent().getBundleExtra(AppConstants.IntentKeys.DATA);
        }

        switch (screenId) {
            case AppConstants.Screens.SCREEN_MAIN_POST:

                mFragment = MainPostFragment.newInstance();
                tag = MainPostFragment.TAG;

                break;
            case AppConstants.Screens.SCREEN_MAIN_ALBUM:

                mFragment = MainAlbumFragment.newInstance();
                tag = MainAlbumFragment.TAG;

                break;
            case AppConstants.Screens.SCREEN_POST:

                mFragment = PostFragment.newInstance();
                tag = PostFragment.TAG;

                break;
            case AppConstants.Screens.SCREEN_ALBUM:

                mFragment = AlbumFragment.newInstance();
                tag = AlbumFragment.TAG;

                break;
            default:
                break;
        }


        if (mFragment != null && tag != null) {

            if (data != null) {
                mFragment.setArguments(data);
            }

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFragment, R.id.content, tag, addToBackStack);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected abstract void init();

    protected abstract void initView();

    protected abstract void initMembers();

    protected abstract void initListeners();

    protected abstract void initData();

}
