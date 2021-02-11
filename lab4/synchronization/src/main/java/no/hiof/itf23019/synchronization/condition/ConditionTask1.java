package no.hiof.itf23019.synchronization.condition;

public class ConditionTask1 implements Runnable {
	
	private TaskController controller;
	
	public ConditionTask1(TaskController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		controller.doTask1();
	}
}
