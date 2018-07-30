package com.example.armen.mynoutbook.adapters;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.armen.mynoutbook.R;
import com.example.armen.mynoutbook.activity.InfoActivity;
import com.example.armen.mynoutbook.dialogFragments.PagerFragmentDialog;
import com.squareup.picasso.Picasso;

public class InfoImageAdapter extends RecyclerView.Adapter<InfoImageAdapter.ImageViewHolder> {
    private static final String PAGER_DIALOG = "dialog";
    private Context context;
    private String [] urls;

    public InfoImageAdapter(Context context, String [] urls) {
        this.context = context;
        this.urls = urls;
    }
    @NonNull
    @Override
    public InfoImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View view = inflater.inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final InfoImageAdapter.ImageViewHolder holder, final int position) {
        Picasso.get().load(urls[position]).into(holder.img);
    }
    @Override
    public int getItemCount() {
        return urls.length;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        ImageViewHolder(View itemView) {
            super(itemView);
          final FragmentManager ft = (((InfoActivity)context)).getFragmentManager();
            img = itemView.findViewById(R.id.image_list_view);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = new PagerFragmentDialog();
                    Bundle bundle = new Bundle();
                    bundle.putInt("kay",getAdapterPosition());
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(ft,PAGER_DIALOG);
                }
            });
        }
    }
}
