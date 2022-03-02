package com.example.databaseapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.databaseapp.R;
import com.example.databaseapp.Worker.Worker;
import com.example.databaseapp.WorkerViewActivity;

import java.util.List;





public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Worker> workers;

    public WorkerAdapter(Context ctx, List<Worker> workers){
        this.inflater = LayoutInflater.from(ctx);
        this.workers  = workers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.worker_card, parent, false);
        return new WorkerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Worker worker = workers.get(position);

        //There are add data in xml-file from worker object
        holder.workerName.setText(worker.getName());
        holder.workerId.setText(String.valueOf(worker.getId()));

        String uri = "@drawable/" + worker.getImg();
        int imageResource = inflater.getContext().getResources().getIdentifier(uri, null, inflater.getContext().getPackageName());
        Drawable res = inflater.getContext().getResources().getDrawable(imageResource);
        holder.workerImage.setImageDrawable(res);

        holder.workerPosition.setText(String.valueOf(worker.getPositionId()));

        holder.moreWorkerBtn.setOnClickListener(v -> {
            Context ctx = inflater.getContext(); //This is amazing way to get current Activity =))))

            Intent intent = new Intent(ctx, WorkerViewActivity.class);
            intent.putExtra("id", worker.getId());
            ctx.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        if(workers == null) return 0;
        return workers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView workerName, workerPosition, workerId;
        public ImageView workerImage;
        public Button moreWorkerBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            workerName     = itemView.findViewById(R.id.nameWorkerView);
            workerPosition = itemView.findViewById(R.id.positionWorkerView);
            workerImage    = itemView.findViewById(R.id.imageWorkerView);
            moreWorkerBtn  = itemView.findViewById(R.id.moreWorkerBtn);
            workerId       = itemView.findViewById(R.id.idWorkerView);
        }
    }
}

