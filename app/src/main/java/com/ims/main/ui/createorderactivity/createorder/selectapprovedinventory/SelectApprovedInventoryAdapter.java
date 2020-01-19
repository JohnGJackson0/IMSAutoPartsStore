package com.ims.main.ui.createorderactivity.createorder.selectapprovedinventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.Item;

public class SelectApprovedInventoryAdapter extends PagedListAdapter<Item, SelectApprovedInventoryAdapter.ApprovedInventoryViewHolder>{
    private Context mContext;
    private InventorySelectedForOrderCallback mCallback;

    protected SelectApprovedInventoryAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
        mCallback = (InventorySelectedForOrderCallback) mContext;
    }

    @NonNull
    @Override
    public ApprovedInventoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutIdentifier = R.layout.select_inventory;
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, viewGroup, false);
        return new ApprovedInventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedInventoryViewHolder approvedInventoryViewHolder, int i) {
        approvedInventoryViewHolder.bindTo(getItem(i));
    }

    public class ApprovedInventoryViewHolder extends RecyclerView.ViewHolder {
        TextView mPartNumber;
        TextView mItemName;
        TextView mMaxControl;
        TextView mIsEnabled;
        TextView mOnHand;
        TextView mInBack;
        TextView mSupplier;
        TextView mApproved;
        TextView mSalesPrice;
        TextView mPending;
        Button mSelectButton;

        public ApprovedInventoryViewHolder(@NonNull View item) {
            super(item);
            mPartNumber = item.findViewById(R.id.partNumber);
            mItemName = item.findViewById(R.id.partDescription);
            mMaxControl = item.findViewById(R.id.maxControl);
            mIsEnabled = item.findViewById(R.id.isEnabled);
            mOnHand = item.findViewById(R.id.quantityOnhand);
            mInBack = item.findViewById(R.id.quantityInBack);
            mSupplier = item.findViewById(R.id.supplier);
            mApproved = item.findViewById(R.id.approved);
            mSalesPrice = item.findViewById(R.id.sales);
            mPending = item.findViewById(R.id.pending);
            mSelectButton = item.findViewById(R.id.selectInventoryButton);
        }

        void bindTo(Item item) {
            mPartNumber.setText(mContext.getResources().getString(R.string.prefix_part_number).concat(item.getPartNumber()));
            mItemName.setText(item.getPartDescription());
            mMaxControl.setText(mContext.getResources().getString(R.string.prefix_inventory_max_control).concat(Long.toString(item.getInventoryMax())));
            if (!item.isEnabled()) {
                mIsEnabled.setText(R.string.inventory_inactive);
                mIsEnabled.setVisibility(View.VISIBLE);
            }
            mOnHand.setText(mContext.getResources().getString(R.string.prefix_inventory_quantity).concat(Long.toString(item.getQuantityOnHand())));
            mInBack.setText(mContext.getResources().getString(R.string.prefix_inventory_in_back).concat(Long.toString(item.getQuantityInBack())));
            mSupplier.setText(mContext.getResources().getString(R.string.prefix_inventory_supplier_number).concat(item.getSupplierId()));
            mSalesPrice.setText(mContext.getResources().getString(R.string.prefix_inventory_sales_cost).concat(item.getSalesCost().toString()));
            mPending.setText(mContext.getResources().getString(R.string.prefix_inventory_pending).concat(Long.toString(item.getPendingOrder())));
            mApproved.setText(mContext.getResources().getString(R.string.prefix_inventory_approved).concat(Long.toString(item.getApprovedOrder())));
            mSelectButton.setOnClickListener(v -> mCallback.inventorySelected(item));
        }
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