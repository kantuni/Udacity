package com.example.intents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button goBtn = findViewById(R.id.btn_go);
    goBtn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        Context context = MainActivity.this;
        Class destinationActivity = ChildActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        MainActivity.this.startActivity(intent);
      }
    });
  }
}
