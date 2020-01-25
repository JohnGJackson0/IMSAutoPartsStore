package com.ims.main.ui.order.orderapproval;

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
