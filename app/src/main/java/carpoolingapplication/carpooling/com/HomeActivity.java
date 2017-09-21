package carpoolingapplication.carpooling.com;

import android.animation.Animator;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import carpoolingapplication.carpooling.com.fragment.AboutAppFragment;
import carpoolingapplication.carpooling.com.fragment.ContentFragment;
import carpoolingapplication.carpooling.com.fragment.HistoryFragment;
import carpoolingapplication.carpooling.com.fragment.ServicesFragment;
import carpoolingapplication.carpooling.com.fragment.SettingFragment;
import carpoolingapplication.carpooling.com.fragment.SharingFragment;
import carpoolingapplication.carpooling.com.fragment.UserAccountFragment;
//import io.codetail.animation.ViewAnimationUtils;
import carpoolingapplication.carpooling.com.model.UserData;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
//import yalantis.com.sidemenu.util.ViewAnimator;

public class HomeActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private int res = R.drawable.home_project;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ContentFragment contentFragment = ContentFragment.newInstance(R.drawable.home_project);   //content_music
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);

        SlideMenuItem menuItem1 = new SlideMenuItem(ContentFragment.SERVICES, R.drawable.icn_1);
        list.add(menuItem1);

        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.HISTORY, R.drawable.history_icon);
        list.add(menuItem2);

        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.MY_ACCOUNT, R.drawable.user_identity);
        list.add(menuItem3);

        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.SETTINGS, R.drawable.ic_menu_manage);
        list.add(menuItem4);

        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SHARING, R.drawable.ic_menu_share);
        list.add(menuItem5);

        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.ABOUT, R.drawable.information_icon);
        list.add(menuItem6);

    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {    //for menu action_settings selecting
//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
        //this.res = this.res == R.drawable.content_music ? R.drawable.ic_launcher : R.drawable.content_music;
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment contentFragment = ContentFragment.newInstance(this.res);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        return contentFragment;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                 finish();

            case ContentFragment.SERVICES:
                ServicesFragment Services_Frag = new ServicesFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, Services_Frag)
                        .addToBackStack("ServicesFragment")
                        .commit();
                return screenShotable;
            case ContentFragment.HISTORY:
                HistoryFragment History_Frag = new HistoryFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, History_Frag)
                        .addToBackStack("HistoryFragment")
                        .commit();
                return screenShotable;
            case ContentFragment.MY_ACCOUNT:
                UserAccountFragment Account_Frag = new UserAccountFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, Account_Frag)
                        .addToBackStack("AccountFragment")
                        .commit();
                return screenShotable;
            case ContentFragment.SETTINGS:
                SettingFragment Setting_Frag = new SettingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, Setting_Frag)
                        .addToBackStack("SettingFragment")
                        .commit();
                return screenShotable;
            case ContentFragment.SHARING:
                SharingFragment Sharing_Frag = new SharingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, Sharing_Frag)
                        .addToBackStack("SharingFragment")
                        .commit();
                return screenShotable;
            case ContentFragment.ABOUT:
                AboutAppFragment About_Frag = new AboutAppFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, About_Frag)
                        .addToBackStack("AboutFragment")
                        .commit();
                return screenShotable;
            default:
                return replaceFragment(screenShotable, position);
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
}
