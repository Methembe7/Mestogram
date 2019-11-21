package com.mmoyo.mestogram.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mmoyo.mestogram.R;

public class PostsFragment extends Fragment {
    //on createView to inflate the view

    private RecyclerView rvPosts;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.rvPosts);

        //crete the adapter
        //create the data source
        //set the adapter on the recyclerview
        //set the layout manager on the recycler view
    }
}
