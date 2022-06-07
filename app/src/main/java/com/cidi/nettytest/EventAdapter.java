package com.cidi.nettytest;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by CIDI zhengxuan on 2019/10/15
 * QQ:1309873105
 */
public class EventAdapter extends RecyclerView.Adapter<EventHolder> {

    private List<Event> data;

    public EventAdapter(List<Event> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event itemData=data.get(position);
        holder.setData(itemData);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
