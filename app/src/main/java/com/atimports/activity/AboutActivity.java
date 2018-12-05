package com.atimports.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.atimports.R;
import com.atimports.utils.PhotoUtils;
import com.bumptech.glide.Glide;


public class AboutActivity extends AppCompatActivity {

    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ivSplash = findViewById(R.id.iv_splash);

        Glide.with(AboutActivity.this)
                .load(PhotoUtils.getURLForResource(R.drawable.logo_splash))
                .into(ivSplash);

    }




}
