; Compiled on Dec 7, 2009 8:56:43 PM
.class public output/FactorialMethod
.super java/lang/Object
.method public static faktorial(I)I
.limit stack 2
.limit locals 2
iload 0
ldc 1
isub
istore 1
lab1:
iload 1
ldc 1
if_icmplt lab2
iload 0
iload 1
imul
istore 0
iload 1
ldc 1
isub
istore 1
goto lab1
lab2:
iload 0
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 2
ldc 0
istore 1
ldc 8
invokestatic output/FactorialMethod/faktorial(I)I
istore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
return
.end method
