package skeleton;


public class Palya{
	//Mezo tömb létrehozás:
	private Mezo mezoTomb [][]; 
	//2D-sre csináltam,  de ugye ez attól is függ, hogy milyenek lesznek a mezők
	//Targy tömb létrehozás:
	private Targy targyTomb []; 
	//1D-s tömb szerintem elég, viszont lehet hogy külön kéne a dobozokat tárolni, és külön a ZPM-eket
	//Szereplo létrehozás:
	private Szereplo szereplo; //mivel még csak az ezredest vagyunk hivatottak létrehozni, 
				   //ezért gondoltam így, de persze ezt is ki kell egészíteni 1D-s tömbre ha több szereplőnk lesz.

	private int num_mezo_x; //ez a mezőkkel való feltöltésnél fontos, gondolom nem akarunk lyukas pályát
	private int num_mezo_y;

	private int oszlopszam; // ez a pálya oszlopainak számát jelképezi
	private int sorszam; // ez a pálya sorainak a számát jelképezi

	// itt lesz a konstruktor
	//KONSTRUKTORBAN MEGHÍVJUK A FELTOLT FÜGGVÉNYEKET VALAMINT BEÁLLÍTJUK A PÁLYA OSZLOP ÉS SORSZÁMÁT
	//Sorrend: 1) először feltöltjük az egész pályát mezőkkel
	// 		   2) majd ha kész a pálya, felrakhatjuk rá a tárgyakat
	//		   3) végezetül rakjuk fel a szereplőt, mert pl dobozra nem lehet rakni

	private void FeltoltMezovel(){

		//feltöltjük mezőkkel a pályát valamilyen koncepció szerint UjMezo hívások lesznek itt
		//new operátorral létrehozunk egy új mezőt <<create>> pl simamezőt
		Mezo sm1= new SimaMezo();
		UjMezo(sm1);
		
		//létrehozunk egy falat is
		Mezo f1=new Fal();
		UjMezo(f1);

	}

	private void FeltoltTarggyal(){
		//feltöltjük mezőkkel a pályát valamilyen koncepció szerint UjTargy hívások lesznek itt
		//new operátorral létrehozunk egy új tárgyat <<create>> pl dobozt
		Targy d1= new Doboz();
		//UjTargy(d1);	//TODO - FIX
		
		//létrehozunk egy ZPM-et is
		//Targy zpm1=new(ZPM, 1, 1); //itt le kellene vizsgálni, hogy a mezoTomb-ben egyáltalán létezik-e (1,1) koordinátájú mező
		Targy zpm1= new ZPM();
		
		UjTargy(zpm1, 1, 2); //itt le kellene vizsgálni, hogy a mezoTomb-ben egyáltalán létezik-e (1,2) koordinátájú mező
	}

	public void UjMezo(Mezo mezoUj){
		//a mezoTomb koordinátájának megfelelő helyre beletöltjük
		mezoTomb[num_mezo_x][num_mezo_y]=mezoUj; 
		
		//a koordinátáknak megfelelően a Mezo pozícióját beállítjuk SetPozzal --> 
		//ez azt eredményezi, hogy az adott mező GetPoz-a ezeket a koordinátákat fogja majd visszaadni másnak
		mezoUj.SetPoz(num_mezo_x, num_mezo_y);
		
		//itt pedig beállítjuk, hogy a következő UjMezo hívásnál mi legyen a koordináta
		if(num_mezo_x%oszlopszam==0 && num_mezo_y<=sorszam){ 
		//ha az oszlopok száma elérte a beállított értéket, új sort kezd, feltéve, ha a sorok száma 
		//sem haladja még túl a beállított értéket
			num_mezo_x=0;
			num_mezo_y++;
		}
		
		else
			num_mezo_x++;
	}

	public void UjTargy(Targy targyUj, int targyPozX, int targyPozY){ 
	//SZERINTEM ITT ÉRDEMES LENNE ÁTADNI A POZÍCIÓKAT, HISZEN NEM MINDENHOVA RAKUNK TÁRGYAT
		//itt úgy kéne eltárolni a targyTomb-be a tárgyat, hogy levizsgáljuk, 
		//egyáltalán oda rakhatunk-e tárgyat (nyilván falra nem stb)
		//SetPoz-zal itt is be kéne állítani a tárgy pozícióját pl targyUj.SetPoz(targyPozX, targyPoz)-al, 
		//hogy később a GetPoz-zal le tudjuk kérdezni
		
		//másik opció, mikor az ezredes leteszi a tárgyat, akkor ezt a függvényt fogja meghívni
		
		//Esetleg rendezetten rakhatnánk bele a tárgy tömbbe a tárgyat, pozíció szerint --> 
		//ez megkönnyítené a törlés során a keresést
	}

	public void UjSzereplo(Szereplo szereploUj, int kezdoPozX, int kezdoPozY){
		//itt is le kéne vizsgálni, hogy egyáltalán rakhatjuk-e a megadott helyre a szereplőnket 
		//(nem szakadék, fal, doboz teteje)
		// valamint SetPoz-zal itt is be kéne állítani a szereplő pozícióját, hogy később le tudjuk kérdezni akár 
		//a kezdőpozíciót is
	}
	
	public void TorolTargy(Targy targyTorlendo){
		//ezt kívülről hívja meg majd az ezredes, amikor felveszi a dobozt vagy a ZPM-et 
		//(a ZPM-nél nem itt fogjuk beállítani a számlálást, hanem az ezredes felvesz függvényénél, 
		//mielőtt meghívjuk ezt a függvényt)
		
		// ugyanezt hívjuk meg, ha a dobozt az ezredes belerakja a szakadékba
		//itt töröljük a tömbből a tárgyat egy kereséssel (getPoz alapján), majd töröljük, és végül rendezzük a 
		//tömböt (minden későbbi helyen levő tárgyat egy hellyel előrébb hozunk)
	}
	
	public void TorolSzereplo(Szereplo szerTorlendo){
		//ha meghal a szereplőnk --> ez hívódik meg
		//innen meg kéne hívni egy prvát törölmezőt is (ez kívülről nem kell látszódjon, hiszen a szereplő halála okozza)
	}
	
	private void TorolMezok(){
		//Itt a mezoTomb tartalmát teljes egészében törölnénk
	}
}