; Compiled on Dec 7, 2009 8:56:11 PM
.class public output/IfIf
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 3
.limit locals 2
ldc 97
istore 1
iload 1
ldc 1
if_icmple lab1
iload 1
ldc 2
if_icmple lab2
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
lab2:
iload 1
ldc 2
if_icmpgt lab3
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
ldc 1
isub
invokevirtual java/io/PrintStream/println(I)V
lab3:
lab1:
iload 1
ldc 1
if_icmpgt lab4
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
ldc 2
isub
invokevirtual java/io/PrintStream/println(I)V
lab4:
return
.end method
