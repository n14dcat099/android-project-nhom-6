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

public class SuaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("DienThoai");

        final EditText edtName = findViewById(R.id.edtName);
        final EditText edtPrice = findViewById(R.id.edtPrice);
        Button btnSave = findViewById(R.id.btnSave);

        final Intent intent = getIntent();
        final String oldName = intent.getStringExtra("PHONENAME");
        final String oldPrice = intent.getStringExtra("PRICE");

        edtName.setText(oldName);
        edtPrice.setText(oldPrice);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String price = edtPrice.getText().toString().trim();
                if(oldName.equals(name)&& oldPrice.equals(price)){
                    Intent intent1 = new Intent(SuaActivity.this,MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
                if(name.isEmpty()||price.isEmpty()){
                    Toast.makeText(SuaActivity.this,"Bạn điền thiếu thông tin",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(oldName.equals(name)){
                    myRef.child(oldName).setValue(price);
                }else {
                    myRef.child(oldName).removeValue();
                    myRef.child(name).setValue(price);
                }
                Toast.makeText(SuaActivity.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SuaActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
