package com.kodexlabs.ntanganaadmin;

/**
 * Created by Aritra on 05-12-2017.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class AddFeeds extends Fragment {
    private ImageButton ib1;
    private Button b1;
    private StorageReference mStorage;
    private EditText et1,et2;

    private static final int GALLERY_INTENT = 2;
    private ProgressDialog mProgress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.addfeeds, container, false);
        ib1 = (ImageButton) rootView.findViewById(R.id.ib1);

        mStorage = FirebaseStorage.getInstance().getReference();

        b1=(Button)rootView.findViewById(R.id.b1);
        et1=(EditText) rootView.findViewById(R.id.et1);
        et2=(EditText)rootView.findViewById(R.id.et2);


        mProgress = new ProgressDialog(getContext());
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                Toast.makeText(getContext(),"got",Toast.LENGTH_LONG).show();
                startActivityForResult(intent, GALLERY_INTENT);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = et1.getText().toString();
                String value2 = et2.getText().toString();
                DatabaseReference db_node = FirebaseDatabase.getInstance().getReference().getRoot()
                        .child("nitrangana").child("feeds").push();
                db_node.child("heading").setValue(value1);
                db_node.child("description").setValue(value2);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == -1 ) {
            mProgress.setMessage("Uploading... ");
            mProgress.show();
            Uri uri =data.getData();
            StorageReference filepath = mStorage.child("photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgress.dismiss();
                    Uri downloadurl = taskSnapshot.getDownloadUrl();
                    Picasso.with(getContext()).load(downloadurl).fit().centerCrop().into(ib1);
                    Toast.makeText(getContext(), "photo uploaded", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
