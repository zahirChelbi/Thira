package kbl.chelbi.listener;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import kbl.chelbi.clavier.ClavierBerberView;
import kbl.chelbi.editeur.AdvancedEditText;


public class ClavierListener implements OnKeyboardActionListener {

    public static final int CodeCancel = -3;
    public static final int CodeClear = 55006;
    public static final int CodeDelete = -5;
    public static final int CodeEntrer = 55007;
    public static final int CodePrev = 55000;

    private ClavierBerberView sKeyboardView;
    private Activity sActivity ;


    public ClavierListener(ClavierBerberView clavier , Activity activity){
        sKeyboardView = clavier ;
        sActivity = activity;

    }


    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        View focusCurrent = sActivity.getWindow().getCurrentFocus();
        if( focusCurrent==null || focusCurrent.getClass()!=AdvancedEditText.class ) return;
        AdvancedEditText edittext = (AdvancedEditText) focusCurrent;
        Editable editable = edittext.getText();
        int start = edittext.getSelectionStart();
        // Apply the key to the edittext
        if( primaryCode==CodeCancel ) {
           editable.insert(start," ");
        } else if( primaryCode==CodeDelete ) {
            if( editable!=null && start>0 ) editable.delete(start - 1, start);
        } else if( primaryCode==CodeClear ) {
            if (editable != null) editable.clear();
        }else if( primaryCode==CodeEntrer) {
           editable.insert(start,"\n");

        } else if( primaryCode==CodePrev ) {

        } else { // insert character
            editable.insert(start, Character.toString((char) primaryCode));
        }

    }



    public void afficherClavier(View clavierView) {

        sKeyboardView.setVisibility(View.VISIBLE);
        sKeyboardView.setEnabled(true);
        if (clavierView != null) {
            ((InputMethodManager) sActivity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(clavierView.getWindowToken(),0);

        }
    }

    public void cacherClavier() {
        this.sKeyboardView.setVisibility(View.INVISIBLE);
        this.sKeyboardView.setEnabled(false);
    }


    public void inserer(Editable editText, int paramInt1, int paramInt2) {
        switch (paramInt2) {
            case 11624:
            case 11625:
            case 11626:
            case 11627:
            case 11628:
            case 11629:
            case 11630:
            case 11568:
                editText.insert(paramInt1, "ⴰ");
                return;
            case 11569:
                editText.insert(paramInt1, "ⴱ");
                return;
            case 11570:
                editText.insert(paramInt1, "ⴲ");
                return;
            case 11571:
                editText.insert(paramInt1, "ⴳ");
                return;
            case 11572:
                editText.insert(paramInt1, "ⴴ");
                return;
            case 11573:
                editText.insert(paramInt1, "ⴵ");
                return;
            case 11574:
                editText.insert(paramInt1, "ⴶ");
                return;
            case 11575:
                editText.insert(paramInt1, "ⴷ");
                return;
            case 11576:
                editText.insert(paramInt1, "ⴸ");
                return;
            case 11577:
                editText.insert(paramInt1, "ⴹ");
                return;
            case 11578:
                editText.insert(paramInt1, "ⴺ");
                return;
            case 11579:
                editText.insert(paramInt1, "ⴻ");
                return;
            case 11580:
                editText.insert(paramInt1, "ⴼ");
                return;
            case 11581:
                editText.insert(paramInt1, "ⴽ");
                return;
            case 11582:
                editText.insert(paramInt1, "ⴾ");
                return;
            case 11583:
                editText.insert(paramInt1, "ⴿ");
                return;
            case 11584:
                editText.insert(paramInt1, "ⵀ");
                return;
            case 11585:
                editText.insert(paramInt1, "ⵁ");
                return;
            case 11586:
                editText.insert(paramInt1, "ⵂ");
                return;
            case 11587:
                editText.insert(paramInt1, "ⵃ");
                return;
            case 11588:
                editText.insert(paramInt1, "ⵄ");
                return;
            case 11589:
                editText.insert(paramInt1, "ⵅ");
                return;
            case 11590:
                editText.insert(paramInt1, "ⵆ");
                return;
            case 11591:
                editText.insert(paramInt1, "ⵇ");
                return;
            case 11592:
                editText.insert(paramInt1, "ⵈ");
                return;
            case 11593:
                editText.insert(paramInt1, "ⵉ");
                return;
            case 11594:
                editText.insert(paramInt1, "ⵊ");
                return;
            case 11595:
                editText.insert(paramInt1, "ⵋ");
                return;
            case 11596:
                editText.insert(paramInt1, "ⵌ");
                return;
            case 11597:
                editText.insert(paramInt1, "ⵍ");
                return;
            case 11598:
                editText.insert(paramInt1, "ⵎ");
                return;
            case 11599:
                editText.insert(paramInt1, "ⵏ");
                return;
            case 11600:
                editText.insert(paramInt1, "ⵐ");
                return;
            case 11601:
                editText.insert(paramInt1, "ⵑ");
                return;
            case 11602:
                editText.insert(paramInt1, "ⵒ");
                return;
            case 11603:
                editText.insert(paramInt1, "ⵓ");
                return;
            case 11604:
                editText.insert(paramInt1, "ⵔ");
                return;
            case 11605:
                editText.insert(paramInt1, "ⵕ");
                return;
            case 11606:
                editText.insert(paramInt1, "ⵖ");
                return;
            case 11607:
                editText.insert(paramInt1, "ⵗ");
                return;
            case 11608:
                editText.insert(paramInt1, "ⵘ");
                return;
            case 11609:
                editText.insert(paramInt1, "ⵙ");
                return;
            case 11610:
                editText.insert(paramInt1, "ⵚ");
                return;
            case 11611:
                editText.insert(paramInt1, "ⵛ");
                return;
            case 11612:
                editText.insert(paramInt1, "ⵜ");
                return;
            case 11613:
                editText.insert(paramInt1, "ⵝ");
                return;
            case 11614:
                editText.insert(paramInt1, "ⵞ");
                return;
            case 11615:
                editText.insert(paramInt1, "ⵟ");
                return;
            case 11616:
                editText.insert(paramInt1, "ⵠ");
                return;
            case 11617:
                editText.insert(paramInt1, "ⵡ");
                return;
            case 11618:
                editText.insert(paramInt1, "ⵢ");
                return;
            case 11619:
                editText.insert(paramInt1, "ⵣ");
                return;
            case 11620:
                editText.insert(paramInt1, "ⵤ");
                return;
            case 11621:
                editText.insert(paramInt1, "ⵥ");
                return;
            case 11622:
                editText.insert(paramInt1, "ⵦ");
                return;
            case 11623:
                editText.insert(paramInt1, "ⵧ");
                return;
            default:
                editText.insert(paramInt1, " ");
                return;
        }
        //paramEditable.insert(paramInt1, "ⵯ");
    }

    public void bindEditText(int paramInt) {
        AdvancedEditText sEditText = (AdvancedEditText) this.sActivity.findViewById(paramInt);

        sEditText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean param) {
                if (param){
                    afficherClavier(view);
                    return;
                }
                cacherClavier();
            }
        });

        sEditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                afficherClavier(paramAnonymousView);
            }
        });
        sEditText.setInputType(0x80000 | sEditText.getInputType());
    }





    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

}
