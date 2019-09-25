package com.otto.nycschools.DataSource;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.otto.nycschools.Model.SatScores;
import com.otto.nycschools.Model.Schools;
import com.otto.nycschools.Util.Util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class Repository {

    private static MutableLiveData<Schools[]> schoolsList = new MutableLiveData<>();

    private static MutableLiveData<SatScores[]> mSchoolScoreList = new MutableLiveData<>();


    private Gson gson = new GsonBuilder().create();

    LiveData<Schools[]> getSchoolListData(){
        new GetSchoolsAsyncTask().execute(Util.NYC_SCHOOL_URL);
        return schoolsList;
    }

    LiveData<SatScores[]> getSchoolSatScore(){
        new GetSchoolScoresAsyncTask().execute(Util.SAT_SCORE_URL);
        return mSchoolScoreList;
    }

    public static class GetSchoolsAsyncTask extends
            AsyncTask<String,Void, LiveData<Schools[]>>{
        private Gson gson = new GsonBuilder().create();
        OkHttpClient okHttpClient = new OkHttpClient();


        @Override
        protected LiveData<Schools[]> doInBackground(String... strings) {

            String schools;
            Request request = new Request.Builder().url(strings[0]).build();

            try {
                Response response = okHttpClient.newCall(request).execute();

                     schools = response.body().string();
                     schoolsList.postValue(gson.fromJson(schools,Schools[].class));

            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

    }

    public static class GetSchoolScoresAsyncTask
            extends AsyncTask<String,Void,LiveData<SatScores[]>>{

        private Gson gson = new GsonBuilder().create();
        OkHttpClient okHttpClient = new OkHttpClient();

        @Override
        protected LiveData<SatScores[]> doInBackground(String... strings) {

            String scoresString;

            Request request = new Request.Builder().url(strings[0]).build();

            try {
                Response response = okHttpClient.newCall(request).execute();
                scoresString = response.body().string();
                mSchoolScoreList.postValue(gson.fromJson(scoresString, SatScores[].class));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

}
