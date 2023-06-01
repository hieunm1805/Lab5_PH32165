package com.hieunguyen.fpoly.lab5_ph32165;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityBai2 extends AppCompatActivity {
    Button btThemMoi;
    ListView lstview;
    ArrayList<SinhVienModel> lst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        btThemMoi = findViewById(R.id.btThemMoi);
        lstview = findViewById(R.id.lstview);

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

    public void fill() {
        SinhVienAdapter adapter = new SinhVienAdapter(lst, ActivityBai2.this);
        lstview.setAdapter(adapter);
    }

    public void deleteSV(int index) {
        lst.remove(index);
        fill();
    }
}
