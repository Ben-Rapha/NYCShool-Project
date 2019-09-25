package com.otto.nycschools.Model;

public class SatScores {

   private String school_name,
    
    sat_critical_reading_avg_score,
            sat_math_avg_score,
            sat_writing_avg_score,
           dbn;

    public SatScores(String mReadingAverageScore,
                     String mMathAverageScore,
                     String mWritingAverageScore,
                     String mDbn) {
        
        this.sat_critical_reading_avg_score = mReadingAverageScore;
        this. sat_math_avg_score = mMathAverageScore;
        this. sat_writing_avg_score = mWritingAverageScore;
        this.dbn = mDbn;
    }


    public String getSchoolName() {
        return school_name;
    }

    public void setSchoolName(String mSchoolName) {
        this.school_name = mSchoolName;
    }

    public String getReadingAverageScore() {
        return sat_critical_reading_avg_score;
    }

    public void setReadingAverageScore(String mReadingAverageScore) {
        this.sat_critical_reading_avg_score = mReadingAverageScore;
    }

    public String getMathAverageScore() {
        return  sat_math_avg_score;
    }

    public void setMathAverageScore(String mMathAverageScore) {
        this. sat_math_avg_score = mMathAverageScore;
    }

    public String getWritingAverageScore() {
        return  sat_writing_avg_score;
    }

    public void setWritingAverageScore(String mWritingAverageScore) {
        this. sat_writing_avg_score = mWritingAverageScore;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String mDbn) {
        this.dbn = mDbn;
    }
}
