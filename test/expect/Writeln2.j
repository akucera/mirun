.class public output/Writeln2
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 7
.limit locals 4
ldc2_w 1.23
dstore 1
ldc 5
istore 3
getstatic java/lang/System/out Ljava/io/PrintStream;
dload 1
dload 1
ldc2_w 1.23
dadd
dmul
invokevirtual java/io/PrintStream/println(D)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 3
iload 3
ldc 1
isub
imul
iload 3
ldc 2
isub
imul
invokevirtual java/io/PrintStream/println(I)V
return
.end method