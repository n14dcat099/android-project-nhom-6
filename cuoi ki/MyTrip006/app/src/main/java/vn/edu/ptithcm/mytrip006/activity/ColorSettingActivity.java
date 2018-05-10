package vn.edu.ptithcm.mytrip006.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import vn.edu.ptithcm.mytrip006.R;


public class ColorSettingActivity extends AppCompatActivity {
    private Button btnColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_setting_activity);
        RadioGroup radGroup = findViewById(R.id.chooseGroup);
        RadioButton radDefault = findViewById(R.id.chooseDefault);
        RadioButton radGreen = findViewById(R.id.chooseGreen);
        RadioButton radPink = findViewById(R.id.choosePink);
        RadioButton radOrange = findViewById(R.id.chooseOrange);
        RadioButton radBlue = findViewById(R.id.chooseBlue);

        final ActionBar actionBar = getSupportActionBar();
        final SharedPreferences pref = getSharedPreferences("sharedSettings", 0);
        String setColor = pref.getString("color", "Default");
     //   Boolean checkNotify = pref.getBoolean("notify", true);


        Setting.settings("Cài đặt", setColor, actionBar, getApplicationContext());
        switch (setColor) {
            case "Default":
                radDefault.setChecked(true);
                break;
            case "Green":
                radGreen.setChecked(true);
                break;
            case "Pink":
                radPink.setChecked(true);
                break;
            case "Orange":
                radOrange.setChecked(true);
                break;
            case "Blue":
                radBlue.setChecked(true);
                break;
            default:
                break;
        }


        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                SharedPreferences.Editor editor = pref.edit();
                switch (i) {
                    case R.id.chooseDefault:
                        editor.putString("color", "Default");
                        break;
                    case R.id.chooseGreen:
                        editor.putString("color", "Green");
                        break;
                    case R.id.choosePink:
                        editor.putString("color", "Pink");
                        break;
                    case R.id.chooseOrange:
                        editor.putString("color", "Orange");
                        break;
                    case R.id.chooseBlue:
                        editor.putString("color", "Blue");
                        break;
                    default:
                        break;
                }
                editor.commit();
               String setColor = pref.getString("color", "Default");
                Setting.settings("Tùy Chỉnh", setColor, actionBar, getApplicationContext());


            }
        });
        btnColor = findViewById(R.id.back2);
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent color = new Intent(ColorSettingActivity.this, MainActivity.class);
                startActivity(color);
                finish();
            }
        });
    }

}
