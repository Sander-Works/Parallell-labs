package no.hiof.itf23019.printing_tasks.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import no.hiof.itf23019.printing_tasks.task.TaskController;


public class TaskControllerCondition implements TaskController{

	/**
	 * Lock to control the order of the tasks
	 */
	private final ReentrantLock lock;

	/**
	 * Conditions to control that the order of the task
	 */
	private final Condition condition;

	private int turn;

	public TaskControllerCondition() {
		
		//TODO: initialize the lock, condition and turn values properly
		this.lock = null;
		this.condition = null;
	}

	@Override
	public void doTaskA() {

		//TODO: use condition and lock to synchronize this task
		System.out.print("Welcome to ");
	}

	@Override
	public void doTaskB() {

		//TODO: use condition and lock to synchronize this task
		System.out.println("Ostfold University College");
		
	}

}
