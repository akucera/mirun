.class public output/MethodInt
.super java/lang/Object
.method public static integer(I)I
.limit stack 2
.limit locals 1
iload 0
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 2
ldc 0
istore 1
ldc 10
invokestatic output/MethodInt/integer(I)I
istore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
return
.end method