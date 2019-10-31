package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.players.gif.DataManagers.UserInfo;

import java.text.SimpleDateFormat;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        View userProfileView = findViewById(R.id.post_profile_settings);

        ImageView userProfile_Img = userProfileView.findViewById(R.id.post_profile_settings_id);
        TextView userProfile_name = userProfileView.findViewById(R.id.post_profile_settings_username);
        TextView userProfile_when = userProfileView.findViewById(R.id.post_profile_settings_when);

        UserInfo info = UserInfo.getInstance();
        userProfile_name.setText(info.getUsername());
        userProfile_when.setText(new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분").format(info.getWhen()));
    }
}
