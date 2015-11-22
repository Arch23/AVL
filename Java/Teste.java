public class Teste{
   public static void main(String[] args) {
      Tree t = new Tree();
      t.addNode(5);
      t.addNode(4);
      t.addNode(7);
      t.addNode(9);
      t.addNode(2);
      t.printTree();
      t.removeNode(4);
      t.printTree();
   }
}
