package com.ims.main.ui.inventoryactivity;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ims.main.R;
import com.ims.model.Item;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {
    private Context mContext;
    private UpdateItemPendingQuantityCallback mItemCallback;
    private ErrorCallback mErrorCallback;

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
        TextView mPartNumber;
        TextView mItemName;
        TextView mMaxControl;
        TextView mIsEnabled;
        TextView mOnHand;
        TextView mInBack;
        TextView mSupplier;
        TextView mSalesPrice;
        TextView mPending;
        EditText mPendingOrderQty;
        Button mSubmitPendingOrderRequest;

        protected ItemViewHolder(View item) {
            super(item);
            mPartNumber= item.findViewById(R.id.partNumber);
            mItemName = item.findViewById(R.id.partDescription);
            mMaxControl = item.findViewById(R.id.maxControl);
            mIsEnabled = item.findViewById(R.id.isEnabled);
            mOnHand = item.findViewById(R.id.quantityOnhand);
            mInBack = item.findViewById(R.id.quantityInBack);
            mSupplier = item.findViewById(R.id.supplier);
            mSalesPrice = item.findViewById(R.id.sales);
            mPending = item.findViewById(R.id.pending);
            mPendingOrderQty = item.findViewById(R.id.orderAmount);
            mSubmitPendingOrderRequest =  item.findViewById(R.id.order);
        }

        void bindTo(Item item) {
            mPartNumber.setText(mContext.getResources().getString(R.string.prefix_part_number).concat(item.getPartNumber()));
            mItemName.setText(item.getPartDescription());
            mMaxControl.setText(mContext.getResources().getString(R.string.prefix_inventory_max_control).concat(Long.toString(item.getInventoryMax())));
            if (!item.isEnabled()){
                mIsEnabled.setText(R.string.inventory_inactive);
                mIsEnabled.setVisibility(View.VISIBLE);
            }
            mOnHand.setText(mContext.getResources().getString(R.string.prefix_inventory_quantity).concat(Long.toString(item.getQuantityOnHand())));
            mInBack.setText(mContext.getResources().getString(R.string.prefix_inventory_in_back).concat(Long.toString(item.getQuantityInBack())));
            mSupplier.setText(mContext.getResources().getString(R.string.prefix_inventory_supplier_number).concat(item.getSupplierId()));
            mSalesPrice.setText(mContext.getResources().getString(R.string.prefix_inventory_sales_cost).concat(item.getSalesCost().toString()));
            mPending.setText(mContext.getResources().getString(R.string.prefix_inventory_pending).concat(Long.toString(item.getPendingQuanity())));

            mSubmitPendingOrderRequest.setOnClickListener(v -> {
                try {
                    if("".equals(mPendingOrderQty.getText().toString())) {
                        mErrorCallback.blankError();
                    } else {
                        mItemCallback.updateItem(item, mPendingOrderQty.getText().toString());
                    }
                } catch (Exception e) {
                    mErrorCallback.blankError();
                }
            });
        }
    }

    protected ItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
        mItemCallback = (UpdateItemPendingQuantityCallback) mContext;
        mErrorCallback = (ErrorCallback) mContext;
    }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {

                @Override
                public boolean areItemsTheSame(Item one, Item two) {
                    return one.getPartNumber() == two.getPartNumber();
                }
                @Override
                public boolean areContentsTheSame(Item one,
                                                  Item two) {
                    //todo fix object equality from reference equality
                    return one.equals(two);
                }
            };
}
