package com.example.koman.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.koman.myapplication.code.connection;
import com.example.koman.myapplication.code.deathnote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private static final int MENU_VIEW_TARGET = 111;
    private static final int MENU_EDIT_TARGET = 222;
    private static final int MENU_CREATE_TARGET = 333;
    private static final int MENU_DELETE_TARGET = 444;

    private static final int REQUEST_CODE = 1000;
    private ArrayAdapter<deathnote> listViewAdapter;
    private final List<deathnote> victimList = new ArrayList<deathnote>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Listview from XML
        listView = (ListView)findViewById(R.id.listView);
        connection db = new connection(this);
        //First time use
        db.createReadme();

        List<deathnote> list = db.getAllTarget();
        this.victimList.addAll(list);

        //Adapter include:
        //1. Context
        //2. Layout for every line
        //3. ID of textView that the data will be written
        //4. Data list
        this.listViewAdapter = new ArrayAdapter<deathnote>(this,android.R.layout.simple_list_item_1,android.R.id.text1,this.victimList);

        //Register Adapter for ListView
        this.listView.setAdapter(this.listViewAdapter);

        //Register Context menu for ListView
        registerForContextMenu(this.listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo)    {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("What do you want today?");

        // groupId, itemId, order, title
        menu.add(0, MENU_VIEW_TARGET , 0, "Reason why he is on the lít");
        menu.add(0, MENU_EDIT_TARGET , 1, "Wanna change the reason?");
        menu.add(0, MENU_CREATE_TARGET , 2, "Write the target to the list");
        menu.add(0, MENU_DELETE_TARGET, 4, "Do the target deserve to live?");
    }

    @Override
    public boolean onContextItemSelected(MenuItem items)
    {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) items.getMenuInfo();

        final deathnote selectedTarget = (deathnote)this.listView.getItemAtPosition(info.position);
        //User touch VIEW
        if(items.getItemId() == MENU_VIEW_TARGET)
        {
            Toast.makeText(getApplicationContext(),selectedTarget.getReason(),Toast.LENGTH_LONG).show();
        }
        //User touch CREATE
        else if (items.getItemId() == MENU_EDIT_TARGET)
        {
            Intent intent = new Intent(this,deathnote_function.class);
            intent.putExtra("Death list",selectedTarget);
            //Start function if got responsed.
            this.startActivityForResult(intent,REQUEST_CODE);
        }
        //User touch EDIT
        else if ( items.getItemId() == MENU_CREATE_TARGET )
        {
            Intent intent = new Intent( this , deathnote_function.class);
            //Start function if got responsed.
            this.startActivityForResult(intent,REQUEST_CODE);
        }
        //User touch DELETE
        else if ( items.getItemId() == MENU_DELETE_TARGET )
        {
            //Ask before deleting.
            new AlertDialog.Builder(this).setMessage("Are you sure you want to delete this target?").setCancelable(false).setPositiveButton("Yes, he deserves it.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int id) {
                    deleteTarget(selectedTarget);
                }
            }).setNegativeButton("No, he don't deserve it",null).show();
        }
        else
        {
            return false;
        }
        return true;
    }

    private void deleteTarget(deathnote target)
    {
        connection db = new connection(this);
        db.deleteTarget(target);
        this.victimList.remove(target);
        //Refresh listView
        this.listViewAdapter.notifyDataSetChanged();
    }

    //When deathnote_fuction finish ( if started ) , send response to user
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data)
    {
        if(resultCode== Activity.RESULT_OK && requestCode == REQUEST_CODE)
        {
            boolean needRefresh = data.getBooleanExtra("Please refresh.",true);
            //Refresh Listview
            if(needRefresh)
            {
                this.victimList.clear();
                connection db = new connection(this);
                List<deathnote> list = db.getAllTarget();
                this.victimList.addAll(list);
                //Notify data changed to user
                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }

}
