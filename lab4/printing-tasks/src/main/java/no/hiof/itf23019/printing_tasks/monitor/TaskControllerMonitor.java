package no.hiof.itf23019.printing_tasks.monitor;

import no.hiof.itf23019.printing_tasks.task.TaskController;

public class TaskControllerMonitor implements TaskController{

	private int turn;
	
	public TaskControllerMonitor() {
		// TODO: Initialize the turn value
	}
	
	//TODO: use monitor (synchronized, wait, notify) and the turn value 
	// to synchronize this task
	@Override
	public void doTaskA() {
		

		System.out.print("Welcome to ");
		
		
		
	}

	//TODO: use monitor (synchronized, wait, notify) and the turn value 
	// to synchronize this task
	@Override
	public void doTaskB() {
		

		System.out.println("Ostfold University College");
		
		

	}

}
