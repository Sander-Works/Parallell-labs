package no.hiof.itf23019.fuzzy_phaser;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * This class implements an student in the exam 
 *
 */
public class Student implements Runnable {

	/**
	 * Phaser to control the execution
	 */
	private Phaser phaser;
	
	/**
	 * Constructor of the class. Initialize its objects
	 * @param phaser Phaser to control the execution
	 */
	public Student(Phaser phaser) {
		this.phaser=phaser;
	}

	/**
	 * Main method of the student. It arrives to the exam and does three exercises. After each
	 * exercise, it calls the phaser to wait that all the students finishes the same exercise
	 */
	public void run() {
		System.out.printf("%s: Has arrived to do the class. %s\n",Thread.currentThread().getName(),new Date());
		int phase = phaser.arrive(); //ready to do the exercise
		
		System.out.printf("%s: Is doing something else while waiting for other to arrive the class. %s\n",Thread.currentThread().getName(),new Date());
		doSomething();
		
		
		phaser.awaitAdvance(phase); //Nothing else to do, but still need to wait for others
		
		
		System.out.printf("%s: Is doing the exam. %s\n",Thread.currentThread().getName(),new Date());
		doExercise();
		
		phase = phaser.arrive(); //Finish the exercise, waiting for other to finish
		
		System.out.printf("%s: Has finished the exam. Doing something else while waiting for other to finish %s\n",Thread.currentThread().getName(),new Date());
		doSomething();
		
		phaser.awaitAdvance(phase);  //Nothing else to do, but still need to wait for others
	}

	/**
	 * Does an exercise is to wait a random time 
	 */
	private void doSomething() {
		try {
			Long duration=(long)(Math.random()*10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Does an exercise is to wait a random time 
	 */
	private void doExercise() {
		try {
			Long duration=(long)(Math.random()*10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
