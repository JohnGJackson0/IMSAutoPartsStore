package com.ims.main.ui.createorder.orderfinalization.selectorder;

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
import com.ims.model.Order;

public class SelectOrderAdapter extends PagedListAdapter<Order, SelectOrderAdapter.SelectOrderViewHolder> {
    private Context mContext;
    private SelectOrderForFinalizationCallback mCallback;

    public interface SelectOrderForFinalizationCallback {
        void selectOrder(Order order);
    }

    public SelectOrderAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
        mCallback = (SelectOrderForFinalizationCallback) mContext;
    }

    @NonNull
    @Override
    public SelectOrderAdapter.SelectOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        int layoutIdentifier = R.layout.select_order;
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, viewGroup, false);
        return new SelectOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectOrderAdapter.SelectOrderViewHolder selectOrderViewHolder, int position) {
        selectOrderViewHolder.bindTo(getItem(position));
    }

    public class SelectOrderViewHolder extends RecyclerView.ViewHolder {
        TextView mOrderNumber;
        TextView mSupplierNumber;
        Button mSelectOrder;

        public SelectOrderViewHolder(@NonNull View view) {
            super(view);
            mOrderNumber = view.findViewById(R.id.orderNumber);
            mSupplierNumber = view.findViewById(R.id.supplierNumber);
            mSelectOrder = view.findViewById(R.id.selectOrder);
        }

        void bindTo(Order order) {
            mOrderNumber.setText(mContext.getResources().getString(R.string.prefix_order_number).concat(Long.toString(order.getOrderNumber())));
            //todo fix
            try {
                mSupplierNumber.setText(mContext.getResources().getString(R.string.prefix_supplier).concat(order.getSupplierNumber()));
            } catch (Exception e){
                mSupplierNumber.setText(mContext.getResources().getString(R.string.error_no_supplier));
            }
            mSelectOrder.setOnClickListener(v -> mCallback.selectOrder(order));
        }

    }

    private static DiffUtil.ItemCallback<Order> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Order>() {

                @Override
                public boolean areItemsTheSame(Order one, Order two) {
                    return one.getOrderNumber() == two.getOrderNumber();
                }
                @Override
                public boolean areContentsTheSame(Order one,
                                                  Order two) {
                    //todo fix object equality from reference equality
                    return one.equals(two);
                }
            };
}
