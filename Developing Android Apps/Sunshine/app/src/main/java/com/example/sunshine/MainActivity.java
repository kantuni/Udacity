package com.example.sunshine;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.sunshine.data.SunshinePreferences;
import com.example.sunshine.utilities.NetworkUtils;
import com.example.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

public class MainActivity extends Activity {

  private RecyclerView mRecyclerView;
  private ForecastAdapter mForecastAdapter;
  private TextView mErrorMessageTextView;
  private ProgressBar mLoadingIndicator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mRecyclerView = findViewById(R.id.rv_forecast);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setHasFixedSize(true);

    mForecastAdapter = new ForecastAdapter();
    mRecyclerView.setAdapter(mForecastAdapter);

    mErrorMessageTextView = findViewById(R.id.display_error_message);
    mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

    loadWeatherData();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.forecast, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_refresh) {
      mForecastAdapter = null;
      loadWeatherData();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void loadWeatherData() {
    showWeatherData();

    String location = SunshinePreferences.getPreferredWeatherLocation(this);
    new FetchWeatherTask().execute(location);
  }

  private void showWeatherData() {
    mErrorMessageTextView.setVisibility(View.INVISIBLE);
    mRecyclerView.setVisibility(View.VISIBLE);
  }

  private void showErrorMessage() {
    mErrorMessageTextView.setVisibility(View.VISIBLE);
    mRecyclerView.setVisibility(View.INVISIBLE);
  }

  public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected String[] doInBackground(String... params) {
      if (params.length == 0) {
        return null;
      }

      String location = params[0];
      URL weatherRequestURL = NetworkUtils.buildUrl(location);

      try {
        String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestURL);
        return OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }

    @Override
    protected void onPostExecute(String[] weatherData) {
      mLoadingIndicator.setVisibility(View.INVISIBLE);

      if (weatherData != null) {
        showWeatherData();
        mForecastAdapter.setWeatherData(weatherData);
      } else {
        showErrorMessage();
      }
    }
  }
}
