package com.ims.main.ui.order.orderfinalization;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ims.main.R;
import com.ims.main.ui.order.selectorder.SelectOrderActivity;
import com.ims.model.Order;

public class OrderFinalizationFragment extends Fragment {
    private View mRoot;
    private Button mSelectOrder;
    private OrderFinalizationViewModel mViewModel;
    private Button mAcceptOrder;
    private Button mRejectOrder;
    private Order mCurrentOrder;
    private FinalizedItemInformationAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.order_finalization_fragment, container, false);
        mSelectOrder = mRoot.findViewById(R.id.selectOrderForFinalization);
        mAcceptOrder = mRoot.findViewById(R.id.acceptOrder);
        mRejectOrder = mRoot.findViewById(R.id.rejectOrder);
        mViewModel = ViewModelProviders.of(this).get(OrderFinalizationViewModel.class);

        mSelectOrder.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SelectOrderActivity.class);
            intent.putExtra(SelectOrderActivity.KEY_EXTRA, SelectOrderActivity.STATE_CREATION);
            startActivity(intent);
        });
        mViewModel.getOrderInFinalizationStatus().removeObservers(this);
        mViewModel.getOrderNumberInFinalizationStatus().removeObservers(this);

        mAcceptOrder.setOnClickListener(v->{
            updateRoom();
            if(mCurrentOrder != null){
                mCurrentOrder.setPending(false);
                mCurrentOrder.setOnFinalization(false);
                mCurrentOrder.setFinished(true);
                mViewModel.updateOrder(mCurrentOrder);
                refresh();
            } else {
                Toast.makeText(mRoot.getContext(), mRoot.getResources().getString(R.string.no_current_order_error), Toast.LENGTH_SHORT).show();
            }

        });

        mRejectOrder.setOnClickListener(v->{
            if(mCurrentOrder!=null) {
                mViewModel.deleteOrder(mCurrentOrder);
            } else {
                Toast.makeText(mRoot.getContext(), mRoot.getResources().getString(R.string.no_current_order_error), Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.getOrderNumberInFinalizationStatus().observe(this, aLong -> {

            if(aLong != null){
                createRecyclerView(aLong);
            }

        });

        mViewModel.getOrderInFinalizationStatus().observe(this, s-> {
            mCurrentOrder = s;
        });

        return mRoot;
    }

    protected void updateRoom() {
        mViewModel.updateItems(mAdapter.getUpdateList());

    }

    public static OrderFinalizationFragment newInstance() {
        return new OrderFinalizationFragment();
    }

    private void createRecyclerView(Long orderNumber){
        RecyclerView recyclerView = mRoot.findViewById(R.id.orderInventoryAndItemInfoRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRoot.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new FinalizedItemInformationAdapter(mRoot.getContext());
        mViewModel.getOrderInventoryAndItemData(orderNumber).observe(this, mAdapter::submitList);
        recyclerView.setAdapter(mAdapter);
    }

    private void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }

}
