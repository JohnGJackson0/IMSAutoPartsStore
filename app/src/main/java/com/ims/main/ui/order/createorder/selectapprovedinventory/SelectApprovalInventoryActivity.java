package com.ims.main.ui.order.createorder.selectapprovedinventory;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.Item;
import com.ims.model.Order;
import com.ims.model.OrderInventory;

public class SelectApprovalInventoryActivity extends AppCompatActivity implements InventorySelectedForOrderCallback {
    private SelectApprovedInventoryViewModel mViewModel;
    private Order mNewestOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_inventory_activity);
        mViewModel = ViewModelProviders.of(this).get(SelectApprovedInventoryViewModel.class);
        initializeRv();
        //todo use a bundle to avoid uninitialized background thread getting the number before button press
        mViewModel.getNewestOrder().observe(this, order -> {
            mNewestOrder = order;
        });
    }

    @Override
    public void inventorySelected(Item a) {
        OrderInventory newInventory = new OrderInventory(mNewestOrder.getOrderNumber(), a.getPartNumber());
        newInventory.setPending(true);
        mViewModel.insertOrderInventory(newInventory);
        Toast.makeText(this, getResources().getString(R.string.added_to_order), Toast.LENGTH_SHORT).show();
    }

    private void initializeRv(){
        RecyclerView recyclerView = findViewById(R.id.inventorySelectionRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SelectApprovedInventoryAdapter selectApprovedInventoryAdapter = new SelectApprovedInventoryAdapter(this);
        mViewModel.getApprovedItems().observe(this,selectApprovedInventoryAdapter::submitList);
        recyclerView.setAdapter(selectApprovedInventoryAdapter);
    }
}
