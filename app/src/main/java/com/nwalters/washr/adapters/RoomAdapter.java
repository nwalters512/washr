package com.nwalters.washr.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nwalters.washr.R;
import com.nwalters.washr.models.Room;

import java.util.List;

public class RoomAdapter extends ListAdapter<Room> {

    public RoomAdapter(Context context, List<Room> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_room, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mRoomName.setText(mList.get(position).name);
        return view;
    }

    private class ViewHolder {
        public TextView mRoomName;

        public ViewHolder(View view) {
            mRoomName = (TextView) view.findViewById(R.id.room_name);
        }
    }
}
