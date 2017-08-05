package com.example.intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends Activity {

  TextView mDisplayTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_child);

    mDisplayTextView = findViewById(R.id.tv_display);
    Intent intentThatStartedThisActivity = getIntent();
    if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
      mDisplayTextView.setText(intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT));
    }
  }
}
