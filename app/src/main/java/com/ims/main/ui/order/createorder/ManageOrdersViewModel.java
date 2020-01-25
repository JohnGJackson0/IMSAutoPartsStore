package com.ims.main.ui.order.createorder;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;

import com.ims.main.db.AppRepository;
import com.ims.model.Item;

public class ManageOrdersViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public ManageOrdersViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public void updateItem(Item a){
        mRepository.updateItem(a);
    }
}
