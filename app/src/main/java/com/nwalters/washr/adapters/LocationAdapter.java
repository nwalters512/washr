package com.nwalters.washr.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nwalters.washr.R;
import com.nwalters.washr.models.LocationInfo;

import java.util.List;

public class LocationAdapter extends ListAdapter<LocationInfo> {

    public LocationAdapter(Context context, List<LocationInfo> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_location, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mLocationName.setText(mList.get(position).name);
        return view;
    }

    private class ViewHolder {
        public TextView mLocationName;

        public ViewHolder(View view) {
            mLocationName = (TextView) view.findViewById(R.id.location_name);
        }
    }
}
