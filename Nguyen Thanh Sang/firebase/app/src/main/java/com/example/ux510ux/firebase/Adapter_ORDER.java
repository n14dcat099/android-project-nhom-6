package com.example.ux510ux.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by UX510UX on 21/04/2018.
 */

public class Adapter_ORDER extends ArrayAdapter {
    Context context;
    int layout;
    ArrayList<ORDER> arrayList;


    public Adapter_ORDER(@NonNull Context context, int resource, @NonNull  ArrayList<ORDER> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,parent, false);

        final TextView txtTen=convertView.findViewById(R.id.txtTen);
        final TextView txtGia=convertView.findViewById(R.id.txtSS);

        ORDER order =new ORDER(arrayList.get(position));
        txtTen.setText(order.getTenlop());
        txtGia.setText(String.valueOf( order.getSiSolop()));

        final ImageButton btnEdit = convertView.findViewById(R.id.btnEdit);
        final ImageButton btnDelete = convertView.findViewById(R.id.btnDelete);
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnDelete.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.VISIBLE);
                return false;
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit = new Intent(getContext(),SuaActivity.class);
                intentEdit.putExtra("CLASSNAME",txtTen.getText().toString());
                intentEdit.putExtra("AMOUNT",txtGia.getText().toString());
                btnDelete.setVisibility(View.INVISIBLE);
                btnEdit.setVisibility(View.INVISIBLE);
                getContext().startActivity(intentEdit);
                Activity currentActivity = (Activity) getContext();
                currentActivity.finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("ORDER");
                myRef.child(txtTen.getText().toString()).removeValue();
                Toast.makeText(getContext(),"Xoá Thành Công",Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

}

