package com.iamjunaidjutt.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends AppCompatActivity {

    private EditText editTextItemName, editTextItemQuantity, editTextItemPrice;
    private DatabaseReference shoppingItemsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextItemQuantity = findViewById(R.id.editTextItemQuantity);
        editTextItemPrice = findViewById(R.id.editTextItemPrice);

        shoppingItemsRef = FirebaseDatabase.getInstance().getReference("shopping_items");
    }

    public void onSaveClick(View view) {
        String name = editTextItemName.getText().toString();
        String quantity = editTextItemQuantity.getText().toString();
        String price = editTextItemPrice.getText().toString();

        if (name.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantityInt = Integer.parseInt(quantity);
        double priceDouble = Double.parseDouble(price);
        addItemToRealtimeDatabase(name, quantityInt, priceDouble);
    }

    private void addItemToRealtimeDatabase(String name, int quantity, double price) {
        String itemId = shoppingItemsRef.push().getKey();

        if (itemId != null) {
            ShoppingItem newItem = new ShoppingItem(name, quantity, price);
            shoppingItemsRef.child(itemId).setValue(newItem)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, ShoppingListActivity.class);
                        startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error adding item", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "Failed to generate item ID", Toast.LENGTH_SHORT).show();
        }
    }
}
