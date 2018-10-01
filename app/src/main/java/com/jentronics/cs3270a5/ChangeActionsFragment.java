package com.jentronics.cs3270a5;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeActionsFragment extends Fragment {
    private View root;
    private TextView tv_correct; //tv_correct_change
    private int correct = 5;
    private OnActionClicked mCallback;

    private int maxChangeCents = 10000;
    private Random random = new Random();

    interface OnActionClicked {
        void onReStart();
        void onNewAmount(double newAmount);
    }

    public ChangeActionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_change_actions, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (OnActionClicked) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException("Must Implement OnActionClicked");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        tv_correct = (TextView) root.findViewById(R.id.tv_correct_change);
        tv_correct.setText(Integer.toString(correct));

        Button btnStartOver = (Button) root.findViewById (R.id.btn_restart);
        btnStartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onReStart();
                Log.d("Buttons", "clicked btn_restart");
            }
        });
        Button btnNewAmount = (Button) root.findViewById (R.id.btn_new_amount);
        btnNewAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAmount();
                Log.d("Buttons", "clicked btn_NewAmount");
            }
        });
    }

    public void updateCorrectCount(){
        updateCorrectCount(++correct);
    }

    public void updateCorrectCount(int newCount) {
        correct = newCount;
        tv_correct.setText(Integer.toString(correct));
    }

    public void createNewAmount() {
        int cents = random.nextInt(maxChangeCents);
        double newAmount = (double) cents/100.0;
        mCallback.onNewAmount(newAmount);
    }
}
