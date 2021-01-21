package no.hiof.itf23019;

//The bonus with Runnable interface is that its not waiting for the previous thread to start run, but will start all the threads at the same time (Runnable does also implement threads)
//The runnable method is supposed to be used only if you are going to override run() and only run(). Because you cant override any thread function since threads are a subclass of this interface and you are not supposed to change the behavior of a subclass
public class DemoRunnable implements Runnable {

    @Override
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");

        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}