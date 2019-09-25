package com.otto.nycschools.DataSource;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.otto.nycschools.Model.SatScores;
import com.otto.nycschools.Model.Schools;

/**
 * ViewModel to persist data
 */

public class SchoolViewModel extends AndroidViewModel {

    private LiveData<Schools[]> mNycSchoolsList;


    private LiveData<SatScores[]> mSatScores;

    private MutableLiveData<Schools> schools = new MutableLiveData<>();

    public SchoolViewModel(@NonNull Application application) {
        super(application);
        Repository repository = new Repository();
        mNycSchoolsList = repository.getSchoolListData();

        mSatScores = repository.getSchoolSatScore();


    }

    public LiveData<Schools[]> getNycSchoolsList() {
        return mNycSchoolsList;
    }

    public LiveData<SatScores[]> getSatScores(){
        return mSatScores;
    }

    public void setSchoolData(Schools schoolData){

        schools.postValue(schoolData);
    }

    public LiveData<Schools> getSchool() {
        return schools;
    }

}
