package com.ono.locatporium;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Search> listdata;

    public MoviesAdapter(List<Search> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.memory_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Search myListData = listdata.get(position);
        holder.textView.setText(myListData.getTitle());
        holder.type.setText(myListData.getType());
//        holder.imageView.setImageResource(myListData.getPoster());
        holder.relativeLayout.setOnClickListener(view -> Toast.makeText(view.getContext(), "click on item: " + myListData.getTitle(), Toast.LENGTH_LONG).show());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView, type;
        public ConstraintLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imgViewMemory);
            relativeLayout = itemView.findViewById(R.id.clMemoryItem);
        }
    }
}
