package com.jentronics.cs3270a5;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsFragment extends Fragment {
    private View root;
    private DialogFragmentInterface mCallback;
    private EditText editText;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (DialogFragmentInterface) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(getString(R.string.err_buttonsfrag));
        }
    }

    public OptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_options, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        editText = (EditText) root.findViewById(R.id.option_change_max);
        editText.setText(mCallback.getChangeMaxAsString());
        editText.addTextChangedListener(amountWatcher);

    }

    TextWatcher amountWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mCallback.setChangeMax(charSequence.toString());
            Log.d("test", "Text change to: " + charSequence);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
