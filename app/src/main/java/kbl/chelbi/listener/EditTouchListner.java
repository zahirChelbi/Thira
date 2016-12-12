package kbl.chelbi.listener;

import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import kbl.chelbi.editeur.AdvancedEditText;

/**
 * Created by chelbi on 07/07/16.
 */
public class EditTouchListner implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Toast.makeText(view.getContext(), "OnTouche", Toast.LENGTH_SHORT).show();

        AdvancedEditText edit = (AdvancedEditText) view ;

        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                android.content.Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        edit.requestFocus();

        return true;
    }
}
