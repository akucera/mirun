program TestovaciKod;
methods

method void pokus(int i; int b; int c) {

 call println(i);
 call println(b);
 call println(c);
 return;
}

sdohtem;

declare
eralced;

call pokus(1; 2; 3);

array int vstup 9;
call readfileintarr("vstupnisoubor.txt";vstup);

array int inputarr 9;
[0]inputarr = 100;
[1]inputarr = 18;
[2]inputarr = 114;
[3]inputarr = 42;
[4]inputarr = 136;
[5]inputarr = 88;
[6]inputarr = 192;
[7]inputarr = 3;
[8]inputarr = 223;

int problemsize = 4;
array int prices 4;
array int weights 4;
int delka = call length(prices);;

int capacity = [0]inputarr;

for(int i = 0; i <= delka; i = i + 1;) {
	[i]weights = [2*i+1]inputarr;
	[i]prices = [2*i+2]inputarr;
}

int bestprofit = 0;
call println("Seznam polozek v batohu [cena, vaha]:")
for(int j = 0; j < 3; j = j + 1;) {
	call print("[");
	call print([j]prices);
	call print(",");
	call print([j]weights);
	call println("]");
}


call print("Best profit: ");
call println(bestprofit);

margorp;
