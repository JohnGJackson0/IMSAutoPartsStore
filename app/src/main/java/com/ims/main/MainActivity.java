package com.ims.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.ims.main.ui.order.createorder.ManageOrdersActivity;
import com.ims.main.ui.customers.ViewCustomersFragment;
import com.ims.main.ui.gatewayactivity.GatewayFragment;
import com.ims.main.ui.inventory.ErrorCallback;
import com.ims.main.ui.inventory.InventoryFragment;
import com.ims.main.ui.inventory.UpdateItemPendingQuantityCallback;
import com.ims.main.ui.order.specialtyorders.SpecialtyOrdersActivity;
import com.ims.main.ui.suppliers.SupplierFragment;
import com.ims.main.util.Validator;
import com.ims.model.Customer;
import com.ims.model.Item;
import com.ims.model.Supplier;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements ErrorCallback, UpdateItemPendingQuantityCallback {
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

        Supplier castrol = new Supplier("129F",true,"Castrol","15556 Fake Address Ln US", "Fake Name","FakeEmail@gmail.com","9998756309");;
        Item sample = new Item("12556G157","129F",true,"Castrol GTX 5W-30 HP Motor Oil",15,0, new BigDecimal("15.23"));
        Item sample2 = new Item("12556G158","129F",true,"Castrol GTX 10W-40 HP Motor Oil",10,0, new BigDecimal("19.95"));

        Customer one = new Customer(0L,"Ralph", "Lauren","ralph@gmail.com", "1556 Fake ST FakeCity, Mi 48111","3133133133");
        Customer two = new Customer(1L,"Not Ralph", "IamNotRalph","notRalph@gmail.com","1556 Fake ST FakeCity, Mi 48111","3133133133");

        mViewModel.insertSupplier(castrol);
        mViewModel.insertInventory(sample);
        mViewModel.insertInventory(sample2);
        mViewModel.insertCustomer(one);
        mViewModel.insertCustomer(two);
    }

    public void openSupplier(View view) {
        replaceFragment(SupplierFragment.newInstance());
    }

    public void openInventory(View view) {
        replaceFragment(InventoryFragment.newInstance());
    }

    public void openOrders(View view) {
        Intent intent = new Intent(MainActivity.this, ManageOrdersActivity.class);
        MainActivity.this.startActivity(intent);
    }

    private void replaceFragment (Fragment fragment){
        if (!isInBackStack(fragment)){
            mFragmentTransaction.beginTransaction()
                    .replace(R.id.containerMain, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
        } else {
            mFragmentTransaction.beginTransaction()
                    .replace(R.id.containerMain, fragment)
                    .commit();
        }
    }

    private boolean isInBackStack(Fragment fragment){
        return getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getName(),0);
    }

    @Override
    public void unknownError() {
        createToast(getResources().getString(R.string.unknown_error));
    }

    @Override
    public void blankError() {
        createToast(getResources().getString(R.string.error_blank));
    }

    @Override
    public void updateItem(Item a, String req) {
        try {
            if(Validator.isNumeric(req)) {
                a.setPendingOrder(Long.parseLong(req));
                mViewModel.updateItem(a);
                createToast(getResources().getString(R.string.pending_order_success));
            }
        } catch (Exception e) {
            createToast(getResources().getString(R.string.unknown_error));
        }
    }

    private void createToast(String message){
        Toast toast= Toast.makeText(this, message,Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
    }

    public void openCustomers(View view) {
        replaceFragment(ViewCustomersFragment.newInstance());
    }

    public void openSpecialtyOrders(View view) {
        Intent intent = new Intent(MainActivity.this, SpecialtyOrdersActivity.class);
        MainActivity.this.startActivity(intent);
    }
}
