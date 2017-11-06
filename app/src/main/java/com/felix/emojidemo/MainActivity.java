package com.felix.emojidemo;

import com.felix.emojicompat.EmojiconEditText;
import com.felix.emojicompat.EmojiconGridFragment;
import com.felix.emojicompat.EmojiconsFragment;
import com.felix.emojicompat.adapter.Emojiconize;
import com.felix.emojicompat.emoji.Emojicon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements EmojiconGridFragment.OnEmojiconClickedListener,
        EmojiconsFragment.OnEmojiconBackspaceClickedListener {

    EmojiconEditText mEditEmojicon;

    TextView txtEmojicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //must call before `super.onCreate()`
        Emojiconize.activity(this).go();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditEmojicon = (EmojiconEditText) findViewById(R.id.editEmojicon);
        txtEmojicon = (TextView) findViewById(R.id.txtEmojicon);
//        Emojiconize.view(txtEmojicon).go();
        mEditEmojicon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtEmojicon.setText(s.toString());
            }
        });
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
