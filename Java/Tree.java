public class Tree extends Constants{
   private Node root;

   public Tree(int inf){
      root = new Node(inf);
   }

   private void setRoot(Node root){
      this.root = root;
   }

   private Node getRoot(){
      return(root);
   }

   private Node insertNode(Node n,int inf){
      if(n == null){
         n = new Node(inf);
      }else{
         if(inf < n.getInf()){
            n.setLeft(insertNode(n.getLeft(),inf));
         }else if(inf > n.getInf()){
            n.setRight(insertNode(n.getRight(),inf));
         }
      }
      n = OP_Function(n);
      return(n);
   }

   private Node removeNode(Node n,int inf){
      if(n == null){
         return(null);
      }else{
         if(inf < n.getInf()){
            n.setLeft(removeNode(n.getLeft(),inf));
         }else if(inf > n.getInf()){
            n.setRight(removeNode(n.getRight(),inf));
         }else{
            if(n.getLeft() == null && n.getRight() == null){
               return(null);
            }else if(n.getLeft() != null && n.getRight() == null){
               return(n.getLeft());
            }else if(n.getLeft() == null && n.getRight() != null){
               return(n.getRight());
            }else{
               Node aux = n.getRight();
               while(aux.getLeft() != null){
                  aux = aux.getLeft();
               }
               n.setInf(aux.getInf());
               aux.setInf(inf);
               n.setLeft(removeNode(n.getLeft(),inf));
            }
         }
         n = OP_Function(n);
         return(n);
      }
   }

   private void printNodes(Node n){
      if(n == null){
         return;
      }else{
         printNodes(n.getLeft());
         System.out.print("\ninf: "+n.getInf()+"bal: "+n.getBal());
         printNodes(n.getRight());
      }
   }

   private int sign(int num){
      if(num >= 0){
         return(POSITIVE);
      }else{
         return(NEGATIVE);
      }
   }

   private int branchHeight(Node n){
      if(n == null){
         return(0);
      }else{
         int left = 0, right = 0;
         left+=branchHeight(n.getLeft());
         right+=branchHeight(n.getRight());
         if(left > right){
            return(++left);
         }else{
            return(++right);
         }
      }
   }

   private int balanceFactor(Node n){
      return(branchHeight(n.getLeft())-branchHeight(n.getRight()));
   }

   private void updateBal(Node n){
      if(n == null){
         return;
      }else{
         n.setBal(balanceFactor(n));
      }
   }

   private int verifBal(Node n){
      updateBal(n);
      if(n.getBal() == -1 || n.getBal() == 0 || n.getBal() == 1){
         return(NO_ACTION);
      }else{
         if(sign(n.getBal()) == POSITIVE){
            if(sign(n.getBal()) == sign(n.getLeft().getBal())){
               return(SIMPLE_RIGHT);
            }else{
               return(DOUBLE_LR);
            }
         }else{
            if(sign(n.getBal()) == sign(n.getRight().getBal())){
               return(SIMPLE_LEFT);
            }else{
               return(DOUBLE_RL);
            }
         }
      }
   }

   private Node OP_Function(Node n){
      switch(verifBal(n)){
         case NO_ACTION:{
            break;
         }
         case SIMPLE_RIGHT:{
            n = rotR(n);
            return(n);
         }
         case SIMPLE_LEFT:{
            n = rotL(n);
            return(n);
         }
         case DOUBLE_LR:{
            n = doubleLR(n);
            return(n);
         }
         case DOUBLE_RL:{
            n = doubleRL(n);
            return(n);
         }
      }
      return(n);
   }

   private Node rotR(Node n){
      Node aux = n.getLeft(), temp = n.getLeft().getRight();
      n.setLeft(temp);
      aux.setRight(n);
      return(aux);
   }

   private Node rotL(Node n){
      Node aux = n.getRight(), temp = n.getRight().getLeft();
      n.setRight(temp);
      aux.setRight(n);
      return(aux);
   }

   private Node doubleLR(Node n){
      n = rotL(n);
      n = rotR(n);
      return(n);
   }

   private Node doubleRL(Node n){
      n = rotL(n);
      n = rotR(n);
      return(n);
   }

   public void addNode(int inf){
      setRoot(insertNode(root,inf));
   }

   public void removeNode(int inf){
      setRoot(removeNode(root,inf));
   }

   public void printTree(){
      System.out.print("\n\n========= Arvore Binaria de Busca =========\n");
      printNodes(root);
   }
}
