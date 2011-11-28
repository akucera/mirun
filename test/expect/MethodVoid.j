.class public output/MethodVoid
.super java/lang/Object
.method public static print()V
.limit stack 2
.limit locals 0
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 10
invokevirtual java/io/PrintStream/println(I)V
return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 1
.limit locals 1
invokestatic output/MethodVoid/print()V
return
.end method