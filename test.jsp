program Knapsack;

methods
	method void getnextconfiguration(array current) {
		int index = call length(current) - 1;
		while (1==1) {
			if ([index]current == 1) {
				if (index == 0) {
					return;
				}
				if (index != 0) {
					[index]current = 0;
					index = index-1;
				}
			}
			if([index]current != 1){
				[index]current = 1;
				return;
			}
			
		}
	};
	
	method int pow(int n; int x) {
		int result = n;
		if(x == 0) {
			return 1;
		}
		if(n == 0) {
			return 0;
		}
		
		for(int j = 1; j < x; j = j + 1;) {
			result = result * n;
		}
		return result;
	};
	
	method void copyarr(array source; array dest) {
		for(int i = 0; i < call length(dest);; i = i + 1;) {
			[i]dest = [i]source;
		}
		return;
	};
sdohtem;

declare
eralced;

string inputfile = "01knap_itemsCount4_maxWeight100.dat";

int itemsCount = call readfileint(inputfile);;
array int inputdata (itemsCount * 2) + 2;
call readfileintarr(inputfile; inputdata);

int capacity = [1]inputdata;
array int v itemsCount;
array int c itemsCount;

for (int u = 0; u < itemsCount; u = u + 1;) {
   	[u]v = [2*u + 2]inputdata;
	[u]c = [2*u+3]inputdata;
}

array int mask itemsCount;

for(int i1 = 0; i1 < itemsCount; i1 = i1 + 1;) {
	[i1]mask = 0;
}

call println("Backpack start configuration:");
call print("  capacity: ");
call println(capacity);
call print("  prices:   ");
call println(c);
call print("  weights:  ");
call println(v);

int curw = 0;
int curp = 0;

int bestprofit = 0;
int bpweight = 0;
array int bestcombination itemsCount;

int run = 1;
int try = 0;

int combcount = call pow(2; itemsCount);;

while(run == 1) {
	if(try >= combcount) {
		run = 0;
	}
	
	for(int i2 = 0; i2 < itemsCount; i2 = i2 + 1;) {
		if([i2]mask == 1)	{
			curw = curw + [i2]v;
			curp = curp + [i2]c;
		}
	}
	
	if(curw <= capacity) {	
		if(curp > bestprofit) {
			bestprofit = curp;
			bpweight = curw;
			
			call copyarr(mask; bestcombination);
			
			call print("Found solution candidate ... weight: ");
			call print(bpweight);
			call print(" price: ");
			call println(bestprofit);
		}
	}
	curp = 0;
	curw = 0;
	
	call getnextconfiguration(mask);
	
	try = try + 1;
}
call println("--------------------------");
call println("Solution: ");
call print("  profit:        ");
call println(bestprofit);
call print("  used capacity: ");
call println(bpweight);
call print("  combination:   ");
call println(bestcombination)

margorp;
