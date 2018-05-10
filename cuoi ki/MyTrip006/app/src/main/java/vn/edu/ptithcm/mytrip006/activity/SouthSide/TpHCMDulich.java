package vn.edu.ptithcm.mytrip006.activity.SouthSide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.edu.ptithcm.mytrip006.R;

public class TpHCMDulich extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diadiem_hochiminh_dulich);
        Button anuong = (Button) findViewById(R.id.anuongg);
        anuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TpHCMDulich.this , Tphcm.class );
                startActivity(intent);
                finish();
            }
        });
    }
}
