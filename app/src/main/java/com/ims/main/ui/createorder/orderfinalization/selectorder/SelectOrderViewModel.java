package com.ims.main.ui.createorder.orderfinalization.selectorder;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.annotation.NonNull;

import com.ims.main.db.AppRepository;
import com.ims.model.Order;

public class SelectOrderViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public SelectOrderViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }
    public LiveData<PagedList<Order>> getAllOrdersNeedingFinalization() {
        return new LivePagedListBuilder<>(
                mRepository.getAllOrdersInFinalizationState(), 15)
                .build();
    }

    public void setAllOnFinalizationToFalseWhereNot(Long orderNumber) {
        mRepository.removeAllFinalizationOrdersWhereNot(orderNumber);
    }

    public void updateOrder(Order order) {
        mRepository.updateOrder(order);
    }
}
