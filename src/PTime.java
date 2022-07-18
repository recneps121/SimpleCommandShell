import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This program keeps track of how much time has been spent on child processes of our Shell.
 * It operates much like unto a stopwatch, needing to be started and stopped to measure the time.
 */
public class PTime {
    private BigDecimal startTime;
    private BigDecimal endTime;
    private BigDecimal totalTime;

    public PTime() {
        this.startTime = BigDecimal.ZERO;
        this.endTime = BigDecimal.ZERO;
        this.totalTime = BigDecimal.ZERO;
    }

    //Timer methods.

    /**
     * This method starts the stopwatch by recording what the time is when executed.
     */
    public void start() {
        this.startTime = BigDecimal.valueOf(System.currentTimeMillis());
    }

    /**
     * This method stops the stopwatch, and records the time elapsed.
     */
    public void stop() {
        //This only works if the stopwatch has been started to begin with.
        //If it has been started before, but not for this specific recording
        //it will just measure how much time since it last was started.
        if (!startTime.equals(BigInteger.ZERO)) {
            this.endTime = BigDecimal.valueOf(System.currentTimeMillis());

            this.totalTime = this.totalTime.add(this.endTime.subtract(this.startTime));
        } else {
            System.out.println("ERROR: PTime.start() must be called at least once before using PTime.stop()");
        }
    }

    /**
     * This method prints the amount of total time spent on child processes.
     */
    public void printPTime() {
        System.out.printf("Total time in child processes: %.4s seconds %n", this.totalTime.divide(BigDecimal.valueOf(1000)).toString());
    }

}
