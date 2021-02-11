package no.hiof.itf23019.demo_recursive_task.task;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

import no.hiof.itf23019.demo_recursive_task.util.Product;

/**
 * This class implements the tasks that are going to compute sum of the prices
 * of product. If the assigned interval of values is less that 10, it compute
 * the sum of the prices of the assigned products. In other case, it divides the
 * assigned interval in two, creates two new tasks and execute them
 *
 */
public class Task extends RecursiveTask<Double> {

	/**
	 * serial version UID. The ForkJoinTask class implements the serializable
	 * interface.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * List of products
	 */
	private List<Product> products;

	/**
	 * Fist and Last position of the interval assigned to the task
	 */
	private int first;
	private int last;

	/**
	 * Increment in the price of products this task has to apply
	 */
	private double increment;

	/**
	 * Constructor of the class. Initializes its attributes
	 * 
	 * @param products  list of products
	 * @param first     first element of the list assigned to the task
	 * @param last      last element of the list assigned to the task
	 * @param increment price increment that this task has to apply
	 */
	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	/**
	 * Method that implements the job of the task
	 * 
	 * @return
	 */
	@Override
	protected Double compute() {
		
		Double result = null;
		if (last - first < 10) {
			result =  computePrices();
		} else {
			int middle = (last + first) / 2;

			Task t1 = new Task(products, first, middle + 1, increment);
			Task t2 = new Task(products, middle + 1, last, increment);
			
			//Option1:
			invokeAll(t1, t2);
			
			//Option2:
			//t1.fork();
			//t2.fork();

			try {
				result =  t1.get() + t2.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();

			}
		}
		
		
		
		return result;
	}

	/**
	 * Method that updates the prices of the assigned products to the task
	 */
	private Double computePrices() {
		double accumulatedPrice = 0;
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			accumulatedPrice += product.getPrice();
		}
		return accumulatedPrice;
	}

}