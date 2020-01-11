package com.ims.main.ui.mainactivity;

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

import com.ims.model.Item;
import com.ims.model.Supplier;
import com.ims.main.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View mRoot;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.main_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //test
        Supplier castrol = new Supplier("129F",true,"Castrol","15556 Fake Address Ln US", "Fake Name","FakeEmail@gmail.com","9998756309");
        mViewModel.addSupplier(castrol);
        Item sample = new Item("12556G157","129F",true,"Castrol GTX 5W-30 HP Motor Oil",15,0);
        mViewModel.addItem(sample);
        Item sample2 = new Item("12556G158","129F",true,"Castrol GTX 10W-40 HP Motor Oil",10,0);
        mViewModel.addItem(sample2);

        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createRecyclerView();
    }

    private void createRecyclerView(){
        RecyclerView recyclerView = mRoot.findViewById(R.id.items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mRoot.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ItemAdapter itemAdapter = new ItemAdapter();
        mViewModel.getAllItems().observe(this, itemAdapter::submitList);
        recyclerView.setAdapter(itemAdapter);
    }
}
