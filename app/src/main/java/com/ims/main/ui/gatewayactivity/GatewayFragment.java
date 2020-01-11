package com.ims.main.ui.gatewayactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ims.main.R;

public class GatewayFragment extends Fragment {
    View mRoot;

    public static GatewayFragment newInstance() {
        return new GatewayFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.gateway_fragment, container, false);
        return mRoot;
    }
}
