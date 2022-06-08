package com.gilang.assessment.androiddeveloper.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gilang.assessment.androiddeveloper.R;
import com.gilang.assessment.androiddeveloper.model.Obj_MovieTV;

import java.util.List;

public class MovieTVAdapter extends RecyclerView.Adapter<MovieTVAdapter.MyViewHolder> {

    private Context mContext;
    private List<Obj_MovieTV> menuList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView tanggal;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            tanggal = (TextView) view.findViewById(R.id.item_date);
            thumbnail = (ImageView) view.findViewById(R.id.item_imgView);
        }
    }


    public MovieTVAdapter(Context mContext, List<Obj_MovieTV> menuList) {
        this.mContext = mContext;
        this.menuList = menuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_movie_tvshow, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Obj_MovieTV menu = menuList.get(position);
        holder.title.setText(menu.getTitle());
        holder.tanggal.setText(menu.getTanggal());
        holder.thumbnail.setImageResource(menu.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}

