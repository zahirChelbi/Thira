package kbl.chelbi.thira;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kbl.chelbi.clavier.ClavierBerber;
import kbl.chelbi.editeur.AdvancedEditText;


public class TabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.tabs, container, false);

        v.setSelected(true);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdvancedEditText editText = (AdvancedEditText) view.findViewById(R.id.editText);
        ClavierBerber sClavier =ThiraActivity.sClavier;
        editText.requestFocus();
        editText.setSelected(true);
        editText.setEnabled(true);
        sClavier.registerEditText(editText);

    }

}
