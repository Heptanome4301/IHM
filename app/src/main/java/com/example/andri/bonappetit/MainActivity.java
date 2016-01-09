package com.example.andri.bonappetit;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.andri.bonappetit.dummy.RestaurantContent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        RestaurantsListFragment.OnFragmentInteractionListener,
        MealFragment.OnListFragmentInteractionListener,
        OnMapReadyCallback{

    private MealFragment mealFragment;
    private RestaurantsListFragment dummyFragment;
    private MapFragment mapFragment;
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            RestaurantsListFragment fragment = new RestaurantsListFragment();
            dummyFragment = fragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.add(R.id.fragment_container, dummyFragment);
            transaction.commit();


        }
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent, 0);
                Snackbar.make(view, "Repas crée avec succès (en vrai c'est pas vrai)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the MainActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.menu_filter) {
            return true;
        } else if (id == R.id.menu_sort) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {

            fab.setVisibility(View.GONE);

            if (mapFragment == null)
                mapFragment = MapFragment.newInstance();
            FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, mapFragment);
            fragmentTransaction.commit();

            mapFragment.getMapAsync(this);

        } else if (id == R.id.nav_user_meal) {
            if (mealFragment == null)
                mealFragment = new MealFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mealFragment).commit();
            fab.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_resto) {
            if (dummyFragment == null)
                dummyFragment = new RestaurantsListFragment();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.replace(R.id.fragment_container, dummyFragment);
            transaction.addToBackStack(null);
            transaction.commit();

            fab.setVisibility(View.GONE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(RestaurantContent.RestaurantItem item) {
        Intent intent = new Intent(MainActivity.this,MealDetailsActivity.class);
        intent.putExtra("title",item.title);
        intent.putExtra("date",item.date);
        intent.putExtra("rating",item.rating);
        intent.putExtra("id",item.id);
        intent.putExtra("location",item.location);
        intent.putExtra("snippet",item.snippet);
        intent.putExtra("seatsAvi",item.seatsAvi);
        intent.putExtra("totalSeats",item.totalSeats);
        intent.putExtra("price",item.price);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        final LatLng BEURK = new LatLng(45.781038, 4.873533);
        final LatLng RESTO_U = new LatLng(45.780917, 4.876201);
        final LatLng CHEZ_PATRICK = new LatLng(45.784489, 4.872739);

        // add POIs
        Marker marker1 = mMap.addMarker(new MarkerOptions()
                .position(BEURK)
                .title("Le beurk")
                .snippet("Un restaurant bien sympa (xd)"));
        marker1.showInfoWindow();

        Marker marker2 = mMap.addMarker(new MarkerOptions()
                .position(RESTO_U)
                .title("Restaurant U")
                .snippet("c'est pas mal"));

        Marker marker3 = mMap.addMarker(new MarkerOptions()
                .position(CHEZ_PATRICK)
                .title("Couscous chez Patrick")
                .snippet("c'est pas mal"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BEURK, 15));
    }
}
