package com.gilang.assessment.androiddeveloper.ui.movie;

import android.content.Intent;
import android.os.Bundle;

import com.gilang.assessment.androiddeveloper.R;
import com.gilang.assessment.androiddeveloper.adapter.MovieTVAdapter;
import com.gilang.assessment.androiddeveloper.adapter.RecyclerItemClickListener;
import com.gilang.assessment.androiddeveloper.model.Obj_MovieTV;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private MovieTVAdapter menuAdapter;
    private List<Obj_MovieTV> menuList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        InitControl(view);
    }

    private void InitControl(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_movie);
        recyclerView.setNestedScrollingEnabled(true);
        menuList = new ArrayList<>();
        menuAdapter = new MovieTVAdapter(getActivity(), menuList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(menuAdapter);

        prepareMenuCard();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                Intent intent = new Intent(getContext(), DetailMovieActivity.class);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getContext(), DetailMovieActivity.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(getContext(), DetailMovieActivity.class);
                                startActivity(intent3);
                                break;
                            case 3:
                                Intent intent4 = new Intent(getContext(), DetailMovieActivity.class);
                                startActivity(intent4);
                                break;
                            case 4:
                                Intent intent5 = new Intent(getContext(), DetailMovieActivity.class);
                                startActivity(intent5);
                                break;

                            default:
                                break;
                        }
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    private void prepareMenuCard() {
        int[] img = new int[]{
                R.drawable.ic_movie,
                R.drawable.ic_movie,
                R.drawable.ic_movie,
                R.drawable.ic_movie,
                R.drawable.ic_movie};

        Obj_MovieTV a = new Obj_MovieTV("Movie 1","01 Januari 2022", img[0]);
        menuList.add(a);
        a = new Obj_MovieTV("Movie 2","02 Januari 2022", img[1]);
        menuList.add(a);
        a = new Obj_MovieTV("Movie 3","03 Januari 2022", img[1]);
        menuList.add(a);
        a = new Obj_MovieTV("Movie 4","04 Januari 2022", img[1]);
        menuList.add(a);
        a = new Obj_MovieTV("Movie 5","05 Januari 2022", img[1]);
        menuList.add(a);
        menuAdapter.notifyDataSetChanged();
    }
}
