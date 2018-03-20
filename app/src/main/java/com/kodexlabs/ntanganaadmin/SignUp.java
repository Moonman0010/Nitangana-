package com.kodexlabs.ntanganaadmin;

/**
 * Created by Aritra on 05-12-2017.
 */
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
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

import static android.content.ContentValues.TAG;

public class SignUp extends Fragment{
private EditText et1,et2,et3,et4;
private TextView tv1;
private ImageButton ib1;
private Button  b1,b2;
private StorageReference mStorage;
private Firebase mRootRef;
private static final int GALLERY_INTENT = 2;
    private ProgressDialog mProgress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.signup, container, false);

        mRootRef = new Firebase("https://nitangana-8749b.firebaseio.com/nitrangana/students");

        et1 = (EditText)rootView.findViewById(R.id.suet1);
        et2= (EditText) rootView.findViewById(R.id.suet2);
        et3=(EditText)rootView.findViewById(R.id.suet3);
        et4=(EditText)rootView.findViewById(R.id.suet4);
        tv1=(TextView)rootView.findViewById(R.id.sutv);
        b1=(Button)rootView.findViewById(R.id.sub1);
        b2=(Button)rootView.findViewById(R.id.sub2);
        ib1=(ImageButton)rootView.findViewById(R.id.ib1);
        mStorage = FirebaseStorage.getInstance().getReference();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = et1.getText().toString();
                String value2 = et2.getText().toString();
                String value3 = et3.getText().toString();
                Firebase childRef = mRootRef.child(value2);
                childRef.child("name").setValue(value1);
                childRef.child("date").setValue(value3);
                Toast.makeText(getContext(),"AdDED",Toast.LENGTH_LONG).show();



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = et4.getText().toString();
                DatabaseReference db_node = FirebaseDatabase.getInstance().getReference().getRoot()
                        .child("nitrangana").child("students").child(value);
                db_node.setValue(null);
            }
        });
        mProgress = new ProgressDialog(getContext());
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                   try {

                           Intent intent = new Intent(Intent.ACTION_PICK);
                           intent.setType("image/*");
                           startActivityForResult(intent, GALLERY_INTENT);

           }catch (Exception e) {
           Toast.makeText(getContext(),"Enter roll",Toast.LENGTH_LONG).show();
           }
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
            final String value2 = et2.getText().toString();
            Uri uri =data.getData();
            StorageReference filepath = mStorage.child("photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgress.dismiss();
                    Uri downloadurl = taskSnapshot.getDownloadUrl();
                    Picasso.with(getContext()).load(downloadurl).fit().centerCrop().into(ib1);
                    DatabaseReference Dref = FirebaseDatabase.getInstance().getReference().getRoot().child("nitrangana")
                            .child("students").child(value2);
                    Dref.child("image").setValue(downloadurl.toString());
                    Toast.makeText(getContext(), "photo uploaded", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
