package com.ims.main.ui.supplieractivity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ims.main.R;

public class SupplierFragment extends Fragment {
    View mRoot;
    SupplierViewModel mViewModel;

    public static SupplierFragment newInstance() {
        return new SupplierFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.supplier_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(SupplierViewModel.class);
        createRecyclerView();
        return mRoot;
    }

    private void createRecyclerView(){
        RecyclerView recyclerView = mRoot.findViewById(R.id.supplierList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRoot.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        SupplierAdapter supplierAdapter = new SupplierAdapter();
        mViewModel.getAllSuppliers().observe(this, supplierAdapter::submitList);
        recyclerView.setAdapter(supplierAdapter);
    }
}
