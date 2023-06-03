package com.hieunguyen.fpoly.lab5_ph32165;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityBai2 extends AppCompatActivity {
    Button btThemMoi;
    ListView lstview;
    ArrayList<SinhVienModel> lst = new ArrayList<>();

    ActivityResultLauncher<Intent> nhan = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String cs = b.getString(ActivityAddOrEditSV.KEY_COSO);
                        //Log.d("coso", "nhan " + cs);
                        String ten = b.getString(ActivityAddOrEditSV.KEY_TEN_SV);
                        String dc = b.getString(ActivityAddOrEditSV.KEY_DIA_CHI);
                        lst.add(new SinhVienModel(cs, ten, dc));
                        fill();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        btThemMoi = findViewById(R.id.btThemMoi);
        lstview = findViewById(R.id.lstview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        lst.add(new SinhVienModel("FPoly Hà Nội", "Nguyễn Văn Dũng", "Lào Cai"));
        lst.add(new SinhVienModel("FPoly Đà Nẵng", "Nguyễn Tiến Anh", "Quảng Nam"));
        lst.add(new SinhVienModel("FPoly Tây Nguyên", "Trần Tiến Đạt", "Đăk Lăk"));
        fill();

        btThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityBai2.this, ActivityAddOrEditSV.class);
                nhan.launch(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quanlysv_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.menu_add_sv) {
            Intent i = new Intent(ActivityBai2.this, ActivityAddOrEditSV.class);
            nhan.launch(i);
        } else if (item.getItemId() == R.id.menu_dang_xuat) {
            onBackPressed();
        } else if (item.getItemId() == R.id.menu_bang_diem) {
            Toast.makeText(this, "Bang diem", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_diem_danh) {
            Toast.makeText(this, "Diem danh", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_search) {
            //Toast.makeText(this, "Bang diem", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void fill() {
        SinhVienAdapter adapter = new SinhVienAdapter(lst, ActivityBai2.this);
        lstview.setAdapter(adapter);

//        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                updateSV(i);
//            }
//        });
    }

    public void deleteSV(int index) {
        lst.remove(index);
        fill();
    }

    public static final String KEY_SV_MODEL = "sv_model";

    ActivityResultLauncher<Intent> goToEditSV = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String cs = b.getString(ActivityAddOrEditSV.KEY_COSO);
                        //Log.d("coso", "nhan " + cs);
                        String ten = b.getString(ActivityAddOrEditSV.KEY_TEN_SV);
                        String dc = b.getString(ActivityAddOrEditSV.KEY_DIA_CHI);

                        svModel.hoTen = ten;
                        svModel.diaChi = dc;
                        svModel.coSo = cs;

                        //lst.add(new SinhVienModel(cs, ten, dc));
                        fill();
                    }
                }
            }
    );

    private SinhVienModel svModel;

    public void updateSV(int position) {

        Intent intent = new Intent(ActivityBai2.this, ActivityAddOrEditSV.class);

        svModel = lst.get(position);
        intent.putExtra(KEY_SV_MODEL, svModel);


        goToEditSV.launch(intent);


    }
}
