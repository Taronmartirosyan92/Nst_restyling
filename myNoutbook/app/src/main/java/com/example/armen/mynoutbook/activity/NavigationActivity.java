package com.example.armen.mynoutbook.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.armen.mynoutbook.CategoryEnum.Category;
import com.example.armen.mynoutbook.R;
import com.example.armen.mynoutbook.fragments.ProductFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        ProductFragment productFragment =
                (ProductFragment) getSupportFragmentManager().findFragmentById(R.id.fe_fragment);
        switch (id) {
            case R.id.home:
                productFragment.updateAdapter();
                break;
            case R.id.msi:
                productFragment.upDaListData(Category.MSI);
                break;
            case R.id.acer:
                productFragment.upDaListData(Category.PREDATOR);
                break;
            case R.id.asus:
                productFragment.upDaListData(Category.ROG);
                break;
            case R.id.gigabyte:
                productFragment.upDaListData(Category.GIG);
                break;
            case R.id.apple:
                productFragment.upDaListData(Category.APPLE);
                break;
            case R.id.razer_blade:
                productFragment.upDaListData(Category.RAZER);
                break;
            case R.id.shopping_card:
                productFragment.setListByCard();
                break;
            case R.id.nav_favorite:
                productFragment.setListByFavorite();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return onOptionsItemSelected(item);
    }
}
