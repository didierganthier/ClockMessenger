package com.example.clockmessenger.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clockmessenger.Model.Item;
import com.example.clockmessenger.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Item> itemList;

    public ItemAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.eventName.setText(item.getEventName());
        holder.eventDate.setText(item.getEventDate());
        holder.eventMessage.setText(item.getEventMessage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView eventName, eventDate, eventMessage;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            eventName = itemView.findViewById(R.id.eventName);
            eventDate = itemView.findViewById(R.id.eventDate);
            eventMessage = itemView.findViewById(R.id.eventMessage);
        }
    }
}
