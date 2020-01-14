package com.ims.main.ui.createorderactivity.orderapproval;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Item;

public class OrderApprovalViewModel extends AndroidViewModel {
    private LiveData<PagedList<Item>> approvalItems;
    private AppRepository mRepository;

    public OrderApprovalViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<PagedList<Item>> getPending() {
        return new LivePagedListBuilder<>(
                mRepository.getPendingOrders(), 15)
                .build();
    }

    public void updateItem(Item a){
        mRepository.updateItem(a);
    }

}
