package geometry;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		
		//--------------Vezbe 2.-----------------
		Point p = new Point();
		// x je promenljiva definisana kao private
		// System.out.println(p.x); < Nije dozvoljeno pristupanje private promenljivama

		// metoda instance treba biti definisana i mora biti pozvana nad objektom
		// Point.getX();
		System.out.println(p.getX() + " " + p.getY() + " " + p.isSelected());

		p.setX(10);
		p.setY(10);
		p.setSelected(true);
		
		System.out.println(p.getX() + " " + p.getY() + " " + p.isSelected());
		
		System.out.println(p.distance(40, 50));
		
		//---------------Vezbe 3.--------------
		Point p1 = new Point();
		p1.setX(15);
		p1.setY(27);
		p1.setSelected(true);
		
		Rectangle r1 = new Rectangle();
		Line l1 = new Line();
		Circle c1 = new Circle();
		
		//1.
		p.setX(p1.getY());
		
		//2.
		l1.setStartPoint(p);
		l1.setEndPoint(p1);
		
		//3.
		l1.getEndPoint().setY(23);
		
		//4.
		l1.getStartPoint().setX(l1.getEndPoint().getY());
		
		//5.
		l1.getEndPoint().setX((int) (l1.length() - (l1.getStartPoint().getX() + l1.getStartPoint().getY())));
		
		//6.
		r1.setUpperLeftPoint(p); //Setujemo bilo koju tacku da bude ova
		//da ne bi program stao u sledecoj liniji 
		//(posto rectangle upperleft na pocetku programa je null pointer)
		r1.getUpperLeftPoint().setX(10);
		r1.getUpperLeftPoint().setY(15);
		System.out.println(r1.getUpperLeftPoint().getX() + " " + r1.getUpperLeftPoint().getY());
		
		//7.
		c1.setCenter(r1.getUpperLeftPoint());
		
		//8.
		//Dobili bi -15 bez setovanja vrednosti pravougaonika
		r1.setWidth(30);
		r1.setHeight(20);
		//Zato setujemo height i width pravougaonika ^
		c1.getCenter().setX(r1.area() - l1.getStartPoint().getY());
		System.out.println(c1.getCenter().getX());
		
		//--------------Vezba 4.--------------
		//Konstruktori , prvo smo definisali svaki u svojoj klasi , sad ide test
		Point p2 = new Point(50,100);
		Line l2 = new Line(p2 , new Point(400,500)); //moguce je generisati samo za konstruktor novi objekat.
		Rectangle r2 = new Rectangle(p2 , 50 , 80);
		Circle c2 = new Circle (p2 , 10);
		
		System.out.println(p2); //vraca nam geometry.Point@200a570f PRE REDEFINISANJA METODE toString
		//Kada redefinisemo metode moramo ih nazvati isto.
		//Nakon redefinisanja metode dobijamo (50 , 100)
		System.out.println(l2);
		System.out.println(r2);
		System.out.println(c2);
		// == da li su isti po referenci objekti 
		//equals() da li su isti po vrednosti objekta ali moramo redefinisati za neke kompleksne objekte (point , line , rectangle)
		Circle c3 = new Circle(p2 , 10);
		System.out.println(c2.equals(c3));
		
		//--------------Vezba 5.--------------
		//Definisanje contains , nasledjivanje
		
		//--------------Vezba 6.--------------
		//Definisanje Shape , draw metoda ; zatim Drawings klase pa crtanje
	
		//--------------Vezba 7.--------------
		//Interface implementacija , implementacija moveTo,moveBy,Compare
		
		System.out.println("\nTacka");
		System.out.println(p1);
		p1.moveBy(3, 5);
		System.out.println(p1);
		p1.moveTo(3, 5);
		System.out.println(p1);
		
		System.out.println("\nLinija");
		System.out.println(l1);
		l1.moveBy(3, 5);
		System.out.println(l1);
		l1.moveTo(3, 5); //Nece nista uraditi , nije implementirano
		System.out.println(l1);
		
		System.out.println("\nPravougaonik");
		System.out.println(r1);
		r1.moveBy(3, 5);
		System.out.println(r1);
		r1.moveTo(3, 5);
		System.out.println(r1);
		
		System.out.println("\nKrug");
		System.out.println(c1);
		c1.moveBy(3, 5);
		System.out.println(c1);
		c1.moveTo(3, 5);
		System.out.println(c1);
		
		System.out.println("\nKrug sa rupom");
		Donut d1 = new Donut(new Point(800,100), 20 , 10);
		System.out.println(d1);
		d1.moveBy(3, 5);
		System.out.println(d1);
		d1.moveTo(3, 5);
		System.out.println(d1);
		
		//Nizovi
		System.out.println("\nNizovi:");
		Shape d3 = new Donut(p1,10,5);
		Shape p3 = new Point();
		Shape l9 = l1;
		Shape c9 = c1;
		//Apstraktna klasa moze da stoji sa leve strane zbog dinamickog povezivanja ,
		//ali sa desne strane uvek mora biti konstruktor klasa koje nisu apstraktne
		//Ovo nam omogucuje da kreiramo niz sa razlicitim oblicima
		Shape[] shapes = {d3,p3,l9,c9};
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i]);//Shapes zna da pozove ToString (Proverava tip objekta)
		}
		System.out.println("---------------");
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].moveBy(1, 2); //Duplo je povecao liniju (krajnju tacku) i drugi krug zato sto svi referenciraju tacku p1
			System.out.println(shapes[i]);
		}
		System.out.println();
		int[] ints = {1,8,5,15,4};
		for(int i = 0; i < ints.length; i++) {
			System.out.print(ints[i] + " ");
		}
		System.out.println();
		Arrays.sort(ints); //Poziva metodu compareTo , i poredjuje elemente
		for(int i = 0; i < ints.length; i++) {
			System.out.print(ints[i] + " ");
		}
		System.out.println("\n" + java.util.Arrays.toString(ints));//Ispis u jednom redu za niz
		//Isto tako mozemo da imamo niz Tacaka , koji mozemo da sortiramo.
	
		//------------- Vezba 8.--------------
		//Array liste , Izuzeci , modifikovanje draw metode , Exception , Hash mape
		
		//------------- Vezba 9.--------------
		//WindowBuilder (instalacija paketa Help->Install new software->Latest eclipse release)
		//Kreiranje Frame
		//Package desni klik , new , Other , WindowBuilder, Swing designer , JFrame <-
		
		//------------- Vezba 10.-------------
		//JDialog
	}
}
