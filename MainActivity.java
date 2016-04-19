package com.testassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.testassignment.R;
import com.testassignment.adapters.CustomAdapter;
import com.testassignment.models.Author;
import com.testassignment.utilities.CustomClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    ArrayList<Author> authorArrayList;
    public int pos;
    CustomAdapter customAdapter;
    public static int RESULT_CODE_FOLLOWING = 10;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);

        authorArrayList = Author.createArray();

        customAdapter = new CustomAdapter(authorArrayList,this);
        recyclerView.setAdapter(customAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter.setOnStoryCardClickListener(new CustomClickListener() {
            @Override
            public void onItemClick(View itemView, int layoutPositon) {
                intent = new Intent(MainActivity.this, StoryDetail.class);
                intent.putExtra("card_position", layoutPositon);
                pos = layoutPositon;
                intent.putExtra("card_isfollowing",authorArrayList.get(layoutPositon).isFollowing());
                intent.putExtra("card_id", authorArrayList.get(layoutPositon).getId());
                intent.putExtra("handle_name", authorArrayList.get(layoutPositon).getUsername());
                startActivityForResult(intent,RESULT_CODE_FOLLOWING);
            }
        });


        customAdapter.setOnDataChangedListener(new CustomAdapter.MainDataChangeListener() {
            @Override
            public void onDataChangedListener(boolean flag,int position) {
                authorArrayList.get(position).setIsFollowing(flag);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == RESULT_CODE_FOLLOWING){
            //    authorArrayList.get(getIntent().getIntExtra("card_position",0)).setFollowing(getIntent().getBooleanExtra("follow_status"));
                authorArrayList.get(pos).setIsFollowing(data.getBooleanExtra("follow_status", false));
                customAdapter.notifyItemChanged(pos);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
