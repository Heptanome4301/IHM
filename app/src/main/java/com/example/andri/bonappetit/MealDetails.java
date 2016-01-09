package com.example.andri.bonappetit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class MealDetails extends AppCompatActivity {

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
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(intent.getCharSequenceExtra("title"));

        TextView date = (TextView)findViewById(R.id.date);
        date.setText(intent.getCharSequenceExtra("date"));
        TextView location = (TextView)findViewById(R.id.location);
        location.setText(intent.getCharSequenceExtra("location"));
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(intent.getCharSequenceExtra("snippet"));






    }
}
