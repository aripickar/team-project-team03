package com.cs160.teachermatch;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class PostViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textViewTeacher, textViewRequest, textViewPrice, textViewSchool,
            textViewPoster, textViewTitle, textViewDescription;

    public PostViewHolder(final View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        textViewTeacher = itemView.findViewById(R.id.textViewTeacherFlag);
        textViewDescription = itemView.findViewById(R.id.textViewPostDescription);
        textViewPoster = itemView.findViewById(R.id.textViewPostPoster);
        textViewRequest = itemView.findViewById(R.id.textViewRequestFlag);
        textViewSchool = itemView.findViewById(R.id.textViewPostSchool);
        textViewPrice = itemView.findViewById(R.id.textViewPriceFlag);
        textViewTitle = itemView.findViewById(R.id.textViewPostTitle);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), PostDetailsActivity.class);
                //intent.putExtra("Teacher", teacher.getTeacherID());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
