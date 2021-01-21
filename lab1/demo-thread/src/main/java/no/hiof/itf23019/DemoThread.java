package no.hiof.itf23019;

public class DemoThread extends java.lang.Thread
{
    @Override
    public void run()
    {
        try
        {
            // Displaying the thread that is running
            System.out.println ("Thread " + Thread.currentThread().getId() + " is running");
        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }
}
