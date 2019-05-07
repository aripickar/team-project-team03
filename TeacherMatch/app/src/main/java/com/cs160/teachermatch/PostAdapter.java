package com.cs160.teachermatch;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{

    private Context mCtx;
    private List<Post> posts;

    public PostAdapter(Context mCtx, List<Post> posts) {
        this.mCtx = mCtx;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.post_card, null);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int position) {
        Post post = posts.get(position);

        if (post.getPoster().getProfilePicture() != 0) {
            postViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(post.getPoster().getProfilePicture()));
        }

//        postViewHolder.textViewTitle.setText(post.getTitle());
        if (post.getPrice() < 10) {
            postViewHolder.textViewPrice.setText("$");
        } else if (post.getPrice() < 50) {
            postViewHolder.textViewPrice.setText("$$");
        } else {
            postViewHolder.textViewPrice.setText("$$$");
        }
        postViewHolder.textViewSchool.setText(post.getSchool());
        postViewHolder.textViewRequest.setText("REQUEST");
        postViewHolder.textViewTitle.setText(post.getTitle());
        postViewHolder.textViewDescription.setText(post.getDescription());
        postViewHolder.textViewTeacher.setText("TEACHER");
        postViewHolder.textViewPoster.setText(post.getPoster().getName());
        postViewHolder.textDaysSince.setText("1 day ago");
        postViewHolder.textDistAway.setText("2.3 miles");
        postViewHolder.textViewDetails.setText("View Details");

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
