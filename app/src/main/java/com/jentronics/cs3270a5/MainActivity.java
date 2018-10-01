package com.jentronics.cs3270a5;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity
        implements ChangeActionsFragment.OnActionClicked,
        ChangeResultsFragment.OnChangeResult,
        ChangeButtonsFragment.OnButtonsFragment,
        DialogFragmentInterface
{
    private FragmentManager fm;
    private ChangeActionsFragment actionsFragment;
    private ChangeButtonsFragment buttonsFragment;
    private ChangeResultsFragment resultsFragment;

    private Boolean mainView = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showMainScreen();

    }
    public void showOptionScreen(){
        if(fm==null) fm = getSupportFragmentManager();
        fm.beginTransaction()
              //  .hide(fm.findFragmentByTag("FragActions"))
                //.hide(fm.findFragmentByTag("FragButton"))
                .replace(R.id.fragment_change_result, new OptionsFragment(), "FragResults")
                .addToBackStack(null)
                .commit();
    }

    public void showMainScreen(){
        if(fm==null) fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_change_action, new ChangeActionsFragment(), "FragActions")
                .replace(R.id.fragment_change_buttons,new ChangeButtonsFragment(), "FragButtons")
                .replace(R.id.fragment_change_result, new ChangeResultsFragment(), "FragResults")
                .commit();
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

        actionsFragment.createNewAmount(); // FIXME: Persist
    }

    private void action_set_max() {
        Toast.makeText(this, R.string.option_set_max_title, Toast.LENGTH_SHORT).show();
        showOptionScreen();
    }
    private void action_zero_score() {
        actionsFragment.updateCorrectCount(0);
        Toast.makeText(this, R.string.option_zero_score_title, Toast.LENGTH_SHORT).show();
    }

    public void startNewGame(){
        // start another game
        resultsFragment.stopGameTimer();
        actionsFragment.createNewAmount();
        resultsFragment.clearChange();
        resultsFragment.startGameTimer();
    }

    public void restartGame(){
        resultsFragment.stopGameTimer();
        resultsFragment.clearChange();
        resultsFragment.startGameTimer();

    }

    @Override
    public void onReStart() {
        resultsFragment.clearChange();
        resultsFragment.startGameTimer();
    }

    @Override
    public void onNewAmount(double newAmount) {
        resultsFragment.setChangeToMake( BigDecimal.valueOf(newAmount));
    }

    @Override
    public void onTimeOut() {
        DialogFragmentTimeOut dialog = new DialogFragmentTimeOut();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "timeoutDialog");
    }

    @Override
    public void onButtonClick(BigDecimal amount) {
        int res = resultsFragment.addChange(amount);
        if(res > 0 ) {
            Log.d("ResultsFrag", "Too Big");
            resultsFragment.stopGameTimer();
            DialogFragmentIncorrectChange dialog = new DialogFragmentIncorrectChange();
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), "failDialog");
        } else if (res < 0 ) {
            Log.d("ResultsFrag", "Too Small");
        } else {
            Log.d("ResultsFrag", "Perfect");
            resultsFragment.stopGameTimer();
            DialogFragmentWinner dialog = new DialogFragmentWinner();
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), "winDialog");

        }
    }

    @Override
    public void onWin() {
        actionsFragment.updateCorrectCount();
        startNewGame();
    }

    @Override
    public void onLose() {
        restartGame();
    }

    @Override
    public String getChangeMaxAsString(){
        return actionsFragment.getmaxChangeCentsString();
    }

    @Override
    public void setChangeMax(String value){
        Log.d("Options", value);
        actionsFragment.setChangeMax(Double.valueOf(value));
    }
}
