package com.example.seifmostafa.stweet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "qvLKCmWfDJQLMDnHA1rcc62xa";
    private static final String TWITTER_SECRET = "0JmEw6NQ2Q6v48Ab3c9VLARL6VgkSWSRtlW04YU9fPc4aIl29K";
    private TwitterLoginButton loginButton;
    EditText editTexttrackuser ,editTexttweet;
    Button Go, TWEET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new TweetComposer());
        setContentView(R.layout.activity_main);


        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        TWEET =(Button) findViewById(R.id.button_t);
        Go = (Button)findViewById(R.id.button_go);
        editTexttrackuser =(EditText)findViewById(R.id.editText_wanteduser);
        editTexttweet = (EditText)findViewById(R.id.editText_tweet);



        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Go.setEnabled(true);
                TWEET.setEnabled(true);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmbeddedTweetActivity.trackuser=editTexttrackuser.getText().toString().trim();
                startActivity(new Intent(MainActivity.this,EmbeddedTweetActivity.class));
            }
        });

        TWEET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TweetComposer.Builder builder = new TweetComposer.Builder(MainActivity.this)
                        .text(editTexttweet.getText().toString().trim());
                builder.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}
