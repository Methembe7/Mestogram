package com.mmoyo.mestogram.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mmoyo.mestogram.LogoutActivity;
import com.mmoyo.mestogram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogout.setVisibility(View.VISIBLE);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ClickLister is working");

                goLogoutActivity();
            }
        });

    }

    @Override
    protected void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.setLimit(20);
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error with query" + e);
                    e.printStackTrace();
                    return;
                }

                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();


                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    Log.d(TAG, "Post: " + posts.get(i).getDescription() + " username " + post.getUser().getUsername());
                }
            }
        });
    }

    private void goLogoutActivity() {
        Log.d(TAG, "Navigating to logout activity");
        Intent i = new Intent(getContext(), LogoutActivity.class);
        startActivity(i);

    }

}
