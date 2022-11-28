package com.ono.locatporium;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SavedActivitiesAdapter extends RecyclerView.Adapter<SavedActivitiesAdapter.ViewHolder> {
    private List<DtoActivity> activities;

    public SavedActivitiesAdapter(List<DtoActivity> activities) {
        this.activities = activities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DtoActivity activity = activities.get(position);
        StringBuilder value = new StringBuilder().append("Activity Title : ").append(activity.getActivity() + "\n\n").append("Activity Type : ").append(activity.getType() + "\n\n").append("Participants Required : ").append(activity.getParticipants() + "\n\n").append("Price : ").append(activity.getPrice() + "\n\n").append("Activity Link : ").append(activity.getLink() + "\n\n").append("Activity Accessibility : ").append(activity.getAccessibility() + "\n\n");
        holder.tvSavedActivity.setText(value);
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSavedActivity;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvSavedActivity = itemView.findViewById(R.id.tvSavedActivity);
        }
    }
}
