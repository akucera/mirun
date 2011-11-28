.class public output/Writeln1
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 3
.limit locals 3
ldc2_w 1.23
dstore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
dload 1
invokevirtual java/io/PrintStream/println(D)V
return
.end method