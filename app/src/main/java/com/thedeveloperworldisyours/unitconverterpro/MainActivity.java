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

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.foundation_icons_typeface_library.FoundationIcons;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.thedeveloperworldisyours.unitconverterpro.currency.CurrencyFragment;
import com.thedeveloperworldisyours.unitconverterpro.fragment.AreaFragment;

public class MainActivity extends AppCompatActivity {

    private static final int BASIC_CATEGORY = 1;
    private static final int CURRENCY_UNIT = 2;
    private static final int AREA_UNIT = 3;
    private static final int POWER_UNIT = 4;
    private static final int WORK_UNIT = 5;
    private static final int FUEL_UNIT = 6;
    private static final int VOLUME_UNIT = 7;

    private Drawer mResult = null;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = (FloatingActionButton) findViewById(R.id.fab);


        creteNavigationDrawer();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void creteNavigationDrawer() {
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

        PrimaryDrawerItem basicItem = new PrimaryDrawerItem().withIdentifier(BASIC_CATEGORY).withName(R.string.activity_main_basic_category);
        SecondaryDrawerItem currencyItem = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(CURRENCY_UNIT).withName(R.string.fragment_currency_title).withIcon(GoogleMaterial.Icon.gmd_attach_money);
        SecondaryDrawerItem areaItem = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(AREA_UNIT).withName(R.string.fragment_area_title).withIcon(GoogleMaterial.Icon.gmd_filter_list);
        SecondaryDrawerItem powerItem = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(POWER_UNIT).withName(R.string.fragment_power_title).withIcon(FontAwesome.Icon.faw_bolt);
        SecondaryDrawerItem workItem = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(WORK_UNIT).withName(R.string.fragment_work_title).withIcon(FoundationIcons.Icon.fou_wheelchair);
        SecondaryDrawerItem fuelItem = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(FUEL_UNIT).withName(R.string.fragment_fuel_title).withIcon(CommunityMaterial.Icon.cmd_gas_station);
        SecondaryDrawerItem voluemenItem = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(VOLUME_UNIT).withName(R.string.fragment_volume_title).withIcon(Ionicons.Icon.ion_ios_flask_outline);

        mResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        basicItem,
                        currencyItem,
                        areaItem,
                        new DividerDrawerItem(),
                        powerItem,
                        workItem,
                        fuelItem,
                        voluemenItem
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        FragmentManager fragmentManager = getSupportFragmentManager();

                        int backgroundColor = R.color.currency_color;
                        int titleString = R.string.fragment_currency_title;

                        switch ((int) drawerItem.getIdentifier()) {
                            case BASIC_CATEGORY:
                                mResult.setSelection(2);
                                break;

                            case CURRENCY_UNIT:
                                fragmentManager.beginTransaction()
                                        .replace(R.id.content_main_container, CurrencyFragment.newInstance())
                                        .commit();
                                backgroundColor = R.color.currency_color;
                                titleString = R.string.fragment_currency_title;
                                break;

                            case AREA_UNIT:
                                fragmentManager.beginTransaction()
                                        .replace(R.id.content_main_container, AreaFragment.newInstance())
                                        .commit();
                                backgroundColor = R.color.area_color;
                                titleString = R.string.fragment_area_title;
                                break;

                            case POWER_UNIT:
                                Toast.makeText(MainActivity.this, R.string.fragment_power_title, Toast.LENGTH_SHORT).show();
                                backgroundColor = R.color.power_color;
                                titleString = R.string.fragment_power_title;
                                break;

                            case WORK_UNIT:
                                Toast.makeText(MainActivity.this, R.string.fragment_work_title, Toast.LENGTH_SHORT).show();
                                backgroundColor = R.color.work_color;
                                titleString = R.string.fragment_work_title;
                                break;

                            case FUEL_UNIT:
                                Toast.makeText(MainActivity.this, R.string.fragment_fuel_title, Toast.LENGTH_SHORT).show();
                                backgroundColor = R.color.fuel_color;
                                titleString = R.string.fragment_fuel_title;
                                break;

                            case VOLUME_UNIT:
                                Toast.makeText(MainActivity.this, R.string.fragment_volume_title, Toast.LENGTH_SHORT).show();
                                backgroundColor = R.color.volume_color;
                                titleString = R.string.fragment_fuel_title;
                                break;
                        }

                        toolbar.setTitle(titleString);
                        toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, backgroundColor));
                        mFab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, backgroundColor)));
                        return false;
                    }
                })
                .build();
        mResult.setSelection(2);
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

}
