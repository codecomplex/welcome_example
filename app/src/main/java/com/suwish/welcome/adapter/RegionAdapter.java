package com.suwish.welcome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suwish.welcome.R;
import com.suwish.welcome.model.enity.CountryRegionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author min.su on 2016/9/28.
 */
public class RegionAdapter extends BaseAdapter{

    private Context context;

    private List<CountryRegionEntity> dataSource = new ArrayList<>();

    public RegionAdapter(Context context){
        this.context = context;
    }
    public void buildDefault(){
        CountryRegionEntity entity = new CountryRegionEntity();
        entity.setCode(-1);
        entity.setId(-1);
        entity.setDisplayName(context.getString(R.string.title_region_code));
        dataSource.add(entity);
        entity = new CountryRegionEntity();
        entity.setCode(-1);
        entity.setId(-1);
        entity.setDisplayName("-----");
        dataSource.add(entity);
    }

    public void setDataSource(List<CountryRegionEntity> list){
        dataSource.clear();
        dataSource.addAll(list);
    }
    @Override
    public int getCount() {
        return dataSource.size();
    }
    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CountryRegionEntity entity = (CountryRegionEntity)getItem(position);
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
            convertView.setTag(holder = new ViewHolder());
            holder.textView = (TextView)convertView.findViewById(android.R.id.text1);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(entity.getDisplayName());
        return convertView;
    }

    private static class ViewHolder{
        TextView textView;
    }
}
