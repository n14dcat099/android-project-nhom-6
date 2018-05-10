package vn.edu.ptithcm.mytrip006.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import vn.edu.ptithcm.mytrip006.R;


public class HelpActivity extends AppCompatActivity {
    private Button btnHelp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ActionBar actionBar = getSupportActionBar();
        SharedPreferences pref = getSharedPreferences("sharedSettings", 0);
        String setColor = pref.getString("color", "Default");
        Setting.settings("Trợ giúp", setColor, actionBar, getApplicationContext());

        btnHelp = findViewById(R.id.back1);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        final LinearLayout[] rsHelp = new LinearLayout[4];
        rsHelp[1] = findViewById(R.id.rsHelp_1);
        rsHelp[2] = findViewById(R.id.rsHelp_2);
        rsHelp[3] = findViewById(R.id.rsHelp_3);



        LinearLayout[] showResults = new LinearLayout[4];
        showResults[1] = findViewById(R.id.help_1);
        showResults[2] = findViewById(R.id.help_2);
        showResults[3] = findViewById(R.id.help_3);

        final boolean[] flagShow = new boolean[4];

        for (int i = 1; i < 4; i++) {
            final int getHelp = i;
            showResults[getHelp].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!flagShow[getHelp]) {
                        rsHelp[getHelp].setVisibility(View.VISIBLE);
                        flagShow[getHelp] = true;
                    } else {
                        rsHelp[getHelp].setVisibility(View.GONE);
                        flagShow[getHelp] = false;
                    }
                }
            });
        }
    }


}
