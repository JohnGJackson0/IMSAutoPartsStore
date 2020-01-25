package com.ims.main.ui.order.orderfinalization;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Item;
import com.ims.model.Order;
import com.ims.model.OrderInventoryAndItemInfo;

import java.util.List;

public class OrderFinalizationViewModel extends AndroidViewModel {
    private AppRepository mRepository;
    private LiveData<PagedList<OrderInventoryAndItemInfo>> mItems;

    public OrderFinalizationViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<PagedList<OrderInventoryAndItemInfo>> getOrderInventoryAndItemData(Long orderNumber){
        mItems = new LivePagedListBuilder<>(
                mRepository.getOrderInventoryAndItemData(orderNumber),15)
                .build();
        return mItems;
    }

    public LiveData<Long> getOrderNumberInFinalizationStatus() {
        return mRepository.getCurrentFinalizationOrderNumber();
    }

    public LiveData<Order> getOrderInFinalizationStatus(){
        return mRepository.getCurrentFinalizationOrder();
    }

    public void updateOrder(Order order) {
        mRepository.updateOrder(order);
    }

    public void updateItems(List<Item> updateList) {
        mRepository.updateItems(updateList);
    }

    public void deleteOrder(Order mCurrentOrder) {
        mRepository.deleteOrder(mCurrentOrder);
    }
}
