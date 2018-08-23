package com.hbo.interview.bowling;

/**
 * Created by innasokolov on 6/29/16.
 */
public class Frame {
    private int firstRollPins;
    private int secondRollPins;

    public Frame() {
        firstRollPins = -1;
        secondRollPins = -1;
    }

    public Frame(String[] rolls) {
        this();
        int numberOfRolls = rolls.length;
        if (numberOfRolls > 0) setFirstRollPins(validateRoll(rolls[0]));
        if (numberOfRolls > 1) setSecondRollPins(validateRoll(rolls[1]));
        if (numberOfRolls > 2) throw new Scorer.InvalidInput("Wrong number of rolls.");
        validate();
    }

    private void validate() {
        if (!isStrike() && getSecondRollPins() == -1)
            throw new Scorer.InvalidInput("Missing roll in frame");
        if (isStrike() && getSecondRollPins() >= 0)
            throw new Scorer.InvalidInput("Extra roll in frame");
    }

    public int score(Frame secondFrame, Frame thirdFrame) {
        int score = 0;
        int nextRollPins = 0, aftrNextRollPins = 0;
        if (secondFrame != null) {
            nextRollPins = secondFrame.getFirstRollPins();
            aftrNextRollPins = secondFrame.getSecondRollPins();
        }
        if (nextRollPins == 10 && aftrNextRollPins <= 0) {
            if (thirdFrame != null) {
                aftrNextRollPins = thirdFrame.getFirstRollPins();
            }
        }
        if (getFirstRollPins() == 10) score += 10 + nextRollPins + aftrNextRollPins;
        else if (getFirstRollPins() + getSecondRollPins() == 10) score+=10 + nextRollPins;
        else if (getFirstRollPins() + getSecondRollPins() < 10) score+= getFirstRollPins() + getSecondRollPins();
        return score;
    }

    public int getFirstRollPins() {
        return firstRollPins;
    }

    public int getSecondRollPins() {
        return secondRollPins;
    }

    public void setFirstRollPins(int firstRollPins) {
        this.firstRollPins = firstRollPins;
    }

    public void setSecondRollPins(int secondRollPins) {
        this.secondRollPins = secondRollPins;
    }

    public int validateRoll(String text) {
        int roll = 0;
        try {
            roll = Integer.valueOf(text);
        } catch(NumberFormatException e) {
            roll = -1;
        }
        if (roll < 0 || roll > 10) throw new Scorer.InvalidInput("Invalid roll");
        return roll;
    }

    public boolean isStrike() {
        return getFirstRollPins() == 10;
    }

    public boolean isSpare() {
        return getFirstRollPins() + getSecondRollPins() == 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frame frame = (Frame) o;

        if (firstRollPins != frame.firstRollPins) return false;
        return secondRollPins == frame.secondRollPins;

    }

    @Override
    public int hashCode() {
        int result = firstRollPins;
        result = 31 * result + secondRollPins;
        return result;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "firstRollPins=" + getFirstRollPins() +
                ", secondRollPins=" + getSecondRollPins() +
                '}';
    }
}
