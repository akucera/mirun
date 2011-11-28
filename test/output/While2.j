; Compiled on Dec 7, 2009 8:56:11 PM
.class public output/While2
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 4
ldc 10
istore 1
ldc 100
istore 2
ldc 0
istore 3
iload 2
istore 3
lab1:
iload 2
iload 1
if_icmple lab2
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 3
invokevirtual java/io/PrintStream/println(I)V
iload 2
iload 1
isub
istore 3
iload 1
ldc 10
iadd
istore 1
goto lab1
lab2:
return
.end method
