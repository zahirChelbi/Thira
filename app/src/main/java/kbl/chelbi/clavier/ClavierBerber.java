package kbl.chelbi.clavier;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import kbl.chelbi.editeur.AdvancedEditText;
import kbl.chelbi.listener.ClavierListener;
import kbl.chelbi.thira.R;


public class ClavierBerber {

    private Boolean isVisible ;
    private ClavierBerberView mKeyboardView;
    private Activity sActivity;
    private KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener;

    public ClavierBerber(Activity activity, int paramInt) {
        this.sActivity = activity;

        mKeyboardView = ((ClavierBerberView) this.sActivity.findViewById(paramInt));
        mKeyboardView.setKeyboard(new Keyboard(this.sActivity, R.xml.keyboard));

        this.mKeyboardView.setPreviewEnabled(false);
        isVisible = mKeyboardView.getVisibility() == View.VISIBLE;

        mOnKeyboardActionListener = new ClavierListener(mKeyboardView, sActivity);
        this.mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);

        this.sActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    public void registerEditText(AdvancedEditText edittext) {

       /** edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    afficherClavier(v);
                } else {
                    cacherClavier();
                }
            }

        });*/

        edittext.setOnClickListener(new View.OnClickListener() {
            // NOTE By setting the on click listener, we can show the custom keyboard again, by tapping on an edit box that already had focus (but that had the keyboard hidden).
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    cacherClavier();
                } else {
                    afficherClavier(v);
                }
            }
        });


        // Disable spell check (hex strings look like words to Android)
        //edittext.setInputType(edittext.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }


    private void afficherClavier(View v) {
        this.mKeyboardView.setVisibility(View.VISIBLE);
        this.mKeyboardView.setEnabled(true);
        this.isVisible = true;

        if (v != null) {
            ((InputMethodManager) sActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
        }
        v.requestFocus();
    }

    private void cacherClavier() {
        this.mKeyboardView.setVisibility(View.GONE);
        this.mKeyboardView.setEnabled(false);
        this.isVisible = false;
    }

}

