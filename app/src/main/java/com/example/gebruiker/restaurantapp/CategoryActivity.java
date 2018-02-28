package com.example.gebruiker.restaurantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements CategoryResponseCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        RestaurantApiHelper helper = new RestaurantApiHelper(this);
        helper.getCategories();
    }

    @Override
    public void onResponseSuccess(ArrayList<?> categoryList) {

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, (ArrayList<String>) categoryList);
        OnCategoryClickedListener listener = new OnCategoryClickedListener();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);
    }


    private class OnCategoryClickedListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(CategoryActivity.this, MenuActivity.class);
            intent.putExtra("category", adapterView.getItemAtPosition(i).toString());
            startActivity(intent);
        }
    }
}