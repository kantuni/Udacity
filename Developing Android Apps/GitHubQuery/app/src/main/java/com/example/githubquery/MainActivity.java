package com.example.githubquery;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    EditText searchBoxEditText = findViewById(R.id.et_search_box);
    TextView urlDisplayTextView = findViewById(R.id.tv_url_display);
    TextView searchResultsTextView = findViewById(R.id.tv_github_search_results_json);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int menuItemSelected = item.getItemId();

    if (menuItemSelected == R.id.action_search) {
      Toast.makeText(MainActivity.this, "Search is called", Toast.LENGTH_LONG).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
