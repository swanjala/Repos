package com.books.sam.repos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        mSearchBoxEditText= findViewById(R.id.et_search_results_json);
        mUrlDisplayTextView = findViewById(R.id.tv_display_url);
        mSearchResultsTextView = findViewById(R.id.tv_show_repos);

    }

    void makeGithubSearchQuery(){
        String gitHubQuery = mSearchBoxEditText.getText().toString();
        URL gitHubSearchURL = NetworkUtils.buildUrl(gitHubQuery);
        mUrlDisplayTextView.setText(gitHubSearchURL.toString());

        String githubSearchResults = null;

        try{
            githubSearchResults = NetworkUtils.getResposeFromHttp(gitHubSearchURL);
            mSearchResultsTextView.setText(githubSearchResults);
        }catch (IOException e){
            e.printStackTrace();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int itemSelected = item.getItemId();
        if (itemSelected == R.id.action_search){
            Context context = MainActivity.this;
            makeGithubSearchQuery();
        }
        return super.onOptionsItemSelected(item);

    }



}
