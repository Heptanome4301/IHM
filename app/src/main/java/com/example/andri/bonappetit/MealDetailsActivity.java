package com.example.andri.bonappetit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MealDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        RatingBar ratingBar = (RatingBar)findViewById(R.id.rating_bar);
        ratingBar.setRating(intent.getFloatExtra("rating", 0));
        final CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(intent.getCharSequenceExtra("title"));

        TextView seats = (TextView)findViewById(R.id.seats);
        seats.setText(intent.getIntExtra("seatsAvi", 0)+" places sur "+intent.getIntExtra("totalSeats", 1));
        TextView date = (TextView)findViewById(R.id.date);
        date.setText(intent.getCharSequenceExtra("date"));
        TextView location = (TextView)findViewById(R.id.location);
        location.setText(intent.getCharSequenceExtra("location"));
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(intent.getCharSequenceExtra("snippet"));

        TextView price = (TextView)findViewById(R.id.price);
        DecimalFormat df = new DecimalFormat("#.#");
        price.setText(df.format(intent.getFloatExtra("price",1))+"€");




        ImageView image = (ImageView)findViewById(R.id.backdrop);
        Bitmap myBitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette palette = Palette.generate(myBitmap);
            toolbarLayout.setContentScrimColor(palette.getMutedColor(R.color.colorPrimary));
            toolbarLayout.setStatusBarScrimColor(palette.getDarkVibrantColor(R.color.colorPrimaryDark));
        }







    }
}
