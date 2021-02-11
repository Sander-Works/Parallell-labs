package no.hiof.itf23019.synchronization.condition;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import no.hiof.itf23019.synchronization.common.CommonTask;

public class TaskController {

	/**
	 * Lock to control the order of the tasks
	 */
	private final ReentrantLock lock;

	/**
	 * Conditions to control that the order of the task
	 */
	private final Condition condition;

	private int turn;

	public TaskController() {
		lock = new ReentrantLock();
		condition = lock.newCondition();
		turn = 2;
	}

	public void doTask1() {
		lock.lock();

		try {

			while (turn != 1)
				condition.await();

			System.out.println("Task1 " + "Date: " + new Date() + ": Running the task");
			CommonTask.doTask();
			System.out.println("Task1 " + "Date: " + new Date() + ": The execution has finished\n");

			turn = 2;
			condition.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void doTask2() {

		lock.lock();
		
		try {

			while (turn != 2)
				condition.await();

			System.out.println("Task2: " + "Date: " + new Date() + ": Running the task");
			CommonTask.doTask();
			System.out.println("Task2: " + "Date: " + new Date() + ": The execution has finished\n");

			turn = 1;
			condition.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}

}
