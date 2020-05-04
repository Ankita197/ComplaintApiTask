package com.ankita.complaintdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ankita.complaintdemo.R;
import com.ankita.complaintdemo.repository.CreateResponse;

import java.util.ArrayList;

public class ItemComplaintAdapter extends RecyclerView.Adapter<ItemComplaintAdapter.ItemViewHolder> {

    private static ArrayList<CreateResponse.User> pandingList=new ArrayList<>();
    Context context;
    ArrayList<CreateResponse.User> userList;

    public ItemComplaintAdapter(Context context, ArrayList<CreateResponse.User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public static ArrayList<CreateResponse.User> getPandingList() {
        return pandingList;
    }

    public static void setPandingList(ArrayList<CreateResponse.User> pandingList) {
        ItemComplaintAdapter.pandingList = pandingList;
    }

    @NonNull
    @Override
    public ItemComplaintAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complaint,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemComplaintAdapter.ItemViewHolder holder, int position) {
        holder.tvItemId.setText(userList.get(position).getCode());
        holder.tvDate.setText(userList.get(position).getCreatedDate());
        holder.tvSubjectValue.setText(userList.get(position).getDetail());
       int i= userList.get(position).getCallStatus();
       if(i==1){
           pandingList.add(userList.get(position));
       }
       setStatusColor(i,holder);
    }

    private void setStatusColor(int i, ItemViewHolder holder) {
        switch (i){
            case 1:
                holder.btnStatus.setText("Panding");
                holder.btnStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.color_green));
                break;
            case 2:
                holder.btnStatus.setText("Forward");
                holder.btnStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.color_pink));

                break;
            case 3:
                holder.btnStatus.setText("Resolved");
                holder.btnStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.color_orange));

                break;
            case 4:
                holder.btnStatus.setText("Non Repairable");
                holder.btnStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.color_yello));

                break;
            case 5:
                holder.btnStatus.setText("Ready For Delivary");
                holder.btnStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.color_purple));

                break;
            case 6:
                holder.btnStatus.setText("Waiting for conformation");
                holder.btnStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.color_blue));

                break;
            case 7:
                holder.btnStatus.setText("Cancelled");
                holder.btnStatus.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorAccent));

                break;

        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemId,tvItemName,tvSubjectValue,tvDate,btnStatus;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemId=itemView.findViewById(R.id.tvItemId);
            tvItemName=itemView.findViewById(R.id.tvItemName);
            tvSubjectValue=itemView.findViewById(R.id.tvSubjectValue);
            tvDate=itemView.findViewById(R.id.tvDate);
            btnStatus=itemView.findViewById(R.id.btnStatus);
        }
    }
}
