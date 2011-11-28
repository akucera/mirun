.class public output/For2
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 2
ldc 10
istore 1
lab1:
iload 1
ldc 0
if_icmplt lab2
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
iload 1
ldc 1
isub
istore 1
goto lab1
lab2:
return
.end method