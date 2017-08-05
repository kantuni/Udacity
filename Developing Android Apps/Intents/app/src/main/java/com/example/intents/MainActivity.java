package com.example.intents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

  Button mBtn;
  EditText mEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mBtn = findViewById(R.id.button);
    mEditText = findViewById(R.id.edit_text);

    mBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Context context = MainActivity.this;
        Class destinationActivity = ChildActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        intent.putExtra(Intent.EXTRA_TEXT, mEditText.getText().toString());
        MainActivity.this.startActivity(intent);
      }
    });
  }
}
