package com.ims.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ims.main.ui.gatewayactivity.GatewayFragment;
import com.ims.main.ui.inventoryactivity.InventoryFragment;
import com.ims.main.ui.supplieractivity.SupplierFragment;
import com.ims.model.Item;
import com.ims.model.Supplier;

public class MainActivity extends AppCompatActivity {
    private Bundle mSavedInstantState;
    FragmentManager mFragmentTransaction;
    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mFragmentTransaction = getSupportFragmentManager();
        mSavedInstantState = savedInstanceState;
        if (mSavedInstantState == null) {
            mFragmentTransaction.beginTransaction()
                    .replace(R.id.containerMain, GatewayFragment.newInstance())
                    .commitNow();
        }

        //to be replaced with api or internal app functions from user depending on answers to project requirements
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //test
        Supplier castrol = new Supplier("129F",true,"Castrol","15556 Fake Address Ln US", "Fake Name","FakeEmail@gmail.com","9998756309");;
        Item sample = new Item("12556G157","129F",true,"Castrol GTX 5W-30 HP Motor Oil",15,0);
        Item sample2 = new Item("12556G158","129F",true,"Castrol GTX 10W-40 HP Motor Oil",10,0);

        mViewModel.insertSupplier(castrol);
        mViewModel.insertInventory(sample);
        mViewModel.insertInventory(sample2);
    }

    public void openSupplier(View view) {
        replaceFragment(SupplierFragment.newInstance());
    }

    public void openInventory(View view) {
        replaceFragment(InventoryFragment.newInstance());
    }

    private void replaceFragment (Fragment fragment){
        if (!isInBackStack(fragment)){
            mFragmentTransaction.beginTransaction()
                    .replace(R.id.containerMain, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
        }
    }

    private boolean isInBackStack(Fragment fragment){
        return getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getName(),0);
    }
}
