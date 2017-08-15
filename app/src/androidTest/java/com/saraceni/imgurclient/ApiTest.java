package com.saraceni.imgurclient;

import android.test.InstrumentationTestCase;

import com.saraceni.imgurclient.api.client.ApiClient;
import com.saraceni.imgurclient.api.response.tags.TagsData;
import com.saraceni.imgurclient.api.response.tags.TagsRoot;
import com.saraceni.imgurclient.api.response.common.Tag;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rafaelgontijo on 13/08/17.
 */
public class ApiTest extends InstrumentationTestCase {

    public void testHttpRequest() throws Exception {

        final CountDownLatch signal = new CountDownLatch(1);
        final ApiClient api = new ApiClient();

        new Thread(){
            public void run()
            {
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
                                assertNotNull(tagsRoot);
                                TagsData tagsData = tagsRoot.getTagsData();
                                assertNotNull(tagsData);
                                List<Tag> tags = tagsData.getTags();
                                assertNotNull(tags);
                                assertTrue(tags.size() > 0);
                                Tag tag = tags.get(0);
                                assertNotNull(tag.getDisplayName());
                            }

                            @Override
                            public void onError(Throwable e) {
                                assertTrue(false);
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                signal.countDown();
                            }
                        });
            }
        }.start();

        signal.await();
    }
}
