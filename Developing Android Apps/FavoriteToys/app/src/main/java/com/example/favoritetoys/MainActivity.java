package com.example.favoritetoys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView mToysListTextView = findViewById(R.id.tv_toy_names);
    String[] toyNames = ToyBox.getToyNames();
    for (String name : toyNames) {
      mToysListTextView.append(name + "\n");
    }
  }
}
