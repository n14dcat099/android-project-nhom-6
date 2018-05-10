package vn.edu.ptithcm.giuaki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemActivity extends AppCompatActivity {
    Button btnLuu;
    EditText edtTenDT,edtGiaDT;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        id();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DienThoai");

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Luu();
                Intent intent =new Intent(ThemActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Luu(){
        DienThoai dienThoai=new DienThoai(edtTenDT.getText().toString(),Integer.parseInt(edtGiaDT.getText().toString()));
        myRef.child(dienThoai.getTenDT()).setValue(dienThoai.getGiaDT());
        Toast.makeText(ThemActivity.this,"Thêm Thành Công",Toast.LENGTH_LONG).show();
    }

    private void id(){
        edtTenDT=findViewById(R.id.edtTenDT);
        edtGiaDT=findViewById(R.id.edtGiaDT);
        btnLuu=findViewById(R.id.btnLuu);
    }

}
