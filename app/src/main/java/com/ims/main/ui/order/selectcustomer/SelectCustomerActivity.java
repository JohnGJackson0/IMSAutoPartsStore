package com.ims.main.ui.order.selectcustomer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.Customer;

public class SelectCustomerActivity extends AppCompatActivity implements SelectCustomerAdapter.SelectCustomerForSpecialityOrdersCallBack {
    SelectCustomerViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_customer_activity);
        mViewModel = ViewModelProviders.of(this).get(SelectCustomerViewModel.class);
        initializeRv();
    }

    private void initializeRv() {
        RecyclerView recyclerView = findViewById(R.id.selectCustomerRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SelectCustomerAdapter adapter = new SelectCustomerAdapter(this);
        mViewModel.getCustomers().observe(this,adapter::submitList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void selectCustomerForSpecialtyOrder(Customer customer) {
        mViewModel.setSpecialtyCustomer(customer.getId());
    }
}
