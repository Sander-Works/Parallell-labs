package no.hiof.itf23019.printing_tasks.semaphore;

import java.util.concurrent.Semaphore;

import no.hiof.itf23019.printing_tasks.task.TaskController;

public class TaskControllerSemaphore implements TaskController {


	private final Semaphore semA, semB;


	public TaskControllerSemaphore() {
		//TODO: Initialize the two semaphore
		this.semA = new Semaphore(1);
		this.semB = new Semaphore(0);
	}

	@Override
	public void doTaskA() {
		//TODO: Use semaphores to synchronize this task
		try {
			semA.acquire();
			System.out.print("Welcome to ");
			semB.release();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doTaskB() {
		//TODO: Use semaphores to synchronize this task
		try {
			semB.acquire();
			System.out.println("Østfold University College");
			semA.release();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
