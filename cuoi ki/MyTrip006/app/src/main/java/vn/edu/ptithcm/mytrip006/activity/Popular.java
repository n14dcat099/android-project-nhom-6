package vn.edu.ptithcm.mytrip006.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.edu.ptithcm.mytrip006.R;

/**
 * Created by LENOVO on 5/2/2018.
 */

public class Popular extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mBottomNavigation
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_popular:
                    //switchToPopularFragment();
                    Intent i_popular = new Intent(Popular.this, Popular.class);
                    startActivity(i_popular);
                    break;

                case R.id.action_my_places:
                    //switchToMyPlacesFragment();
                    Intent i_myplace = new Intent(Popular.this, MyPlace.class);
                    startActivity(i_myplace);
                    break;
                case R.id.action_favorites:
                    //switchToFavoritesFragment();
                    Intent i_favorite = new Intent(Popular.this, Favorite.class);
                    startActivity(i_favorite);
                    break;

                case R.id.action_settings:
                    switchToSettingsFragment();
                    break;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_white);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Popular.this, AddActivity.class);
                startActivity(intent);
                finish();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
       /* getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/
        toggle.syncState();

        FirebaseUser currentUser;
        DatabaseReference mData;
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mData = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.CHILD_USER_IMAGE_URL);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUser = (TextView) headerView.findViewById(R.id.navUser);
        navUser.setText(currentUser.getDisplayName());
        TextView navEmail = (TextView) headerView.findViewById(R.id.navEmail);
        navEmail.setText(currentUser.getEmail());
        navigationView.setNavigationItemSelectedListener(this);


        navigationView.setNavigationItemSelectedListener(this);
        //Khá»Ÿi táº¡o thanh Äiá»u hÆ°á»›ng phÃ­a dÆ°á»›i cá»§a app
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Helps Navigation Bar could Items display all Icon with text
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        //
        bottomNavigationView.setOnNavigationItemSelectedListener(mBottomNavigation);


        //Khánh Vy
        ActionBar actionBar = getSupportActionBar();
        SharedPreferences pref = getSharedPreferences("sharedSettings", 0);
        final String setColor = pref.getString("color", "Default");
        Setting.settings("Du Lịch", setColor, actionBar, getApplicationContext());





        TabLayout tabLayout = (TabLayout)findViewById( R.id.tablayout_popular );
        tabLayout.addTab( tabLayout.newTab().setText( "Du Lich " ) );
        tabLayout.addTab( tabLayout.newTab().setText( "Am Thuc " ) );
        tabLayout.setTabGravity( TabLayout.GRAVITY_FILL );

        final ViewPager viewPager = (ViewPager)findViewById( R.id.viewPagerPopular );
        final PageAdapterPopular adapter = new PageAdapterPopular( getSupportFragmentManager(),tabLayout.getTabCount() );
        viewPager.setAdapter( adapter );
        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabLayout ) );
        tabLayout.setOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem( tab.getPosition() );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            getFragmentManager().popBackStack();
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
            Intent in = new Intent(Popular.this, ColorSettingActivity.class);
            startActivity(in);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void switchToSettingsFragment() {
        SettingsFragment settingsFragment = new SettingsFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragmentContainer, settingsFragment);
        ft.addToBackStack(settingsFragment.getClass().getSimpleName());
        ft.commit();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(Popular.this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_to_left, R.anim.slide_from_right);

            // Handle the camera action
        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(Popular.this, HelpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right);
            finish();
        } else if (id == R.id.nav_setting) {
            Intent in = new Intent(Popular.this, ColorSettingActivity.class);
            startActivity(in);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

