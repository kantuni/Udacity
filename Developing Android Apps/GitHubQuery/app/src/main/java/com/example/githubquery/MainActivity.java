package com.example.githubquery;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import com.example.githubquery.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends Activity {

  private EditText mSearchBoxEditText;
  private TextView mUrlDisplayTextView;
  private TextView mSearchResultsTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSearchBoxEditText = findViewById(R.id.et_search_box);
    mUrlDisplayTextView = findViewById(R.id.tv_url_display);
    mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json);
  }

  private void makeGitHubSearchQuery() {
    URL url = NetworkUtils.buildUrl(mSearchBoxEditText.getText().toString());
    mUrlDisplayTextView.setText(url.toString());
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
      makeGitHubSearchQuery();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
