package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.douban.R;
import com.example.utils.Group;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private List<Group> mGroupList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View groupView;
        ImageView groupImage;
        TextView groupName;
        TextView groupIntro;
        public ViewHolder(View view) {
            super(view);
            groupView = view;
            groupImage = view.findViewById(R.id.pic);
            groupName = view.findViewById(R.id.name);
            groupIntro=view.findViewById(R.id.intro);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.groupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Group group = mGroupList.get(position);
                Toast.makeText(view.getContext(), "你点击了View"+ group.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.groupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Group group = mGroupList.get(position);
                Toast.makeText(view.getContext(), "你点击了小组"+ group.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = mGroupList.get(position);
        holder.groupImage.setImageResource(group.getImageId());
        holder.groupName.setText(group.getName());
    }

    @Override
    public int getItemCount() {
        return mGroupList.size();
    }

    public GroupAdapter(List<Group> fruitList) {
        mGroupList = fruitList;
    }

}