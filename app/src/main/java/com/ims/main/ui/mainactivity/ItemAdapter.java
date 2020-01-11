package com.ims.main.ui.mainactivity;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ims.db.Item;
import com.ims.main.R;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        int layoutIdentifier = R.layout.item;
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.bindTo(getItem(position));
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mItemName;
        TextView mItemQuantity;
        TextView mItemSupplier;

        protected ItemViewHolder(View item) {
            super(item);
            mItemName = item.findViewById(R.id.name);
            mItemQuantity = item.findViewById(R.id.quantity);
            mItemSupplier = item.findViewById(R.id.supplier);
        }

        void bindTo(Item item) {
            mItemName.setText(item.getPartDescription());
            mItemQuantity.setText(Long.toString(item.getQuantityOnHand()));
            mItemSupplier.setText(Long.toString(item.getSupplierId()));
        }
    }

    protected ItemAdapter() { super(DIFF_CALLBACK); }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {

                @Override
                public boolean areItemsTheSame(Item one, Item two) {
                    return one.getPart_number() == two.getPart_number();
                }
                @Override
                public boolean areContentsTheSame(Item one,
                                                  Item two) {
                    //todo fix object equality from reference equality
                    return one.equals(two);
                }
            };
}
