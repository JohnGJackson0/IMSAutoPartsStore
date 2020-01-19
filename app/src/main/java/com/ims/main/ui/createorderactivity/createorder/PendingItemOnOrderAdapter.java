package com.ims.main.ui.createorderactivity.createorder;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.OrderInventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PendingItemOnOrderAdapter extends PagedListAdapter<OrderInventory, PendingItemOnOrderAdapter.PendingItemOnOrderViewHolder> {
    private Context mContext;
    private Set<OrderInventory> mUpdatedOrderInventoryItems = new HashSet<>();

    public PendingItemOnOrderAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    public List<OrderInventory> getPendingUpdates() {
        List<OrderInventory> updatedList = new ArrayList<>(mUpdatedOrderInventoryItems);
        mUpdatedOrderInventoryItems.clear();
        return updatedList;
    }

    @NonNull
    @Override
    public PendingItemOnOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutIdentifier = R.layout.pending_item_on_order;
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, viewGroup, false);
        return new PendingItemOnOrderViewHolder(view, new ExtendedCostTextListener(), new QuantityTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull PendingItemOnOrderViewHolder pendingItemOnOrderViewHolder, int position) {
        pendingItemOnOrderViewHolder.bindTo(getItem(position));
        pendingItemOnOrderViewHolder.mExtendedCostTextListener.updatePosition(pendingItemOnOrderViewHolder.getAdapterPosition());
        pendingItemOnOrderViewHolder.mQuantityTextListener.updatePosition(pendingItemOnOrderViewHolder.getAdapterPosition());

    }

    public class PendingItemOnOrderViewHolder extends RecyclerView.ViewHolder {
        TextView mPart;
        EditText mToOrder;
        EditText mExtendedCost;
        public ExtendedCostTextListener mExtendedCostTextListener;
        public QuantityTextListener mQuantityTextListener;

        public PendingItemOnOrderViewHolder(@NonNull View OrderInventory, ExtendedCostTextListener extendedCostTextListener, QuantityTextListener quantityTextListener) {
            super(OrderInventory);
            mPart = OrderInventory.findViewById(R.id.partNumber);
            mToOrder = OrderInventory.findViewById(R.id.to_order);
            mExtendedCost = OrderInventory.findViewById(R.id.cost);
            mExtendedCostTextListener = extendedCostTextListener;
            mQuantityTextListener =quantityTextListener;
            mExtendedCost.addTextChangedListener(this.mExtendedCostTextListener);
            mToOrder.addTextChangedListener(this.mQuantityTextListener);
        }

        void bindTo(OrderInventory orderInventory) {
            mPart.setText(mContext.getResources().getString(R.string.prefix_part_number).concat(orderInventory.getPartNumber()));

            //todo this is to prone to error at this stage in the development, fix and properly test
            //this would allow state saving text when leaving and reentering
            /*
            //The following produces bugs related to setting all values in rv to the first value
            //because we are using text-watchers this is extremely prone this type of error
            //      (specifically because of the pagination, the text watchers update previous positions
            //         on different view holders but can also be triggered by refreshes in small lists)
            //Although, this really only saves the state, it could potentially change values
            //before putting into the database so do not call a set text here until we can do proper testing
            // jgj
            if (orderInventory.isValid()) {
                if (orderInventory.getExtentedCost() != null) {
                    mExtendedCost.getText().clear();
                    mExtendedCost.append(orderInventory.getExtentedCost().toString());
                }
                    mToOrder.getText().clear();
                    mToOrder.append(Long.toString(orderInventory.getQuantity()));
            } else if (mExtendedCost.length() > 0){
                mExtendedCost.getText().clear();
                mToOrder.getText().clear();
            }*/

        }
    }

    private class ExtendedCostTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if ((charSequence.toString().startsWith(".") && charSequence.length() == 1) || charSequence.length() == 0) {
                getItem(position).setValid(false);
            } else {
                getItem(position).setValid(true);
                Log.d("asdjhkaskjdh",charSequence.toString());
                getItem(position).setExtentedCost(new BigDecimal(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            mUpdatedOrderInventoryItems.add(getItem(position));
        }
    }
    //todo error validating


    private class QuantityTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if(charSequence.length() != 0){
                getItem(position).setValid(true);
                getItem(position).setQuantity(Long.parseLong(charSequence.toString()));
            }

            /*
            if (charSequence.length() == 1 || charSequence.length() == 0) {
                Log.d("sadjhasd do no update","asdas");
                getItem(position).setValid(false);
            } else {
                Log.d("sadjhasd","asdas");
                getItem(position).setValid(true);
                getItem(position).setQuantity(Long.parseLong(charSequence.toString()));
            }*/
        }

        @Override
        public void afterTextChanged(Editable editable) {
            mUpdatedOrderInventoryItems.add(getItem(position));
        }
    }

    //todo fix
    private static DiffUtil.ItemCallback<OrderInventory> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<OrderInventory>() {

                @Override
                public boolean areItemsTheSame(OrderInventory one, OrderInventory two) {
                    return one.getPartNumber().concat(one.getOrderNumber().toString()) == two.getPartNumber().concat(two.getOrderNumber().toString());
                }
                @Override
                public boolean areContentsTheSame(OrderInventory one,
                                                  OrderInventory two) {
                    return one.getPartNumber().equals(two.getPartNumber());
                }
            };
}
