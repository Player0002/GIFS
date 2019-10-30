package com.players.gif.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.players.gif.R;
import com.players.gif.item.SubtitleItem;

public class SubtitleItemView extends LinearLayout {
    TextView name, inside;

    public SubtitleItemView(Context context) {
        super(context);
        init(context);
    }
    public SubtitleItemView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.subtitle, this, true);

        name = findViewById(R.id.textView1);
        inside = findViewById(R.id.textView2);

    }
    public void setName(String name){
        this.name.setText(name);
    }
    public void setInside(String inside){
        this.inside.setText(inside);
    }
}
