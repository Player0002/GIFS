package com.players.gif;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.players.gif.item.SubtitleItem;

import java.util.ArrayList;
class SubtitleViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView inside;

    public SubtitleViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textView1);
        inside=itemView.findViewById(R.id.textView2);
    }

    public void setName(String name){
        this.name.setText(name);
    }
    public void setInside(String inside){
        this.inside.setText(inside);
    }
}
public class SubtitleAdapter extends RecyclerView.Adapter {
    private ArrayList<SubtitleItem> items = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.subtitle, parent, false);
        SubtitleViewHolder holder = new SubtitleViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SubtitleViewHolder){
            SubtitleItem i = items.get(position);

            ((SubtitleViewHolder)holder).setInside(i.getInsideWrite());
            ((SubtitleViewHolder)holder).setName(i.getUsername());
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(){
        SubtitleItem item = new SubtitleItem();
        item.setInsideWrite("TEST");
        item.setUsername("USER");
        items.add(item);
    }
}
