; Compiled on Dec 7, 2009 8:56:11 PM
.class public output/IfEQ1
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 2
ldc 42
istore 1
iload 1
ldc 42
if_icmpne lab1
iload 1
ldc 20
iadd
istore 1
lab1:
iload 1
ldc 42
if_icmpne lab2
iload 1
ldc 10
iadd
istore 1
lab2:
iload 1
ldc 42
if_icmpeq lab3
iload 1
ldc 10
isub
istore 1
lab3:
return
.end method
