package com.example.sunshine;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {
  private String[] mWeatherData;
  private ForecastAdapterOnClickHandler mClickHandler;

  interface ForecastAdapterOnClickHandler {
    void onClickHandler(String s);
  }

  ForecastAdapter(ForecastAdapterOnClickHandler handler) {
    mClickHandler = handler;
  }

  void setWeatherData(String[] weatherData) {
    mWeatherData = weatherData;
    notifyDataSetChanged();
  }

  class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView mWeatherTextView;

    ForecastAdapterViewHolder(View view) {
      super(view);
      mWeatherTextView = view.findViewById(R.id.tv_weather_data);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      mClickHandler.onClickHandler(mWeatherTextView.getText().toString());
    }
  }

  @Override
  public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    int layoutId = R.layout.forecast_list_item;
    View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    return new ForecastAdapterViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
    holder.mWeatherTextView.setText(mWeatherData[position]);
  }

  @Override
  public int getItemCount() {
    if (mWeatherData == null) {
      return 0;
    }
    return mWeatherData.length;
  }
}
