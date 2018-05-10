package vn.edu.ptithcm.giuaki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnThem,btnXoa,btnSua;
    ListView listView;
    ArrayList<DienThoai> arrayList;
    DatabaseReference myRef;
    Adapter_DienThoai adapterDienThoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DienThoai");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ThemActivity.class);
                startActivity(intent);
            }
        });

        arrayList=new ArrayList<>(); // Khởi tạo danh sách điện thoại.
        adapterDienThoai=new Adapter_DienThoai(MainActivity.this,R.layout.layout_item_dienthoai,arrayList);
        // MainActivity.this là màn hình hiển thị
        //R.layout.layout_item_dienthoai là custom layout
        // arrayList: cho du lieu vao adapter để nó vẽ ra listview

        listView.setAdapter(adapterDienThoai); // đặt adapter cho listview

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DienThoai dienThoai = new DienThoai(dataSnapshot.getKey().toString(),Integer.parseInt(dataSnapshot.getValue().toString()));
                arrayList.add(dienThoai);
                adapterDienThoai.notifyDataSetChanged(); // thông báo cho adapter biết dữ liệu đã thay dổi -> vẽ lại listview
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for(int i = 0; i < arrayList.size(); i++){
                    if (arrayList.get(i).getTenDT() == key){
                        arrayList.remove(i);
                        break;
                    }
                }
                adapterDienThoai.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void doc(){

    }

    private void id(){
        btnThem=findViewById(R.id.btnThem);
//        btnSua=findViewById(R.id.btnSua);
//        btnXoa=findViewById(R.id.btnXoa);
        listView=findViewById(R.id.lst);
    }
}
