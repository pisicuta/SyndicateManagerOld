package fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.syndicatemanager.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    RadioGroup lotteryChoiceGroup;
    RadioGroup lottoDayChoiceGroup;
    RadioGroup euroDayChoiceGroup;
    RadioGroup tballDayChoiceGroup;
    TextView dayChoice;
    Button btnSave;
    Button btnCancel;
    EditText syndicateNameText;



    public SettingsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        lotteryChoiceGroup = (RadioGroup) rootView.findViewById(R.id.lottery_choice_group);
        lottoDayChoiceGroup= (RadioGroup) rootView.findViewById(R.id.lotto_days_group);
        euroDayChoiceGroup= (RadioGroup) rootView.findViewById(R.id.euro_days_group);
        tballDayChoiceGroup= (RadioGroup) rootView.findViewById(R.id.tball_days_group);
        dayChoice= (TextView) rootView.findViewById(R.id.day_choice);

        lotteryChoiceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.rbLotto:
                        dayChoice.setVisibility(View.VISIBLE);
                        euroDayChoiceGroup.setVisibility(View.INVISIBLE);
                        tballDayChoiceGroup.setVisibility(View.INVISIBLE);
                        lottoDayChoiceGroup.setVisibility(View.VISIBLE);
                        setVisibilty(rootView, View.INVISIBLE);
                        break;
                    case R.id.rbEuroMillions:
                        dayChoice.setVisibility(View.VISIBLE);
                        lottoDayChoiceGroup.setVisibility(View.INVISIBLE);
                        tballDayChoiceGroup.setVisibility(View.INVISIBLE);
                        euroDayChoiceGroup.setVisibility(View.VISIBLE);
                        setVisibilty(rootView, View.INVISIBLE);
                        break;
                    case R.id.rbThunderball:
                        dayChoice.setVisibility(View.VISIBLE);
                        lottoDayChoiceGroup.setVisibility(View.INVISIBLE);
                        euroDayChoiceGroup.setVisibility(View.INVISIBLE);
                        tballDayChoiceGroup.setVisibility(View.VISIBLE);
                        setVisibilty(rootView, View.INVISIBLE);
                        break;
                }
            }
        });

        lottoDayChoiceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected, not used in this case
                setVisibilty(rootView, View.VISIBLE);
            }
        });

        euroDayChoiceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected, not used in this case
                setVisibilty(rootView, View.VISIBLE);
            }
        });

        tballDayChoiceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected, not used in this case
                setVisibilty(rootView, View.VISIBLE);
            }
        });

//        R.id.rbLottoWednesday:
//        R.id.rbLottoSaturday:
//        R.id.rbEuroTuesday:
//        R.id.rbEuroFriday:
//        R.id.rbTballWednesday:
//        R.id.rbTballFriday:
//        R.id.rbTballSaturday
        return rootView;
    }

    private void setVisibilty(View v, int visibilty) {
        btnSave = (Button) v.findViewById(R.id.btnSave);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        syndicateNameText = (EditText) v.findViewById(R.id.syndicateNameText);

        btnSave.setVisibility(visibilty);
        btnCancel.setVisibility(visibilty);
        syndicateNameText.setVisibility(visibilty);

    }

}
