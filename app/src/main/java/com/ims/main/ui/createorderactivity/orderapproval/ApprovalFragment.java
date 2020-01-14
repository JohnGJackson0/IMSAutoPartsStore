package com.ims.main.ui.createorderactivity.orderapproval;

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
import com.ims.model.Item;

public class ApprovalFragment extends Fragment implements ApprovalCallback {
    View mRoot;

    public static ApprovalFragment newInstance() {
        return new ApprovalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.order_approval, container, false);
        createRecyclerView();
        return mRoot;
    }

    private void createRecyclerView(){
        RecyclerView recyclerView = mRoot.findViewById(R.id.approval_items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRoot.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        OrderApprovalAdapter orderApprovalAdapter = new OrderApprovalAdapter(mRoot.getContext());
        ViewModelProviders.of(this).get(OrderApprovalViewModel.class).getPending().observe(this, orderApprovalAdapter::submitList);
        recyclerView.setAdapter(orderApprovalAdapter);
    }

    @Override
    public void approve(Item a) {
        a.setApprovedOrder(a.getPendingOrder());
        a.setPendingOrder(0);
        ViewModelProviders.of(this).get(OrderApprovalViewModel.class).updateItem(a);
    }
}
