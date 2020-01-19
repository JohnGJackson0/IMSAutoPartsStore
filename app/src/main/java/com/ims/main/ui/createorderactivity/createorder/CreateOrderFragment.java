package com.ims.main.ui.createorderactivity.createorder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.main.ui.createorderactivity.createorder.selectapprovedinventory.SelectApprovalInventoryActivity;
import com.ims.main.ui.createorderactivity.createorder.selectsupplier.SelectSupplierActivity;
import com.ims.model.Order;

public class CreateOrderFragment extends Fragment {
    private View mRoot;
    private CreateOrderViewModel mViewModel;
    private TextView mOrderGenerated;
    private Button mAddToOrder;
    private Button mAddSupplier;
    private Button mFinalizeOrder;
    PendingItemOnOrderAdapter mPendingItemOnOrderAdapter;
    private TextView mSelectSupplierInfo;
    private Order mNewestOrder;

    public static CreateOrderFragment newInstance() {
        return new CreateOrderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.create_order_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(CreateOrderViewModel.class);
        initializeAutomaticOrderGeneration();

        mOrderGenerated = mRoot.findViewById(R.id.generatedOrderNumber);
        mSelectSupplierInfo = mRoot.findViewById(R.id.supplierInfoLabel);
        mAddToOrder = mRoot.findViewById(R.id.addToOrder);
        mFinalizeOrder = mRoot.findViewById(R.id.finalize);

        mFinalizeOrder.setOnClickListener(v->{
            updateRoom();


            //todo validate supplier
            mViewModel.setNewestOrderToFinalized();
            mViewModel.getNewestOrder().removeObservers(this);
            refresh();
        });

        mViewModel.getNewestOrder().observe(this, order -> {
            mNewestOrder = order;
        });

        //todo add to order should have a validation table if the supplier makes the item or not
        //and to check this before submitting when pressing the button

        mAddToOrder.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SelectApprovalInventoryActivity.class);
            startActivity(intent);
        });

        mAddSupplier = mRoot.findViewById(R.id.selectSupplierButton);

        mAddSupplier.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SelectSupplierActivity.class);
            startActivity(intent);
        });

        return mRoot;
    }

    @Override
    public void onStop() {
        super.onStop();
        updateRoom();
    }

    private void updateRoom() {
        mViewModel.insertOrderInventories(mPendingItemOnOrderAdapter.getPendingUpdates());
    }

    private void initializeAutomaticOrderGeneration() {
        mViewModel.countPendingOrders().observe(getViewLifecycleOwner(), v->{
            if (v == 0L){
                createNewOrder();
            }
            else {
                initializeOrderNumber();
                initializeRv();
            }
        });
    }
    private void createNewOrder() {
        Order order = new Order(true);
        mViewModel.insertOrder(order);
    }

    private void initializeOrderNumber() {
        mViewModel.getNewestOrder().removeObservers(this);
        mViewModel.getNewestOrder().observe(getViewLifecycleOwner(), Order->{
            try {
                mOrderGenerated.setText(mRoot.getResources().getString(R.string.prefix_generated_order_number).concat(Long.toString(Order.getOrderNumber())));
            } catch(Exception e) {
                Toast toast=Toast. makeText(mRoot.getContext().getApplicationContext(),mRoot.getResources().getString(R.string.error_order_number_generation),Toast. LENGTH_SHORT);
                toast.show();
            }
            try {
                if (Order.getSupplierNumber()!=null){
                    mSelectSupplierInfo.setText(getResources().getString(R.string.prefix_supplier_order).concat(Order.getSupplierNumber()));
                    mAddSupplier.setVisibility(View.GONE);
                }
            } catch (Exception w) {
                Toast toast=Toast. makeText(mRoot.getContext().getApplicationContext(),mRoot.getResources().getString(R.string.error_supplier),Toast. LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void initializeRv() {
        RecyclerView recyclerView = mRoot.findViewById(R.id.addedToOrderRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRoot.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        mPendingItemOnOrderAdapter = new PendingItemOnOrderAdapter(mRoot.getContext());
        mViewModel.getAllPendingInventoryOrders().observe(this,mPendingItemOnOrderAdapter::submitList);
        recyclerView.setAdapter(mPendingItemOnOrderAdapter);

    }

    //credit: https://stackoverflow.com/questions/20702333/refresh-fragment-at-reload
    private void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }
}
