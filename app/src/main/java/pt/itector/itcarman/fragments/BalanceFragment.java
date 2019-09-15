package pt.itector.itcarman.fragments;

import android.graphics.Paint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.tankery.lib.circularseekbar.CircularSeekBar;
import pt.itector.itcarman.R;
import pt.itector.itcarman.utils.DBUtils;


/**
 * Created by me on 31/07/2019
 */
public class BalanceFragment extends Fragment {
    private double mCurrentBalance;

    private TextView mCurrentBalance_tv;
    private CircularSeekBar mCurrentBalance_csb;

    public static BalanceFragment newInstance(double currentBalance){
        BalanceFragment instance = new BalanceFragment();

        Bundle args = new Bundle();
        args.putDouble(DBUtils.CURRENT_BALANCE_TAG, currentBalance);

        instance.setArguments(args);

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_balance, container, false);

        mCurrentBalance = getArguments().getDouble(DBUtils.CURRENT_BALANCE_TAG, -999999);

        bindViews(rootView);

        return rootView;
    }

    private void bindViews(View rootView) {
        mCurrentBalance_tv = rootView.findViewById(R.id.current_balance_tv);
        if(mCurrentBalance_tv != null){
            mCurrentBalance_tv.setText(String.format("%.2f", mCurrentBalance) + "€");

            if(mCurrentBalance < 0 )
                mCurrentBalance_tv.setTextColor(getResources().getColor(R.color.customRed));
            else
                mCurrentBalance_tv.setTextColor(getResources().getColor(R.color.gray_darker));
        }


        mCurrentBalance_csb = rootView.findViewById(R.id.current_balance_csb);
        if(mCurrentBalance_csb != null){
            setupCircularSeekBar();
        }

    }

    private void setupCircularSeekBar() {
        mCurrentBalance_csb.setCircleStyle(Paint.Cap.BUTT);
        mCurrentBalance_csb.setPointerStrokeWidth(20);
        mCurrentBalance_csb.setMax(100);
        mCurrentBalance_csb.setProgress((float) ((mCurrentBalance <= 0) ? 0 : mCurrentBalance));
    }
}
