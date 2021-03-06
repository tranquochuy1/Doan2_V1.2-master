package com.example.pcc.doan2_v1.danhmuctin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.pcc.doan2_v1.R;
import com.example.pcc.doan2_v1.adapter.ListNewsActiveAdapter;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyTran on 11/30/2017.
 */

public class GiaiTrii extends AppCompatActivity {

    DatabaseReference databaseReference;
    List<Tintuc> listTinTuc=new ArrayList<>();
    RecyclerView recyclerView_DanhMucTin;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_rss);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quanly_danhmuc_tin);
        recyclerView_DanhMucTin=findViewById(R.id.recyclerView_DanhMucTin);
        recyclerView_DanhMucTin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        TextView txtDanhMuc= findViewById(R.id.txtDanhmuctin);
        txtDanhMuc.setText("Danh mục tin tức Giải trí");
        getDanhsachTin();

    }
    public void getDanhsachTin(){

        databaseReference= FirebaseDatabase.getInstance().getReference("Tintuc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listTinTuc.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    Tintuc tintuc= snapshot.getValue(Tintuc.class);
                    if(tintuc.getChude().equals("Giai tri")){
                        listTinTuc.add(tintuc);
                    }
                }
                ListNewsActiveAdapter adapterActive=new ListNewsActiveAdapter(getApplicationContext(),listTinTuc);
                recyclerView_DanhMucTin.setAdapter(adapterActive);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}