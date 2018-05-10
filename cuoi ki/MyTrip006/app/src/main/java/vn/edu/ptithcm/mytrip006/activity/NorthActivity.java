package vn.edu.ptithcm.mytrip006.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.edu.ptithcm.mytrip006.R;
import vn.edu.ptithcm.mytrip006.activity.list_data.list_data;

public class NorthActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView.OnNavigationItemSelectedListener mBottomNavigation
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_popular:
                    //switchToPopularFragment();
                    Intent i_popular = new Intent( NorthActivity.this , Popular.class );
                    startActivity( i_popular );
                    break;

                case R.id.action_my_places:
                    //switchToMyPlacesFragment();
                    Intent i_myplace = new Intent( NorthActivity.this,list_data.class );
                    startActivity( i_myplace );
                    break;
                case R.id.action_favorites:
                    //switchToFavoritesFragment();
                    Intent i_favorite = new Intent( NorthActivity.this,Favorite.class );
                    startActivity( i_favorite );
                    break;

                case R.id.action_settings:
                    switchToSettingsFragment();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_north);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(NorthActivity.this,AddActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FirebaseUser currentUser;
        DatabaseReference mData;
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mData = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.CHILD_USER_IMAGE_URL);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUser = (TextView) headerView.findViewById(R.id.navUser);
     navUser.setText(currentUser.getDisplayName());
        TextView navEmail = (TextView) headerView.findViewById(R.id.navEmail);
        navEmail.setText(currentUser.getEmail());
        navigationView.setNavigationItemSelectedListener(this);



//Khá»Ÿi táº¡o thanh Äiá»u hÆ°á»›ng phÃ­a dÆ°á»›i cá»§a app
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Helps Navigation Bar could Items display all Icon with text
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        //
        bottomNavigationView.setOnNavigationItemSelectedListener(mBottomNavigation);
        ActionBar actionBar = getSupportActionBar();
        SharedPreferences pref = getSharedPreferences("sharedSettings", 0);
        final String setColor = pref.getString("color", "Default");
        Setting.settings("Miền Bắc", setColor, actionBar, getApplicationContext());}

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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(NorthActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(NorthActivity.this,HelpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_from_right);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* public void switchToPopularFragment() {
        PopularFragment popularFragment = new PopularFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragmentContainer, popularFragment);
        ft.addToBackStack(popularFragment.getClass().getSimpleName());
        ft.commit();

    }

    private void switchToFavoritesFragment() {
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragmentContainer, favoritesFragment);
        ft.addToBackStack(favoritesFragment.getClass().getSimpleName());
        ft.commit();
    }

    private void switchToMyPlacesFragment() {
        MyPlacesFragment myPlacesFragment = new MyPlacesFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragmentContainer, myPlacesFragment);
        ft.addToBackStack(myPlacesFragment.getClass().getSimpleName());
        ft.commit();
    }*/

    private void switchToSettingsFragment() {
        SettingsFragment settingsFragment = new SettingsFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragmentContainer, settingsFragment);
        ft.addToBackStack(settingsFragment.getClass().getSimpleName());
        ft.commit();
    }


}

