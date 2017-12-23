package org.assignments.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.assignments.ModelClasses.RecipesBeen;
import org.assignments.ModelClasses.Result;
import org.assignments.R;
import org.assignments.RecipeDetails;
import org.assignments.Utilities;

import java.util.List;


/**
 * Created by mahiti on 22/4/17.
 */

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.CategoryViewHolder> {
    List<Result> data;
    private LayoutInflater inflater; //layout_home variable
    Activity context;
    boolean loading = true;

    /**
     * construcors.....
     *
     * @param context
     * @param data
     */
    public ProductsListAdapter(Activity context, List<Result> data) {
        this.context = context; // context
        inflater = LayoutInflater.from(context); // inflater
        this.data = data;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.snippet_product, parent, false); // adding the xml to view....
        // return the view...
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        holder.price.setVisibility(View.VISIBLE);
        holder.txtCategoryName.setText(data.get(position).getTitle());
        if (data.get(position).getThumbnail() != null && !data.get(position).getThumbnail().isEmpty())
            Utilities.loadImage(data.get(position).getThumbnail(), holder.imgCategory, context);
        else
            holder.imgCategory.setDefaultImageResId(R.drawable.placeholder);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeDetails.class);
                intent.putExtra("recipeListDetails",data.get(position));
                context.startActivity(intent);
            }
        });
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryName; // title variable
        NetworkImageView imgCategory;
        CardView cardView;
        RelativeLayout lyCategory;
        TextView price;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            // finding the ui elements
            txtCategoryName = (TextView) itemView.findViewById(R.id.txtCategoryName);
            imgCategory = (NetworkImageView) itemView.findViewById(R.id.imgCategory);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            price = (TextView) itemView.findViewById(R.id.txtPrice);
            lyCategory = (RelativeLayout) itemView.findViewById(R.id.lyCategory);
        }

    }

    public void setLoaded() {
        loading = false;
    }

}