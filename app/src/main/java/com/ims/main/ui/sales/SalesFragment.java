package com.ims.main.ui.sales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.main.ui.selectsale.SelectSaleActivity;

public class SalesFragment extends Fragment {
    private View mRoot;
    private SalesViewModel mViewModel;
    Button mSelectSaleButton;
    private TextView mSaleId;
    private TextView mCustomerNumber;
    private TextView mSoldFor;

    public static SalesFragment newInstance() {
        SalesFragment fragment = new SalesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(SalesViewModel.class);
        // Inflate the layout for this fragment
        mRoot = inflater.inflate(R.layout.sales_fragment, container, false);
        mSelectSaleButton = mRoot.findViewById(R.id.selectSale);
        mSaleId = mRoot.findViewById(R.id.saleId);
        mCustomerNumber = mRoot.findViewById(R.id.customerNumber);
        mSoldFor = mRoot.findViewById(R.id.soldFor);

        mSelectSaleButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SelectSaleActivity.class);
            startActivity(intent);
        });

        mViewModel.getSale().observe(this, sale -> {
            if (sale != null){
                mSaleId.setText(mRoot.getResources().getString(R.string.prefix_sale_id).concat(Long.toString(sale.getId())));
                mCustomerNumber.setText(mRoot.getResources().getString(R.string.prefix_customer_number).concat(Long.toString(sale.getCustomerNumber())));
                mSoldFor.setText(mRoot.getResources().getString(R.string.prefix_total_order).concat(sale.getSoldFor().toString()));
                createRVOfInventoriesOnNumber(sale.getId());
            }
        });

        return mRoot;
    }

    private void createRVOfInventoriesOnNumber(long saleId) {
        RecyclerView recyclerView = mRoot.findViewById(R.id.saleInventoriesRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRoot.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        SaleInventoryAdapter saleInventoryAdapter = new SaleInventoryAdapter(mRoot.getContext());
        mViewModel.getSaleInventories(saleId).observe(this, saleInventoryAdapter::submitList);
        recyclerView.setAdapter(saleInventoryAdapter);

    }
}
