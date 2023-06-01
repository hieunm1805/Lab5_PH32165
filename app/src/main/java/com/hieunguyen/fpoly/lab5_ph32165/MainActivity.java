package com.hieunguyen.fpoly.lab5_ph32165;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner sp ;
    TextView edtName, edtAddress;
    Button btSubmit;

    ArrayList<School> lst = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = findViewById(R.id.sp);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        btSubmit = findViewById(R.id.btSubmit);

        lst.add(new School(R.drawable.hnpoly,"FPoly Hà Nội"));
        lst.add(new School(R.drawable.dnpoly,"FPoly Đà Nẵng"));
        lst.add(new School(R.drawable.tnpoly,"FPoly Tây Nguyên"));
        lst.add(new School(R.drawable.hcmpoly,"FPoly Hồ Chí Minh"));
        lst.add(new School(R.drawable.ctpoly,"FPoly Cần Thơ"));

        SchoolAdapter adapter = new SchoolAdapter(lst,MainActivity.this);
        sp.setAdapter(adapter);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index= sp.getSelectedItemPosition();
                String cs=lst.get(index).ten;

                String name = edtName.getText().toString();
                String adr = edtAddress.getText().toString();

                Intent i = new Intent();
                Bundle b = new Bundle();

                b.putString("coso",cs);
                Log.d("coso", cs);

                b.putString("ten",name);
                b.putString("diachi",adr);
                i.putExtras(b);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }
}