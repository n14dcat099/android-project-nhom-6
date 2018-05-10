package com.example.ux510ux.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by UX510UX on 21/04/2018.
 */

public class SuaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("ORDER");

        final EditText edtName = findViewById(R.id.edtName);
        final EditText edtSS = findViewById(R.id.edtSS);
        Button btnSave = findViewById(R.id.btnSave);

        final Intent intent = getIntent();
        final String oldName = intent.getStringExtra("CLASSNAME");
        final String oldSS = intent.getStringExtra("AMOUNT");

        edtName.setText(oldName);
        edtSS.setText(oldSS);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String SS = edtSS.getText().toString().trim();
                if(oldName.equals(name)&& oldSS.equals(SS)){
                    Intent intent1 = new Intent(SuaActivity.this,MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
                if(name.isEmpty()||SS.isEmpty()){
                    Toast.makeText(SuaActivity.this,"Bạn điền thiếu thông tin",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(oldName.equals(name)){
                    myRef.child(oldName).setValue(SS);
                }else {
                    myRef.child(oldName).removeValue();
                    myRef.child(name).setValue(SS);
                }
                Toast.makeText(SuaActivity.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SuaActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
