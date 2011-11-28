.class public output/BinaryMul
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 4
ldc 1
istore 1
ldc2_w 3.13
dstore 2
iload 1
ldc 2
imul
istore 1
dload 2
ldc2_w 2.78
dmul
dstore 2
iload 1
ldc 3
imul
ldc 4
imul
istore 1
return
.end method