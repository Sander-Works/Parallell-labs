package no.hiof.itf23019.printing_tasks.semaphore;

import no.hiof.itf23019.printing_tasks.task.PrintingTaskA;
import no.hiof.itf23019.printing_tasks.task.PrintingTaskB;
import no.hiof.itf23019.printing_tasks.task.TaskController;

public class SemaphoreMain {

	public static void main(String[] args) {
		TaskController controller = new TaskControllerSemaphore();
		PrintingTaskA taskA = new PrintingTaskA(controller);
		Thread thread1 =  new Thread(taskA);
		thread1.start();
		
		PrintingTaskB taskB = new PrintingTaskB(controller);
		Thread thread2 = new Thread(taskB);
		thread2.start();

	}

}
