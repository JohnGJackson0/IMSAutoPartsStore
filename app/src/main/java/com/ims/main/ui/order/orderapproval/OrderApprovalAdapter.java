package com.ims.main.ui.order.orderapproval;

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

public class OrderApprovalAdapter extends PagedListAdapter<Item, OrderApprovalAdapter.OrderApprovalViewHolder> {
    private Context mContext;
    private ApprovalCallback mApprovalCallback;
    private RejectionCallback mRejectionCallback;

    @NonNull
    @Override
    public OrderApprovalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        int layoutIdentifier = R.layout.pending_order;
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, viewGroup, false);
        return new OrderApprovalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderApprovalViewHolder orderApprovalViewHolder, int position) {
        orderApprovalViewHolder.bindTo(getItem(position));
    }

    protected class OrderApprovalViewHolder extends RecyclerView.ViewHolder {
        TextView mPartNumber;
        TextView mItemName;
        TextView mMaxControl;
        TextView mIsEnabled;
        TextView mOnHand;
        TextView mInBack;
        TextView mSupplier;
        TextView mSalesPrice;
        TextView mPending;
        Button mApproval;
        Button mRejection;

        protected OrderApprovalViewHolder(View item) {
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
            mApproval = item.findViewById(R.id.approve);
            mRejection = item.findViewById(R.id.reject);
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
            mPending.setText(mContext.getResources().getString(R.string.prefix_inventory_pending).concat(Long.toString(item.getPendingOrder())));


            mApproval.setOnClickListener(v -> {
                mApprovalCallback.approve(item);
            });

            mRejection.setOnClickListener(v-> {
                mRejectionCallback.reject(item);
            });
        }
    }

    protected OrderApprovalAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
        mApprovalCallback = (ApprovalCallback) mContext;
        mRejectionCallback = (RejectionCallback) mContext;
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
                    return one.equals(two);
                }
            };
}