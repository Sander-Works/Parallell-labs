package no.hiof.itf23019;


public class App {


    public static void main(String[] args)
    {
        int n = 8; // Number of threads
        System.out.println('\n' + "This is DemoThreads" + '\n');

        for (int i=0; i<n; i++)
        {
            Thread object = new Thread(new DemoRunnable());
            object.start();
        }

        System.out.println('\n' + "This is DemoThreads" + '\n');
        for (int i = 0; i < n; i++)
        {
            DemoThread object = new DemoThread();
            object.start();

        }
    }

}
