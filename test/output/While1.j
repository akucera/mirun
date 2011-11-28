; Compiled on Dec 7, 2009 8:56:11 PM
.class public output/While1
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 2
ldc 10
istore 1
lab1:
iload 1
ldc 20
if_icmpge lab2
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
iload 1
ldc 1
iadd
istore 1
goto lab1
lab2:
return
.end method
