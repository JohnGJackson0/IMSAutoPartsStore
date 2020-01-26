package com.ims.main.ui.order.createorder;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Order;
import com.ims.model.OrderInventory;

import java.util.List;

public class CreateOrderViewModel extends AndroidViewModel {
    private AppRepository mRepository;
    private LiveData<PagedList<OrderInventory>> mOrderInventory;

    public CreateOrderViewModel(Application app) {
        super(app);
        mRepository = new AppRepository(app);
    }

    public void insertOrder(Order a) {
        mRepository.insertOrder(a);
    }

    public LiveData<Order> getNewestOrder(){
        return mRepository.getNewestOrder();
    }

    public LiveData<PagedList<OrderInventory>> getAllPendingInventoryOrders() {
        mOrderInventory = new LivePagedListBuilder<>(
                mRepository.getPendingInventoryOrders(), 15)
                .build();
        return mOrderInventory;
    }

    public void setNewestOrderToFinalized() {
        mRepository.finalizeNewestOrder();
    }

    public void insertOrderInventories(List<OrderInventory> a){
        mRepository.insertOrderInventories(a);
    }
    public LiveData<Long> countPendingOrders() {
        return mRepository.countPendingOrders();
    }
}
