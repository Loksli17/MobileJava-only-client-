package com.example.databaseapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseapp.Position.Position;
import com.example.databaseapp.R;

import java.util.ArrayList;
import java.util.List;


public class PositionAdapter extends RecyclerView.Adapter<PositionAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Position> positions;

    public PositionAdapter(Context ctx, List<Position> positions){
        this.inflater  = LayoutInflater.from(ctx);
        this.positions = positions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.position_card, parent, false);
        return new PositionAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Position positionItem = positions.get(position);

        holder.namePositionView.setText(positionItem.getName());
        holder.idPositionView.setText(String.valueOf(positionItem.getId()));
    }


    @Override
    public int getItemCount() {
        if(positions == null) return 0;
        return positions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView idPositionView, namePositionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idPositionView   = itemView.findViewById(R.id.idPositionView);
            namePositionView = itemView.findViewById(R.id.namePositionView);
        }
    }
}
