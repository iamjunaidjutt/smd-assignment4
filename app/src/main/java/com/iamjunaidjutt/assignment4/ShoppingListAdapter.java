package com.iamjunaidjutt.assignment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class ShoppingListAdapter extends FirebaseRecyclerAdapter<ShoppingItem, ShoppingListAdapter.ShoppingItemViewHolder> {

    private DatabaseReference shoppingItemsRef;


    public ShoppingListAdapter(FirebaseRecyclerOptions<ShoppingItem> options, DatabaseReference shoppingItemsRef) {
        super(options);
        this.shoppingItemsRef = shoppingItemsRef;
    }

    @Override
    protected void onBindViewHolder(ShoppingItemViewHolder holder, int position, ShoppingItem model) {
        if (model != null) {
            holder.itemNameTextView.setText(model.getName());
            holder.itemQuantityTextView.setText("Quantity: " + model.getQuantity());
            holder.itemPriceTextView.setText("Price: " + model.getPrice());
        }


        holder.deleteButton.setOnClickListener(v -> {
            String itemId = getRef(position).getKey();
            if (itemId != null) {
                deleteItemFromDatabase(holder.itemView.getContext(), itemId);
            } else {
                Toast.makeText(holder.itemView.getContext(), "Error: Item ID is missing", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping, parent, false);
        return new ShoppingItemViewHolder(view);
    }


    private void deleteItemFromDatabase(Context context, String itemId) {
        shoppingItemsRef.child(itemId).removeValue()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(context, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Error deleting item", Toast.LENGTH_SHORT).show();
                });
    }


    public class ShoppingItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView, itemQuantityTextView, itemPriceTextView;
        Button deleteButton;

        public ShoppingItemViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemName);
            itemQuantityTextView = itemView.findViewById(R.id.itemQuantity);
            itemPriceTextView = itemView.findViewById(R.id.itemPrice);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
