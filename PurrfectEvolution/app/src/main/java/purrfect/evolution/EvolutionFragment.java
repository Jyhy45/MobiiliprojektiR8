package purrfect.evolution;


import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EvolutionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public EvolutionFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EvolutionFragment newInstance() {
        EvolutionFragment fragment = new EvolutionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_evolution , container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("Evolution fragment");
        return rootView;
    }

}
