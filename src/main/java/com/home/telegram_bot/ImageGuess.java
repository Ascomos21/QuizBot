package com.home.telegram_bot;

import java.util.ArrayList;
import java.util.Random;

class ImageGuess {
    private String url;
    private String answer;

    public ImageGuess(String url, String answer) {
        this.url = url;
        this.answer = answer;
    }

    public String getUrl() {
        return url;
    }

    public String getAnswer() {
        return answer;
    }

    private static ArrayList<ImageGuess> l = new ArrayList<>();

    private static void initList() {

        l.add(new ImageGuess("https://bit.ly/2R9FiRU", "zebra"));
        l.add(new ImageGuess("https://bit.ly/2RCc3Gu", "horse"));
        l.add(new ImageGuess("https://bit.ly/2RxAcOD", "shark"));
        // and so forth...
    }

    // get a random image
    static ImageGuess random() {
        if (l.isEmpty())
            initList();
        return l.get(new Random().nextInt(l.size()));
    }
}