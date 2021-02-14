package no.hiof.itf23019.printing_tasks.monitor;

import no.hiof.itf23019.printing_tasks.task.TaskController;

public class TaskControllerMonitor implements TaskController{

	private int turn;
	
	public TaskControllerMonitor() {
		// TODO: Initialize the turn value
		turn = 1;
	}
	
	//TODO: use monitor (synchronized, wait, notify) and the turn value 
	// to synchronize this task
	@Override
	public synchronized void doTaskA() {
		while (turn != 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		turn = 2;
		System.out.print("Welcome to ");
		notify();
	}

	//TODO: use monitor (synchronized, wait, notify) and the turn value 
	// to synchronize this task
	@Override
	public synchronized void doTaskB() {
		while (turn != 2) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		turn = 1;
		System.out.println("Ostfold University College");
		notify();
	}
}
