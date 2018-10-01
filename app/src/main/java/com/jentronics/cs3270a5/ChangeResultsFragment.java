package com.jentronics.cs3270a5;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeResultsFragment extends Fragment {
    private View root;
    private TextView tv_changeToMake;
    private TextView tv_changeSoFar;
    private TextView tv_timeRemain;
    private NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.US);;

    private BigDecimal changeToMake = BigDecimal.ZERO;
    private BigDecimal changeSoFar  = BigDecimal.ZERO;
    private int timeRemaining = 0;

    public ChangeResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_change_results, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();
        tv_changeToMake = (TextView) root.findViewById(R.id.tv_changeToMake);
        tv_changeToMake.setText(numFormat.format(changeToMake.doubleValue()));
        tv_changeSoFar = (TextView) root.findViewById(R.id.tv_totalSoFar);
        tv_changeSoFar.setText(numFormat.format(changeSoFar.doubleValue()));
        tv_timeRemain = (TextView) root.findViewById(R.id.tv_timeRemain);
        tv_timeRemain.setText(timeRemaining);
    }

    public int addChange(BigDecimal change) {

        changeSoFar = changeSoFar.add(change);
        if(tv_changeSoFar != null) tv_changeSoFar.setText(numFormat.format(changeSoFar.doubleValue()));

        return changeSoFar.compareTo(changeToMake); /* -1 less than, 0 equal, 1 over */
    }

    public void setTimeRemaining(int time){
        timeRemaining = time;
        if(tv_timeRemain != null) tv_timeRemain.setText(timeRemaining);
    }

    public void setChangeToMake(BigDecimal change) {
        changeToMake = change;
        if(tv_changeToMake != null) tv_changeToMake.setText(numFormat.format(changeToMake.doubleValue()));
        changeSoFar = BigDecimal.ZERO;
        if(tv_changeSoFar != null) tv_changeSoFar.setText(numFormat.format(changeSoFar.doubleValue()));
    }

    public void clearChange() {
        changeSoFar = BigDecimal.ZERO;
        if(tv_changeSoFar != null) tv_changeSoFar.setText(numFormat.format(changeSoFar.doubleValue()));
    }
}
