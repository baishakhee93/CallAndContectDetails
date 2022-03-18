package com.shakhee.contentproviderexample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContactRecieveAdapter extends RecyclerView.Adapter<ContactRecieveAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Context mcontext;
    private List<ContactModel> ContactModel;


    public ContactRecieveAdapter(Context context, List<ContactModel> ContactlModelist) {
        mcontext = context;
        ContactModel = ContactlModelist;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(mcontext);
        View view = layoutInflater.inflate(R.layout.contact_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.contact_name.setText(ContactModel.get(position).getName());
        holder.contact_number.setText(ContactModel.get(position).getNumbers());
    }

    @Override
    public int getItemCount() {
        return ContactModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contact_name, contact_number;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            contact_name = itemView.findViewById(R.id.person);
            contact_number = itemView.findViewById(R.id.phone);
        }
    }
}
