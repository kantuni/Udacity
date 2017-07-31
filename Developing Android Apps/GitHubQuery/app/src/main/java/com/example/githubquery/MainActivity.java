package com.example.githubquery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    EditText searchBoxEditText = findViewById(R.id.et_search_box);
    TextView urlDisplayTextView = findViewById(R.id.tv_url_display);
    TextView searchResultsTextView = findViewById(R.id.tv_github_search_results_json);
  }
}
