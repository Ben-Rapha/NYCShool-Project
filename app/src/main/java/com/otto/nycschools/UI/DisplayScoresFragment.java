package com.otto.nycschools.UI;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.otto.nycschools.R;
import com.otto.nycschools.Model.SatScores;
import com.otto.nycschools.DataSource.SchoolViewModel;
import com.otto.nycschools.Model.Schools;
import com.otto.nycschools.Util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayScoresFragment extends Fragment {

    @BindView(R.id.school_name)
    TextView mSchoolName;

    @BindView(R.id.reading_score)
    TextView mReadingScore;

    @BindView(R.id.math_score)
    TextView mMathScore;

    @BindView(R.id.writing_score)
    TextView mWritingScore;

    @BindView(R.id.no_score)
    TextView no_score;

    private List<SatScores> satScoresList;

    private SchoolViewModel schoolViewModel;

    public DisplayScoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.display_scores_fragment, container, false);

        satScoresList = new ArrayList<>();

        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        schoolViewModel =
                ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SchoolViewModel.class);


        schoolViewModel.getSatScores().observe(getViewLifecycleOwner(),
                satScoresData -> {

                    satScoresList.addAll(Arrays.asList(satScoresData));

                    boolean weHaveAMatch = false;

                   Schools schools =  schoolViewModel.getSchool().getValue();

                   for (SatScores satScores : satScoresList){
                       if (schools != null && satScores.getDbn().equals(schools.getDbn())) {
                           weHaveAMatch = true;

                           String reading = Util.READING + satScores.getReadingAverageScore();
                           String math = Util.MATH + satScores.getMathAverageScore();
                           String writing = Util.WRITING + satScores.getWritingAverageScore();

                           mSchoolName.setText(schools.getSchoolName());
                           mReadingScore.setText(reading);
                           mMathScore.setText(math);
                           mWritingScore.setText(writing);

                       }
                   }
                    setVisibility(weHaveAMatch);
                });


    }

    private void setVisibility(boolean weHaveAMatch){

        if (weHaveAMatch){
            no_score.setVisibility(View.GONE);

            mSchoolName.setVisibility(View.VISIBLE);

            mWritingScore.setVisibility(View.VISIBLE);

            mMathScore.setVisibility(View.VISIBLE);

            mReadingScore.setVisibility(View.VISIBLE);

        } else {
            no_score.setVisibility(View.VISIBLE);

            mSchoolName.setVisibility(View.GONE);

            mWritingScore.setVisibility(View.GONE);

            mMathScore.setVisibility(View.GONE);

            mReadingScore.setVisibility(View.GONE);
        }

    }

}
