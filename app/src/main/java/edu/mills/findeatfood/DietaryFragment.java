package edu.mills.findeatfood;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class DietaryFragment extends Fragment {

    private List<Integer> dietOptionsIds = new ArrayList<Integer>();
    private List<Integer> allergyOptionsIds = new ArrayList<Integer>();

    OnDietOptionsSelectedListener dietOptionsSelectedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dietary, container, false);

        String[] dietOptions =
                getResources().getStringArray(R.array.dietOptionsName);
        String[] allergyOptions =
                getResources().getStringArray(R.array.allergyOptionsName);
        LinearLayout dietaryOptionsLayout =
                (LinearLayout) view.findViewById(R.id.dietaryOptionsLayout);


        for (int i = 0; i < dietOptions.length; i++) {
            CheckBox checkBox = new CheckBox(this.getActivity());
            checkBox.setText(dietOptions[i]);
            checkBox.setTextColor(getActivity().getResources().getColor(R.color.white));
            int checkBoxId = view.generateViewId();
            dietOptionsIds.add(checkBoxId);
            checkBox.setId(checkBoxId);
            dietaryOptionsLayout.addView(checkBox);
        }

        for (int i = 0; i < allergyOptions.length; i++) {
            CheckBox checkBox = new CheckBox(this.getActivity());
            checkBox.setText(allergyOptions[i]);
            checkBox.setTextColor(getActivity().getResources().getColor(R.color.white));
            int checkBoxId = view.generateViewId();
            allergyOptionsIds.add(checkBoxId);
            checkBox.setId(checkBoxId);
            dietaryOptionsLayout.addView(checkBox);
        }

        dietOptionsSelectedListener.onDietOptionsSelected(dietOptionsIds, allergyOptionsIds);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            dietOptionsSelectedListener = (OnDietOptionsSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnDietOptionsSelectedListener");
        }
    }

    public interface OnDietOptionsSelectedListener {
        void onDietOptionsSelected(List<Integer> dietOptionsIds, List<Integer> allergyOptionsIds);
    }
}
