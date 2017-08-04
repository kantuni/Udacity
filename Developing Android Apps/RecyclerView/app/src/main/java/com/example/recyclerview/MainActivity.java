package com.example.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends Activity implements GreenAdapter.ListItemClickListener {

  private static final int NUM_LIST_ITEMS = 100;
  private GreenAdapter mAdapter;
  private RecyclerView mNumbersList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mNumbersList = findViewById(R.id.rv_numbers);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mNumbersList.setLayoutManager(layoutManager);
    mNumbersList.setHasFixedSize(true);
    mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
    mNumbersList.setAdapter(mAdapter);
  }

  @Override
  public void onListItemClick(int itemIndex) {
    System.out.println(itemIndex);
  }
}
