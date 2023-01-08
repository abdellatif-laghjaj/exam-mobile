package com.abdellatif.exemapp.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abdellatif.exemapp.R;
import com.abdellatif.exemapp.models.Cooperative;

import java.util.ArrayList;
import java.util.List;

public class CooperativeAdapter extends RecyclerView.Adapter<CooperativeAdapter.MyViewHolder> {
    private List<Cooperative> cooperatives;
    private Context context;

    public CooperativeAdapter(Context context, List<Cooperative> cooperatives) {
        this.context = context;
        this.cooperatives = cooperatives;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cooperative_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Cooperative item = cooperatives.get(position);

        //set the data to the views
        holder.cooperativeName.setText(item.getName());
        holder.cooperativeType.setText(item.getType());
        holder.cooperativeLogo.setImageResource(item.getImage());

        //set the click listener
        holder.itemView.setOnClickListener(v -> {
            //display popup
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_cooperative);

            TextView name = dialog.findViewById(R.id.name);
            TextView type = dialog.findViewById(R.id.type);
            ImageView logo = dialog.findViewById(R.id.logo);
            Button cancelBtn = dialog.findViewById(R.id.cancel_btn);

            name.setText("Name: " + item.getName());
            type.setText("Type: " + item.getType());
            logo.setImageResource(item.getImage());

            cancelBtn.setOnClickListener(v1 -> dialog.dismiss());

            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return cooperatives.size();
    }

    //set items

    public void filterList(ArrayList<Cooperative> cooperativesList) {
        this.cooperatives = cooperativesList;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView cooperativeName, cooperativeType;
        private ImageView cooperativeLogo;

        public MyViewHolder(View itemView) {
            super(itemView);
            cooperativeName = itemView.findViewById(R.id.cooperative_name);
            cooperativeType = itemView.findViewById(R.id.cooperative_type);
            cooperativeLogo = itemView.findViewById(R.id.logo);
        }
    }
}
