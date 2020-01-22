package com.ims.main.ui.createorder.createorder.selectsupplier;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.Order;
import com.ims.model.Supplier;

public class SelectSupplierActivity extends AppCompatActivity implements SupplierSelectedCallback {
    private SelectSupplierViewModel mViewModel;
    private Order mNewestOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_supplier_activity);
        mViewModel = ViewModelProviders.of(this).get(SelectSupplierViewModel.class);
        initializeRv();

        mViewModel.getNewestOrder().observe(this, order->{
            mNewestOrder = order;
        });
    }

    private void initializeRv(){
        RecyclerView recyclerView = findViewById(R.id.supplierSelector);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SelectSupplierAdapter selectSupplierAdapter = new SelectSupplierAdapter(this);
        mViewModel.getAllSuppliers().observe(this,selectSupplierAdapter::submitList);
        recyclerView.setAdapter(selectSupplierAdapter);
    }

    @Override
    public void supplierSelected(Supplier v) {
        mNewestOrder.setSupplierNumber(v.getSupplierId());
        mViewModel.updateOrder(mNewestOrder);
        Toast toast=Toast.makeText(getApplicationContext(),R.string.supplier_added_to_order,Toast. LENGTH_SHORT);
        toast. setMargin(50,50);
        toast. show();
    }
}
