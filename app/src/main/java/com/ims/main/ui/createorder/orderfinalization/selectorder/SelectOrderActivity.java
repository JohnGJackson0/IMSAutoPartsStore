package com.ims.main.ui.createorder.orderfinalization.selectorder;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.Order;

public class SelectOrderActivity extends AppCompatActivity implements SelectOrderAdapter.SelectOrderForFinalizationCallback{
    SelectOrderViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_order_activity);
        mViewModel = ViewModelProviders.of(this).get(SelectOrderViewModel.class);
        initializeRv();
    }

    private void initializeRv() {
        RecyclerView recyclerView = findViewById(R.id.orderSelection);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SelectOrderAdapter adapter = new SelectOrderAdapter(this);
        mViewModel.getAllOrdersNeedingFinalization().observe(this,adapter::submitList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void selectOrder(Order order) {
        mViewModel.setAllOnFinalizationToFalseWhereNot(order.getOrderNumber());
        order.setOnFinalization(true);
        mViewModel.updateOrder(order);
        Toast.makeText(this,getResources().getString(R.string.toast_order_selected), Toast.LENGTH_LONG).show();
    }
}
