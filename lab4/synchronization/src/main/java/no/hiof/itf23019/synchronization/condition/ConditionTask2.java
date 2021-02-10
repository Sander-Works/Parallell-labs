package no.hiof.itf23019.synchronization.condition;

public class ConditionTask2 implements Runnable {
	
	private TaskController controller;
	
	public ConditionTask2(TaskController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		controller.doTask2();
	}
}