package com.ims.main.ui.selectsale;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.model.Sale;

public class SelectSaleActivity extends AppCompatActivity implements SelectSaleAdapter.SelectSaleCallback {
    private SelectSaleViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sale);
        mViewModel = ViewModelProviders.of(this).get(SelectSaleViewModel.class);

        createRv();
    }

    private void createRv(){
        RecyclerView recyclerView = findViewById(R.id.selectCustomerRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SelectSaleAdapter selectSaleAdapter = new SelectSaleAdapter(this);
        mViewModel.getSales().observe(this, selectSaleAdapter::submitList);
        recyclerView.setAdapter(selectSaleAdapter);
    }

    @Override
    public void selectSale(Sale sale) {
        mViewModel.setSale(sale.getId());
    }
}
