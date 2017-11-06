package com.felix.emojicompat.adapter;

import com.felix.emojicompat.emoji.Emojicon;

import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.style.DynamicDrawableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class Emojiconize {

    public static Builder activity(AppCompatActivity activity) {
        return new Builder(activity);
    }

    public static Builder view(View view) {
        return new Builder(view);
    }

    public static final class Builder {

        AppCompatActivity activity;

        View view;

        int size = -1;

        @Emojicon.Alignment
        int alignment = DynamicDrawableSpan.ALIGN_BASELINE;

        boolean gone = false;

        public Builder(@NonNull AppCompatActivity activity) {
            if (activity == null) {
                throw new IllegalArgumentException("The given activity must not be null");
            }
            this.activity = activity;
        }

        public Builder(@NonNull View view) {
            if (view == null) {
                throw new IllegalArgumentException("The given view must not be null");
            }
            this.view = view;
        }

        public Builder size(int size) {
            checkGone();
            this.size = size;
            return this;
        }

        public Builder alignment(@Emojicon.Alignment int alignment) {
            checkGone();
            this.alignment = alignment;
            return this;
        }

        public void go() {
            checkGone();
            if (activity != null) {
                LayoutInflaterCompat.setFactory(activity.getLayoutInflater(),
                        new EmojiconLayoutInflaterFactory(this));
            } else {
                emojiconize(view);
            }
            gone = true;
        }

        private void emojiconize(View view) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int index = 0; index < viewGroup.getChildCount(); index++) {
                    emojiconize(viewGroup.getChildAt(index));
                }
            } else if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.addTextChangedListener(new EmojiconTextWatcher(textView, this));
            }
        }

        void checkGone() {
            if (gone) {
                throw new IllegalStateException("Sorry, it's already gone");
            }
        }
    }
}
