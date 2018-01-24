package ru.necessitudo.app.vk_alternative.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import ru.necessitudo.app.vk_alternative.R;

/**
 * Created by olegdubrovin on 23/01/18.
 */



public class MyPreferencesFragment extends PreferenceFragment {


    public MyPreferencesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }



}
