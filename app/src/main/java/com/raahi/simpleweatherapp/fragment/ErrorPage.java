package com.raahi.simpleweatherapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.raahi.simpleweatherapp.R;

/**
 * Created by Raahi on 07-07-2018.
 */

public class ErrorPage extends Fragment implements View.OnClickListener {
    private Button btnRetry;
    private OnRefreshButtonListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.error_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnRetry = view.findViewById(R.id.retry);
        btnRetry.setOnClickListener(this);
    }

    public static ErrorPage newInstance() {
        ErrorPage fragment = new ErrorPage();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retry:
                mListener.onRefresh();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRefreshButtonListener) {
            mListener = (OnRefreshButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnRefreshButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnRefreshButtonListener {
        void onRefresh();
    }
}