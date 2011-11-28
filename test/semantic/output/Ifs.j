; Compiled on Dec 7, 2009 8:56:43 PM
.class public output/Ifs
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 6
.limit locals 3
ldc 22
i2d
ldc 7
i2d
ddiv
dstore 1
dload 1
ldc2_w 3.00
dcmpl
ifle lab1
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 1
invokevirtual java/io/PrintStream/println(I)V
dload 1
ldc2_w 3.10
dcmpl
iflt lab2
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 2
invokevirtual java/io/PrintStream/println(I)V
dload 1
ldc2_w 3.15
dcmpg
ifgt lab3
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 3
invokevirtual java/io/PrintStream/println(I)V
dload 1
ldc2_w 3.15
dcmpg
ifge lab4
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 4
invokevirtual java/io/PrintStream/println(I)V
dload 1
ldc 23
i2d
ldc 7
i2d
ddiv
dcmpl
ifeq lab5
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 5
invokevirtual java/io/PrintStream/println(I)V
dload 1
ldc 22
i2d
ldc 7
i2d
ddiv
dcmpl
ifne lab6
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 6
invokevirtual java/io/PrintStream/println(I)V
lab6:
lab5:
lab4:
lab3:
lab2:
lab1:
return
.end method
