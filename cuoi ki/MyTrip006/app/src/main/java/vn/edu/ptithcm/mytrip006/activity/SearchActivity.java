package vn.edu.ptithcm.mytrip006.activity;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.ptithcm.mytrip006.R;
import vn.edu.ptithcm.mytrip006.activity.list_data.ListAdapter;
import vn.edu.ptithcm.mytrip006.model.du_lieu;

public class SearchActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<String> loai_hinh_list ;
    private ArrayList<String> ten_list ;
    private ArrayList<String> khu_vuc_list ;
    private ArrayList<String> thanh_pho_list ;
    private ArrayList<String> mota_list;
    private ArrayList<String> hinh_anh_list ;
    private ListView lv;
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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


        navigationView.setNavigationItemSelectedListener(this);

        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        Spinner loai_hinh_spinner = (Spinner) findViewById(R.id.loai_hinh);
        final String loai_hinh = loai_hinh_spinner.getSelectedItem().toString();
        Spinner thanh_pho_spinner = (Spinner) findViewById(R.id.thanh_pho);
        final String thanh_pho = thanh_pho_spinner.getSelectedItem().toString();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loai_hinh_list = new ArrayList<>();
                ten_list = new ArrayList<>();
                khu_vuc_list = new ArrayList<>();
                thanh_pho_list = new ArrayList<>();
                mota_list = new ArrayList<>();
                hinh_anh_list = new ArrayList<>();
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference();
                lv = (ListView) findViewById(R.id.androidList);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String content = ((EditText) findViewById(R.id.content)).getText().toString();
                        if(content.length() == 0 )
                        {
                            if (thanh_pho.contains("default"))
                            {
                                if ( loai_hinh.contains("default"))
                                {
                                    for (DataSnapshot ds: dataSnapshot.getChildren())
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
                                    if ( ten_list.size() == 0 )
                                    {
                                        Toast.makeText(SearchActivity.this, "Không có kết quả phù hợp 1", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new ListAdapter(SearchActivity.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                                    lv.setAdapter(adapter);
                                }
                                else
                                {
                                    for (DataSnapshot ds: dataSnapshot.getChildren())
                                    {
                                        du_lieu dulieu = new du_lieu();
                                        dulieu = ds.getValue(du_lieu.class);
                                        if ( dulieu.loai_hinh.contains(loai_hinh)) {
                                            loai_hinh_list.add(dulieu.getLoai_hinh());
                                            ten_list.add(dulieu.getTen());
                                            khu_vuc_list.add(dulieu.getKhu_vuc());
                                            thanh_pho_list.add(dulieu.getThanh_pho());
                                            mota_list.add(dulieu.getMota());
                                            hinh_anh_list.add(dulieu.getHinh_anh());
                                        }
                                    }
                                    if ( ten_list.size() == 0 )
                                    {
                                        Toast.makeText(SearchActivity.this, "Không có kết quả phù hợp 2", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new ListAdapter(SearchActivity.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                                    lv.setAdapter(adapter);
                                }
                            }
                            else
                            {
                                if(loai_hinh.contains("default"))
                                {
                                    for (DataSnapshot ds: dataSnapshot.getChildren())
                                    {
                                        du_lieu dulieu = new du_lieu();
                                        dulieu = ds.getValue(du_lieu.class);
                                        if ( dulieu.thanh_pho.contains(thanh_pho) ) {
                                            loai_hinh_list.add(dulieu.getLoai_hinh());
                                            ten_list.add(dulieu.getTen());
                                            khu_vuc_list.add(dulieu.getKhu_vuc());
                                            thanh_pho_list.add(dulieu.getThanh_pho());
                                            mota_list.add(dulieu.getMota());
                                            hinh_anh_list.add(dulieu.getHinh_anh());
                                        }
                                    }
                                    if ( ten_list.size() == 0 )
                                    {
                                        Toast.makeText(SearchActivity.this, "Không có kết quả phù hợp 3", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new ListAdapter(SearchActivity.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                                    lv.setAdapter(adapter);
                                }
                                else
                                {
                                    for (DataSnapshot ds: dataSnapshot.getChildren())
                                    {
                                        du_lieu dulieu = new du_lieu();
                                        dulieu = ds.getValue(du_lieu.class);
                                        if ( dulieu.thanh_pho.contains(thanh_pho) && dulieu.loai_hinh.contains(loai_hinh) )
                                        {
                                            loai_hinh_list.add(dulieu.getLoai_hinh());
                                            ten_list.add(dulieu.getTen());
                                            khu_vuc_list.add(dulieu.getKhu_vuc());
                                            thanh_pho_list.add(dulieu.getThanh_pho());
                                            mota_list.add(dulieu.getMota());
                                            hinh_anh_list.add(dulieu.getHinh_anh());
                                        }
                                    }
                                    if ( ten_list.size() == 0 )
                                    {
                                        Toast.makeText(SearchActivity.this, "Không có kết quả phù hợp 4", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new ListAdapter(SearchActivity.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                                    lv.setAdapter(adapter);
                                }
                            }
                        }
                        else
                        {
                            if ( thanh_pho.contains("default" ))
                            {
                                if ( loai_hinh.contains("default"))
                                {
                                    for (DataSnapshot ds: dataSnapshot.getChildren())
                                    {
                                        du_lieu dulieu = new du_lieu();
                                        dulieu = ds.getValue(du_lieu.class);
                                        if(dulieu.getTen().contains(content.toString())) {
                                            loai_hinh_list.add(dulieu.getLoai_hinh());
                                            ten_list.add(dulieu.getTen());
                                            khu_vuc_list.add(dulieu.getKhu_vuc());
                                            thanh_pho_list.add(dulieu.getThanh_pho());
                                            mota_list.add(dulieu.getMota());
                                            hinh_anh_list.add(dulieu.getHinh_anh());
                                        }
                                    }
                                    if ( ten_list.size() == 0 )
                                    {
                                        Toast.makeText(SearchActivity.this, "Không có kết quả phù hợp 5", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new ListAdapter(SearchActivity.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                                    lv.setAdapter(adapter);
                                }
                                else
                                {
                                    for (DataSnapshot ds: dataSnapshot.getChildren())
                                    {
                                        du_lieu dulieu = new du_lieu();
                                        dulieu = ds.getValue(du_lieu.class);
                                        if ( dulieu.loai_hinh.contains(loai_hinh) && dulieu.getTen().contains(content.toString())) {
                                            loai_hinh_list.add(dulieu.getLoai_hinh());
                                            ten_list.add(dulieu.getTen());
                                            khu_vuc_list.add(dulieu.getKhu_vuc());
                                            thanh_pho_list.add(dulieu.getThanh_pho());
                                            mota_list.add(dulieu.getMota());
                                            hinh_anh_list.add(dulieu.getHinh_anh());
                                        }
                                    }
                                    if ( ten_list.size() == 0 )
                                    {
                                        Toast.makeText(SearchActivity.this, "Không có kết quả phù hợp 6", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new ListAdapter(SearchActivity.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                                    lv.setAdapter(adapter);
                                }
                            }
                            else
                            {
                                if(loai_hinh.contains("default"))
                                {
                                    for (DataSnapshot ds: dataSnapshot.getChildren())
                                    {
                                        du_lieu dulieu = new du_lieu();
                                        dulieu = ds.getValue(du_lieu.class);
                                        if ( dulieu.thanh_pho.contains(thanh_pho) && dulieu.getTen().contains(content.toString())) {
                                            loai_hinh_list.add(dulieu.getLoai_hinh());
                                            ten_list.add(dulieu.getTen());
                                            khu_vuc_list.add(dulieu.getKhu_vuc());
                                            thanh_pho_list.add(dulieu.getThanh_pho());
                                            mota_list.add(dulieu.getMota());
                                            hinh_anh_list.add(dulieu.getHinh_anh());
                                        }
                                    }
                                    if ( ten_list.size() == 0 )
                                    {
                                        Toast.makeText(SearchActivity.this, "Không có kết quả phù hợp 7", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new ListAdapter(SearchActivity.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                                    lv.setAdapter(adapter);
                                }
                                else
                                {
                                    for (DataSnapshot ds: dataSnapshot.getChildren())
                                    {
                                        du_lieu dulieu = new du_lieu();
                                        dulieu = ds.getValue(du_lieu.class);
                                        if ( dulieu.thanh_pho.contains(thanh_pho) && dulieu.loai_hinh.contains(loai_hinh) && dulieu.getTen().contains(content.toString())) {
                                            loai_hinh_list.add(dulieu.getLoai_hinh());
                                            ten_list.add(dulieu.getTen());
                                            khu_vuc_list.add(dulieu.getKhu_vuc());
                                            thanh_pho_list.add(dulieu.getThanh_pho());
                                            mota_list.add(dulieu.getMota());
                                            hinh_anh_list.add(dulieu.getHinh_anh());
                                        }
                                    }
                                    if ( ten_list.size() == 0 )
                                    {
                                        Toast.makeText(SearchActivity.this, "Không có kết quả phù hợp 8", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new ListAdapter(SearchActivity.this,ten_list.toArray(new String[ten_list.size()]),khu_vuc_list.toArray(new String[khu_vuc_list.size()]),thanh_pho_list.toArray(new String[thanh_pho_list.size()]),hinh_anh_list.toArray(new String[hinh_anh_list.size()]),mota_list.toArray(new String[mota_list.size()]),loai_hinh_list.toArray(new String[loai_hinh_list.size()]));
                                    lv.setAdapter(adapter);
                                }

                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
          //  getFragmentManager().popBackStack();
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
            Intent in = new Intent(SearchActivity.this, ColorSettingActivity.class);
            startActivity(in);

            return true;
        }else if ( id == R.id.search)
        {
            Intent in = new Intent(SearchActivity.this,SearchActivity.class);
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
            Intent i = new Intent(SearchActivity.this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_to_left, R.anim.slide_from_right);

            // Handle the camera action
        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(SearchActivity.this, HelpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right);
            finish();
        } else if (id == R.id.nav_setting) {
            Intent in = new Intent(SearchActivity.this, ColorSettingActivity.class);
            startActivity(in);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
