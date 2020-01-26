package com.ims.main.ui.order.createorder.selectapprovedinventory;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Item;
import com.ims.model.Order;
import com.ims.model.OrderInventory;

public class SelectApprovedInventoryViewModel extends AndroidViewModel {
    private LiveData<PagedList<Item>> items;
    private AppRepository mRepository;

    public SelectApprovedInventoryViewModel(Application app){
        super(app);
        mRepository = new AppRepository(app);
    }

    public LiveData<PagedList<Item>> getApprovedItems() {
        items = new LivePagedListBuilder<>(
                mRepository.getApprovedItems(),15)
                .build();
        return items;
    }

    public void insertOrderInventory(OrderInventory order){
        mRepository.insertOrderInventory(order);
    }

    public LiveData<Order> getNewestOrder(){
        return mRepository.getNewestOrder();
    }

}

