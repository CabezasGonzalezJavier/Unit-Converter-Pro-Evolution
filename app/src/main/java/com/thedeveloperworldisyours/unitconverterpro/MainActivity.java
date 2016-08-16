package com.thedeveloperworldisyours.unitconverterpro;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.foundation_icons_typeface_library.FoundationIcons;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.thedeveloperworldisyours.unitconverterpro.fragment.AreaFragment;
import com.thedeveloperworldisyours.unitconverterpro.fragment.CurrencyFragment;

public class MainActivity extends AppCompatActivity {

    private Drawer mResult =null;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .build();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.activity_main_basic_category);
//        item1.withTextColor(Color.WHITE);
        SecondaryDrawerItem item2 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(2).withName(R.string.fragment_currency_title).withIcon(GoogleMaterial.Icon.gmd_attach_money);
        SecondaryDrawerItem item3 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(3).withName(R.string.fragment_area_title).withIcon(GoogleMaterial.Icon.gmd_filter_list);
        SecondaryDrawerItem item4 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(4).withName(R.string.fragment_power_title).withIcon(FontAwesome.Icon.faw_bolt);
        SecondaryDrawerItem item5 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(5).withName(R.string.fragment_work_title).withIcon(FoundationIcons.Icon.fou_wheelchair);

        mResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        new DividerDrawerItem(),
                        item4,
                        item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        FragmentManager fragmentManager = getSupportFragmentManager();

                        switch ((int) drawerItem.getIdentifier()) {
                            case 1:
                                mResult.setSelection(2);
                            case 2:
                                fragmentManager.beginTransaction()
                                        .replace(R.id.content_main_container, CurrencyFragment.newInstance())
                                        .commit();
                                toolbar.setTitle(R.string.fragment_currency_title);
                                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.currency_color));
                                mFab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.currency_color)));
                                break;
                            case 3:
                                fragmentManager.beginTransaction()
                                        .replace(R.id.content_main_container, AreaFragment.newInstance())
                                        .commit();
                                toolbar.setTitle(R.string.fragment_area_title);
                                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.area_color));
                                mFab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.area_color)));
                                break;

                            case 4:
                                Toast.makeText(MainActivity.this, R.string.fragment_power_title, Toast.LENGTH_SHORT).show();
                                toolbar.setTitle(R.string.fragment_power_title);
                                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.power_color));
                                mFab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.power_color)));
                                break;
                            case 5:
                                Toast.makeText(MainActivity.this, R.string.fragment_work_title, Toast.LENGTH_SHORT).show();
                                toolbar.setTitle(R.string.fragment_work_title);
                                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.work_color));
                                mFab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.work_color)));
                                break;
                            case 6:
                                Toast.makeText(MainActivity.this, R.string.app_name, Toast.LENGTH_SHORT).show();
                                toolbar.setTitle(R.string.fragment_work_title);
                                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.work_color));
                                mFab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.work_color)));
                                break;
                            case R.id.nav_send:
                                break;
                        }
                        return false;
                    }
                })
                .build();
        mResult.setSelection(1, false);
        mResult.setSelection(2);



        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        switch (item.getItemId()) {
//            case R.id.nav_camera:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.content_main_container, CurrencyFragment.newInstance())
//                        .commit();
//                break;
//            case R.id.nav_gallery:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.content_main_container, AreaFragment.newInstance())
//                        .commit();
//                break;
//            case R.id.nav_slideshow:
//                break;
//            case R.id.nav_manage:
//                break;
//            case R.id.nav_share:
//                break;
//            case R.id.nav_send:
//                break;
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
