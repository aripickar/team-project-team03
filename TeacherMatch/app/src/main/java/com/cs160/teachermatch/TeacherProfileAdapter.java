package com.cs160.teachermatch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TeacherProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mCtx;
    private Post post;
    private User teacher;


    public TeacherProfileAdapter(Context mCtx, User teacher) {
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
                view = inflater.inflate(R.layout.teacher_about_card, null);
                return new TeacherProfileAdapter.PostDetailsViewHolder(view);

//            case 2:
//                view = inflater.inflate(R.layout.teacher_about_card, null);
//                return new TeacherProfileAdapter.TeacherDetailsViewHolder(view);

            default:
                view = inflater.inflate(R.layout.teacher_info_card, null);
                return new PostViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        System.out.println(position);
        String uri = teacher.getProfilePicture();

        if (uri != null) {
//            Picasso.with(mCtx).load(uri).into(R.layout.teacher_about_card.profilepic);
            Log.d("loading", teacher.getProfilePicture());
        }
    }


    @Override
    public int getItemCount() {
        return 2;
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
