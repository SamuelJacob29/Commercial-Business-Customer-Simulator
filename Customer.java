/*
* @Authors - Samuel Bostic, Luke Genna 
* @version - 11-12-17
* Project  3: A Grocery Store.
* 
* The Customer class initialized a few important varibles that we use to 
* complete various actions in the Grocery Store class. Such as time and number
* of items a customer might have. 
*
*
*/


public class Customer{
    private int items;
    private int timeRemain;
    
      
    public Customer(int items, int transTime) {
        this.items = items;
        this.timeRemain = transTime*items;
    }

    public int getItems() {
        return items;
    }

    public int getTimeRemain() {
        return timeRemain;
    }
  /*
   * This method simply decreases our time remaining in the simulation. 
   * This makes it look like time is decreasing.
   */  
    public void decrement(){
        timeRemain --;
    }
}
