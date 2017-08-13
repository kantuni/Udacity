package com.example.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

  /*
   * This tag will be used for logging. It is best practice to use the class's name using
   * getSimpleName as that will greatly help to identify the location from which your logs are
   * being posted.
   */

  private static final String TAG = MainActivity.class.getSimpleName();
  private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

  // Constant values for the names of each respective lifecycle callback
  private static final String ON_CREATE = "onCreate";
  private static final String ON_START = "onStart";
  private static final String ON_RESUME = "onResume";
  private static final String ON_PAUSE = "onPause";
  private static final String ON_STOP = "onStop";
  private static final String ON_RESTART = "onRestart";
  private static final String ON_DESTROY = "onDestroy";
  private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";

  /*
   * This TextView will contain a running log of every lifecycle callback method called from this
   * Activity. This TextView can be reset to its default state by clicking the Button labeled
   * "Reset Log"
   */

  private TextView mLifecycleDisplay;
  private static ArrayList<String> mLifecycleCallbacks = new ArrayList<>();

  /**
   * Called when the activity is first created. This is where you should do all of your normal
   * static set up: create views, bind data to lists, etc.
   * <p>
   * Always followed by onStart().
   *
   * @param savedInstanceState The Activity's previously frozen state, if there was one.
   */

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mLifecycleDisplay = findViewById(R.id.tv_lifecycle_events_display);

    if (savedInstanceState != null) {
      if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
        mLifecycleDisplay.setText(savedInstanceState.getString(LIFECYCLE_CALLBACKS_TEXT_KEY));
      }
    }

    for (int i = mLifecycleCallbacks.size() - 1; i >= 0; --i) {
      mLifecycleDisplay.append(mLifecycleCallbacks.get(i) + "\n");
    }
    mLifecycleCallbacks.clear();

    logAndAppend(ON_CREATE);
  }

  @Override
  protected void onStart() {
    super.onStart();
    logAndAppend(ON_START);
  }

  @Override
  protected void onResume() {
    super.onResume();
    logAndAppend(ON_RESUME);
  }

  @Override
  protected void onPause() {
    super.onPause();
    logAndAppend(ON_PAUSE);
  }

  @Override
  protected void onStop() {
    super.onStop();
    mLifecycleCallbacks.add(0, ON_STOP);
    logAndAppend(ON_STOP);
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    logAndAppend(ON_RESTART);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mLifecycleCallbacks.add(0, ON_DESTROY);
    logAndAppend(ON_DESTROY);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    logAndAppend(ON_SAVE_INSTANCE_STATE);
    outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, mLifecycleDisplay.getText().toString());
  }

  /**
   * Logs to the console and appends the lifecycle method name to the TextView so that you can
   * view the series of method callbacks that are called both from the app and from within
   * Android Studio's Logcat.
   *
   * @param lifecycleEvent The name of the event to be logged.
   */

  private void logAndAppend(String lifecycleEvent) {
    Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
    mLifecycleDisplay.append(lifecycleEvent + "\n");
  }

  /**
   * This method resets the contents of the TextView to its default text of "Lifecycle callbacks"
   *
   * @param view The View that was clicked. In this case, it is the Button from our layout.
   */

  public void resetLifecycleDisplay(View view) {
    mLifecycleDisplay.setText("Lifecycle callbacks:\n");
  }
}
