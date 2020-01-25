package com.ims.main.ui.order.selectorder;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.Order;

public class SelectOrderActivity extends AppCompatActivity implements SelectOrderAdapter.SelectOrderForFinalizationCallback, SelectOrderAdapter.SelectOrderForSpecialtyOrderCallBack {
    public static final String KEY_EXTRA = "com.ims.main.STATE";

    //every activity must bundle a state for the stability of SelectOrderActivity
    public static final String STATE_SPECIAL = "SPECIALTY";
    public static final String STATE_CREATION = "CREATION";

    SelectOrderViewModel mViewModel;
    String mState;

    //todo consider refactor to use polymorphism and not flags of com.ims.main.STATE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_order_activity);
        if (getIntent().hasExtra(KEY_EXTRA)){
            mState = getIntent().getStringExtra(KEY_EXTRA);
        } else {
            throw new IllegalArgumentException(getResources().getString(R.string.error_state)+ KEY_EXTRA);
        }
        mViewModel = ViewModelProviders.of(this).get(SelectOrderViewModel.class);
        initializeRv();
    }

    private void initializeRv() {
        RecyclerView recyclerView = findViewById(R.id.orderSelection);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SelectOrderAdapter adapter = new SelectOrderAdapter(this);
        if(mState.equals(STATE_CREATION)){
            mViewModel.getAllOrdersNeedingFinalization().observe(this,adapter::submitList);
        } else {
            mViewModel.getAllFinishedOrders().observe(this,adapter::submitList);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void selectOrderForFinalization(Order order) {
        //todo use enum
        if (mState.equals(STATE_CREATION)){
            mViewModel.setAllOnFinalizationToFalseWhereNot(order.getOrderNumber());
            order.setOnFinalization(true);
            mViewModel.updateOrder(order);
            Toast.makeText(this,getResources().getString(R.string.toast_order_selected), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void selectOrderForSpecialty(Order order) {
        if (mState.equals(STATE_SPECIAL)){
            order.setOnSpecialty(true);
            mViewModel.removeAllOnSpecialty(order.getOrderNumber());
        }
    }
}
