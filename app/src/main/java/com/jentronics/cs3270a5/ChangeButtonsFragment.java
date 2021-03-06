package com.jentronics.cs3270a5;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.math.BigDecimal;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeButtonsFragment extends Fragment {
    private View root;
    private OnButtonsFragment mCallback;

    interface OnButtonsFragment {
        void onButtonClick(BigDecimal amount);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (OnButtonsFragment) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(getString(R.string.err_buttonsfrag));
        }
    }
    public ChangeButtonsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_change_buttons, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();
        Button btn1Cents = (Button) root.findViewById(R.id.button1cents);
        Button btn5Cents = (Button) root.findViewById(R.id.button5cents);
        Button btn10Cents = (Button) root.findViewById(R.id.button10cents);
        Button btn25Cents = (Button) root.findViewById(R.id.button25cents);
        Button btn50Cents = (Button) root.findViewById(R.id.button50cents);
        Button btn1Dollar = (Button) root.findViewById(R.id.button1dollars);
        Button btn5Dollar = (Button) root.findViewById(R.id.button5dollars);
        Button btn10Dollar = (Button) root.findViewById(R.id.button10dollars);
        Button btn20Dollar = (Button) root.findViewById(R.id.button20dollars);
        Button btn50Dollar = (Button) root.findViewById(R.id.button50dollars);

        View.OnClickListener foo = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int myBtn = view.getId();
                BigDecimal amount = BigDecimal.ZERO;
                switch(myBtn) {
                    case R.id.button1cents:
                        amount = amount.add(new BigDecimal("0.01"));
                        break;

                    case R.id.button5cents:
                        amount = amount.add(new BigDecimal("0.05"));
                        break;

                    case R.id.button10cents:
                        amount = amount.add(new BigDecimal("0.10"));
                        break;

                    case R.id.button25cents:
                        amount = amount.add(new BigDecimal("0.25"));
                        break;

                    case R.id.button50cents:
                        amount = amount.add(new BigDecimal("0.50"));
                        break;

                    case R.id.button1dollars:
                        amount = amount.add(new BigDecimal("1.00"));
                        break;

                    case R.id.button5dollars:
                        amount = amount.add(new BigDecimal("5.00"));
                        break;

                    case R.id.button10dollars:
                        amount = amount.add(new BigDecimal("10.00"));
                        break;

                    case R.id.button20dollars:
                        amount = amount.add(new BigDecimal("20.00"));
                        break;

                    case R.id.button50dollars:
                        amount = amount.add(new BigDecimal("50.00"));
                        break;
                    default:
                        amount = amount.add(new BigDecimal("0"));
                }
                mCallback.onButtonClick(amount);
                Log.d("button", String.valueOf(amount));
            }
        };

        btn1Cents.setOnClickListener(foo);
        btn5Cents.setOnClickListener(foo);
        btn10Cents.setOnClickListener(foo);
        btn25Cents.setOnClickListener(foo);
        btn50Cents.setOnClickListener(foo);
        btn1Dollar.setOnClickListener(foo);
        btn5Dollar.setOnClickListener(foo);
        btn10Dollar.setOnClickListener(foo);
        btn20Dollar.setOnClickListener(foo);
        btn50Dollar.setOnClickListener(foo);

    }



}
