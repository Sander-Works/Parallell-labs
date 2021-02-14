package no.hiof.itf23019.video_conference.Barrier;

public class BarrierAction implements  Runnable{
    @Override
    public void run() {
        System.out.println("The threads are done sleeping \n");

        // Starts the conference
        System.out.printf("VideoConference: All the participants have come\n");
        System.out.printf("VideoConference: Let's start...\n");
    }
}
