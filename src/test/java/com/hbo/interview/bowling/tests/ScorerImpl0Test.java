package com.hbo.interview.bowling.tests;

import java.util.List;
import com.hbo.interview.bowling.Scorer;
import junit.framework.AssertionFailedError;
import org.junit.Test;
import static junit.framework.Assert.fail;

/**
 * User: blangel
 * Date: 3/10/12
 * Time: 4:10 PM
 */
public class ScorerImpl0Test extends ScorerImplAbstractTest {

    @Test
    public void onePlayer0() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/oneplayer0.txt"));
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
            if ((score == null) || (0 != score)) {
                fail(String.format("Ronnie Dobbs scored 0 but your implementation gave %d.", score));
            }
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }
    
    @Test
    public void onePlayer300() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/oneplayer300.txt"));
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
            if ((score == null) || (300 != score)) {
                fail(String.format("Ronnie Dobbs scored 300 but your implementation gave %d.", score));
            }
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void twoPlayer300() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/twoplayer300.txt"));
            List<String> players = intervieweeImpl.getPlayers();
            if (players.isEmpty()) {
                fail("There should be two players, your implementation has none.");
            }
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
            if ((score == null) || (300 != score)) {
                fail(String.format("Ronnie Dobbs scored 300 but your implementation gave %d.", score));
            }
            score = intervieweeImpl.getPlayerScore("Terry Twillstein");
            if ((score == null) || (300 != score)) {
                fail(String.format("Terry Twillstein scored 300 but your implementation gave %d.", score));
            }
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void twoPlayer300101() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/twoplayer300101.txt"));
            List<String> players = intervieweeImpl.getPlayers();
            if (players.isEmpty()) {
                fail("There should be two players, your implementation has none.");
            }
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
            if ((score == null) || (300 != score)) {
                fail(String.format("Ronnie Dobbs scored 300 but your implementation gave %d.", score));
            }
            score = intervieweeImpl.getPlayerScore("Terry Twillstein");
            if ((score == null) || (101 != score)) {
                fail(String.format("Terry Twillstein scored 101 but your implementation gave %d.", score));
            }
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void threePlayer777777() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/threeplayer777777.txt"));
            List<String> players = intervieweeImpl.getPlayers();
            if (players.isEmpty()) {
                fail("There should be three players, your implementation has none.");
            }
            if (players.size() != 3) {
                fail(String.format("There should be three players but your implementation returned %d", players.size()));
            }
            if (!"Ronnie Dobbs".equals(players.get(0))) {
                fail(String.format("The first player's name should be \"Ronnie Dobbs\", your implementation gave \"%s\".", players.get(0)));
            }
            if (!"Terry Twillstein".equals(players.get(1))) {
                fail(String.format("The second player's name should be \"Terry Twillstein\", your implementation gave \"%s\".", players.get(1)));
            }
            if (!"Senator Howell Tankerbell".equals(players.get(2))) {
                fail(String.format("The third player's name should be \"Senator Howell Tankerbell\", your implementation gave \"%s\".", players.get(2)));
            }
            Integer score = intervieweeImpl.getPlayerScore("Ronnie Dobbs");
            if ((score == null) || (77 != score)) {
                fail(String.format("Ronnie Dobbs scored 77 but your implementation gave %d.", score));
            }
            score = intervieweeImpl.getPlayerScore("Terry Twillstein");
            if ((score == null) || (77 != score)) {
                fail(String.format("Terry Twillstein scored 77 but your implementation gave %d.", score));
            }
            score = intervieweeImpl.getPlayerScore("Senator Howell Tankerbell");
            if ((score == null) || (77 != score)) {
                fail(String.format("Senator Howell Tankerbell scored 77 but your implementation gave %d.", score));
            }
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }

    @Test
    public void threePlayer19077100() {
        try {
            Scorer intervieweeImpl = newIntervieweeImpl();
            intervieweeImpl.init(fromClasspath("etc/interview-questions/threeplayer19077100.txt"));
            List<String> players = intervieweeImpl.getPlayers();
            if (players.isEmpty()) {
                fail("There should be three players, your implementation has none.");
            }
            if (players.size() != 3) {
                fail(String.format("There should be three players but your implementation returned %d", players.size()));
            }
            if (!"Ronnie Dobbs".equals(players.get(0))) {
                fail(String.format("The first player's name should be \"Ronnie Dobbs\", your implementation gave \"%s\".", players.get(0)));
            }
            if (!"Terry Twillstein".equals(players.get(1))) {
                fail(String.format("The second player's name should be \"Terry Twillstein\", your implementation gave \"%s\".", players.get(1)));
            }
            if (!"Senator Howell Tankerbell".equals(players.get(2))) {
                fail(String.format("The third player's name should be \"Senator Howell Tankerbell\", your implementation gave \"%s\".", players.get(2)));
            }
            Integer score = intervieweeImpl.getPlayerScore("Ronnie Dobbs");
            if ((score == null) || (190 != score)) {
                fail(String.format("Ronnie Dobbs scored 190 but your implementation gave %d.", score));
            }
            score = intervieweeImpl.getPlayerScore("Terry Twillstein");
            if ((score == null) || (77 != score)) {
                fail(String.format("Terry Twillstein scored 77 but your implementation gave %d.", score));
            }
            score = intervieweeImpl.getPlayerScore("Senator Howell Tankerbell");
            if ((score == null) || (100 != score)) {
                fail(String.format("Senator Howell Tankerbell scored 100 but your implementation gave %d.", score));
            }
        } catch (AssertionFailedError afe) {
            throw afe;
        } catch (Throwable t) {
            fail(String.format("Your implementation should not have thrown an exception, instead it threw a %s [ %s ].",
                    t.getClass().getSimpleName(), t.getMessage()));
        }
    }
    
}
