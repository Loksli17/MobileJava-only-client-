package com.example.databaseapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

        holder.workerName.setText(worker.getName());
//        Drawable res = getResources().getDrawable(imageResource);
        holder.workerImage.setImageResource(R.drawable.image1);
        holder.workerPosition.setText(String.valueOf(worker.getPositionId()));

        holder.moreWorkerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = inflater.getContext();
                Intent intent = new Intent(ctx, WorkerViewActivity.class);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(workers == null) return 0;
        return workers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView workerName, workerPosition;
        public ImageView workerImage;
        public Button moreWorkerBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            workerName     = itemView.findViewById(R.id.nameWorkerView);
            workerPosition = itemView.findViewById(R.id.positionWorkerView);
            workerImage    = itemView.findViewById(R.id.imageWorkerView);
            moreWorkerBtn  = itemView.findViewById(R.id.moreWorkerBtn);
        }
    }
}

