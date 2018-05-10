package vn.edu.ptithcm.mytrip006.activity.show;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import vn.edu.ptithcm.mytrip006.R;
import vn.edu.ptithcm.mytrip006.activity.MainActivity;

public class show extends AppCompatActivity {

    private TextView tv_thanh_pho;
    private TextView tv_khu_vuc;
    private TextView tv_ten;
    private TextView tv_mota;
    private ImageView tv_hinh_anh;
    private TextView tv_loai_hinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        String data = getIntent().getStringExtra("key");
        tv_thanh_pho = (TextView) findViewById(R.id.thanh_pho);
        tv_khu_vuc = (TextView) findViewById(R.id.khu_vuc);
        tv_mota = (TextView) findViewById(R.id.mota);
        tv_loai_hinh = (TextView) findViewById(R.id.loai_hinh);
        tv_ten = (TextView) findViewById(R.id.ten);
        String[] data_list = data.split("\n");
        //hinh_anh + "\n" + khu_vuc + "\n" + loai_hinh + "\n" + mota + "\n" + ten + "\n" + thanh_pho;
        tv_thanh_pho.setText(data_list[5]);
        tv_khu_vuc.setText(data_list[1]);
        tv_mota.setText(data_list[3]);
        tv_ten.setText(data_list[4]);
        tv_loai_hinh.setText(data_list[2]);
        tv_hinh_anh = (ImageView) findViewById(R.id.imageView) ;
        Glide.with(show.this).load(Uri.parse(data_list[0])).into(tv_hinh_anh);
        Button confirmBtn = (Button) findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(show.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}
