package com.example.Home_step1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class LooperPagerAdapter extends RecyclerView.Adapter<LooperPagerAdapter.LooperPagerViewHolder> {
    List<Loopitem>loopitems;
    ViewPager2 viewPager2;

    public LooperPagerAdapter(List<Loopitem> loopitems, ViewPager2 viewPager2) {
        this.loopitems = loopitems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public LooperPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LooperPagerViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.looperpager_photo_view,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LooperPagerViewHolder holder, int position) {
        holder.setImageView(loopitems.get(position));
        if(position==loopitems.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return loopitems.size();
    }

    class LooperPagerViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;

        public LooperPagerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.looperpager_photo);
        }
        void setImageView(Loopitem loopitem){
            //此处可显示网络图片
            imageView.setImageResource(loopitem.getImage());
        }
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            loopitems.addAll(loopitems);
            notifyDataSetChanged();
        }
    };
}

