package vn.edu.ptithcm.mytrip006.activity.list_data;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.ptithcm.mytrip006.R;
import vn.edu.ptithcm.mytrip006.activity.AddActivity;
import vn.edu.ptithcm.mytrip006.activity.ColorSettingActivity;
import vn.edu.ptithcm.mytrip006.activity.Constant;
import vn.edu.ptithcm.mytrip006.activity.HelpActivity;
import vn.edu.ptithcm.mytrip006.activity.MainActivity;
import vn.edu.ptithcm.mytrip006.activity.SearchActivity;
import vn.edu.ptithcm.mytrip006.model.du_lieu;

/**
 * Created by koman on 5/5/2018.
 */

public class list_data extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<String> loai_hinh_list = new ArrayList<>();
    private ArrayList<String> ten_list = new ArrayList<>();
    private ArrayList<String> khu_vuc_list = new ArrayList<>();
    private ArrayList<String> thanh_pho_list = new ArrayList<>();
    private ArrayList<String> mota_list = new ArrayList<>();
    private ArrayList<String> hinh_anh_list = new ArrayList<>();
    private ListView lv;
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        lv = (ListView) findViewById(R.id.androidList);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    du_lieu dulieu = new du_lieu();
                    dulieu = ds.getValue(du_lieu.class);
                    loai_hinh_list.add(dulieu.getLoai_hinh());
                    ten_list.add(dulieu.getTen());
                    khu_vuc_list.add(dulieu.getKhu_vuc());
                    thanh_pho_list.add(dulieu.getThanh_pho());
                    mota_list.add(dulieu.getMota());
                    hinh_anh_list.add(dulieu.getHinh_anh());
                }
                adapter = new vn.edu.ptithcm.mytrip006.activity.list_data.ListAdapter(list_data.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
            Intent in = new Intent(list_data.this, ColorSettingActivity.class);
            startActivity(in);

            return true;
        }else if ( id == R.id.search)
        {
            Intent in = new Intent(list_data.this,SearchActivity.class);
            startActivity(in);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(list_data.this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_to_left, R.anim.slide_from_right);

            // Handle the camera action
        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(list_data.this, HelpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right);
            finish();
        } else if (id == R.id.nav_setting) {
            Intent in = new Intent(list_data.this, ColorSettingActivity.class);
            startActivity(in);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}