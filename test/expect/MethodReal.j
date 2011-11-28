.class public output/MethodReal
.super java/lang/Object
.method public static double(D)D
.limit stack 4
.limit locals 2
dload 0
dreturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 7
.limit locals 3
ldc2_w 0.00
dstore 1
ldc2_w 10.00
invokestatic output/MethodReal/double(D)D
dstore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
dload 1
invokevirtual java/io/PrintStream/println(D)V
return
.end method