package com.hieunguyen.fpoly.lab5_ph32165;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListSVAdapter extends BaseAdapter {
    ArrayList<ListSV> list;
    Context c;

    public ListSVAdapter(ArrayList<ListSV> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = ((Bai2)c).getLayoutInflater();
        convertView = inf.inflate(R.layout.item_listview,null);

        TextView cs = convertView.findViewById(R.id.tvCS);
        TextView name = convertView.findViewById(R.id.tvName);
        TextView dc = convertView.findViewById(R.id.tvDc);
        Button btupdate = convertView.findViewById(R.id.btUpdate);
        Button btdelete = convertView.findViewById(R.id.btDelete);

        cs.setText(list.get(position).coSo);
        name.setText(list.get(position).hoTen);
        dc.setText(list.get(position).diaChi);

        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Bai2)c).deleteSV(position);
            }
        });

        return convertView;
    }
}
