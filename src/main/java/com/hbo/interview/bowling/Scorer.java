package com.hbo.interview.bowling;

import java.io.InputStream;
import java.util.List;

/**
 * User: blangel
 * Date: 3/10/12
 * Time: 2:11 PM
 *
 * Implementations of this interface can compute the score of a game of ten-pin bowling for an arbitrary amount of
 * players.  The rules of ten-pin bowling can be read here {@literal http://en.wikipedia.org/wiki/Ten-pin_bowling#Rules_of_play}
 * but the gist is this:
 * <p/>
 * Each player plays ten frames.  Each frame starts with ten pins up.  The frame ends as soon as all ten pins are down
 * or the player has used two balls in attempt to get all pins down, which ever comes first.  If the player knocks
 * down all ten pins using only one ball it is called a strike.  If the player knocks down all ten pins using both
 * balls it is called a spare.  If the player does not knock down all ten pins after using both balls it is called
 * an open frame.
 * If the player knocks down all ten pins in the tenth frame, the player is allowed up to three throws.  If the player
 * gets a strike on his first throw in the tenth frame, he is allowed to throw at most two more times.  If the player
 * gets a spare on his second throw in the tenth frame, he is allowed to throw once more.
 *<p/>
 * The scoring of the game can be read about here {@literal http://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring} but
 * the gist is this:
 * <p/>
 * If a frame ends as an open frame, the points for that frame are the summation of the number of pins knocked down.  For
 * instance, if the player knocks down one pin in two attempts his score for that frame is one.
 * If a frame ends with a spare, the points for that frame are 10 plus the number of pins knocked down in the player's
 * next throw of a ball.
 * If a frame ends with a strike, the points for that frame are 10 plus the number of pins knocked down in the player's
 * next two throws of the ball.
 * The tenth frame is the exception to the strike/spare bonuses, clearly, as there is no eleventh frame.  The tenth
 * frame is always scored as the accumulation of the number of pins knocked down (with a maximum score for the tenth
 * frame alone being 30, three strikes on three throws).  Thus the maximum score for a game of bowling is 300; e.g.,
 * <pre>
 *     Frame 1: X on first throw
 *     Frame 2: X on first throw
 *     Frame 3: X on first throw (10 + 10 + 10 for first frame score of 30; total score of 30 + result of frames 2 and 3)
 *     Frame 4: X on first throw (10 + 10 + 10 for second frame score of 30; total score of 60 + result of frames 3 and 4)
 *     Frame 5: X on first throw (10 + 10 + 10 for third frame score of 30; total score of 90 + result of frames 4 and 5)
 *     Frame 6: X on first throw (10 + 10 + 10 for fourth frame score of 30; total score of 120 + result of frames 5 and 6)
 *     Frame 7: X on first throw (10 + 10 + 10 for fifth frame score of 30; total score of 150 + result of frames 6 and 7)
 *     Frame 8: X on first throw (10 + 10 + 10 for sixth frame score of 30; total score of 180 + result of frames 7 and 8)
 *     Frame 9: X on first throw (10 + 10 + 10) for seventh frame score of 30; total score of 210 + result of frames 8 and 9)
 *     Frame 10: X on first throw (10 + 10 + 10) for eighth frame score of 30; total score of 240 + result of frames 9 and 10)
 *     Frame 10: X on second throw (10 + 10 + 10) for ninth frame score of 30; total score of 270 + result of frame 10)
 *     Frame 10: X on third throw (10 + 10 + 10) for tenth frame score of 30; total score of 300!
 * </pre>
 * <p/>
 * The test cases for your class will do the following sequence of method calls, checking for proper values each time:
 * 1) call {@link #init(InputStream)}
 * 2) call {@link #getPlayers()}
 * 3) call {@link #getPlayerScore(String)} for each player within the {@linkplain List} returned by {@linkplain #getPlayers()}
 * The test case will also test for handling of mal-formed input by way of catching {@linkplain InvalidInput}
 * when calling {@link #init(InputStream)} with known invalid input.
 * <p/>
 * The input will be a text file of the following format:
 * <pre>
 * n
 * player 1
 * ...
 * player n
 * score for player 1 on frame 1
 * ...
 * score for player n on frame 1
 * ...
 * ...
 * score for player 1 on frame 10
 * ...
 * score for player n on frame 10
 * </pre>
 * Where {@literal n} is the number of players, and their scores are in the format:
 * {@literal score-frame-1 score-frame-2 score-frame-3} where each score is between 0-10 and {@literal score-frame-3}
 * is only valid on frame 10.
 * For instance, the text file for a one player game where the player bowls a perfect game would look like this:
 * <pre>
 *     1
 *     Chris Fagiani
 *     10
 *     10
 *     10
 *     10
 *     10
 *     10
 *     10
 *     10
 *     10
 *     10 10 10
 * </pre>
 * The text file for a two player game where the first player bowls a perfect game and the second player always
 * throws a spare for each frame would look like this:
 * <pre>
 *     2
 *     Chris
 *     Brian
 *     10
 *     9 1
 *     10
 *     9 1
 *     10
 *     9 1
 *     10
 *     9 1
 *     10
 *     9 1
 *     10
 *     9 1
 *     10
 *     9 1
 *     10
 *     9 1
 *     10
 *     9 1
 *     10 10 10
 *     9 1 9
 * </pre>
 * The text file will either be invalid or be valid in which case it will contain at least one player and for each
 * player will always contain 10 frames.  That is, a valid bowling game will always be a complete game of 10 frames.
 */
public interface Scorer {

    /**
     * Thrown by {@linkplain Scorer} implementations whenever the input is given in an invalid format.
     */
    static class InvalidInput extends RuntimeException {
        public InvalidInput() { }
        public InvalidInput(String message) {
            super(message);
        }
        public InvalidInput(String message, Object ... args) {
            super(String.format(message, args));
        }
        public InvalidInput(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * @param inputStream the text stream containing the input
     * @throws InvalidInput if {@code inputStream} does not conform to the format listed above or is null.
     */
    void init(InputStream inputStream) throws InvalidInput;

    /**
     * Assumes {@link #init(java.io.InputStream)} has already been called.  It is undefined what would happen if
     * this method was called before {@linkplain #init(java.io.InputStream)}
     * @return the list of players parsed from the {@linkplain #init(java.io.InputStream)} in order
     */
    List<String> getPlayers();

    /**
     * Assumes {@link #init(java.io.InputStream)} has already been called.  It is undefined what would happen if
     * this method was called before {@linkplain #init(java.io.InputStream)}
     * @param player for which to retrieve his/her score
     * @return the final score for {@code player} 
     * @throws InvalidInput if {@code player} is null or not in the list of known players
     */
    Integer getPlayerScore(String player) throws InvalidInput;
}
