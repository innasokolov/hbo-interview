package com.hbo.interview.bowling.tests;

import java.io.*;
import java.util.List;
import com.hbo.interview.bowling.Scorer;
import junit.framework.AssertionFailedError;
import org.junit.Test;

import static junit.framework.Assert.fail;

/**
 * User: blangel
 * Date: 3/10/12
 * Time: 4:11 PM
 */
public class ScorerImpl3PerformanceTest extends ScorerImplAbstractTest {

    @Test
    public void smallFile() throws IOException, InterruptedException {
        performanceTest(10000, (1 * 60 * 1000), 0.587F, "small.txt", 1000, 64, 21);
    }

    @Test
    public void mediumFile() throws IOException, InterruptedException {
        performanceTest(50000, (1 * 60 * 1000), 1.381F, "medium.txt", 2000, 128, 53);
    }

    @Test
    public void largeFile() throws IOException, InterruptedException {
        performanceTest(100000, (2 * 60 * 1000), 3.172F, "large.txt", 5000, 512, 114);
    }

    @Test
    public void hugeFile() throws IOException, InterruptedException {
        performanceTest(500000, (4 * 60 * 1000), 12.754F, "huge.txt", 10000, 1024, 606);
    }
    
    private void performanceTest(int size, int durationMs, float refImplTime, String fileName, int check,
                                 int expectedMaxMemMb, long refImplMem) throws IOException, InterruptedException {
        System.out.printf("Running your implementation on a game of %,7d bowlers", size);
        System.out.flush();
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("java", "-cp", System.getProperty("java.class.path"), String.format("-Xmx%dM", expectedMaxMemMb), 
                                ScorerImpl3PerformanceTest.class.getName(), fileName, String.valueOf(size), String.valueOf(refImplTime),
                                String.valueOf(refImplMem));
        builder.redirectErrorStream(true);
        Process runner = builder.start();
        // fail test if this takes longer than duration min.
        long start = System.currentTimeMillis();
        BufferedReader processStdout = new BufferedReader(new InputStreamReader(runner.getInputStream()));
        String processStdoutLine;
        // run until done or two minutes has passed
        while (isAlive(runner) && ((System.currentTimeMillis() - start) < durationMs)) {
            while (processStdout.ready() && ((processStdoutLine = processStdout.readLine()) != null)) {
                System.out.format("%s%n", processStdoutLine);
            }
            Thread.sleep(check);
        }
        while ((processStdoutLine = processStdout.readLine()) != null) {
            System.out.format("%s%n", processStdoutLine);
        }
        if (isAlive(runner)) {
            runner.destroy();
            fail(String.format(
                    "Your implementation has taken longer than %d min to process a game of %d bowlers, failing.",
                    (durationMs / 1000 / 60), size));
        } else if (runner.exitValue() != 0) {
            fail();
        }
    }
    
    private boolean isAlive(Process process) {
        try {
            process.exitValue();
            return false;
        } catch (IllegalThreadStateException itse) {
            return true;
        }
    }
    
    public static void main(String[] args) {
        String fileName = args[0];
        int size = Integer.parseInt(args[1]);
        float refImplTime = Float.parseFloat(args[2]);
        long refImplMem = Long.parseLong(args[3]);
        try {
            ScorerImpl3PerformanceTest instance = new ScorerImpl3PerformanceTest();
            instance.setup();
            long start = System.currentTimeMillis();
            Scorer intervieweeImpl = instance.newIntervieweeImpl();
            intervieweeImpl.init(instance.fromClasspath(
                    String.format("etc/interview-questions/performance/%s", fileName)));
            List<String> players = intervieweeImpl.getPlayers();
            if (players.size() != size) {
                fail(String.format("There should be %d players but your implementation returned %d", size, players.size()));
            }
            Integer score = intervieweeImpl.getPlayerScore("name 0");
            if ((score == null) || (90 != score)) {
                fail(String.format("\"name 0\" scored 90 but your implementation gave %d.", score));
            }
            String lastPlayer = String.format("name %d", size - 1);
            score = intervieweeImpl.getPlayerScore(lastPlayer);
            if ((score == null) || (90 != score)) {
                fail(String.format("\"%s\" scored 90 but your implementation gave %d.", lastPlayer, score));
            }
            long now = System.currentTimeMillis();
            float seconds = ((now - start) / 1000.0f);
            long totalMem = Runtime.getRuntime().totalMemory() / 1024 / 1024;
            long freeMem = Runtime.getRuntime().freeMemory() / 1024 / 1024;
            System.out.format(" took %6.3f seconds using ~ %3d MB; reference implementation's time is ~ %6.3f seconds using ~ %3d MB.", seconds, (totalMem - freeMem), refImplTime, refImplMem);
        } catch (AssertionFailedError afe) {
            afe.printStackTrace();
            System.exit(1);
        } catch (Throwable t) {
            t.printStackTrace();
            System.err.printf("Your implementation should not have thrown an exception, instead it threw a %s [ %s ]%s.%n",
                    t.getClass().getSimpleName(), t.getMessage(), (t.getCause() != null ? String.format(" caused by %s", t.getCause().getClass().getSimpleName()) : ""));
            System.exit(1);
            
        }
    }

    private void createFile(int size, String name) throws IOException {
        File nameFile = new File(String.format("src/test/resources/etc/interview-questions/performance/%s-name.txt", name));
        File file = new File(String.format("src/test/resources/etc/interview-questions/performance/%s.txt", name));
        nameFile.createNewFile();
        file.createNewFile();
        OutputStreamWriter nameWriter = new OutputStreamWriter(new FileOutputStream(nameFile));
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
        nameWriter.write(String.valueOf(size));
        nameWriter.write("\n");

        for (long i = 0; i < size; i++) {
            nameWriter.write(String.format("name %d%n", i));
            writer.append("7 2\n");
            writer.append("7 2\n");
            writer.append("7 2\n");
            writer.append("7 2\n");
            writer.append("7 2\n");
            writer.append("7 2\n");
            writer.append("7 2\n");
            writer.append("7 2\n");
            writer.append("7 2\n");
            writer.append("7 2\n");
        }

        nameWriter.close();
        writer.close();

        // Now do the following: 'cat ${name}.txt >> ${name}-name.txt; mv ${name}-name.txt ${name}.txt'
    }
}
