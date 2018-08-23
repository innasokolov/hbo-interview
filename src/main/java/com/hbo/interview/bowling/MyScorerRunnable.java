package com.hbo.interview.bowling;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by innasokolov on 6/29/16.
 */
public class MyScorerRunnable implements Runnable {
    private LinkedHashMap<String, List<Frame>> frames;
    private InputStream inputStream;

    public MyScorerRunnable(InputStream inputStream) {
        this.inputStream = inputStream;
        frames = new LinkedHashMap<String, List<Frame>>();
    }
    public void run() {
        try {
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(inputStream));
            int playersNumber = initPlayersNumber(reader.readLine());

            for (int i = 1; i <= playersNumber; i++) {
                String nextLine = initPlayer(reader.readLine());
                frames.put(nextLine, new ArrayList<Frame>());
            }
            for (int frameNumber = 1; frameNumber <= 10; frameNumber++) {
                for (String nextPlayer : frames.keySet()) {
                    frames.get(nextPlayer).add(FrameFactory.getFactory().createFrame(frameNumber, reader.readLine()));
                }
            }
        } catch (Exception e) {
            throw new Scorer.InvalidInput("Unable to process the input", e);
        }
    }

    private String initPlayer(String player) {
        if (player == null || player.isEmpty()) throw new Scorer.InvalidInput("Empty player");
        if (frames.keySet().contains(player)) throw new Scorer.InvalidInput("Duplicate player");
        return player;
    }

    private int initPlayersNumber(String number) {
        int playersNumber = 0;
        try {
            playersNumber = Integer.valueOf(number);
        } catch(NumberFormatException e) {
            playersNumber = 0;
        }
        if (playersNumber <= 0) throw new Scorer.InvalidInput("Invalid number of players");
        return playersNumber;
    }

    public LinkedHashMap<String, List<Frame>> getFrames() {
        return frames;
    }
}
