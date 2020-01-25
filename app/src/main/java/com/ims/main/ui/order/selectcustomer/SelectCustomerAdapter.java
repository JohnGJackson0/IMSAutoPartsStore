package com.ims.main.ui.order.selectcustomer;

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
import com.ims.model.Customer;

public class SelectCustomerAdapter extends PagedListAdapter<Customer, SelectCustomerAdapter.SelectCustomerViewHolder> {
    private Context mContext;
    private SelectCustomerForSpecialityOrdersCallBack mCallbackSpecial;

    public interface SelectCustomerForSpecialityOrdersCallBack {
        void selectCustomerForSpecialtyOrder(Customer customer);
    }

    protected SelectCustomerAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext=context;
        mCallbackSpecial = (SelectCustomerForSpecialityOrdersCallBack) mContext;

    }

    @NonNull
    @Override
    public SelectCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdentifier = R.layout.select_customer;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdentifier, parent, false);
        return new SelectCustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectCustomerViewHolder holder, int position) {
        holder.bindTo(getItem(position));

    }

    public class SelectCustomerViewHolder extends RecyclerView.ViewHolder {
        TextView mCustomerId;
        TextView mCustomerName;
        TextView mCustomerEmail;
        TextView mCustomerPhoneNumber;
        TextView mAddress;
        Button mSelectCustomer;

        public SelectCustomerViewHolder(@NonNull View item) {
            super(item);
            mCustomerId = item.findViewById(R.id.customerId);
            mCustomerName = item.findViewById(R.id.customerName);
            mCustomerEmail = item.findViewById(R.id.customerEmail);
            mCustomerPhoneNumber = item.findViewById(R.id.customerPhoneNumber);
            mAddress = item.findViewById(R.id.address);
            mSelectCustomer = item.findViewById(R.id.selectCustomer);
        }

        public void bindTo(Customer customer) {
            mCustomerId.setText(mContext.getResources().getString(R.string.prefix_customer_id).concat(Long.toString(customer.getId())));
            mCustomerName.setText(mContext.getResources().getString(R.string.prefix_customer_name).concat(customer.getFirstName()).concat(" ").concat(customer.getLastName()));
            mCustomerEmail.setText(mContext.getResources().getString(R.string.prefix_customer_email).concat(customer.getEmail()));
            mCustomerPhoneNumber.setText(mContext.getResources().getString(R.string.prefix_customer_phone_number).concat(customer.getPhoneNumber()));
            mAddress.setText(mContext.getResources().getString(R.string.prefix_customer_address).concat(customer.getAddress()));
            mSelectCustomer.setOnClickListener(v-> {mCallbackSpecial.selectCustomerForSpecialtyOrder(customer);});
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
                    return one.equals(two);
                }
            };
}
