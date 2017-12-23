package org.assignments.activity

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import org.assignments.ModelClasses.RecipesBeen
import org.assignments.R
import org.assignments.adapters.ProductsListAdapter
import org.assignments.api.RecipesListApi
import org.assignments.databinding.ActivityMainBinding
import org.assignments.interfaces.RecipesInterface
import org.assignments.utils.HideKeyboard


class MainActivity : RecipesInterface, AppCompatActivity() {
    override fun getRecipesList(recipesBeenList: String?) {
        if (!recipesBeenList!!.isEmpty())
            setAdapter(recipesBeenList)
        Log.v("the recipes list is", "the recipes..." + recipesBeenList)
    }

    internal lateinit var activityMainBinding: ActivityMainBinding
    private var mLinearLayoutManager: LinearLayoutManager? = null
    var page = 1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mLinearLayoutManager = GridLayoutManager(applicationContext, 2)
        var dialog = ProgressDialog(this)
        dialog.setMessage("Please wait")
        dialog.setTitle("Loading")
        dialog.setCancelable(false)
        dialog.isIndeterminate = true
        activityMainBinding.getRecipes.setOnClickListener(View.OnClickListener
        {
            HideKeyboard.hideKeyboard(this)
            page = 1
            RecipesListApi.recipesListApi(this, this, activityMainBinding.ingredients.text.toString(), activityMainBinding.searchQuery.text.toString(), page, dialog)

        })
        activityMainBinding.next.setOnClickListener(View.OnClickListener
        {
            page++;
            RecipesListApi.recipesListApi(this, this, activityMainBinding.ingredients.text.toString(), activityMainBinding.searchQuery.text.toString(), page, dialog)

        })
        activityMainBinding.previous.setOnClickListener(View.OnClickListener
        {
            page--;
            RecipesListApi.recipesListApi(this, this, activityMainBinding.ingredients.text.toString(), activityMainBinding.searchQuery.text.toString(), page, dialog)

        })
        if (page == 1) {
            activityMainBinding.previous.visibility = View.GONE
            activityMainBinding.next.visibility = View.GONE
        } else {
            activityMainBinding.previous.visibility = View.VISIBLE
            activityMainBinding.next.visibility = View.VISIBLE
        }
    }

    fun setAdapter(recipes: String?) {
        if (page == 1) {
            activityMainBinding.previous.visibility = View.GONE
        } else {
            activityMainBinding.previous.visibility = View.VISIBLE
        }
        activityMainBinding.next.visibility = View.VISIBLE

        val gson = Gson()
        val recipesList = gson.fromJson<RecipesBeen>(recipes, RecipesBeen::class.java!!)
        val recipesListBeen = recipesList.results;
        if (recipesListBeen.size > 0) {
            var adapter = ProductsListAdapter(this, recipesListBeen);
            activityMainBinding.recipesList.setLayoutManager(mLinearLayoutManager)
            activityMainBinding.recipesList.smoothScrollBy(50, 50)
            activityMainBinding.recipesList.setHasFixedSize(true)
            activityMainBinding.recipesList.setAdapter(adapter)
        } else {
            Toast.makeText(this, "Receipe list is empty", Toast.LENGTH_LONG).show()

        }
    }

}



