package com.example.armen.mynoutbook.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.armen.mynoutbook.R;
import com.example.armen.mynoutbook.activity.InfoActivity;
import com.example.armen.mynoutbook.models.ProductModel;
import com.example.armen.mynoutbook.providers.PrProvider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PrAdapter extends RecyclerView.Adapter<PrAdapter.ProductViewHolder> implements Filterable {
    private Context context;
    private List<ProductModel> list;
    private List<ProductModel> searchResList;

    public PrAdapter(Context context, List<ProductModel> list) {
        this.context = context;
        this.list = list;
        this.searchResList = list;
    }

    @NonNull
    @Override
    public PrAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return searchResList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final PrAdapter.ProductViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        final ProductModel prModel = searchResList.get(position);
        holder.title.setText(prModel.getTitle());
        holder.price.setText(prModel.getPrice());
        setButtonColors(holder, position);
        Picasso.get().load(prModel.getImageUrl()[0])
                .placeholder(R.drawable.ic_image_aspect_24dp).into(holder.image);
        allClickListeners(holder, position, prModel);

    }

    private void allClickListeners(@NonNull final ProductViewHolder holder,
                                   @SuppressLint("RecyclerView")
                                   final int position, final ProductModel prModel) {
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fab.setRippleColor(Color.rgb(255, 0, 0));
                holder.fab.setImageResource(R.drawable.ic_favorite_24dp);
                holder.fab.setImageResource(R.drawable.ic_favorite_press_24dp);
                prModel.setFavorite(true);
                holder.rab.setRating(5f);
                prModel.setRating(holder.rab.getNumStars());
                holder.rab.setIsIndicator(true);
                holder.barCount.setText(String.valueOf(prModel.getRating()));
            }
        });
        holder.fabBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fabBy.setImageResource(R.drawable.ic_shopping_cart_press_24dp);
                prModel.setCardBy(true);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDatePosition(searchResList, position);
                Intent intent = new Intent(context, InfoActivity.class);
                context.startActivity(intent);

            }
        });
    }

    private void setButtonColors(@NonNull ProductViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        if (searchResList.get(position).isFavorite()) {
            holder.fab.setImageResource(R.drawable.ic_favorite_press_24dp);
        }
        if (searchResList.get(position).isCardBy()) {
            holder.fabBy.setImageResource(R.drawable.ic_shopping_cart_press_24dp);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    searchResList = list;
                } else {
                    List<ProductModel> listForFilter = new ArrayList<>();
                    for (ProductModel model : list) {
                        if (model.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            listForFilter.add(model);
                        }
                    }
                    searchResList = listForFilter;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = searchResList;
                filterResults.count = searchResList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                searchResList = (List<ProductModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        ImageView image;
        FloatingActionButton fab;
        ImageButton fabBy;
        RatingBar rab;
        TextView barCount;

        ProductViewHolder(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.pr_title);
            price = itemView.findViewById(R.id.pr_prise);
            image = itemView.findViewById(R.id.pr_img);
            fab = itemView.findViewById(R.id.pr_favorite_fab);
            fabBy = itemView.findViewById(R.id.pr_fabBy);
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(context.getString(R.string.fab_back_color))));
            rab = itemView.findViewById(R.id.myRatingBar);
            barCount = itemView.findViewById(R.id.rating_int);
        }
    }
    private static void upDatePosition(List<ProductModel> list, int position) {
        for (int i = 0; i < PrProvider.prList.size(); i++) {
            if (PrProvider.prList.get(i).getTitle().equals(list.get(position).getTitle())) {
                PrProvider.position = i;
            }
        }
    }
}
