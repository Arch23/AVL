import java.util.*;

public class Teste{
   public static void main(String[] args) {
      Tree t = new Tree();
      Random g = new Random();
      for(int i=0;i<10000;i++){
         t.addNode(g.nextInt(10000));
         System.out.print("\n"+i+"\n");
      }
      t.printTree();
   }
}
