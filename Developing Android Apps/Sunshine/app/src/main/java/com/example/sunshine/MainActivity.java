package com.example.sunshine;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.sunshine.data.SunshinePreferences;
import com.example.sunshine.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends Activity {

  private TextView mWeatherTextView;
  private TextView mErrorMessageTextView;
  private ProgressBar mLoadingIndicator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mWeatherTextView = findViewById(R.id.tv_weather_data);
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
      mWeatherTextView.setText("");
      loadWeatherData();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void loadWeatherData() {
    String location = SunshinePreferences.getPreferredWeatherLocation(this);
    URL weatherDataUrl = NetworkUtils.buildUrl(location);
    new FetchWeatherTask().execute(weatherDataUrl);
  }

  public class FetchWeatherTask extends AsyncTask<URL, Void, String> {
    @Override
    protected void onPreExecute() {
      mLoadingIndicator.setVisibility(View.VISIBLE);
      super.onPreExecute();
    }

    @Override
    protected String doInBackground(URL... urls) {
      URL query = urls[0];
      String weatherData = null;
      try {
        weatherData = NetworkUtils.getResponseFromHttpUrl(query);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return weatherData;
    }

    @Override
    protected void onPostExecute(String s) {
      mLoadingIndicator.setVisibility(View.INVISIBLE);

      if (s != null && !s.equals("")) {
        mWeatherTextView.setText(s);
        showWeatherData();
      } else {
        showErrorMessage();
      }
    }
  }

  private void showWeatherData() {
    mErrorMessageTextView.setVisibility(View.INVISIBLE);
    mWeatherTextView.setVisibility(View.VISIBLE);
  }

  private void showErrorMessage() {
    mErrorMessageTextView.setVisibility(View.VISIBLE);
    mWeatherTextView.setVisibility(View.INVISIBLE);
  }
}
