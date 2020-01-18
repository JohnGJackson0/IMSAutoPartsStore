package com.ims.main.ui.createorderactivity.createorder.selectapprovedinventory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Item;
import com.ims.model.Order;
import com.ims.model.OrderInventory;

public class SelectApprovedInventoryViewModel extends AndroidViewModel {
    private LiveData<PagedList<Item>> items;
    private AppRepository mRepository;

    protected SelectApprovedInventoryViewModel(Application app){
        super(app);
        mRepository = new AppRepository(app);
    }

    protected LiveData<PagedList<Item>> getApprovedItems() {
        items = new LivePagedListBuilder<>(
                mRepository.getApprovedItems(),15)
                .build();
        return items;
    }

    protected void insertOrderInventory(OrderInventory order){
        mRepository.insertOrderInventory(order);
    }

    public LiveData<Order> getNewestOrder(){
        return mRepository.getNewestOrder();
    }

}

