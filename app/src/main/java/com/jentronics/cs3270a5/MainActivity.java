package com.jentronics.cs3270a5;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity implements ChangeActionsFragment.OnActionClicked {
    private FragmentManager fm;
    private ChangeActionsFragment actionsFragment;
    private ChangeButtonsFragment buttonsFragment;
    private ChangeResultsFragment resultsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_change_action, new ChangeActionsFragment(), "FragActions")
                .replace(R.id.fragment_change_buttons,new ChangeButtonsFragment(), "FragButtons")
                .replace(R.id.fragment_change_result, new ChangeResultsFragment(), "FragResults")
                .commit();


        Button btnShowTimeOut = (Button) findViewById(R.id.btn_show_time_out);
        btnShowTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragmentTimeOut dialog = new DialogFragmentTimeOut();
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "timeoutDialog");
            }
        });
        Button btnShowFail = (Button) findViewById(R.id.btn_show_fail);
        btnShowFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragmentIncorrectChange dialog = new DialogFragmentIncorrectChange();
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "failDialog");
            }
        });
        Button btnShowWinner = (Button) findViewById(R.id.btn_show_win);
        btnShowWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragmentWinner dialog = new DialogFragmentWinner();
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "winDialog");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_set_max:
                action_set_max();
                return true;
            case R.id.action_zero_score:
                action_zero_score();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        actionsFragment = (ChangeActionsFragment) fm.findFragmentByTag("FragActions");
        buttonsFragment = (ChangeButtonsFragment) fm.findFragmentByTag("FragButtons");
        resultsFragment = (ChangeResultsFragment) fm.findFragmentByTag("FragResults");
    }

    private void action_set_max() {
        Toast.makeText(this, R.string.option_set_max_title, Toast.LENGTH_SHORT).show();
    }
    private void action_zero_score() {
        actionsFragment.updateCorrectCount(0);
        Toast.makeText(this, R.string.option_zero_score_title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReStart() {
        resultsFragment.clearChange();
        // todo reset the timer
    }

    @Override
    public void onNewAmount(double newAmount) {
        resultsFragment.setChangeToMake(new BigDecimal(newAmount));
    }
}
