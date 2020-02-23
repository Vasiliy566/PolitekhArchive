# binEqualitySolver
![alt text](https://www.spbstu.ru/upload/branding/logo_main.png)


Check equals of equation : (equation1) => (equation2) for all variables
To use:
1. clone this code to your IDE
2. change boolean functions equation1 and equation2 in com.binSolver.politekh/src/binsolv.java to your boolean expressions (where equation1 => equation2)
3. bash ./run
***
## commands:
##### 1. git clone https://github.com/Vasiliy566/binEqualitySolver
##### 2. change in com.binSolver.politekh/src/binsolv.java functions equation1 and equation2:
```
 static boolean equation1(boolean a, boolean b, boolean c, boolean d) {
        return (((d | a) & d) | (((b & ( !(b | d) ) ) & (((c & ((b ^ d) & c)) & b) & c)) & b)); //<-- Change it!
    }
```  
##### In task: equation1 => equation2

##### 3. bash ./run

### output:

```
x1 x2 x3 x4 result1 |  x1 x2 x3 x4 result
0  0  0  0  0       |  0  0  0  0  0  
0  0  0  1  1       |  0  0  0  1  1  
0  0  1  0  0       |  0  0  1  0  0  
0  0  1  1  1       |  0  0  1  1  1  
0  1  0  0  0       |  0  1  0  0  1  
0  1  0  1  1       |  0  1  0  1  1  
0  1  1  0  0       |  0  1  1  0  1  
0  1  1  1  1       |  0  1  1  1  1  
1  0  0  0  0       |  1  0  0  0  0  
1  0  0  1  1       |  1  0  0  1  1  
1  0  1  0  0       |  1  0  1  0  0  
1  0  1  1  1       |  1  0  1  1  1  
1  1  0  0  0       |  1  1  0  0  1  
1  1  0  1  1       |  1  1  0  1  1  
1  1  1  0  0       |  1  1  1  0  1  
1  1  1  1  1       |  1  1  1  1  1  
res1 = 0 res2 = 0 (res1 => res2) = true
res1 = 1 res2 = 1 (res1 => res2) = true
res1 = 0 res2 = 0 (res1 => res2) = true
res1 = 1 res2 = 1 (res1 => res2) = true
res1 = 0 res2 = 1 (res1 => res2) = true
res1 = 1 res2 = 1 (res1 => res2) = true
res1 = 0 res2 = 1 (res1 => res2) = true
res1 = 1 res2 = 1 (res1 => res2) = true
res1 = 0 res2 = 0 (res1 => res2) = true
res1 = 1 res2 = 1 (res1 => res2) = true
res1 = 0 res2 = 0 (res1 => res2) = true
res1 = 1 res2 = 1 (res1 => res2) = true
res1 = 0 res2 = 1 (res1 => res2) = true
res1 = 1 res2 = 1 (res1 => res2) = true
res1 = 0 res2 = 1 (res1 => res2) = true
res1 = 1 res2 = 1 (res1 => res2) = true
 All implications was true
```
***
## Logical Operations:
```
name | symbol | example
------------------------
or   |    |   |   a | b
------------------------
and  |    &   |   a & b
------------------------
not  |    !   |    !a
------------------------
xor  |    ^   |   a ^ b
------------------------
```

### Expression example
1. (a & b) | c
2. (((d | a) & d) | (((b & ( !(b | d) ) ) & (((c & ((b ^ d) & c)) & b) & c)) & b))
