package com.hbo.interview.bowling;

/**
 * Created by innasokolov on 6/29/16.
 */
public class TenthFrame extends Frame {
    private int thirdRollPins;

    public TenthFrame() {
        super();
        thirdRollPins = -1;
    }

    public TenthFrame(String[] rolls) {
        this();
        init(rolls);
    }

    private void init(String[] rolls) {
        int numberOfRolls = rolls.length;
        if (numberOfRolls > 0) setFirstRollPins(validateRoll(rolls[0]));
        if (numberOfRolls > 1) setSecondRollPins(validateRoll(rolls[1]));
        if (numberOfRolls > 2) setThirdRollPins(validateRoll(rolls[2]));
        if (numberOfRolls > 3) throw new Scorer.InvalidInput("Wrong number of rolls in tenth frame");
        validate();
    }

    private void validate() {
        if (isSpare() && getThirdRollPins() == -1)
            throw new Scorer.InvalidInput("Missing roll in tenth frame");
        if (isStrike() && (getSecondRollPins() == -1 && getThirdRollPins() == -1))
            throw new Scorer.InvalidInput("Missing roll in tenth frame");
    }

    public int getThirdRollPins() {
        return thirdRollPins;
    }

    public void setThirdRollPins(int thirdRollPins) {
        this.thirdRollPins = thirdRollPins;
    }

    @Override
    public int score(Frame secondFrame, Frame thirdFrame) {
        int score = 0;
        if (getFirstRollPins() == 10) score+=10 + getSecondRollPins() + getThirdRollPins();
        else if (getFirstRollPins() + getSecondRollPins() == 10) score+=10 + getThirdRollPins();
        else if (getFirstRollPins() + getSecondRollPins() < 10) score+= getFirstRollPins() + getSecondRollPins();
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TenthFrame)) return false;
        if (!super.equals(o)) return false;

        TenthFrame that = (TenthFrame) o;

        return thirdRollPins == that.thirdRollPins;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + thirdRollPins;
        return result;
    }

    @Override
    public String toString() {
        return "TenthFrame{" +
                "firstRollPins=" + getFirstRollPins() +
                ", secondRollPins=" + getSecondRollPins() +
                ", thirdRollPins=" + getThirdRollPins() +
                '}';
    }
}
