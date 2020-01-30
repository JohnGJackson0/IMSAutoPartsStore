package com.ims.main.ui.sales;

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
import com.ims.model.SaleInventory;

public class SaleInventoryAdapter extends PagedListAdapter<SaleInventory,SaleInventoryAdapter.SaleInventoryViewHolder> {
    private Context mContext;

    protected SaleInventoryAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public SaleInventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdentifier = R.layout.sale_inventory;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, parent, false);
        return new SaleInventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleInventoryViewHolder holder, int position) {
        holder.bindTo(getItem(position), position);
    }

    public class SaleInventoryViewHolder extends RecyclerView.ViewHolder {
        TextView mLineNumber;
        TextView mPartNumber;
        TextView mAmount;
        TextView mSaleAmount;

        public SaleInventoryViewHolder(@NonNull View item) {
            super(item);
            mLineNumber = item.findViewById(R.id.lineNumber);
            mPartNumber= item.findViewById(R.id.partNumber);
            mAmount = item.findViewById(R.id.amount);
            mSaleAmount= item.findViewById(R.id.saleAmount);
        }

        public void bindTo(SaleInventory saleInventory, int pos) {
            mLineNumber.setText(mContext.getResources().getString(R.string.prefix_line).concat(Integer.toString(pos+1)));
            mPartNumber.setText(mContext.getResources().getString(R.string.prefix_part).concat(saleInventory.getPartNumber()));
            mAmount.setText(mContext.getResources().getString(R.string.prefix_amount).concat(Integer.toString(saleInventory.getAmount())));
            mSaleAmount.setText(mContext.getResources().getString(R.string.prefix_sale_amount).concat(saleInventory.getLineSaleAmount().toString()));
        }
    }


    private static DiffUtil.ItemCallback<SaleInventory> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SaleInventory>() {

                @Override
                public boolean areItemsTheSame(SaleInventory one, SaleInventory two) {
                    return one.getId() == two.getId();
                }
                @Override
                public boolean areContentsTheSame(SaleInventory one,
                                                  SaleInventory two) {
                    //todo fix object equality from reference equality
                    return one.equals(two);
                }
            };
}
