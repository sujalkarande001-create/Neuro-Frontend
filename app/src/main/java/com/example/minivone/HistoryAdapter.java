package com.example.minivone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context context;
    private ArrayList<Patient> patientList;

    public HistoryAdapter(Context context, ArrayList<Patient> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_card, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Patient p = patientList.get(position);
        holder.tvName.setText("Name: " + p.name);
        holder.tvPrediction.setText("Prediction: " + p.prediction);
        holder.tvConfidence.setText("Confidence: " + p.confidence + "%");
        holder.imgPatient.setImageResource(p.imageResId);
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPatient;
        TextView tvName, tvPrediction, tvConfidence;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPatient = itemView.findViewById(R.id.imgPatient);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrediction = itemView.findViewById(R.id.tvPrediction);
            tvConfidence = itemView.findViewById(R.id.tvConfidence);
        }
    }
}