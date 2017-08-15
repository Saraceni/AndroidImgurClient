package com.saraceni.imgurclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.saraceni.imgurclient.R;
import com.saraceni.imgurclient.api.client.ApiClient;
import com.saraceni.imgurclient.api.response.tags.TagsRoot;
import com.saraceni.imgurclient.api.response.common.Tag;
import com.saraceni.imgurclient.ui_adapters.TagColumnAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Home extends AppCompatActivity {

    private List<Tag> tagsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TagColumnAdapter tagColumnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prepareTagsListView();
        loadTags();
    }

    private void prepareTagsListView() {
        recyclerView = (RecyclerView) findViewById(R.id.content_home_list);

        tagColumnAdapter = new TagColumnAdapter(this, tagsList);
        tagColumnAdapter.setOnTagClickListener(new TagColumnAdapter.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag) {
                Intent intent = new Intent(Home.this, GalleryActivity.class);
                intent.putExtra(GalleryActivity.TAG_NAME_PARAM, tag.getName());
                intent.putExtra(GalleryActivity.TAG_DISP_NAME_PARAM, tag.getDisplayName());
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tagColumnAdapter);
    }

    private void loadTags() {
        ApiClient api = new ApiClient();
        api.getTagsObservable()
                .getTags()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<TagsRoot>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagsRoot tagsRoot) {
                        List<Tag> tags = tagsRoot.getTagsData().getTags();
                        tagsList.clear();
                        tagsList.addAll(tags);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tagColumnAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
