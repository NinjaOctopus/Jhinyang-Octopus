/**
 *
 *  HomeAdapter.java
 *
 *  Created by Ashutosh Sharma on 18/15/18.
 *  Copyright © 2018 Jhinyang Food Ltd.  All rights reserved.
 *
 */

package jhinyang.octopus.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jhinyang.octopus.R;
import jhinyang.octopus.data.CookbookDTO;
import jhinyang.octopus.utils.GlideApp;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<CookbookDTO> cookbookDTOS;

    public HomeAdapter(List<CookbookDTO> cookbookDTOS) {
        this.cookbookDTOS = cookbookDTOS;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_rv_item,
                null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CookbookDTO cookbookDTO = cookbookDTOS.get(position);
        // Load image into imageview
        GlideApp.with(holder.itemView.getContext())
                .load(cookbookDTO.getImage())
                .apply(new RequestOptions()
                        .error(R.drawable.kissping)
                        .placeholder(R.drawable.kissping))
                .into(holder.imgRecipe);

        if(TextUtils.isEmpty(cookbookDTO.getName())) {
            holder.textRecipeName.setText("FOODIE");
        } else {
            holder.textRecipeName.setText(cookbookDTO.getName());
        }
        holder.textTarget.setText(holder.itemView.getContext().getResources().getString(R.string
                .recipe_target, cookbookDTO.getTarget()));
        holder.textContents.setText(holder.itemView.getContext().getResources().getString(R.string
                .recipe_contents, cookbookDTO.getFoodContents()));
        holder.textAptFor.setText(holder.itemView.getContext().getResources().getString(R.string
                .recipe_apt_for, cookbookDTO.getAptFor()));
        holder.textCategory.setText(holder.itemView.getContext().getResources().getString(R.string
                .recipe_category, cookbookDTO.getCategory()));
        holder.textPrice.setText(holder.itemView.getContext().getResources().getString(R.string
                        .recipe_price, cookbookDTO.getPrice()));
    }

    @Override
    public int getItemCount() {
        return cookbookDTOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_recipe_image) ImageView imgRecipe;
        @BindView(R.id.tv_recipe_name) TextView textRecipeName;
        @BindView(R.id.tv_target) TextView textTarget;
        @BindView(R.id.tv_apt_for) TextView textAptFor;
        @BindView(R.id.tv_category) TextView textCategory;
        @BindView(R.id.tv_price) TextView textPrice;
        @BindView(R.id.tv_contents) TextView textContents;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
