package com.cs160.teachermatch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCtx;
    private Post post;
    private User teacher;


    public DetailsAdapter(Context mCtx, Post post, User teacher) {
        this.mCtx = mCtx;
        this.post = post;
        this.teacher = teacher;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view;
        switch(viewType) {
            case 1:
                view = inflater.inflate(R.layout.details_card, null);
                return new PostDetailsViewHolder(view);

            case 2:
                view = inflater.inflate(R.layout.about_teacher_card, null);
                return new TeacherDetailsViewHolder(view);

            default:
                view = inflater.inflate(R.layout.post_card, null);
                return new PostViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        System.out.println(position);
        if (position == 1) {
            PostDetailsViewHolder postDetailsViewHolder = (PostDetailsViewHolder) viewHolder;
            postDetailsViewHolder.itemPicture.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.mcdougal));
            postDetailsViewHolder.deadline.setText("22 days");
            postDetailsViewHolder.itemDescription.setText(post.getDescription());

        } else if (position == 2) {
            TeacherDetailsViewHolder teacherDetailsViewHolder = (TeacherDetailsViewHolder) viewHolder;
            teacherDetailsViewHolder.classroomImage.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.teacher));
            teacherDetailsViewHolder.description.setText(teacher.getDescription());
            teacherDetailsViewHolder.moreDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToTeacherDetails(teacher);
                }
            });


        } else {
            PostViewHolder postViewHolder = (PostViewHolder) viewHolder;

            postViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(post.getPoster().getProfilePicture()));
            postViewHolder.textViewTitle.setText(post.getTitle());
            if (post.getPrice() < 10) {
                postViewHolder.textViewPrice.setText("$");
            } else if (post.getPrice() < 50) {
                postViewHolder.textViewPrice.setText("$$");
            } else {
                postViewHolder.textViewPrice.setText("$$$");
            }
            postViewHolder.textViewSchool.setText(post.getSchool());
            postViewHolder.textViewRequest.setText("REQUEST");
            postViewHolder.textViewPoster.setText(post.getPoster().getName());
            postViewHolder.textViewDescription.setText(post.getDescription());
            postViewHolder.textViewTeacher.setText("TEACHER");

        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }



    public void goToTeacherDetails(User teacher){
        Intent intent = new Intent(mCtx, TeacherProfileActivity.class);
        intent.putExtra("Teacher", teacher.getTeacherID());
        mCtx.startActivity(intent);
    }
    class TeacherDetailsViewHolder extends RecyclerView.ViewHolder {

        ImageView classroomImage;
        TextView description;
        Button moreDetailsButton;

        public TeacherDetailsViewHolder(View itemView) {
            super(itemView);

            classroomImage = itemView.findViewById(R.id.imageViewClassroom);
            description = itemView.findViewById(R.id.textViewTeacher_Content);
            moreDetailsButton = itemView.findViewById(R.id.buttonViewMore);

        }
    }

    class PostDetailsViewHolder extends RecyclerView.ViewHolder {

        ImageView itemPicture;
        TextView itemDescription, deadline;

        public PostDetailsViewHolder(View itemView) {
            super(itemView);

            itemPicture = itemView.findViewById(R.id.imageViewProductPicture);
            itemDescription = itemView.findViewById(R.id.textViewPostDetails_Content);
            deadline = itemView.findViewById(R.id.textViewPostDetails_Deadline_value);

        }

    }

}
