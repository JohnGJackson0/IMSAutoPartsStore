package com.ims.main.ui.selectsale;

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
import com.ims.model.Sale;

public class SelectSaleAdapter extends PagedListAdapter<Sale, SelectSaleAdapter.SelectSaleViewHolder> {
    private Context mContext;
    private SelectSaleCallback mSelectSaleCallback;

    public interface SelectSaleCallback {
        void selectSale(Sale sale);
    }

    protected SelectSaleAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
        mSelectSaleCallback = (SelectSaleCallback) mContext;
    }

    @NonNull
    @Override
    public SelectSaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdentifier = R.layout.select_sale;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, parent, false);
        return new SelectSaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSaleViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    public class SelectSaleViewHolder extends RecyclerView.ViewHolder {
        TextView mSaleId;
        TextView mCustomerNumber;
        TextView mSoldOn;
        TextView mTotalOrderPrice;
        Button mSelectOrder;


        public SelectSaleViewHolder(@NonNull View item) {
            super(item);
            mSaleId= item.findViewById(R.id.saleId);
            mCustomerNumber = item.findViewById(R.id.customerNumber);
            mSoldOn = item.findViewById(R.id.soldOn);
            mTotalOrderPrice = item.findViewById(R.id.totalOrderPrice);
            mSelectOrder = item.findViewById(R.id.selectOrder);
        }

        public void bindTo(Sale sale) {
            mSaleId.setText(mContext.getResources().getString(R.string.sale_id).concat(Long.toString(sale.getId())));
            mCustomerNumber.setText(mContext.getResources().getString(R.string.customer_number).concat(Long.toString(sale.getCustomerNumber())));
            //todo !important fix date
            mSoldOn.setText(Long.toString(sale.getSoldOn()).concat("/date to fix in later release"));
            mTotalOrderPrice.setText(mContext.getResources().getString(R.string.total).concat(sale.getSoldFor().toString()));
            mSelectOrder.setOnClickListener(v -> mSelectSaleCallback.selectSale(sale));
        }
    }

    private static DiffUtil.ItemCallback<Sale> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Sale>() {

                @Override
                public boolean areItemsTheSame(Sale one, Sale two) {
                    return one.getId() == two.getId();
                }
                @Override
                public boolean areContentsTheSame(Sale one,
                                                  Sale two) {
                    return one.equals(two);
                }
            };
}
