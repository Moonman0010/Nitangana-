package com.kodexlabs.ntanganaadmin;

/**
 * Created by Aritra on 05-12-2017.
 */
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.database.ChildEventListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentList extends  Fragment {
    private RecyclerView mBlogList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.studentlist_recycler, container, false);
        mBlogList=(RecyclerView)rootView.findViewById(R.id.str);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getContext()));


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            itemView = mView;
        }
        public void setName(String name){
            TextView post_name =(TextView) mView.findViewById(R.id.st_name);
            post_name.setText(name);
        }
        public void setDate(String date){
            TextView post_date =(TextView)mView.findViewById(R.id.st_date);
            post_date.setText(date);
        }

    }
}
