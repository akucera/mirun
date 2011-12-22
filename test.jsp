program TestovaciKod;
methods
method void pokus(int i;) {
	call println(i);
	i = i + 1;
	array int pole 3;
	call readfileintarr("vstupnisoubor.txt"; pole);
	string outfile = "vystupnisoubor.txt";
	[0]pole = 3;
	[1]pole = 2;
	[2]pole = 1;
	return;
}
	
sdohtem;
declare
eralced;
array int pole 10;
 for(int r = 0; r < 10; r = r + 1;) {
 	for(int s = 0; s < 10; s = s + 1;) {
  		call println([r]pole);
  	}
 }

int i = 1;
int a = 1;
while(i < 10) {
   if(a == 1) {
      call println("A je 1");
   }
}

margorp;
