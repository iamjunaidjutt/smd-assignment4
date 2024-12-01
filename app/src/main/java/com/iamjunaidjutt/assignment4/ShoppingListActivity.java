package com.iamjunaidjutt.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShoppingListAdapter adapter;
    DatabaseReference shoppingItemsRef;
    private FloatingActionButton fabAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        recyclerView = findViewById(R.id.recyclerView);
        fabAddItem = findViewById(R.id.fab_add_item);

        shoppingItemsRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("shopping_items");

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("shopping_items");

        FirebaseRecyclerOptions<ShoppingItem> options = new FirebaseRecyclerOptions.Builder<ShoppingItem>()
                .setQuery(query, ShoppingItem.class)
                .build();

        if (adapter == null) {
            adapter = new ShoppingListAdapter(options, shoppingItemsRef);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }

        fabAddItem.setOnClickListener(v -> openAddItemForm());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    private void openAddItemForm() {
        Intent intent = new Intent(ShoppingListActivity.this, AddItemActivity.class);
        startActivity(intent);
    }

    public void onLogoutClick(View view) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        Intent intent = new Intent(ShoppingListActivity.this, LoginActivity.class);
        startActivity(intent);

        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

}
