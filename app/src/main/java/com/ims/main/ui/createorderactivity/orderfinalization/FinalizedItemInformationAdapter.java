package com.ims.main.ui.createorderactivity.orderfinalization;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.Item;
import com.ims.model.OrderInventoryAndItemInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FinalizedItemInformationAdapter extends PagedListAdapter<OrderInventoryAndItemInfo, FinalizedItemInformationAdapter.FinalizedItemInformationViewHolder> {
    Context mContext;
    private Set<Item> mUpdateList = new HashSet<>();

    protected FinalizedItemInformationAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public FinalizedItemInformationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutIdentifier = R.layout.finalized_item_information;
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, viewGroup, false);
        return new FinalizedItemInformationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinalizedItemInformationViewHolder finalizedItemInformationViewHolder, int position) {
        finalizedItemInformationViewHolder.bindTo(getItem(position));
    }

    public class FinalizedItemInformationViewHolder extends RecyclerView.ViewHolder {
        TextView mPartNumber;
        TextView mItemName;
        TextView mMaxControl;
        TextView mIsEnabled;
        TextView mOnHand;
        TextView mInBack;
        TextView mSalesPrice;
        TextView mApprovedQuantity;
        TextView mExtendedCost;
        TextView mQuantityOnOrder;
        TextView mWarning;
        TextView mOrderNumber;
        TextView mSupplierNumber;


        public FinalizedItemInformationViewHolder(@NonNull View item) {
            super(item);
            mPartNumber= item.findViewById(R.id.partNumber);
            mItemName = item.findViewById(R.id.partDescription);
            mMaxControl = item.findViewById(R.id.maxControl);
            mIsEnabled = item.findViewById(R.id.isEnabled);
            mOnHand = item.findViewById(R.id.quantityOnhand);
            mInBack = item.findViewById(R.id.quantityInBack);
            mApprovedQuantity = item.findViewById(R.id.approved);
            mSalesPrice = item.findViewById(R.id.sales);
            mExtendedCost = item.findViewById(R.id.extendedCost);
            mQuantityOnOrder = item.findViewById(R.id.quantityOnOrder);
            mWarning = item.findViewById(R.id.warning);
            mOrderNumber = item.findViewById(R.id.orderNumber);
            mSupplierNumber = item.findViewById(R.id.supplierNumber);
        }

        void bindTo(OrderInventoryAndItemInfo orderAndOrderInventory) {
            String warnings = "";
            if (orderAndOrderInventory.orderInventory.getQuantity() > orderAndOrderInventory.item.getApprovedOrder()) {
                mWarning.setVisibility(View.VISIBLE);
                warnings = warnings.concat(mContext.getResources().getString(R.string.warning_order_is_more_than_approval));
            }
            if((orderAndOrderInventory.orderInventory.getQuantity() + orderAndOrderInventory.item.getQuantityOnHand()
                    + orderAndOrderInventory.item.getQuantityInBack()) > orderAndOrderInventory.item.getInventoryMax()
                    || orderAndOrderInventory.item.getInventoryMax() == 0 ) {
                mWarning.setVisibility(View.VISIBLE);
                warnings = warnings.concat(mContext.getResources().getString(R.string.warning_order_is_over_capacity));
            }
            mWarning.setText(warnings);
            mPartNumber.setText(mContext.getResources().getString(R.string.prefix_part_number).concat(orderAndOrderInventory.orderInventory.getPartNumber()));
            mItemName.setText(orderAndOrderInventory.item.getPartDescription());
            mMaxControl.setText(mContext.getResources().getString(R.string.prefix_inventory_max_control).concat(Long.toString(orderAndOrderInventory.item.getInventoryMax())));
            if (!orderAndOrderInventory.item.isEnabled()){
                mIsEnabled.setText(R.string.inventory_inactive);
                mIsEnabled.setVisibility(View.VISIBLE);
            }
            mOnHand.setText(mContext.getResources().getString(R.string.prefix_inventory_quantity).concat(Long.toString(orderAndOrderInventory.item.getQuantityOnHand())));
            mInBack.setText(mContext.getResources().getString(R.string.prefix_inventory_in_back).concat(Long.toString(orderAndOrderInventory.item.getQuantityInBack())));
            mSalesPrice.setText(mContext.getResources().getString(R.string.prefix_inventory_sales_cost).concat(orderAndOrderInventory.item.getSalesCost().toString()));
            mApprovedQuantity.setText(mContext.getResources().getString(R.string.prefix_inventory_approved).concat(Long.toString(orderAndOrderInventory.item.getApprovedOrder())));
            mQuantityOnOrder.setText(mContext.getResources().getString(R.string.prefix_quantity_on_order).concat(Long.toString(orderAndOrderInventory.orderInventory.getQuantity())).concat("\n\n"));
            mExtendedCost.setText(mContext.getResources().getString(R.string.prefix_extended_cost).concat(orderAndOrderInventory.orderInventory.getExtentedCost().toString()));
            mOrderNumber.setText(mContext.getResources().getString(R.string.prefix_order_number).concat(Long.toString(orderAndOrderInventory.orderInventory.getOrderNumber())));

            if((orderAndOrderInventory.orderInventory.getQuantity() > orderAndOrderInventory.item.getApprovedOrder())) {
                orderAndOrderInventory.item.setApprovedOrder(0);
                mUpdateList.add(orderAndOrderInventory.item);
            } else {
                orderAndOrderInventory.item.setApprovedOrder(orderAndOrderInventory.item.getApprovedOrder()-orderAndOrderInventory.orderInventory.getQuantity());
                mUpdateList.add(orderAndOrderInventory.item);
            }
        }
    }

    public List<Item> getUpdateList() {
        List<Item> updateList = new ArrayList<>(mUpdateList);
        return updateList;
    }

    private static DiffUtil.ItemCallback<OrderInventoryAndItemInfo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<OrderInventoryAndItemInfo>() {
                @Override
                public boolean areItemsTheSame(OrderInventoryAndItemInfo one, OrderInventoryAndItemInfo two) {
                    return one.equals(two);
                }
                @Override
                public boolean areContentsTheSame(OrderInventoryAndItemInfo one,
                                                  OrderInventoryAndItemInfo two) {
                    //todo fix object equality from reference equality
                    return one.equals(two);
                }
            };
}
