package com.hbo.interview.bowling.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

import com.hbo.interview.bowling.MyScorer;
import com.hbo.interview.bowling.Scorer;
import org.junit.Before;

import static junit.framework.Assert.assertNotNull;

/**
 * User: blangel
 * Date: 3/10/12
 * Time: 4:11 PM
 */
public abstract class ScorerImplAbstractTest {

    protected final Scorer intervieweeImpl = new MyScorer();

    protected final AtomicReference<Constructor<Scorer>> intervieweeImplConstructor = new AtomicReference<Constructor<Scorer>>();

    @Before @SuppressWarnings("unchecked")
    public void setup() {
        if (intervieweeImpl == null) {
            throw new IllegalStateException("Please initialize your implementation: \"ScorerImplTest#intervieweeImpl = new YourImpl();\"");
        }
        try {
            Constructor<Scorer> intervieweeImplConstructor = (Constructor<Scorer>) intervieweeImpl.getClass().getDeclaredConstructor();
            this.intervieweeImplConstructor.set(intervieweeImplConstructor);
        } catch (NoSuchMethodException nsme) {
            throw new IllegalStateException(String.format("Please provide a no-args constructor for your implementation, %s",
                    intervieweeImpl.getClass().getSimpleName()), nsme);
        }
        assertNotNull(intervieweeImpl);
        assertNotNull(intervieweeImplConstructor.get());
    }

    protected Scorer newIntervieweeImpl() {
        Constructor<Scorer> constructor = intervieweeImplConstructor.get();
        try {
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (IllegalAccessException iae) {
            throw new AssertionError();
        } catch (InstantiationException ie) {
            throw new IllegalStateException("Your implementation is abstract, please provide a concrete implementation of the Scorer interface.", ie);
        } catch (InvocationTargetException ite) {
            Throwable t = ite.getCause();
            throw new IllegalStateException(String.format("Your implementation's constructor threw an exception [ %s ].", t.getMessage()), t);
        }
    }

    protected InputStream fromClasspath(String onClasspath) {
        ClassLoader loader = (ScorerImpl1ErrorTest.class.getClassLoader() == null
                ? ClassLoader.getSystemClassLoader()
                : ScorerImpl1ErrorTest.class.getClassLoader());
        InputStream resource = loader.getResourceAsStream(onClasspath);
        if (resource == null) {
            try {
                resource = new FileInputStream("src/test/resources/" + onClasspath);
            } catch (FileNotFoundException fnfe) {
                System.out.println("cannot find resource and trigger wrong assertion error");
                throw new AssertionError(String.format("Could not find %s [ try running on command-line via 'mvn test' or 'ant test' ]", onClasspath));
            }
        }
        return resource;
    }

}
