package com.felix.emojidemo;

import com.felix.emojicompat.EmojiconEditText;
import com.felix.emojicompat.EmojiconGridFragment;
import com.felix.emojicompat.EmojiconsFragment;
import com.felix.emojicompat.emoji.Emojicon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements EmojiconGridFragment.OnEmojiconClickedListener,
        EmojiconsFragment.OnEmojiconBackspaceClickedListener {

    EmojiconEditText mEditEmojicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditEmojicon = (EmojiconEditText) findViewById(R.id.editEmojicon);
    }

    @Override
    public void onEmojiconClicked(Emojicon emojicon) {
        EmojiconsFragment.input(mEditEmojicon, emojicon);
    }

    @Override
    public void onEmojiconBackspaceClicked(View v) {
        EmojiconsFragment.backspace(mEditEmojicon);
    }
}
