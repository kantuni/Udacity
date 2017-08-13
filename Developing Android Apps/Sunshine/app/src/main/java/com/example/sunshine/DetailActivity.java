package com.example.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    Intent intentFromMainActivity = getIntent();
    String data = intentFromMainActivity.getStringExtra(Intent.EXTRA_TEXT);
    TextView weatherDetail = findViewById(R.id.tv_weather_detail);
    weatherDetail.setText(data);
  }
}
