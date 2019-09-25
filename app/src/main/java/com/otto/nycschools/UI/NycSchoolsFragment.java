package com.otto.nycschools.UI;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.otto.nycschools.Adapter.NycSchoolsAdapter;
import com.otto.nycschools.R;
import com.otto.nycschools.DataSource.SchoolViewModel;
import com.otto.nycschools.Model.Schools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NycSchoolsFragment extends Fragment {

    @BindView(R.id.nyc_school_recycler_view)
    RecyclerView mSchoolListRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.loading)
    TextView mLoadingTextView;

    private NycSchoolsAdapter nycSchoolsAdapter;

    private View view;

    private List<Schools> mNycSchoolList;

    private SchoolViewModel schoolViewModel;

    public NycSchoolsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.nyc_schools_fragment, container,
                false);
        ButterKnife.bind(this, view);

        mNycSchoolList = new ArrayList<>();

        nycSchoolsAdapter = new NycSchoolsAdapter(getContext(),mNycSchoolList);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());

        mSchoolListRecyclerView.setLayoutManager(mLinearLayoutManager);

        mSchoolListRecyclerView.addItemDecoration
                (new DividerItemDecoration(Objects.requireNonNull(getContext())
                        , DividerItemDecoration.VERTICAL));

        mSchoolListRecyclerView.setAdapter(nycSchoolsAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        /**
         * check internet connection
         */
        if (!isNetworkConnected()){
            mLoadingTextView.setText(getString(R.string.connect_device));
            mLoadingTextView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            mSchoolListRecyclerView.setVisibility(View.GONE);
        } else{

            mLoadingTextView.setText(getString(R.string.connect_device));
            mLoadingTextView.setVisibility(View.GONE);
            mSchoolListRecyclerView.setVisibility(View.VISIBLE);
        }
        nycSchoolsAdapter.setListener(schools -> {

            schoolViewModel.setSchoolData(schools);

            final NavController navController = Navigation.findNavController(view);

            navController.navigate(R.id.action_nycSchoolsFragment2_to_displayScoresFragment2);
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        schoolViewModel =
                ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SchoolViewModel.class);

        schoolViewModel.getNycSchoolsList().observe(getViewLifecycleOwner(),
                schools -> {
                    if (schools != null) {
                        progressBar.setVisibility(View.GONE);
                        mLoadingTextView.setVisibility(View.GONE);
                        mNycSchoolList.addAll(Arrays.asList(schools));
                        nycSchoolsAdapter.
                                updateNycSchoolsAdapter(mNycSchoolList);
                    }
                });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(getContext()).
                getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
