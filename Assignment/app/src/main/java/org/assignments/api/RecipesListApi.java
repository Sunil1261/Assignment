package org.assignments.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.assignments.utils.Constants;
import org.assignments.ModelClasses.RecipesBeen;
import org.assignments.interfaces.RecipesInterface;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunil on 23/12/17.
 */

public class RecipesListApi
{
    public static void recipesListApi(final RecipesInterface customer, final Activity activity, String ingredients,String searchKey,  final int pageCount, final ProgressDialog progressDialog) {
        String recipesUrl = Constants.RECIPESAPI +  "?i=" + ingredients + "&q=" + searchKey + "&p=" + pageCount;
        progressDialog.show();
        progressDialog.setMessage("Fetching Recipes list");
        Log.v("the url is","the urlis...."+recipesUrl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, recipesUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progressDialog.cancel();
                try {
                    JSONObject object=new JSONObject(response);

                    Gson cusGson = new Gson();
                    RecipesBeen customerList = cusGson.fromJson(response, RecipesBeen.class);
                    if (customerList.getResults().size()>0) {
                        customer.getRecipesList(response);
                        Log.v("the response is","the response is"+response);
                    }
                    else {
                        customer.getRecipesList("");
                    }

                } catch (Exception ex)
                {
                    Toast.makeText(activity,"Some thing wrong, Please check your internet",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.cancel();
                customer.getRecipesList("");
                NetworkResponse networkResponse = error.networkResponse;

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> mHeaderPart = new HashMap<>();
                mHeaderPart.put("Content-Type", "application/x-www-form-urlencoded");
                return mHeaderPart;
            }
        };
        Volley.newRequestQueue(activity).add(stringRequest);
    }

}
