package com.example.moneycache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.moneycache.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.moneycache.databinding.FragmentItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<BankData> items;
    int singleitem_selection_position = -1;
    private boolean isSelected;



    public List<BankData> getItems() {
        return items;
    }

    public MyItemRecyclerViewAdapter(EditDataActivity context, List<BankData> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(MyItemRecyclerViewAdapter.ViewHolder holder, int position) {
        context = holder.itemView.getContext();
        ViewHolder.mItem = items.get(position);
        holder.mDateView.setText(items.get(position).getDate());
        holder.mDescriptionView.setText(items.get(position).getDescription());
        holder.mAmountView.setText(items.get(position).getStringAmount());
        holder.itemView.findViewById(R.id.list);
        if (singleitem_selection_position == position) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.purple_200));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.itemView.setOnClickListener(v -> setSingleSelection(holder.getAbsoluteAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void setSingleSelection(int adapterPosition){
        if (adapterPosition == RecyclerView.NO_POSITION) return;

        notifyItemChanged(singleitem_selection_position);
        singleitem_selection_position = adapterPosition;
        notifyItemChanged(singleitem_selection_position);

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public BankData getmItem() {
            return mItem;
        }

        public static BankData mItem;
        public final TextView mDateView;
        public final TextView mDescriptionView;
        public final TextView mAmountView;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mDateView = binding.date;
            mDescriptionView = binding.description;
            mAmountView = binding.amount;

        }



        public String toString() {
            return super.toString() + " '" + mDateView.getText() + "'" + " '"
                    + mDescriptionView.getText() + "'" +  " '" + mAmountView.getText() + "'";
        }

    }

    public void removeItem(int position) {

        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

}