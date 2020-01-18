package com.ims.main.ui.createorderactivity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ims.main.R;
import com.ims.main.ui.createorderactivity.createorder.CreateOrderFragment;
import com.ims.main.ui.createorderactivity.orderapproval.ApprovalCallback;
import com.ims.main.ui.createorderactivity.orderapproval.ApprovalFragment;
import com.ims.main.ui.createorderactivity.orderapproval.RejectionCallback;
import com.ims.model.Item;

public class ManageOrdersActivity extends AppCompatActivity implements ApprovalCallback, RejectionCallback {
    FragmentManager mFragmentTransaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.nav_approval:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, ApprovalFragment.newInstance())
                                .commit();
                        return true;
                    case R.id.nav_order:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, CreateOrderFragment.newInstance())
                                .commit();
                        return true;
                    case R.id.nav_finalize:


                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mFragmentTransaction = getSupportFragmentManager();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ApprovalFragment.newInstance())
                .commit();
    }

    @Override
    public void approve(Item a) {
        a.setApprovedOrder(a.getPendingOrder());
        a.setPendingOrder(0);
        ViewModelProviders.of(this).get(ManageOrdersViewModel.class).updateItem(a);
    }

    @Override
    public void reject(Item a) {
        a.setPendingOrder(0);
        ViewModelProviders.of(this).get(ManageOrdersViewModel.class).updateItem(a);
    }
}
