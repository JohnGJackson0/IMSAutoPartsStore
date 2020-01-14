package com.ims.main.ui.createorderactivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.ims.main.db.AppRepository;
import com.ims.model.Item;

public class CreateOrderViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public CreateOrderViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public void updateItem(Item a){
        mRepository.updateItem(a);
    }
}
