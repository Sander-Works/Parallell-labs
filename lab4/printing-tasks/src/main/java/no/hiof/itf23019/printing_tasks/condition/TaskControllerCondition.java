package no.hiof.itf23019.printing_tasks.condition;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import no.hiof.itf23019.printing_tasks.task.TaskController;


public class TaskControllerCondition implements TaskController {

	/**
	 * Lock to control the order of the tasks
	 */
	private ReentrantLock lock;


	/**
	 * Conditions to control that the order of the task
	 */
	private Condition condition;

	private int turn;

	public TaskControllerCondition() {

		//TODO: initialize the lock, condition and turn values properly

		this.lock = new ReentrantLock();
		this.condition = lock.newCondition();
		turn = 1;

	}

	@Override


	public void doTaskA() {
		lock.lock();
		//TODO: use condition and lock to synchronize this task
		try {
			while (turn != 1)
				condition.await();
			System.out.print("welcome to ");
			turn = 2;
			condition.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			lock.unlock();
		}
	}

	@Override
	public void doTaskB() {
		lock.lock();
		try {
			while (turn != 2)
				condition.await();
			System.out.println("Ostfold University College");
			turn = 1;
			condition.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			lock.unlock();
		}

	}
}
