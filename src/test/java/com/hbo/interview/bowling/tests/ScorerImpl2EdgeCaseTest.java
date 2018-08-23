package com.hbo.interview.bowling.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import com.hbo.interview.bowling.Scorer;
import junit.framework.AssertionFailedError;
import org.junit.Test;

import static junit.framework.Assert.fail;

/**
 * User: blangel
 * Date: 3/10/12
 * Time: 6:19 PM
 */
public class ScorerImpl2EdgeCaseTest extends ScorerImplAbstractTest {

   @Test
    public void invalidLookup() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/edge/invalidlookup.txt"));
            List<String> players = intervieweeImpl.getPlayers();
            if (players.isEmpty()) {
                fail("There should be one player, your implementation has none.");
            }
            if (players.size() != 1) {
                fail(String.format("There is only one player but your implementation returned %d", players.size()));
            }
            if (!"Ronnie Dobbs".equals(players.get(0))) {
                fail(String.format("The player's name should be \"Ronnie Dobbs\", your implementation gave \"%s\".", players.get(0)));
            }

            Integer score = intervieweeImpl.getPlayerScore("Ronnie Dobbs");
            if ((score == null) || (30 != score)) {
                fail(String.format("Ronnie Dobbs scored 30 but your implementation gave %d.", score));
            }

            try {
                intervieweeImpl.getPlayerScore(null);
            } catch (Scorer.InvalidInput ii) {
                // expected
            } catch (AssertionFailedError afe) {
                throw afe;
            } catch (Throwable t) {
                fail(String.format("Your implementation should have thrown an InvalidInput exception, instead it threw a %s [ %s ].",
                        t.getClass().getSimpleName(), t.getMessage()));
            }

            try {
                intervieweeImpl.getPlayerScore("not in your list");
            } catch (Scorer.InvalidInput ii) {
                // expected
            } catch (AssertionFailedError afe) {
                throw afe;
            } catch (Throwable t) {
                fail(String.format("Your implementation should have thrown an InvalidInput exception, instead it threw a %s [ %s ].",
                        t.getClass().getSimpleName(), t.getMessage()));
            }

            players = intervieweeImpl.getPlayers();
            if (players.size() != 1) {
                fail(String.format("There is only one player but your implementation returned %d", players.size()));
            }
            if (!"Ronnie Dobbs".equals(players.get(0))) {
                fail(String.format("The player's name should be \"Ronnie Dobbs\", your implementation gave \"%s\".", players.get(0)));
            }

        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void integrity() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/edge/integrity.txt"));
            List<String> players = intervieweeImpl.getPlayers();
            if (players.isEmpty()) {
                fail("There should be one player, your implementation has none.");
            }
            if (players.size() != 1) {
                fail(String.format("There is only one player but your implementation returned %d", players.size()));
            }
            if (!"Ronnie Dobbs".equals(players.get(0))) {
                fail(String.format("The player's name should be \"Ronnie Dobbs\", your implementation gave \"%s\".", players.get(0)));
            }

            // try and remove the entry
            try {
                players.remove("Ronnie Dobbs");
            } catch (Throwable t) {
                // probably expected, checking integrity below to be certain.
            }

            Integer score = intervieweeImpl.getPlayerScore("Ronnie Dobbs");
            if ((score == null) || (0 != score)) {
                fail(String.format("Ronnie Dobbs scored 0 but your implementation gave %d.", score));
            }

            players = intervieweeImpl.getPlayers();
            if (players.isEmpty()) {
                fail("There should be one player, your implementation has none.");
            }
            if (players.size() != 1) {
                fail(String.format("There is only one player but your implementation returned %d", players.size()));
            }
            if (!"Ronnie Dobbs".equals(players.get(0))) {
                fail(String.format("The player's name should be \"Ronnie Dobbs\", your implementation gave \"%s\".", players.get(0)));
            }

            // try and add an element
            try {
                players.add("added into your implementation");
            } catch (Throwable t) {
                // probably expected, checking integrity below to be certain.
            }

            try {
                intervieweeImpl.getPlayerScore("added into your implementation");
            } catch (Scorer.InvalidInput ii) {
                // expected
            } catch (AssertionFailedError afe) {
                throw afe;
            } catch (Throwable t) {
                fail(String.format("Your implementation should have thrown an InvalidInput exception, instead it threw a %s [ %s ].",
                        t.getClass().getSimpleName(), t.getMessage()));
            }

