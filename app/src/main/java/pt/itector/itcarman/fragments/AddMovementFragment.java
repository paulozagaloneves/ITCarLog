package pt.itector.itcarman.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import pt.itector.itcarman.R;
import pt.itector.itcarman.activities.MainActivity;
import pt.itector.itcarman.models.MovementItem;
import pt.itector.itcarman.utils.BalanceUtils;
import pt.itector.itcarman.utils.MovementsListViewModel;

/**
 * Created by me on 01/08/2019
 */
public class AddMovementFragment extends Fragment implements MovementsListViewModel.MovementAddedCallback{
    private MovementsListViewModel movementsListViewModel;

    private double mValue;
    private double mVehicleKms;

    private Button mCancelMovement_btn;
    private Button mAddMovement_btn;
    private EditText mValue_et;
    private EditText mVehicleKms_et;

    public static AddMovementFragment newInstance() {
        AddMovementFragment instance = new AddMovementFragment();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_movement, container, false);


        bindViews(rootView);
        setupListeners();
        setupMovementsListViewModel();

        return rootView;
    }

    private void bindViews(View rootView) {
        mCancelMovement_btn = rootView.findViewById(R.id.cancel_movement_btn);
        mAddMovement_btn = rootView.findViewById(R.id.add_movement_btn);

        mValue_et = rootView.findViewById(R.id.value_et);
        mVehicleKms_et = rootView.findViewById(R.id.vehicle_kms_et);
    }

    private void setupListeners() {
        if (mCancelMovement_btn != null)
            mCancelMovement_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelMovement();
                }
            });

        if (mAddMovement_btn != null)
            mAddMovement_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addMovement();
                }
            });
    }

    private void setupMovementsListViewModel() {
        movementsListViewModel = ViewModelProviders.of(this).get(MovementsListViewModel.class);
    }

    private void addMovement() {
        if (inputIsValid()) {
            mValue = Double.parseDouble(mValue_et.getText().toString());
            mVehicleKms = Double.parseDouble((!mVehicleKms_et.getText().toString().equals(""))? mVehicleKms_et.getText().toString(): "0");


            double balanceAfterMovement = BalanceUtils.getCurrentBalance(getContext()) - mValue;

            movementsListViewModel.addMovement(getContext(), new MovementItem(mValue, mVehicleKms, balanceAfterMovement), this);
        }else
            showToast(getString(R.string.message_add_movement_failure));
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_SHORT).show();
    }

    private boolean inputIsValid() {
        if (mValue_et == null) return false;

        if (mValue_et.getText().toString().equals(""))
            return false;

        return true;
    }

    private void cancelMovement() {
        getActivity().onBackPressed();
    }

    @Override
    public void onMovementAdded(MovementItem item) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(getString(R.string.message_add_movement_success));
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }
}
