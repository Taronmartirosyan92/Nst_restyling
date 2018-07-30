package com.example.armen.mynoutbook.activity;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.armen.mynoutbook.R;
import com.example.armen.mynoutbook.adapters.InfoImageAdapter;
import com.example.armen.mynoutbook.dialogFragments.VideoDialogFragment;
import com.example.armen.mynoutbook.models.ProductModel;
import com.example.armen.mynoutbook.providers.PrProvider;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {
    private static final String VIDEO_DIALOG = "VIDEO DIALOG";
    ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        productModel = PrProvider.getItemPosition();
        final LinearLayoutManager layoutManager =
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL, false);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(productModel.getTitle());
        initializationRecycler(layoutManager);
        getProductData();
        getVideoDialog();
    }

    private void getProductData() {
        final TextView textView = findViewById(R.id.info_text);
        final RatingBar rab = findViewById(R.id.info_rb);
        final TextView rabInt = findViewById(R.id.info_rb_int);
        ImageView imageView = findViewById(R.id.image_title);
        Picasso.get()
                .load(productModel.getImageUrl()[0])
                .placeholder(R.drawable.ic_image_aspect_24dp)
                .into(imageView);
        rab.setRating(productModel.getRating());
        rabInt.setText(String.valueOf(productModel.getRating()));
        textView.setText(productModel.getDescription());
    }

    private void getVideoDialog() {
        final FragmentManager ft = getFragmentManager();
        ImageView videoImg = findViewById(R.id.imgVideo);
        videoImg.setImageResource(R.drawable.ic_play_circle_24dp);
        videoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new VideoDialogFragment();
                dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFragmentTheme);
                dialogFragment.show(ft, VIDEO_DIALOG);
            }
        });
    }

    private void initializationRecycler(LinearLayoutManager layoutManager) {
        InfoImageAdapter adapter =
                new InfoImageAdapter(InfoActivity.this, productModel.getImageUrl());
        RecyclerView recyclerView = findViewById(R.id.info_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
