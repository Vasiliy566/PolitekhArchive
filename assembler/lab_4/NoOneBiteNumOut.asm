DOSSEG
.MODEL TINY
.STACK 100H
.DATA
A DB 10,10,20,30,40,50,60,70,80,90
B DB 10 DUP (0)
.CODE
mov ax, @Data
mov ds , ax
mov es , ax
          
cld              
lea si , A
lea di , B
mov cx , 10
rep movsb

mov cx , 10 ; Body will run N times
lea bx , B

arrL:;start Loop for array
mov dx , [bx]
 while_begin:
 cmp dx , 0 
 jge while_end; while cycle stared
  a:
  mov ah , 0
  mov al , dl
  mov bl , 10;
  div bl;
  push ax
  cmp al , 0
  jmp a 
  mov ah , 2
  int 21h
  jmp while_begin
  while_end:
  inc bx
LOOP arrL  ;end loop for array

mov ah , 4ch
int 21h

END
