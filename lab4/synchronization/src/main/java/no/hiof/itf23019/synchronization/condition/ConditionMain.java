package no.hiof.itf23019.synchronization.condition;

public class ConditionMain {

	public static void main(String[] args) {
		
		TaskController controller = new TaskController();
		ConditionTask1 conditionTask1 = new ConditionTask1(controller);
		Thread thread1 =  new Thread(conditionTask1);
		thread1.start();
		
		ConditionTask2 conditionTask2 = new ConditionTask2(controller);
		Thread thread2 = new Thread(conditionTask2);
		thread2.start();
		
		
	}

}
