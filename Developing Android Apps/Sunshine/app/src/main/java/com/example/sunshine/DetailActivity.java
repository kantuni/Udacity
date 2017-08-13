package com.example.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends Activity {

  TextView mWeatherDetail;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    Intent intentFromMainActivity = getIntent();
    String data = intentFromMainActivity.getStringExtra(Intent.EXTRA_TEXT);
    mWeatherDetail = findViewById(R.id.tv_weather_detail);
    mWeatherDetail.setText(data);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_share) {
      Intent shareIntent = new Intent(Intent.ACTION_SEND);
      shareIntent.setType("text/plain");
      shareIntent.putExtra(Intent.EXTRA_TEXT, mWeatherDetail.getText().toString());

      if (shareIntent.resolveActivity(getPackageManager()) != null) {
        startActivity(shareIntent);
        return true;
      }
    }
    return super.onOptionsItemSelected(item);
  }
}
