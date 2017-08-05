package com.example.intents;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /**
   * This method is called when the Open Website button is clicked. It will open the website
   * specified by the URL represented by the variable urlAsString using implicit Intents.
   *
   * @param v Button that was clicked.
   */

  public void onClickOpenWebsiteButton(View v) {
    String website = "https://udacity.com";
    openWebsite(website);
  }

  /**
   * This method is called when the Open Location in Map button is clicked. It will open the
   * a map to the location represented by the variable addressString using implicit Intents.
   *
   * @param v Button that was clicked.
   */

  public void onClickOpenAddressButton(View v) {
    Toast.makeText(this, "TODO: Open a map when this button is clicked", Toast.LENGTH_SHORT).show();
  }

  /**
   * This method is called when the Share Text Content button is clicked. It will simply share
   * the text contained within the String textThatYouWantToShare.
   *
   * @param v Button that was clicked.
   */

  public void onClickShareTextButton(View v) {
    Toast.makeText(this, "TODO: Share text when this is clicked", Toast.LENGTH_LONG).show();
  }

  /**
   * This is where you will create and fire off your own implicit Intent. Yours will be very
   * similar to what I've done above. You can view a list of implicit Intents on the Common
   * Intents page from the developer documentation.
   *
   * @param v Button that was clicked.
   */

  public void createYourOwn(View v) {
    Toast.makeText(this,
        "TODO: Create Your Own Implicit Intent",
        Toast.LENGTH_SHORT)
        .show();
  }

  private void openWebsite(String url) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    }
  }
}
