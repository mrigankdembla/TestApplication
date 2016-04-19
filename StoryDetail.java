package com.testassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

import com.testassignment.R;
import com.testassignment.adapters.StoryAdapter;
import com.testassignment.models.AuthorDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shopclues on 18/4/16.
 */
public class StoryDetail extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<AuthorDetails> authorDetailsArrayList;
    int position;
    StoryAdapter storyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getIntent().getIntExtra("card_position",0);
        String id = getIntent().getStringExtra("card_id");
        String handleName = getIntent().getStringExtra("handle_name");
        setContentView(R.layout.activity_storydetail);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_story_detail);
        authorDetailsArrayList = new AuthorDetails().createDetailsList(id,handleName);
         storyAdapter = new StoryAdapter(this,authorDetailsArrayList,getIntent().getBooleanExtra("card_isfollowing", false));


        mRecyclerView.setAdapter(storyAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(null);

//        Intent intent = new Intent();
//        intent.putExtra("follow_status", authorDetailsArrayList.get(position).isAuthorFollowed());
//        intent.putExtra("card_position", position);
//        setResult(RESULT_OK, intent);
//        storyAdapter.
//
        storyAdapter.setOnDataChangedLitener(new StoryAdapter.DataChangeListener() {
            @Override
            public void onDataChangedListener(boolean flag) {
                Intent intent = new Intent();
        intent.putExtra("follow_status", flag);
        intent.putExtra("card_position", position);
        setResult(RESULT_OK, intent);
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
