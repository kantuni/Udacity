package com.example.githubquery;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.githubquery.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<String> {

  private static final String QUERY_URL_KEY = "QUERY_URL";
  private static final String RAW_JSON_KEY = "RAW_JSON";
  private final int GITHUB_SEARCH_LOADER = 1;

  private EditText mSearchBoxEditText;
  private TextView mUrlDisplayTextView;
  private TextView mSearchResultsTextView;
  private TextView mErrorMessageTextView;
  private ProgressBar mLoadingIndicator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSearchBoxEditText = findViewById(R.id.et_search_box);
    mUrlDisplayTextView = findViewById(R.id.tv_url_display);
    mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json);
    mErrorMessageTextView = findViewById(R.id.tv_error_message_display);
    mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

    if (savedInstanceState != null) {
      mSearchBoxEditText.setText(savedInstanceState.getString(QUERY_URL_KEY));
      mSearchResultsTextView.setText(savedInstanceState.getString(RAW_JSON_KEY));
    }
  }

  private void makeGitHubSearchQuery() {
    String githubQuery = mSearchBoxEditText.getText().toString();
    URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
    mUrlDisplayTextView.setText(githubSearchUrl.toString());
    new GitHubQueryTask().execute(githubSearchUrl);
  }

  private void showJSONDataView() {
    mErrorMessageTextView.setVisibility(View.INVISIBLE);
    mSearchResultsTextView.setVisibility(View.VISIBLE);
  }

  private void showErrorMessage() {
    mSearchResultsTextView.setVisibility(View.INVISIBLE);
    mErrorMessageTextView.setVisibility(View.VISIBLE);
  }

  @Override
  public Loader<String> onCreateLoader(int i, final Bundle bundle) {
    return new AsyncTaskLoader<String>(this) {
      @Override
      protected void onStartLoading() {
        super.onStartLoading();
        if (bundle == null) {
          return;
        }

        mLoadingIndicator.setVisibility(View.VISIBLE);
        forceLoad();
      }

      @Override
      public String loadInBackground() {
        String githubSearchUrl = bundle.getString(GITHUB_SEARCH_LOADER);

        if (githubSearchUrl == null || TextUtils.isEmpty(githubSearchUrl)) {
          return null;
        }

        try {
          String githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl);
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        }
      }
    };
  }

  @Override
  public void onLoadFinished(Loader<String> loader, String s) {
    mLoadingIndicator.setVisibility(View.INVISIBLE);
    if (s != null && !s.equals("")) {
      showJSONDataView();
      mSearchResultsTextView.setText(s);
    } else {
      showErrorMessage();
    }
  }

  @Override
  public void onLoaderReset(Loader<String> loader) {
  }

  public class GitHubQueryTask extends AsyncTask<URL, Void, String> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(URL... urls) {
      URL githubSearchUrl = urls[0];
      String githubSearchResults = null;
      try {
        githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return githubSearchResults;
    }

    @Override
    protected void onPostExecute(String s) {
      mLoadingIndicator.setVisibility(View.INVISIBLE);

      if (s != null && !s.equals("")) {
        showJSONDataView();
        mSearchResultsTextView.setText(s);
      } else {
        showErrorMessage();
      }
    }
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

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    String query = mSearchBoxEditText.getText().toString();
    outState.putString(QUERY_URL_KEY, query);

    String results = mSearchResultsTextView.getText().toString();
    outState.putString(RAW_JSON_KEY, results);
  }
}
