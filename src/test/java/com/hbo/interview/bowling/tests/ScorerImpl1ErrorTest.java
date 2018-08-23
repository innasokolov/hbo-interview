package com.hbo.interview.bowling.tests;

import java.io.IOException;
import java.io.InputStream;
import com.hbo.interview.bowling.Scorer;
import junit.framework.AssertionFailedError;
import org.junit.Test;

import static junit.framework.Assert.fail;

/**
 * User: blangel
 * Date: 3/10/12
 * Time: 2:57 PM
 */
public class ScorerImpl1ErrorTest extends ScorerImplAbstractTest {
    
    @Test
    public void nullInput() {
        try {
            newIntervieweeImpl().init(null);
            fail("Your implementation should have thrown an InvalidInput exception, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) { 
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }
    
    @Test 
    public void ioExceptionInput() {
        try {
            newIntervieweeImpl().init(new InputStream() {
                @Override public int read() throws IOException {
                    throw new IOException();
                }
            });
            fail("Your implementation should have thrown an InvalidInput exception, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void blankInput() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/blank.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as the input file was blank, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as the input file was blank, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void negativeNumberOfPlayers() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/negativenumberofplayers.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as the number of players was negative, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as the number of players was negative, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void notANumberOfPlayers() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/notanumberofplayers.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as the number of players was not a number, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as the number of players was not a number, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void zeroNumberOfPlayers() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/zeronumberofplayers.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as the number of players was zero, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as the number of players was zero, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void notAnInteger() {
        // System.out.format("%d %d%n", Integer.MAX_VALUE, Long.MAX_VALUE);
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/notaninteger.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as the number of players was greater than Long.MAX_VALUE, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as the number of players was greater than Long.MAX_VALUE, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void invalidNumberOfPlayerNames() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/invalidnumberofplayernames.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as the number of players was greater than the number of player names, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as the number of players was greater than the number of player names, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void blankPlayerName() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/blankplayername.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player name was blank, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player name was blank, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void invalidNumberOfScores() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/invalidnumberofscores.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as there weren't enough scores in the input file, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as there weren't enough scores in the input file, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void blankScoreLine() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/blankscoreline.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was blank, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was blank, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void invalidScore() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/invalidscore.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, instead (at frame 4) it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was invalid (at frame 4), instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void invalidScoreNumber() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/invalidscorenumber.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was greater than ten (at frame 4), instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was greater than ten (at frame 4), instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void invalidScoreNegative() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/invalidscorenegative.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was less than 0 (at frame 4), instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was less than 0 (at frame 4), instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void invalidTenthFrameScore() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/invalidtenthframescore.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was invalid in the tenth frame, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was invalid in the tenth frame, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void noSecondThrowInfo() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/nosecondthrowinfo.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, need two throws if not a strike, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, need two throws if not a strike, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void noThirdThrowOnStrike() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/nothirdthrowonstrike.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, need three throws if a strike in the tenth frame, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, need three throws if a strike in the tenth frame, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void noThirdThrowOnSpare() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/nothirdthrowonspare.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, need three throws if a spare in the tenth frame, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, need three throws if a spare in the tenth frame, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void noSecondThrowOnStrikeInTenth() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/nosecondthrowonstrikeintenth.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, need three throws if a strike in the tenth frame, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, need three throws if a strike in the tenth frame, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void noSecondThrowOnStrike() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/error/nosecondthrowonstrike.txt"));
            fail("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, only one throw if a strike, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception as a player's score was invalid, only one throw if a strike, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }
    
}
