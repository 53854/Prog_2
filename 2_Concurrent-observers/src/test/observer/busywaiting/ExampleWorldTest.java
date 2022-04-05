package test.observer.busywaiting;

import main.observer.busywaiting.ConcreteObservee;
import main.observer.busywaiting.ConcreteObserver;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleWorldTest {

    private static final Logger LOGGER = Logger.getLogger(ExampleWorldTest.class.getName());

    private void waitMilliseconds(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simulateUpdatesTest() {
        // Arrange
        final var observee = new ConcreteObservee();
        final var observer1 = new ConcreteObserver();
        final var observer2 = new ConcreteObserver();
        final var observer3 = new ConcreteObserver();

        observee.register(observer1);
        observee.register(observer2);
        observee.register(observer3);

        observer1.setObservee(observee);
        observer2.setObservee(observee);
        observer3.setObservee(observee);

        final var waitTime = 500;

        // Act
        final var start = Instant.now();

        observee.changeState(waitTime);

        final var finish = Instant.now();
        final var elapsedTime = Duration.between(start, finish).toMillis();
        LOGGER.info(String.format("Elapsed time in milliseconds: %d ", elapsedTime));

        // Assert
        waitMilliseconds(waitTime * 2);
        assertEquals(waitTime, observer1.getValue());
        assertEquals(waitTime, observer2.getValue());
        assertEquals(waitTime, observer3.getValue());

    }

    /**
     * Lost updates do not happen with the busy waiting strategy
     * because every Observer has exactly one thread, that is always running
     * and processes updates for that Observer sequentially.
     * The big drawback of that strategy is the unnecessary resource consumption
     * during the time when no updates are happening.
     */
    @Test
    public void simulateLostUpdateTest() {
        // Arrange
        final var observee = new ConcreteObservee();
        final var observer1 = new ConcreteObserver();
        final var observer2 = new ConcreteObserver();
        final var observer3 = new ConcreteObserver();

        observee.register(observer1);
        observee.register(observer2);
        observee.register(observer3);

        observer1.setObservee(observee);
        observer2.setObservee(observee);
        observer3.setObservee(observee);

        final var waitTime1 = 1000;
        final var waitTime2 = 10;

        // Act
        observee.changeState(waitTime1);
        waitMilliseconds(100);
        observee.changeState(waitTime2);

        // Assert
        waitMilliseconds((waitTime1 + waitTime2) * 2);
        assertEquals(waitTime2, observer1.getValue());
        assertEquals(waitTime2, observer2.getValue());
        assertEquals(waitTime2, observer3.getValue());
    }
}
