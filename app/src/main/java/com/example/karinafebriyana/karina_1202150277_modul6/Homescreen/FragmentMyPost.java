package com.example.karinafebriyana.karina_1202150277_modul6.Homescreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karinafebriyana.karina_1202150277_modul6.Adapter.PostAdapter;
import com.example.karinafebriyana.karina_1202150277_modul6.MainActivity;
import com.example.karinafebriyana.karina_1202150277_modul6.Model.Post;
import com.example.karinafebriyana.karina_1202150277_modul6.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Karina Febriyana on 01/04/2018.
 */

public class FragmentMyPost extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<Post> listPosts;

    Query databaseFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_my_post, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        databaseFood = FirebaseDatabase.getInstance().getReference(MainActivity.table1).orderByChild("userID").equalTo(mAuth.getUid());

        listPosts = new ArrayList<>() ;

        return view;
    }

    public void onStart() {
        super.onStart();
        databaseFood.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listPosts.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Post post = postSnapshot.getValue(Post.class);

                    listPosts.add(post);
                }

                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

                PostAdapter postList = new PostAdapter(getContext(), listPosts);

                recyclerView.setAdapter(postList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

