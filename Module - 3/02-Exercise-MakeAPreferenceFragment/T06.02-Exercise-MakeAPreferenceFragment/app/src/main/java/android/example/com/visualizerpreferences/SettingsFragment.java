package android.example.com.visualizerpreferences;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.preference.PreferenceFragmentCompat;

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
