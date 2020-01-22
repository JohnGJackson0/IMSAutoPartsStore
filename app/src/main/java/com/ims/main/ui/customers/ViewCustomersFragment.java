package com.ims.main.ui.customers;

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

public class ViewCustomersFragment extends Fragment {
    View mRoot;

    public static ViewCustomersFragment newInstance() {
        return new ViewCustomersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.view_customer_fragment, container, false);
        createRecyclerView();
        return mRoot;
    }

    private void createRecyclerView(){
        RecyclerView recyclerView = mRoot.findViewById(R.id.customerRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRoot.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ViewCustomersAdapter viewCustomersAdapter = new ViewCustomersAdapter(mRoot.getContext());
        ViewModelProviders.of(this).get(ViewCustomersViewModel.class).getCustomers().observe(this, viewCustomersAdapter::submitList);
        recyclerView.setAdapter(viewCustomersAdapter);
    }
}
