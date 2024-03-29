package com.cs160.teachermatch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

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

        String uri = post.getPoster().getProfilePicture();

        if (uri != null) {
            Picasso.with(mCtx).load(uri).into(postViewHolder.imageView);
            Log.d("loading", post.getPoster().getProfilePicture());
        }
        postViewHolder.postId = posts.get(position).getPostId();

//        postViewHolder.textViewTitle.setText(post.getTitle());
        if (post.getPrice() < 10) {
            postViewHolder.textViewPrice.setText("$");
        } else if (post.getPrice() < 50) {
            postViewHolder.textViewPrice.setText("$$");
        } else {
            System.out.println("expensive");
            postViewHolder.textViewPrice.setText("$$$");
        }

        Random random = new Random();
        double randomNumber = random.nextInt(10 - 0) + random.nextDouble();
        double roundOff = Math.round(randomNumber * 100.0) / 100.0;

        postViewHolder.textViewSchool.setText(post.getPoster().getSchoolName());
        postViewHolder.textViewRequest.setText("REQUEST");
        postViewHolder.textViewTitle.setText(post.getTitle());
        postViewHolder.textViewDescription.setText(post.getDescription());
        postViewHolder.textViewTeacher.setText("TEACHER");
        postViewHolder.textViewPoster.setText(post.getPoster().getName());
        postViewHolder.textDaysSince.setText("1 day ago");
        postViewHolder.textDistAway.setText(roundOff + " miles away");
        postViewHolder.textViewDetails.setText("View Details");

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
