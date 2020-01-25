package com.ims.main.ui.order.specialtyorders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.ims.main.R;
import com.ims.main.ui.order.selectcustomer.SelectCustomerActivity;
import com.ims.main.ui.order.selectorder.SelectOrderActivity;
import com.ims.model.Customer;
import com.ims.model.Order;
import com.ims.model.SpecialtyOrders;

public class SpecialtyOrdersActivity extends AppCompatActivity{
    SpecialtyOrdersViewModel mViewModel;
    Button mSelectOrderButton;
    Button mSelectCustomerButton;
    Button mFinalizeSpecialtyPurchaseOrderButton;
    TextView mSpecialtyOrderInfo;
    TextView mSpecialtyCustomerInfo;

    private Order mOrder;
    private Customer mCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialty_orders_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewModel = ViewModelProviders.of(this).get(SpecialtyOrdersViewModel.class);
        mSelectOrderButton = findViewById(R.id.selectOrder);
        mSelectCustomerButton = findViewById(R.id.selectCustomer);
        mSpecialtyOrderInfo = findViewById(R.id.infoOrder);
        mSpecialtyCustomerInfo = findViewById(R.id.infoCustomer);
        mFinalizeSpecialtyPurchaseOrderButton = findViewById(R.id.finalizeButton);

        mSelectOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SelectOrderActivity.class);
            intent.putExtra(SelectOrderActivity.KEY_EXTRA, SelectOrderActivity.STATE_SPECIAL);
            startActivity(intent);
        });

        mSelectCustomerButton.setOnClickListener(v->{
            Intent intent = new Intent(this, SelectCustomerActivity.class);
            startActivity(intent);
        });

        //todo change query to remove all specialty orders on selection
        mFinalizeSpecialtyPurchaseOrderButton.setOnClickListener(v->{
            if(mOrder == null || mCustomer == null){
                Toast.makeText(this, getResources().getString(R.string.error_not_valid_specialty), Toast.LENGTH_SHORT).show();
            } else {
                SpecialtyOrders order = new SpecialtyOrders(mOrder.getOrderNumber(),mCustomer.getId());
                mViewModel.insertSpecialtyOrder(order);
                mViewModel.removeAllSpecialtyOrders();
            }
        });

        mViewModel.getCurrentSpecialtyOrder().observe(this, v->{
            mOrder = v;
            if (v!=null)
                mSpecialtyOrderInfo.setText(getResources().getString(R.string.prefix_order_info).concat(Long.toString(v.getOrderNumber())));
        });

        mViewModel.getCurrentSpecialtyCustomer().observe(this, v->{
            mCustomer = v;
            if(v != null)
                mSpecialtyCustomerInfo.setText(getResources().getString(R.string.prefix_current_customer).concat(Long.toString(v.getId())));
        });
    }
}
