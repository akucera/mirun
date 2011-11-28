; Compiled on Dec 7, 2009 8:56:11 PM
.class public output/BinaryAdd
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 4
ldc 1
istore 1
ldc2_w 3.14
dstore 2
iload 1
ldc 2
iadd
istore 1
dload 2
ldc2_w 2.78
dadd
dstore 2
iload 1
ldc 3
iadd
ldc 4
iadd
istore 1
return
.end method
