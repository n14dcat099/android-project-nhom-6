package com.example.koman.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.koman.myapplication.code.connection;
import com.example.koman.myapplication.code.deathnote;

public class deathnote_function extends AppCompatActivity {

    deathnote target;
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private int mode;
    private EditText textName;
    private EditText textReason;
    private boolean needRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deathnote_function);

        this.textName = (EditText)this.findViewById(R.id.death_name);
        this.textReason = (EditText)this.findViewById(R.id.death_reason);

        Intent intent = this.getIntent();
        this.target = (deathnote)intent.getSerializableExtra("Death list");
        if (target == null)
        {
            this.mode = MODE_CREATE;
        }
        else
        {
            this.mode = MODE_EDIT;
            this.textName.setText(target.getName());
            this.textReason.setText(target.getReason());
        }
    }

    //User touch Save button
    public void buttonSaveClicked(View view)
    {
        connection db = new connection(this);
        String name = this.textName.getText().toString();
        String reason = this.textReason.getText().toString();
        if ( name.isEmpty() || reason.isEmpty() )
        {
            Toast.makeText(getApplicationContext(),"Don't you know how to add a victim?",Toast.LENGTH_LONG).show();
            return;
        }

        if ( mode == MODE_CREATE)
        {
            this.target = new deathnote(name,reason);
            db.addTarget(target);
        }
        else
        {
            this.target.setName(name);
            this.target.setReason(reason);
            db.updateTarget(target);
        }

        this.needRefresh = true;
        //Back to MainActivity
        this.onBackPressed();
    }

    //User touch cancel button
    public void buttonCancelClicked(View view)
    {
        this.onBackPressed();
    }

    //When an activity is finished
    public void finish()
    {
        Intent data = new Intent();
        //Request to refresh
        data.putExtra("Please refresh",needRefresh);
        this.setResult(Activity.RESULT_OK,data);
        super.finish();
    }

}
