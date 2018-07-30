package com.example.armen.mynoutbook.dialogFragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.armen.mynoutbook.R;
import com.example.armen.mynoutbook.adapters.ImgPagerAdapter;

public class PagerFragmentDialog extends DialogFragment {
    private static int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            position = getArguments().getInt("kay");
        }
        View v = inflater.inflate(R.layout.view_pager_item, container, false);
        final ViewPager viewPager = v.findViewById(R.id.view_pager);
        ImgPagerAdapter imgPagerAdapter = new ImgPagerAdapter(getDialog().getContext());
        viewPager.setAdapter(imgPagerAdapter);
        viewPager.setCurrentItem(position);
        TabLayout tabLayout = v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

        return v;
    }
}
