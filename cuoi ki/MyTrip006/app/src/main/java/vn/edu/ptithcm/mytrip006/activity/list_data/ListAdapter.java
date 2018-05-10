package vn.edu.ptithcm.mytrip006.activity.list_data;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import vn.edu.ptithcm.mytrip006.R;

/**
 * Created by koman on 5/5/2018.
 */

public class ListAdapter extends BaseAdapter {
    private Context context;
    private  String [] ten_list;
    private  String [] khu_vuc_list;
    private  String [] thanh_pho_list;
    private  String [] mota_list;
    private  String [] hinh_anh_list;
    private  String [] loai_hinh_list;

    private static class ViewHolder {

        TextView ten;
        TextView thanh_pho;
        TextView khu_vuc;
        TextView mota;
        TextView loai_hinh;
        ImageView hinh_anh;
    }

    public ListAdapter(Context context,String[] ten_list , String [] khu_vuc_list ,String [] thanh_pho_list,String [] hinh_anh_list,String [] mota_list,String [] loai_hinh_list)
    {
        this.context = context;
        this.ten_list = ten_list;
        this.khu_vuc_list = khu_vuc_list;
        this.thanh_pho_list = thanh_pho_list;
        this.mota_list = mota_list;
        this.hinh_anh_list = hinh_anh_list;
        this.loai_hinh_list = loai_hinh_list;
    }

    @Override
    public int getCount()
    {
        return ten_list.length;
    }
    @Override
    public Object getItem(int i)
    {
        return i;
    }
    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;
        final View result;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_list_data,parent,false);
            viewHolder.ten = (TextView) convertView.findViewById(R.id.ten);
            viewHolder.khu_vuc = (TextView) convertView.findViewById(R.id.khu_vuc);
            viewHolder.thanh_pho = (TextView) convertView.findViewById(R.id.thanh_pho);
            viewHolder.mota = (TextView) convertView.findViewById(R.id.mota);
            viewHolder.hinh_anh = (ImageView) convertView.findViewById(R.id.imgAnh);
            viewHolder.loai_hinh = (TextView) convertView.findViewById(R.id.loai_hinh);
            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.ten.setText(ten_list[position]);
        viewHolder.mota.setText(mota_list[position]);
        viewHolder.thanh_pho.setText(thanh_pho_list[position]);
        viewHolder.khu_vuc.setText(khu_vuc_list[position]);
        Glide.with(this.context).load(Uri.parse(hinh_anh_list[position])).into(viewHolder.hinh_anh);
        viewHolder.loai_hinh.setText(loai_hinh_list[position]);
        return convertView;
    }





}
