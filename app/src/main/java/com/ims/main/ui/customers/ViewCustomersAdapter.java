package com.ims.main.ui.customers;

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
import com.ims.model.Customer;

public class ViewCustomersAdapter extends PagedListAdapter<Customer, ViewCustomersAdapter.ViewCustomersViewHolder> {
    private Context mContext;


    protected ViewCustomersAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext=context;
    }

    @NonNull
    @Override
    public ViewCustomersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdentifier = R.layout.customer;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, parent, false);
        return new ViewCustomersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCustomersViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    public class ViewCustomersViewHolder extends RecyclerView.ViewHolder {
        TextView mId;
        TextView mName;
        TextView mEmail;
        TextView mPhoneNumber;
        TextView mAddress;

        public ViewCustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            mId = itemView.findViewById(R.id.customerId);
            mName= itemView.findViewById(R.id.customerName);
            mEmail = itemView.findViewById(R.id.customerEmail);
            mPhoneNumber=itemView.findViewById(R.id.customerPhoneNumber);
            mAddress=itemView.findViewById(R.id.address);
        }

        void bindTo(Customer customer) {
            mId.setText(mContext.getResources().getString(R.string.id_prefix).concat(Long.toString(customer.getId())));
            mName.setText(customer.getFirstName().concat(" ".concat(customer.getLastName())));
            mEmail.setText(customer.getEmail());
            mPhoneNumber.setText(customer.getPhoneNumber());
            mAddress.setText(customer.getAddress().concat("\n"));
        }
    }

    private static DiffUtil.ItemCallback<Customer> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Customer>() {
                @Override
                public boolean areItemsTheSame(Customer one, Customer two) {
                    return one.getId() == two.getId();
                }
                @Override
                public boolean areContentsTheSame(Customer one,
                                                  Customer two) {
                    //todo fix object equality from reference equality
                    return one.equals(two);
                }
            };
}
