#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NO_ACTION 0
#define SIMPLE_RIGHT 1
#define SIMPLE_LEFT 2
#define DOUBLE_LR 3
#define DOUBLE_RL 4

typedef struct node{
   struct node *l,*r;
   int inf,bal;
}Node;

int sign(int num);
int branchHeight(Node *N);
int balanceFactor(Node *N);
int verifBal(Node *N);
Node* OPfunction(Node *N);
Node* insertNode(Node *N,int num);
Node* removeNode(Node *N,int num);
Node* rotR(Node *N);
Node* rotL(Node *N);
Node* rotLR(Node *N);
Node* rotRL(Node *N);
void printTree(Node *N);
void updateBal(Node *N);

int main(){
   Node *R = NULL;
   R = insertNode(R,0);
   R = insertNode(R,1);
   R = insertNode(R,5);
   R = insertNode(R,2);
   R = insertNode(R,7);
   R = insertNode(R,8);
   R = insertNode(R,-1);
   R = insertNode(R,99);
   printf("\n\n");
   printTree(R);

   R = removeNode(R,5);
   printf("\n\n");
   printTree(R);

   R = removeNode(R,4);
   printf("\n\n");
   printTree(R);
   return 0;
}

int sign(int num){
   /*int res = 0;
   asm("movl   %0,%%eax;"
       "cmp    %%eax,%0;"
       "je     L1;"
       "jl     L2;"
       "jg     L3;"
       "L1:    movl  $0,%1; jmp  L4;"
       "L2:    movl  $-1,%1; jmp  L4;"
       "L3:    movl  $1,%1; jmp  L4;"
       "L4:"
       :"=r"(num)
       :"r"(res)
   );

   return(res);
   */

   if(num > 0){
      return(1);
   }else if(num < 0){
      return(-1);
   }else{
      return(0);
   }

}

int branchHeight(Node *N){
   if(N == NULL){
      return(0);
   }else{
      int right = branchHeight(N->r),left = branchHeight(N->l);
      if(right > left){
         return(++right);
      }else{
         return(++left);
      }
   }
}

int balanceFactor(Node *N){
   return(branchHeight(N->l)-branchHeight(N->r));
}

int verifBal(Node *N){
   if(N != NULL){
      N->bal = balanceFactor(N);
      if((N->bal == -1) || (N->bal == 0) || (N->bal == 1)){
         return(NO_ACTION);
      }else{
         //A positivo
         if(sign(N->bal) == 1){
            //A e B positivos
            if(sign(N->bal) == sign(N->l->bal)){
               return(SIMPLE_RIGHT);
            //A positivo e B negativo
            }else{
               return(DOUBLE_LR);
            }
         //A negativo
         }else{
            //A e B negativos
            if(sign(N->bal) == sign(N->r->bal)){
               return(SIMPLE_LEFT);
            //A negativo e B positivo
            }else{
               return(DOUBLE_RL);
            }
         }
      }
   }else{
      return(NO_ACTION);
   }
}

Node* OPfunction(Node *N){
   switch(verifBal(N)){
      case(NO_ACTION):{
         break;
      }
      case(SIMPLE_LEFT):{
         printf("\nSIMPLE LEFT");
         N = rotL(N);
         break;
      }
      case(SIMPLE_RIGHT):{
         printf("\nSIMPLE RIGHT");
         N = rotR(N);
         break;
      }
      case(DOUBLE_LR):{
         printf("\nDOUBLE LR");
         N = rotLR(N);
         break;
      }
      case(DOUBLE_RL):{
         printf("\nDOUBLE RL");
         N = rotRL(N);
         break;
      }
   }
   updateBal(N);
   return(N);
}

Node* insertNode(Node *N,int num){
   if(N == NULL){
      N = (Node*)malloc(sizeof(Node));
      N->inf = num;
      N->l = NULL;
      N->r = NULL;
      N->bal = balanceFactor(N);
   }else{
      if(num < N->inf){
         N->l = insertNode(N->l,num);
      }else{
         N->r = insertNode(N->r,num);
      }
      N = OPfunction(N);
   }
   return(N);
}

Node* removeNode(Node *N,int num){
   if(N == NULL){
      return(NULL);
   }else{
      if(num < N->inf){
         N->l = removeNode(N->l,num);
      }else if(num > N->inf){
         N->r = removeNode(N->r,num);
      }else{
         if(N->l == NULL && N->r == NULL){
            free(N);
            return(NULL);
         }else if(N->l == NULL && N->r != NULL){
            Node *aux = N->r;
            free(N);
            return(aux);
         }else if(N->l != NULL && N->r == NULL){
            Node *aux = N->l;
            free(N);
            return(aux);
         }else{
            Node *aux = N->l;
            while(aux->r != NULL){
               aux = aux->r;
            }
            N->inf = aux->inf;
            aux->inf = num;
            N->l = removeNode(N->l,num);
         }
      }
      N = OPfunction(N);
      return(N);
   }
}

Node* rotR(Node *N){
   Node *aux,*temp;
   aux = N->l;
   temp = aux->r;
   aux->r = N;
   N->l = temp;
   N = aux;
   return(N);
}

Node* rotL(Node *N){
   Node *aux,*temp;
   aux = N->r;
   temp = aux->l;
   aux->l = N;
   N->r = temp;
   N = aux;
   return(N);
}

Node* rotLR(Node *N){
   N->l = rotL(N->l);
   N = rotR(N);
   return(N);
}

Node* rotRL(Node *N){
   N->r = rotR(N->r);
   N = rotL(N);
   return(N);
}

void updateBal(Node *N){
   if(N == NULL){
      return;
   }else{
      updateBal(N->l);
      updateBal(N->r);
      N->bal = balanceFactor(N);
   }
}

void printTree(Node *N){
   if(N == NULL){
      return;
   }else{
      printTree(N->l);
      printf("info: %d \tbal: %d\tNode: %p\tLeft: %p\tRight: %p\n",N->inf,N->bal,N,N->l,N->r);
      printTree(N->r);
   }
}
