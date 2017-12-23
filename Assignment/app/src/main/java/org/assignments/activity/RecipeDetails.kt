package org.assignments.activity

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import org.assignments.ModelClasses.Result
import org.assignments.R
import org.assignments.databinding.RecipeDetailsBinding;
import org.assignments.utils.Utilities

/**
 * Created by mahiti on 23/12/17.
 */

class RecipeDetails : Activity() {
    internal lateinit var detailsBinding: RecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.recipe_details)
        var detailsIntent = intent;
        var recipeDetails = detailsIntent.getParcelableExtra<Result>("recipeListDetails");
        if (recipeDetails.thumbnail != null && !recipeDetails.thumbnail.isEmpty())
            Utilities.loadImage(recipeDetails.thumbnail, detailsBinding.image, this)
        else
            detailsBinding.image.setDefaultImageResId(R.drawable.placeholder)
        detailsBinding.title.setText("Title: " + recipeDetails.title)
        detailsBinding.details.setText("Description: " + recipeDetails.ingredients)

    }
}
