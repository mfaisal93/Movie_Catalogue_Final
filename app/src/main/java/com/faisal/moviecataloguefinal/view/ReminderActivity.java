package com.faisal.moviecataloguefinal.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.faisal.moviecataloguefinal.R;
import com.faisal.moviecataloguefinal.model.Movie;
import com.faisal.moviecataloguefinal.presenter.PresenterReleaseMovie;
import com.faisal.moviecataloguefinal.reminder.ReminderReceiver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ReminderActivity extends AppCompatActivity implements View.OnClickListener, ReleaseMovieView{
    Switch aSwitchDaily = null;
    Switch aSwitchRelease = null;
    List<Movie> movies = new ArrayList<>();
    Movie movie;
    String releaseMessage = "Release Movie Today :";
    private ReminderReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        aSwitchDaily = findViewById(R.id.switch_daily);
        aSwitchDaily.setOnClickListener(this);

        aSwitchRelease = findViewById(R.id.switch_release);
        aSwitchRelease.setOnClickListener(this);
        receiver = new ReminderReceiver();

        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.YEAR);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.DATE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());

        PresenterReleaseMovie presenterReleaseMovie = new PresenterReleaseMovie(this, this);
        presenterReleaseMovie.getReleaseMovie(date);

        isCheckDaily();
        isCheckRelease();

    }

    @Override
    public void getReleaseMovie(List<Movie> releaseMovie) {
        movies.clear();
        movies.addAll(releaseMovie);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_daily:
                if (aSwitchDaily.isChecked()) {
                    String dailyMessage = "Check Movie Favorite Now";
                    receiver.setReminderAlarm(this, ReminderReceiver.TYPE_DAILY, dailyMessage);
                } else {
                    receiver.cancelAlarm(this, ReminderReceiver.TYPE_DAILY);
                }
                break;
            case R.id.switch_release:
                if (aSwitchRelease.isChecked()) {
                    for (int i = 0; i < movies.size(); i++) {
                        movie = movies.get(i);
                        releaseMessage = releaseMessage + " -" + movie.title + "\n";

                    }


                    receiver.setReleaseReminderAlarm(this, ReminderReceiver.TYPE_RELEASE, releaseMessage);
                } else {
                    receiver.cancelReleaseAlarm(this, ReminderReceiver.TYPE_RELEASE);
                }
                break;
            default:
                break;
        }
    }

    private void isCheckDaily() {
        if (receiver.isAlarmSetDaily(this, ReminderReceiver.TYPE_DAILY)) {
            aSwitchDaily.setChecked(true);
        }
    }

    private void isCheckRelease() {
        if (receiver.isAlarmSetRelease(this, ReminderReceiver.TYPE_RELEASE)) {
            aSwitchRelease.setChecked(true);
        }

    }

}
