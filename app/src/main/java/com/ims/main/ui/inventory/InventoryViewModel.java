package com.ims.main.ui.inventory;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.ims.main.db.AppRepository;
import com.ims.model.Item;

public class InventoryViewModel extends AndroidViewModel {
    private LiveData<PagedList<Item>> items;
    private AppRepository mRepository;

    public InventoryViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<PagedList<Item>> getAllItems() {
        items = new LivePagedListBuilder<>(
                mRepository.getAllItems(), 15)
                .build();
        return items;
    }
}
