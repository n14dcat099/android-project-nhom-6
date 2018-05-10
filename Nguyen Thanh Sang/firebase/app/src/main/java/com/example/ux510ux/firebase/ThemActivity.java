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

public class ThemActivity extends AppCompatActivity {
    Button btnLuu;
    EditText edtTen,edtSS;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        id();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ORDER");

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
        ORDER order =new ORDER(edtTen.getText().toString(),Integer.parseInt(edtSS.getText().toString()));
        myRef.child(order.getTenlop()).setValue(order.getSiSolop());
        Toast.makeText(ThemActivity.this,"Thêm Thành Công",Toast.LENGTH_LONG).show();
    }

    private void id(){
        edtTen=findViewById(R.id.edtTen);
        edtSS=findViewById(R.id.edtSS);
        btnLuu=findViewById(R.id.btnLuu);
    }

}
