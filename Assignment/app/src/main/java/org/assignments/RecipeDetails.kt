package org.assignments

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import org.assignments.ModelClasses.Result
import org.assignments.databinding.RecipeDetailsBinding;

/**
 * Created by mahiti on 23/12/17.
 */

class RecipeDetails : Activity() {
    internal lateinit var detailsBinding: RecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.recipe_details)
        var detailsIntent=intent;
        var recipeDetails=detailsIntent.getParcelableExtra<Result>("recipeListDetails");
        Utilities.loadImage(recipeDetails.thumbnail,detailsBinding.image, this)
        detailsBinding.title.setText("Title:"+recipeDetails.title)
        detailsBinding.details.setText("Description"+recipeDetails.ingredients)

    }
}
