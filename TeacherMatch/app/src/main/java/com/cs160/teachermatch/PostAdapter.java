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

import org.w3c.dom.Text;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

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

        postViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(post.getPoster().getProfilePicture()));
        postViewHolder.textViewTitle.setText(post.getTitle());
        postViewHolder.textViewPrice.setText("$$$");
        postViewHolder.textViewSchool.setText(post.getSchool());
        postViewHolder.textViewRequest.setText("Request");
        postViewHolder.textViewPoster.setText(post.getTitle());
        postViewHolder.textViewDescription.setText(post.getDescription());
        postViewHolder.textViewTeacher.setText(post.getPoster().getName());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTeacher, textViewRequest, textViewPrice, textViewSchool,
                textViewPoster, textViewTitle, textViewDescription;

        public PostViewHolder( View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTeacher = itemView.findViewById(R.id.textViewTeacherFlag);
            textViewDescription = itemView.findViewById(R.id.textViewPostDescription);
            textViewPoster = itemView.findViewById(R.id.textViewPostPoster);
            textViewRequest = itemView.findViewById(R.id.textViewRequestFlag);
            textViewSchool = itemView.findViewById(R.id.textViewPostSchool);
            textViewPrice = itemView.findViewById(R.id.textViewPriceFlag);
            textViewTitle = itemView.findViewById(R.id.textViewPostTitle);
        }
    }
}
