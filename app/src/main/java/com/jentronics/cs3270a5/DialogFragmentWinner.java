package com.jentronics.cs3270a5;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentWinner extends DialogFragment {
    onDialogFragWinner mCallback;

    public interface onDialogFragWinner {
        void onWin();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (onDialogFragWinner) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(getString(R.string.err_actionsfrag));
        }
    }
    public DialogFragmentWinner() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity() );

        builder.setMessage(R.string.diag_win_body)
                .setTitle(R.string.diag_win_title)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ;
                        Log.d("TODO","What happens after win?");
                        mCallback.onWin();
                    }
                });

        return builder.create(); //super.onCreateDialog(savedInstanceState);
    }

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_out_dialog, container, false);
    }
*/

}
