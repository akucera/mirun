; Compiled on Dec 7, 2009 8:56:43 PM
.class public output/Operators
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 1
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 1
ldc 2
isub
ldc 3
isub
ldc 4
isub
ldc 9
iadd
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 100
ldc 25
idiv
ldc 4
idiv
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 3
ldc 2
ldc 30
imul
iadd
ldc 62
isub
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 4
ldc 3
isub
invokevirtual java/io/PrintStream/println(I)V
return
.end method
