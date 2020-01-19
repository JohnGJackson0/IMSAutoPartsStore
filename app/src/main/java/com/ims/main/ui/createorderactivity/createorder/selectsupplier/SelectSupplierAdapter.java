package com.ims.main.ui.createorderactivity.createorder.selectsupplier;

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
import com.ims.model.Supplier;

public class SelectSupplierAdapter extends PagedListAdapter<Supplier, SelectSupplierAdapter.SelectSupplierViewHolder> {
    private SupplierSelectedCallback mCallback;

    @NonNull
    @Override
    public SelectSupplierAdapter.SelectSupplierViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdentifier = R.layout.select_supplier;
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, viewGroup, false);
        return new SelectSupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSupplierViewHolder supplierViewHolder, int position) {
        supplierViewHolder.bindTo(getItem(position));
    }

    protected class SelectSupplierViewHolder extends RecyclerView.ViewHolder {
        TextView mSupplierName;
        TextView mId;
        TextView mAddress;
        TextView mContact;
        TextView mEmail;
        TextView mPhone;
        Button mSelectedSupplier;

        protected SelectSupplierViewHolder(View supplier) {
            super(supplier);
            mSupplierName = supplier.findViewById(R.id.partDescription);
            mAddress = supplier.findViewById(R.id.address);
            mContact = supplier.findViewById(R.id.contact);
            mEmail = supplier.findViewById(R.id.email);
            mPhone = supplier.findViewById(R.id.phone);
            mId = supplier.findViewById(R.id.id);
            mSelectedSupplier = supplier.findViewById(R.id.selectSupplier);
        }

        void bindTo(Supplier supplier) {
            mSupplierName.setText(supplier.getSupplierName());
            mAddress.setText(supplier.getAddress());
            mContact.setText(supplier.getContactName());
            mEmail.setText(supplier.getContactEmail());
            mPhone.setText(supplier.getPhoneNumber());
            mId.setText(supplier.getSupplierId());

            mSelectedSupplier.setOnClickListener(v -> {
                mCallback.supplierSelected(supplier);
            });
        }
    }

    protected SelectSupplierAdapter(Context mContext) {
        super(DIFF_CALLBACK);
        mCallback = (SupplierSelectedCallback) mContext;
    }

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