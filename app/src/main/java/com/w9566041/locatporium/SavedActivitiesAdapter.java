package com.w9566041.locatporium;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SavedActivitiesAdapter extends RecyclerView.Adapter<SavedActivitiesAdapter.ViewHolder> {
    public List<DtoActivity> activities;
    public Activity activityContext;
    private Executor executor = Executors.newSingleThreadExecutor();

    public SavedActivitiesAdapter(Activity activityContext, List<DtoActivity> activities) {
        this.activityContext = activityContext;
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
        final int currentPosition = position;
        final DtoActivity activity = activities.get(position);
        StringBuilder value = new StringBuilder().append("Activity Title : ").append(activity.getActivity() + "\n\n").append("Activity Type : ").append(activity.getType() + "\n\n").append("Participants Required : ").append(activity.getParticipants() + "\n\n").append("Price : ").append(activity.getPrice() + "\n\n").append("Activity Link : ").append(activity.getLink() + "\n\n").append("Activity Accessibility : ").append(activity.getAccessibility() + "\n\n");
        holder.tvSavedActivity.setText(value);

        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, value.toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Something worthy...");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                activityContext.startActivity(shareIntent);
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executor.execute(() -> DatabaseEngine.getInstance().getDBInstance().activityDao().deleteActivity(activity));
                activities.remove(currentPosition);
                notifyItemRemoved(currentPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSavedActivity;
        private ImageView ivShare, ivDelete;
        private Activity activity;

        public ViewHolder(View itemView) {
            super(itemView);
            this.activity = activity;
            this.tvSavedActivity = itemView.findViewById(R.id.tvSavedActivity);
            this.ivShare = itemView.findViewById(R.id.ivShare);
            this.ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}
