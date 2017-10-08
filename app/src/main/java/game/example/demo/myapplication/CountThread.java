package game.example.demo.myapplication;

/**
 * Created by sev_user on 02-Dec-14.
 */
public class CountThread extends Thread {

    private int count;

    /**
     * Constructs a new {@code Thread} with no {@code Runnable} object and a
     * newly generated name. The new {@code Thread} will belong to the same
     * {@code ThreadGroup} as the {@code Thread} calling this constructor.
     *
     * @see ThreadGroup
     * @see Runnable
     */
    public CountThread() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    /**
     * Calls the <code>run()</code> method of the Runnable object the receiver
     * holds. If no Runnable is set, does nothing.
     *
     * @see Thread#start
     */
    @Override
    public void run() {
        super.run();

        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}
