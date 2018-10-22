/*
* @Authors - Samuel Bostic, Luke Genna 
* @version - 11-12-17
* Project  3: A Grocery Store.
* 
* This is the driver class. It simply calles methods from the GroceryStore class
* to use in the simulation with the parameters you provide. 
*
*
 */
public class StoreDriver {
    
    /*
     *This is the main method that runs our methods that we pull from the
     * the GroceryStore class, with the parameters you can add.
     */
    public static void main(String[] args){
        GroceryStore store = new GroceryStore(5);
        store.run(6456, .16, 5, 10);
        store.printData();
        System.out.println();
        
        store = new GroceryStore(7);
        store.run(1267983, .789, 4, 101112);
        store.printData();
        System.out.println();
        
        store = new GroceryStore(2);
        store.run(8555, .54, 5, 12);
        store.printData();
        System.out.println();
    }
}
