package no.hiof.itf23019.printing_tasks.semaphore;

import java.util.concurrent.Semaphore;

import no.hiof.itf23019.printing_tasks.task.TaskController;

public class TaskControllerSemaphore implements TaskController{

	
	private Semaphore semA, semB;
	
	public TaskControllerSemaphore() {
		//TODO: Initialize the two semaphore
		
	}
	
	@Override
	public void doTaskA() {
		
		
		//TODO: Use semaphores to synchronize this task
		System.out.print("Welcome to ");
		
		
	}

	@Override
	public void doTaskB() {
		//TODO: Use semaphores to synchronize this task
		
		System.out.println("Ostfold University College");
		
	}

}
