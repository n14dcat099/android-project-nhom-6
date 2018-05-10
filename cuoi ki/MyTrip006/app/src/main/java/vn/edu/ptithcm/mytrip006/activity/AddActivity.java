package vn.edu.ptithcm.mytrip006.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.UUID;

import vn.edu.ptithcm.mytrip006.R;
import vn.edu.ptithcm.mytrip006.activity.show.show;
import vn.edu.ptithcm.mytrip006.model.du_lieu;



    public class AddActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

        private int REQUEST_CODE_IMAGE = 1;
        private ImageView hinh_anh;
        private String url;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_insert);

            Button backbtn=(Button) findViewById(R.id.backBtn);
            backbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(AddActivity.this,MainActivity.class);
                    startActivity(i);
                }
            });

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

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            FirebaseStorage storage = FirebaseStorage.getInstance();//

            Button addBtn = (Button) findViewById(R.id.addBtn);
            Button uploadBtn = (Button) findViewById(R.id.uploadBtn);
            hinh_anh = (ImageView) findViewById(R.id.imageV);
            uploadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CODE_IMAGE);
                }
            });

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference storageRef = storage.getReferenceFromUrl("gs://mytrip006-127d7.appspot.com");
                    Calendar calendar = Calendar.getInstance();
                    final StorageReference mountainsRef = storageRef.child("image-"+calendar.toString());

                    hinh_anh.setDrawingCacheEnabled(true);
                    hinh_anh.buildDrawingCache();
                    Bitmap bitmap = hinh_anh.getDrawingCache();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] data = baos.toByteArray();

                    UploadTask uploadTask = mountainsRef.putBytes(data);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(AddActivity.this,"Không thể upload",Toast.LENGTH_LONG).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            url = downloadUrl.toString();
                            //Lấy tên thành phố
                            Spinner thanh_pho_spinner = (Spinner) findViewById(R.id.thanh_pho);
                            String thanh_pho = thanh_pho_spinner.getSelectedItem().toString();
                            //Lấy Mô tả
                            String mota = ((EditText) findViewById(R.id.mota)).getText().toString();
                            //Lấy khu vực
                            String khu_vuc = khuvuc(thanh_pho);
                            //Lấy dữ liệu ( địa điểm hoặc ăn uống )
                            String ten = ((EditText) findViewById(R.id.ten)).getText().toString();
                            //Set image mặc định ( sau thêm upload sau )
                            //Lấy dữ liệu loại hình
                            Spinner loai_hinh_spinner = (Spinner) findViewById(R.id.loai_hinh);
                            String loai_hinh = loai_hinh_spinner.getSelectedItem().toString();
                            //Lấy tên User up
                            FirebaseUser currentUser;
                            currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String name_User = currentUser.getEmail();
                            String ten_hinh_anh = url;
                            if ( khu_vuc.isEmpty() || mota.isEmpty() || ten.isEmpty() || thanh_pho.isEmpty() || loai_hinh.isEmpty() || url.isEmpty())
                            {
                                Toast.makeText(AddActivity.this,"Không được bỏ trống bất cứ ô nào",Toast.LENGTH_LONG).show();
                                return;
                            }
                            String uniqueID = UUID.randomUUID().toString();
                            du_lieu dulieu = new du_lieu(thanh_pho, ten, mota,  url, khu_vuc, loai_hinh,name_User);
                            mDatabase.child(uniqueID).setValue(dulieu);
                            Intent it = new Intent(AddActivity.this,show.class);
                            String _data = url + "\n" + khu_vuc + "\n" + loai_hinh + "\n" + mota + "\n" + ten + "\n" + thanh_pho;
                            it.putExtra("key",_data);
                            startActivity(it);
                           // finish();
                        }
                    });
                    //
                //
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
                Intent in = new Intent(AddActivity.this, ColorSettingActivity.class);
                startActivity(in);

                return true;
            }else if ( id == R.id.search)
            {
                Intent in = new Intent(AddActivity.this,SearchActivity.class);
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
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_to_left, R.anim.slide_from_right);

                // Handle the camera action
            } else if (id == R.id.nav_help) {
                Intent intent = new Intent(AddActivity.this, HelpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right);
                finish();
            } else if (id == R.id.nav_setting) {
                Intent in = new Intent(AddActivity.this, ColorSettingActivity.class);
                startActivity(in);

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if ( requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null)
            {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                hinh_anh.setImageBitmap(bitmap);
            }
            super.onActivityResult(requestCode, resultCode, data);

        }

        public String khuvuc(String thanh_pho){
            if ( thanh_pho.equalsIgnoreCase("An Giang") )
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Bà Rịa Vũng Tàu"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Bạc Liêu"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Bắc Kạn"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Bắc Giang"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Bắc Ninh"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Bến Tre"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Bình Dương"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Bình Định"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Bình Phước"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Bình Thuận"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Cà Mau"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Cao Bằng"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Cần Thơ"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Đà Nẵng"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Đắk Lắk"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Đắk Nông"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Điện Biên"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Đồng Nai"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Đồng Tháp"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Gia Lai"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Hà Giang"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Hà Nam"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Hà Nội"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Hà Tây"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Hà Tĩnh"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Hải Dương"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Hải Phòng"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Hòa Bình"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("TP Hồ Chí Minh"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Hậu Giang"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Hưng Yên"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Khánh Hòa"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Kiên Giang"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Kon Tum"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Lai Châu"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Lào Cai"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Lạng Sơn"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Lâm Đồng"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Long An"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Nam Định"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Nghệ An"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Ninh Bình"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Ninh Thuận"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Phú Thọ"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Phú Yên"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Quảng Bình"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Quảng Nam"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Quảng Ngãi"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Quảng Ninh"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Quảng Trị"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Sóc Trăng"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Sơn La"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Tây Ninh"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Thái Bình"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Thái Nguyên"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Thanh Hóa"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Thừa Thiên Huế"))
                return "Trung";
            else if (thanh_pho.equalsIgnoreCase("Tiền Giang"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Trà Vinh"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Tuyên Quang"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Vĩnh Long"))
                return "Nam";
            else if (thanh_pho.equalsIgnoreCase("Vĩnh Phúc"))
                return "Bắc";
            else if (thanh_pho.equalsIgnoreCase("Yên Bái"))
                return "Bắc";

            return null;

        }
    }