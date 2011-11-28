; Compiled on Dec 7, 2009 8:56:11 PM
.class public output/IfEQ2
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 5
.limit locals 3
ldc2_w 56.00
dstore 1
dload 1
ldc2_w 56.78
dcmpl
ifne lab1
dload 1
ldc2_w 3.45
dmul
dstore 1
lab1:
dload 1
ldc2_w 56.78
dcmpl
ifne lab2
dload 1
ldc2_w 2.34
dmul
dstore 1
lab2:
dload 1
ldc2_w 56.78
dcmpl
ifeq lab3
dload 1
ldc2_w 5.43
ddiv
dstore 1
lab3:
return
.end method
