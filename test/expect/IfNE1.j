.class public output/IfNE1
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 2
ldc 42
istore 1
iload 1
ldc 42
if_icmpeq lab1
iload 1
ldc 20
iadd
istore 1
lab1:
iload 1
ldc 42
if_icmpeq lab2
iload 1
ldc 10
iadd
istore 1
lab2:
iload 1
ldc 42
if_icmpne lab3
iload 1
ldc 10
isub
istore 1
lab3:
return
.end method