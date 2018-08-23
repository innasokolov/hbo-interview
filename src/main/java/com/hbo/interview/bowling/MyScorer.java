package com.hbo.interview.bowling;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;

/**
 * Created by innasokolov on 6/29/16.
 */
public class MyScorer implements Scorer {
    private LinkedHashMap<String, List<Frame>> frames;

    public MyScorer() {
        super();
        frames = new LinkedHashMap<String, List<Frame>>();
    }
    public void init(InputStream inputStream) throws InvalidInput {
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
            inputStream.close();
        } catch (Exception e) {
            throw new InvalidInput("Unable to process the input", e);
        }
    }
    public List<String> getPlayers() {
        return Arrays.asList(frames.keySet().toArray(new String[0]));
    }

    public Integer getPlayerScore(String player) throws InvalidInput {
        int score = 0;
        Frame current, second, third;
        for (int i = 0; i < 10; i++) {
            current = frames.get(player).get(i);
            if (i < 9) {
                second = frames.get(player).get(i + 1);
            } else {
                second = null;
            }
            if (i + 1 < 9) {
                third = frames.get(player).get(i + 2);
            } else {
                third = null;
            }
            score+=current.score(second, third);
        }
        return score;
    }

    private int initPlayersNumber(String number) {
        int playersNumber = 0;
        try {
            playersNumber = Integer.valueOf(number);
        } catch(NumberFormatException e) {
            playersNumber = 0;
        }
        if (playersNumber <= 0) throw new InvalidInput("Invalid number of players");
        return playersNumber;
    }

    private String initPlayer(String player) {
        if (player == null || player.isEmpty()) throw new InvalidInput("Empty player");
        if (frames.keySet().contains(player)) throw new InvalidInput("Duplicate player");
        return player;
    }
}
