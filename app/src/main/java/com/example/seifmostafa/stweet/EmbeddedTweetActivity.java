package com.example.seifmostafa.stweet;

import android.app.ListActivity;
import android.os.Bundle;

import com.mopub.nativeads.MoPubAdAdapter;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.*;

public class EmbeddedTweetActivity extends ListActivity {

   static String trackuser = "fabric";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Use a more specific parent

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(trackuser)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        
        setListAdapter(adapter);
    }

}
