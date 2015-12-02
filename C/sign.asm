%include "asm_io.inc"

global _main

_main:
   mov   eax,0
   cmp   eax,4
   je    L1
   jl    L3
   jg    L2

L1:
   mov   eax,0
   call  print_int
   jmp   L4
L2:
   mov   eax,1
   call  print_int
   jmp   L4
L3:
   mov   eax,-1
   call  print_int
   jmp   L4
L4:
   ret
