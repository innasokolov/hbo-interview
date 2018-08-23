package com.hbo.interview.bowling;

/**
 * Created by innasokolov on 6/29/16.
 */
public class FrameFactory {
    private static FrameFactory factory = new FrameFactory();

    public static FrameFactory getFactory() {
        return factory;
    }

    public static void setFactory(FrameFactory aFactory) {
        factory = aFactory;
    }

    public static void reset() {
        setFactory(new FrameFactory());
    }

    public Frame createFrame(int frameNumber, String textLine) {
        Frame frame;
        if (textLine == null || textLine.isEmpty()) throw  new IllegalArgumentException("Line is empty");
        String[] rolls = textLine.trim().split("\\s");
        if (frameNumber == 10) {
            frame = new TenthFrame(rolls);
        } else {
            frame = new Frame(rolls);
        }
        return frame;
    }
}
