package com.ims.main.ui.suppliers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
