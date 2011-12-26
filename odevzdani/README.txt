KOMPILACE KOMPILÁTORU A INTERPRETERU
Kompilátor a interpreter jsou samostatné programy.
Ve složkách "interpreter" a "compiler" jsou build.xml build soubory pro ant.

Pro kompilaci každé části je třeba v dané složce spuštit příkazy:
	ant clean
	ant compile
	ant jar

V podsložce build/classes/ jsou zkompilované třídy, v podsložce build/jar/ 
se nachází spustitelný jar soubor.


Ve složce ./bin jsou předkompilované programy interpreteru a kompilátoru.



KOMPILACE A SPUŠTĚNÍ VLASTNÍHO PROGRAMU
Nejprve je potřeba zkompilovat zdrojový kód vlastního programu.
Kompilátor přijimá 2 vstupy: <vstupní soubor> <výstupní soubor>

Příklad kompilace:
	java -jar run-compiler.jar ../samples/backpack-source.txt ../samples/backpack.mirun

Když se kód podaří v pořádku zkompilovat, zobrazí se zpráva:
	Bytecode successfully built. Output file name: "../samples/backpack.mirun"

Kompilátor vytvoří soubor s bytecodem s názvem dle parametru a vytvoří i soubor 
s textovou reprezentací instrukcí s názvem "soubor.mirun.instr"


Spuštění kódu
Interpreter požaduje jeden parametr: <vstupní zkompilovaný soubor>
Interpreter standardně vypisuje na konzoli. Pro zápis výstupu interpreteru do 
souboru je třeba nastavit Javě systémovou proměnnou "outfile" na jméno 
výstupního souboru.

Spuštění s výpisem na konzoli (standardní):
	java -jar run-interpreter.jar ../samples/backpack.mirun

Následuje vykonání programu a jeho výpisů. Po doběhnutí programu interpreter 
vypíše zprávu, že program doběhu.
Př.:
	Program ended with status 0 (OK) in 0.429 seconds.


Spuštění s výpisem do souboru:
	java -Doutfile=../samples/backpack.out -jar run-interpreter.jar ../samples/backpack.mirun

Po provedení programu interpreter vypíše zprávu, že běh skončil.
Př.:
	Program ended with status 0 (OK) in 0.377 seconds.
	Output written to file backpack.out