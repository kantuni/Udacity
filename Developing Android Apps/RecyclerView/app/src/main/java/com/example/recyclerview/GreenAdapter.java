package com.example.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

  private int mNumberOfItems;
  private ListItemClickListener mOnClickListener;

  public interface ListItemClickListener {
    void onListItemClick(int itemIndex);
  }

  GreenAdapter(int numberOfItems, ListItemClickListener listener) {
    mNumberOfItems = numberOfItems;
    mOnClickListener = listener;
  }

  @Override
  public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    View view = LayoutInflater.from(context).inflate(R.layout.number_list_item, parent, false);
    return new NumberViewHolder(view);
  }

  @Override
  public void onBindViewHolder(NumberViewHolder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return mNumberOfItems;
  }

  class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView mListItemNumberView;

    NumberViewHolder(View itemView) {
      super(itemView);
      mListItemNumberView = itemView.findViewById(R.id.tv_item_number);
      itemView.setOnClickListener(this);
    }

    void bind(int listIndex) {
      mListItemNumberView.setText(String.valueOf(listIndex));
    }

    @Override
    public void onClick(View view) {
      mOnClickListener.onListItemClick(getAdapterPosition());
    }
  }
}
