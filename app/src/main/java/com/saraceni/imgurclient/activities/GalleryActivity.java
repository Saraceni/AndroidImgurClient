package com.saraceni.imgurclient.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.saraceni.imgurclient.R;
import com.saraceni.imgurclient.api.client.ApiClient;
import com.saraceni.imgurclient.api.response.common.Image;
import com.saraceni.imgurclient.api.response.galleries.GalleriesTagRoot;
import com.saraceni.imgurclient.ui_adapters.PostAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GalleryActivity extends AppCompatActivity {

    public static final String TAG_NAME_PARAM = "TAG_PARAM";
    public static final String TAG_DISP_NAME_PARAM = "TAG_DISP_NAME_PARAM";

    private List<Image> postsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private int currentPage = 0;
    private boolean loading = false;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.tag = getIntent().getStringExtra(TAG_NAME_PARAM);
        String activityName = getIntent().getStringExtra(TAG_DISP_NAME_PARAM);
        getSupportActionBar().setTitle(activityName);

        preparePostListView();
        loadPosts(currentPage);

    }

    private void preparePostListView() {
        postAdapter = new PostAdapter(this, postsList);
        recyclerView = (RecyclerView) findViewById(R.id.content_galley_posts);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) //check for scroll down
                {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int[] pastVisiblesItems = layoutManager.findFirstVisibleItemPositions(null);
                    int lastVisibleItem = Math.max(pastVisiblesItems[0], pastVisiblesItems[1]);

                    if (!loading)
                    {
                        if ( (visibleItemCount + lastVisibleItem) >= totalItemCount)
                        {
                            loadPosts(currentPage);
                        }
                    }
                }
            }
        });
    }

    private void loadPosts(int page) {

        loading = true;
        ApiClient api = new ApiClient();
        api.getGallerieForTag()
                .getPostsFromTag(this.tag, "top", "week", page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<GalleriesTagRoot>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GalleriesTagRoot galleriesTagRoot) {
                        List<Image> posts = galleriesTagRoot.getData().getItems();
                        if(posts != null) {
                            currentPage++;
                            postsList.addAll(posts);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                postAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        loading = false;
                    }
                });
    }


}
