DOSSEG
.MODEL TINY
.STACK 100H
.DATA
A DB 0,1,2,3,4,5,6,7,8,9
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

L:;start 
mov dx ,[bx]
add dl , 30h
mov ah , 2
int 21h
inc bx
LOOP L  ;end

mov ah , 4ch
int 21h

END