            players = intervieweeImpl.getPlayers();
            if (players.size() != 1) {
                fail(String.format("There is only one player but your implementation returned %d", players.size()));
            }
            if (!"Ronnie Dobbs".equals(players.get(0))) {
                fail(String.format("The player's name should be \"Ronnie Dobbs\", your implementation gave \"%s\".", players.get(0)));
            }

        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void consistent() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/edge/consistent.txt"));
            List<String> players = intervieweeImpl.getPlayers();
            if (players.size() != 2) {
                fail(String.format("There should be two players but your implementation returned %d", players.size()));
            }
            if (!"Ronnie Dobbs".equals(players.get(0))) {
                fail(String.format("The first player's name should be \"Ronnie Dobbs\", your implementation gave \"%s\".", players.get(0)));
            }
            if (!"Terry Twillstein".equals(players.get(1))) {
                fail(String.format("The second player's name should be \"Terry Twillstein\", your implementation gave \"%s\".", players.get(1)));
            }
            Integer score = intervieweeImpl.getPlayerScore("Ronnie Dobbs");
            if ((score == null) || (1 != score)) {
                fail(String.format("Ronnie Dobbs scored 1 but your implementation gave %d.", score));
            }
            score = intervieweeImpl.getPlayerScore("Terry Twillstein");
            if ((score == null) || (18 != score)) {
                fail(String.format("Terry Twillstein scored 18 but your implementation gave %d.", score));
            }

            // repeatedly call score, should be a consistent result
            for (int i = 0; i < 1000; i++) {
                // interleave order of checking (want to check in opposite order of player position as well)
                if ((i % 2) == 0) {
                    score = intervieweeImpl.getPlayerScore("Ronnie Dobbs");
                    if ((score == null) || (1 != score)) {
                        fail(String.format("Ronnie Dobbs scored 1 but your implementation gave %d.", score));
                    }
                    score = intervieweeImpl.getPlayerScore("Terry Twillstein");
                    if ((score == null) || (18 != score)) {
                        fail(String.format("Terry Twillstein scored 18 but your implementation gave %d.", score));
                    }
                } else {
                    score = intervieweeImpl.getPlayerScore("Terry Twillstein");
                    if ((score == null) || (18 != score)) {
                        fail(String.format("Terry Twillstein scored 18 but your implementation gave %d.", score));
                    }
                    score = intervieweeImpl.getPlayerScore("Ronnie Dobbs");
                    if ((score == null) || (1 != score)) {
                        fail(String.format("Ronnie Dobbs scored 1 but your implementation gave %d.", score));
                    }
                }
            }
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void floatScore() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/edge/floatscore.txt"));
            fail("Your implementation should have thrown an InvalidInput exception, as the score is in decimal format, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception, as the score is in decimal format, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void sameName() {
        try {
            newIntervieweeImpl().init(fromClasspath("etc/interview-questions/edge/samename.txt"));
            fail("Your implementation should have thrown an InvalidInput exception, as the players' names are the same, instead it returned normally.");
        } catch (Scorer.InvalidInput ii) {
            // expected
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should have thrown an InvalidInput exception, as the players' names are the same, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }
    
    @Test
    public void inputStreamClosed() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            final InputStream delegate = fromClasspath("etc/interview-questions/oneplayer300.txt");
            final AtomicBoolean closed = new AtomicBoolean(false);
            InputStream stream = new InputStream() {
                @Override public int read() throws IOException {
                    return delegate.read();
                }
                public int read(byte[] b) throws IOException {
                    return delegate.read(b);
                }
                public int read(byte[] b, int off, int len) throws IOException {
                    return delegate.read(b, off, len);
                }
                public long skip(long n) throws IOException {
                    return delegate.skip(n);
                }
                public int available() throws IOException {
                    return delegate.available();
                }
                public void close() throws IOException {
                    closed.set(true);
                    delegate.close();
                }
                public void mark(int readlimit) {
                    delegate.mark(readlimit);
                }
                public void reset() throws IOException {
                    delegate.reset();
                }
                public boolean markSupported() {
                    return delegate.markSupported();
                }
            };
            intervieweeImpl.init(stream);
            if (!closed.get()) {
                fail("Your implementation should have closed the inputted InputStream.");
            }

            Scorer intervieweeImplFail = newIntervieweeImpl();
            final InputStream delegateFail = fromClasspath("etc/interview-questions/error/blankscoreline.txt");
            final AtomicBoolean closedFail = new AtomicBoolean(false);
            InputStream streamFail = new InputStream() {
                @Override public int read() throws IOException {
                    return delegateFail.read();
                }
                public int read(byte[] b) throws IOException {
                    return delegateFail.read(b);
                }
                public int read(byte[] b, int off, int len) throws IOException {
                    return delegateFail.read(b, off, len);
                }
                public long skip(long n) throws IOException {
                    return delegateFail.skip(n);
                }
                public int available() throws IOException {
                    return delegateFail.available();
                }
                public void close() throws IOException {
                    closedFail.set(true);
                    delegateFail.close();
                }
                public void mark(int readlimit) {
                    delegateFail.mark(readlimit);
                }
                public void reset() throws IOException {
                    delegateFail.reset();
                }
                public boolean markSupported() {
                    return delegateFail.markSupported();
                }
            };
            try {
                intervieweeImplFail.init(streamFail);
                fail("Your implementation should have thrown an InvalidInput.");
            } catch (Scorer.InvalidInput ii) {
                // expected
            }
            if (!closedFail.get()) {
                fail("Your implementation should have closed the inputted InputStream even on failure.");
            }
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

}
