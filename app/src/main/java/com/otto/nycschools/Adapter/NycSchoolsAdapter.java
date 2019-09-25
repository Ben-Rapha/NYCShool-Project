package com.otto.nycschools.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.otto.nycschools.R;
import com.otto.nycschools.Model.Schools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to supply recyclerView with data
 */

public class NycSchoolsAdapter extends
        RecyclerView.Adapter<NycSchoolsAdapter.ViewHolder> {

    private Context context;
    private List<Schools> schoolsList;

    private OnSchoolClickListener listener;



    public NycSchoolsAdapter(Context context, List<Schools> schoolsList){
        this.context = context;
        this.schoolsList = schoolsList;
    }


    @NonNull
    @Override
    public NycSchoolsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                 int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.school_row, parent, false);

        return new NycSchoolsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NycSchoolsAdapter.ViewHolder holder,
                                 int position) {
        final Schools schools = schoolsList.get(holder.getAdapterPosition());

        holder.mSchoolName.setText(schools.getSchoolName());

        holder.mBorough.setText(schools.getBorough());

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{

        @BindView(R.id.school_name)
        TextView mSchoolName;

        @BindView(R.id.school_borough)
        TextView mBorough;



        public ViewHolder(@NonNull View dataView){
            super(dataView);
            ButterKnife.bind(this,dataView);
            dataView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {

            listener.goToSchoolDetail(schoolsList.get(getAdapterPosition()));

        }
    }


    @Override
    public int getItemCount() {
        return schoolsList.size();
    }

    public void updateNycSchoolsAdapter(List<Schools> schoolsList){
        this.schoolsList = schoolsList;
        notifyDataSetChanged();
    }

    public interface OnSchoolClickListener{
        void goToSchoolDetail(Schools schools);
    }

    public void setListener(OnSchoolClickListener listener) {
        this.listener = listener;
    }
}
