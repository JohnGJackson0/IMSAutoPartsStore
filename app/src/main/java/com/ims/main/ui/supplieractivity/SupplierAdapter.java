package com.ims.main.ui.supplieractivity;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ims.main.R;
import com.ims.model.Supplier;

public class SupplierAdapter extends PagedListAdapter<Supplier, SupplierAdapter.SupplierViewHolder> {
    @NonNull
    @Override
    public SupplierAdapter.SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        int layoutIdentifier = R.layout.supplier;
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, viewGroup, false);
        return new SupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder supplierViewHolder, int position) {
        supplierViewHolder.bindTo(getItem(position));
    }

    protected class SupplierViewHolder extends RecyclerView.ViewHolder {
        TextView mSupplierName;
        TextView mId;
        TextView mAddress;
        TextView mContact;
        TextView mEmail;
        TextView mPhone;

        protected SupplierViewHolder(View supplier) {
            super(supplier);
            mSupplierName = supplier.findViewById(R.id.name);
            mAddress = supplier.findViewById(R.id.address);
            mContact = supplier.findViewById(R.id.contact);
            mEmail = supplier.findViewById(R.id.email);
            mPhone = supplier.findViewById(R.id.phone);
            mId =supplier.findViewById(R.id.id);
        }

        void bindTo(Supplier supplier) {
            mSupplierName.setText(supplier.getSupplierName());
            mAddress.setText(supplier.getAddress());
            mContact.setText(supplier.getContactName());
            mEmail.setText(supplier.getContactEmail());
            mPhone.setText(supplier.getPhoneNumber());
            mId.setText(supplier.getSupplierId());
        }
    }

    protected SupplierAdapter() { super(DIFF_CALLBACK); }

    private static DiffUtil.ItemCallback<Supplier> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Supplier>() {

                @Override
                public boolean areItemsTheSame(Supplier one, Supplier two) {
                    return one.getSupplierId() == two.getSupplierId();
                }
                @Override
                public boolean areContentsTheSame(Supplier one,
                                                  Supplier two) {
                    return one.equals(two);
                }
            };
}
