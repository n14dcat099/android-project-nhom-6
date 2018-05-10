package vn.edu.ptithcm.mytrip006.activity.SouthSide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.edu.ptithcm.mytrip006.R;

public class Tphcm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diadiem_tphcm_anuong);

        Button dulich = (Button) findViewById(R.id.dulich);
        dulich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tphcm.this , TpHCMDulich.class );
                startActivity(intent);
                finish();
            }
        });
        Button chitiet = (Button) findViewById(R.id.chitiethcm);
        chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tphcm.this, Bunrieu.class);
                startActivity(intent);

            }
        });
/*        Button xoiga = (Button) findViewById(R.id.xoiga);
        xoiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Tphcm.this , )
            }
        });*/
    }
}
