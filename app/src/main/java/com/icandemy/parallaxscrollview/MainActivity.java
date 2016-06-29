package com.icandemy.parallaxscrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ParallaxScrollView parallaxScrollView;
    private Toolbar toolbar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.bar_toolbar);
        imageView = (ImageView) findViewById(R.id.img_example);
        parallaxScrollView = (ParallaxScrollView) findViewById(R.id.icandemy_scroller);

        parallaxScrollView.setScrollViewControl(toolbar, imageView);
    }
}
