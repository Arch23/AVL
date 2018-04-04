import java.util.*;

public class Driver{
   public static void main(String[] args) {
      Tree t = new Tree();
      Random g = new Random();
      System.out.println("\nIsEmpty: "+t.isEmpty());
      t.addNode(1);
      t.addNode(2);
      t.addNode(3);
      t.addNode(4);
      t.addNode(5);
      t.addNode(6);
      t.addNode(7);
      t.addNode(8);
      t.addNode(9);
      t.addNode(10);
      t.addNode(11);
      t.printTree();

      t.removeNode(1);
      t.removeNode(5);
      t.printTree();

      System.out.println("\nIsEmpty: "+t.isEmpty());
   }
}
