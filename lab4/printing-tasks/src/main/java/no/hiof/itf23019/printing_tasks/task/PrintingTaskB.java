package no.hiof.itf23019.printing_tasks.task;

public class PrintingTaskB implements Runnable {
	
	private TaskController controller;
	
	public PrintingTaskB(TaskController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 3; i ++)
			controller.doTaskB();
	}
}