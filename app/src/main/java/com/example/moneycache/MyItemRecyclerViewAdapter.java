package com.example.moneycache;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneycache.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.moneycache.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<BankData> mValues;

    public MyItemRecyclerViewAdapter(List<BankData> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mDateView.setText(mValues.get(position).getDate());
        holder.mDescriptionView.setText(mValues.get(position).getDescription());
        holder.mAmountView.setText(mValues.get(position).getStringAmount());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mDateView;
        public final TextView mDescriptionView;
        public final TextView mAmountView;
        public BankData mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mDateView = binding.date;
            mDescriptionView = binding.description;
            mAmountView = binding.amount;
        }

        @Override
        //TODO: is this how we create a JSON formatted string???(Dixie)
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }
    }
}