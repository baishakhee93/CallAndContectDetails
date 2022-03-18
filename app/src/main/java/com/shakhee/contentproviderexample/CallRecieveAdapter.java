package com.shakhee.contentproviderexample;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CallRecieveAdapter extends RecyclerView.Adapter<CallRecieveAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Context mcontext;
    private List<CallLogModel> callLogModels;


    public CallRecieveAdapter(Context context, List<CallLogModel> callLogModelList) {

        mcontext = context;
        callLogModels = callLogModelList;

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(mcontext);
        View view = layoutInflater.inflate(R.layout.call_logs_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        CallLogModel model = callLogModels.get(position);

        holder.name.setText(callLogModels.get(position).getName());
        holder.num.setText(callLogModels.get(position).getNumbers());
        holder.details.setText(callLogModels.get(position).getDuration() + callLogModels.get(position).getDate());


        holder.num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no=holder.num.getText().toString();
                String dail = "tel:" + no;
                if (ContextCompat.checkSelfPermission(mcontext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                }
                mcontext.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no=holder.num.getText().toString();
                String dail = "tel:" + no;
                if (ContextCompat.checkSelfPermission(mcontext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                }
                mcontext.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return callLogModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, num, details;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.person);
            num = itemView.findViewById(R.id.Phone_number);
            details = itemView.findViewById(R.id.details);
        }

    }
}


