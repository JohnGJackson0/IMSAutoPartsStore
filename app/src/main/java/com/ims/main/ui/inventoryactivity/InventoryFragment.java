package com.ims.main.ui.inventoryactivity;

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

public class InventoryFragment extends Fragment {

    private InventoryViewModel mViewModel;
    private View mRoot;

    public static InventoryFragment newInstance() {
        return new InventoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.inventory_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(InventoryViewModel.class);
        createRecyclerView();
        return mRoot;
    }

    private void createRecyclerView(){
        RecyclerView recyclerView = mRoot.findViewById(R.id.items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRoot.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemAdapter itemAdapter = new ItemAdapter(mRoot.getContext());
        mViewModel.getAllItems().observe(this, itemAdapter::submitList);
        recyclerView.setAdapter(itemAdapter);
    }

}
