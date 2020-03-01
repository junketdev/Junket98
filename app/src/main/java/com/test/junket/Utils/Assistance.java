package com.test.junket.Utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class Assistance {
    private TextToSpeech t1;

    public Assistance(Context applicationContext) {
        t1 = new TextToSpeech(applicationContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void speak(String toSpeak) {
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}
