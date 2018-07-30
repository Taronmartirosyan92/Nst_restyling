package com.example.armen.mynoutbook.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.armen.mynoutbook.R;
import com.example.armen.mynoutbook.providers.PrProvider;
import com.squareup.picasso.Picasso;

public class ImgPagerAdapter extends PagerAdapter {

    private Context context;
    private String[] array = PrProvider.getItemPosition().getImageUrl();

    public ImgPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_pager_img, container, false);
        ImageView image = view.findViewById(R.id.image_for_pager);
        Picasso.get().load(array[position]).into(image);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
