public class Node{
   private Node left,right;
   private int inf,bal;

   public Node(int inf){
         this.inf = inf;
         bal = 0;
         left = null;
         right = null;
   }
   public Node getLeft(){
      return(left);
   }
   public Node getRight(){
      return(right);
   }
   public int getInf(){
      return(inf);
   }
   public int getBal(){
      return(bal);
   }
   public void setLeft(Node left){
      this.left = left;
   }
   public void setRight(Node right){
      this.right = right;
   }
   public void setInf(int inf){
      this.inf = inf;
   }
   public void setBal(int bal){
      this.bal = bal;
   }
}
