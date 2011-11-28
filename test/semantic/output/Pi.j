; Compiled on Dec 7, 2009 8:56:43 PM
.class public output/Pi
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 6
.limit locals 5
ldc 3
istore 1
ldc 0
ldc 1
isub
istore 2
ldc2_w 1.00
dstore 3
lab1:
iload 1
ldc 2147483647
if_icmpge lab2
dload 3
iload 2
i2d
iload 1
i2d
ddiv
dadd
dstore 3
ldc 0
iload 2
isub
istore 2
iload 1
ldc 2
iadd
istore 1
goto lab1
lab2:
dload 3
ldc2_w 4.00
dmul
dstore 3
getstatic java/lang/System/out Ljava/io/PrintStream;
dload 3
invokevirtual java/io/PrintStream/println(D)V
return
.end method
