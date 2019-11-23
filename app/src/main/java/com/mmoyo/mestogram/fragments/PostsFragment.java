package com.mmoyo.mestogram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mmoyo.mestogram.Post;
import com.mmoyo.mestogram.PostsAdapter;
import com.mmoyo.mestogram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    //on createView to inflate the view

    private RecyclerView rvPosts;
    public static final String TAG = "PostsFragment";

    protected PostsAdapter adapter;
    protected List<Post> mPosts;

    protected SwipeRefreshLayout swipeContainer;

    protected Button btnLogout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPosts = view.findViewById(R.id.rvPosts);

        swipeContainer = view.findViewById(R.id.swipeContainer);

        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setVisibility(View.GONE);

        //create the data source
        mPosts = new ArrayList<>();
        //create the adapter
        adapter = new PostsAdapter(getContext(), mPosts);
        //set the adapter on the recyclerview
        rvPosts.setAdapter(adapter);
        //set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                Log.i(TAG, "fetching new data");
                queryPosts();
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }



    protected void queryPosts(){
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error with query" + e);
                    e.printStackTrace();
                    return;
                }
//                mPosts.addAll(posts);
//               adapter.notifyDataSetChanged();

                adapter.clear();
                adapter.addAll(posts);



                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
//                    Log.d(TAG, "Post: " + posts.get(i).getDescription() + " username " + post.getUser().getUsername());
                }
            }
        });
    }

}
