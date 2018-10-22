/*
* @Authors - Samuel Bostic, Luke Genna 
* @version - 11-12-17
* Project  3: A Grocery Store.
* 
*The GroceryStore Class is the class that does most of the work. It pulls time
* and items from the Customer class, but it initialized a random number for 
* probability and number of items a customer could have. Grocery Store also
* keeps track of how many customers we have served, the time of the transaction
* the maximum number of items a person has had. In the GroceryStore class we
* also create the ArrayDeque we use for all of our "lanes" you would find
* in a store. 
*
*
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class GroceryStore<E> {

    private ArrayList<ArrayDeque<Customer>> lanes;
    private Random randy;
    private int served;
    private int steps;
    private double prob;
    private int transTime;
    private int maxItems;
    private int[] max;

    /*
    * Our constructor not only initalizes some of our variables we also have it 
    * initialize how many lanes we would have in our simulation.
     */
    public GroceryStore(int lines) {
        this.max = new int[lines];
        lanes = new ArrayList<>();
        for (int i = 0; i < lines; i++) {
            lanes.add(new ArrayDeque());
            max[i] = 0;
        }
        this.randy = new Random();
        this.served = 0;
    }

    /*
 * The run method does most of the work for our entire program. It initializes 
 * the variable we use for the maxItems we have, this also calculates the, 
 * shortest line probability to add someone to the shortest line, 
 * the transaction time, the number of steps for each person.
     */
    public void run(int steps, double prob, int transTime, int maxItems) {
        this.steps = steps;
        this.transTime = transTime;
        this.prob = prob;
        this.maxItems = 0;
        for (int time = 0; time < steps; time++) {
            if (randy.nextDouble() <= prob) {
                int shortestLine = shortestLine();
                int numberOfItems = randy.nextInt(maxItems) + 1;
                if (numberOfItems > this.maxItems) {
                    this.maxItems = numberOfItems;
                }
                lanes.get(shortestLine).add(new Customer(numberOfItems, transTime));
                if (lanes.get(shortestLine).size() > max[shortestLine]) {
                    max[shortestLine] = lanes.get(shortestLine).size();
                }
            }
            for (int i = 0; i < lanes.size(); i++) {
                if (lanes.get(i).size() > 0) {
                    lanes.get(i).element().decrement();
                    if (lanes.get(i).element().getTimeRemain() <= 0) {
                        lanes.get(i).remove();
                        served++;
                    }
                }
            }
        }
    }

    /*
 * The numPeopleLeft method runs through the ArrayDeque and it gathers how many
 * people are left in a line so we can get the total number of people 
 * in each line.
 * 
 * It returns leftOvers which is the number of people left in each line. 
     */
    public String numPeopleLeft() {
        String leftOvers = "\n";

        for (int i = 0; i < lanes.size(); i++) {
            leftOvers += "Number left in lane " + i + ": " + lanes.get(i).size() + "\n";
        }
        return leftOvers;
    }

    /*
     *The method maxQueue keeps track of the longest the line ever got. 
     * It returns the maxLength which is the longest the line ever got. 
     */
    public String maxQueue() {
        String maxLength = "\n";

        for (int i = 0; i < max.length; i++) {
            maxLength += "The max length for lane " + i + " was " + max[i] + " people.\n";
        }
        return maxLength;
    }

    /*
     *This method keeps track of which line is the shortest so we know what
     * to add more people to. 
     *
     * It returns imin which is the shortest line. 
     */
    public int shortestLine() {
        int min = Integer.MAX_VALUE;
        int imin = 0;
        for (int i = 0; i < lanes.size(); i++) {
            if (lanes.get(i).size() < min) {
                min = lanes.get(i).size();
                imin = i;
            }
        }
        return imin;
    }
    /*
    * This method just collects all of our data and puts it in really nice and 
    * neat print statements so the user can see all of the data 
    * from a simulation.
    */
    public void printData() {
        System.out.println("Number of time Steps: " + steps);
        System.out.println("Number of lanes: " + lanes.size());
        System.out.println("Arrival probability: " + prob);
        System.out.println("The Time per item is: " + transTime);
        System.out.println("Number of items: " + maxItems);
        System.out.print(maxQueue());
        System.out.println(numPeopleLeft());
        System.out.println("Customers served: " + served);
        System.out.println();

    }
}
