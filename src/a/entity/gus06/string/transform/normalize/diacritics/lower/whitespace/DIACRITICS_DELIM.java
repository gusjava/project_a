package a.entity.gus06.string.transform.normalize.diacritics.lower.whitespace;

public class DIACRITICS_DELIM {

	public static String normalize(String s, String delim)
	{
		int l = s.length();
		StringBuilder b = new StringBuilder();
		for(int i=0;i<l;i++) handle(b,s.charAt(i),delim);
		return b.toString();
	}
	
	private static void handle(StringBuilder b, char c, String delim)
	{
		switch(c)
		{
		// lowercase
		
		case 'a':b.append('a');break;//a
		case 'b':b.append('b');break;//b
		case 'c':b.append('c');break;//c
		case 'd':b.append('d');break;//d
		case 'e':b.append('e');break;//e
		case 'f':b.append('f');break;//f
		case 'g':b.append('g');break;//g
		case 'h':b.append('h');break;//h
		case 'i':b.append('i');break;//i
		case 'j':b.append('j');break;//j
		case 'k':b.append('k');break;//k
		case 'l':b.append('l');break;//l
		case 'm':b.append('m');break;//m
		case 'n':b.append('n');break;//n
		case 'o':b.append('o');break;//o
		case 'p':b.append('p');break;//p
		case 'q':b.append('q');break;//q
		case 'r':b.append('r');break;//r
		case 's':b.append('s');break;//s
		case 't':b.append('t');break;//t
		case 'u':b.append('u');break;//u
		case 'v':b.append('v');break;//v
		case 'w':b.append('w');break;//w
		case 'x':b.append('x');break;//x
		case 'y':b.append('y');break;//y
		case 'z':b.append('z');break;//z
		
		// uppercase
		
		case 'A':b.append('a');break;//A
		case 'B':b.append('b');break;//B
		case 'C':b.append('c');break;//C
		case 'D':b.append('d');break;//D
		case 'E':b.append('e');break;//E
		case 'F':b.append('f');break;//F
		case 'G':b.append('g');break;//G
		case 'H':b.append('h');break;//H
		case 'I':b.append('i');break;//I
		case 'J':b.append('j');break;//J
		case 'K':b.append('k');break;//K
		case 'L':b.append('l');break;//L
		case 'M':b.append('m');break;//M
		case 'N':b.append('n');break;//N
		case 'O':b.append('o');break;//O
		case 'P':b.append('p');break;//P
		case 'Q':b.append('q');break;//Q
		case 'R':b.append('r');break;//R
		case 'S':b.append('s');break;//S
		case 'T':b.append('t');break;//T
		case 'U':b.append('u');break;//U
		case 'V':b.append('v');break;//V
		case 'W':b.append('w');break;//W
		case 'X':b.append('x');break;//X
		case 'Y':b.append('y');break;//Y
		case 'Z':b.append('z');break;//Z
		
		// digits
		
		case '0':b.append('0');break;//0
		case '1':b.append('1');break;//1
		case '2':b.append('2');break;//2
		case '3':b.append('3');break;//3
		case '4':b.append('4');break;//4
		case '5':b.append('5');break;//5
		case '6':b.append('6');break;//6
		case '7':b.append('7');break;//7
		case '8':b.append('8');break;//8
		case '9':b.append('9');break;//9
		
		// lowercase diacritics
		
		case '\u00e0':b.append('a');break;//� accent grave
		case '\u00e1':b.append('a');break;//� accent aigu
		case '\u00e2':b.append('a');break;//� accent circonflexe
		case '\u00e3':b.append('a');break;//� tilde
		case '\u00e4':b.append('a');break;//� tr�ma
		case '\u00e5':b.append('a');break;//� rond en chef
		case '\u0101':b.append('a');break;//a macron (accent plat)
		case '\u0103':b.append('a');break;//a br�ve (accent goutte)
		case '\u0105':b.append('a');break;//a ogonek (petite queue)
		case '\u1ea1':b.append('a');break;//a point souscrit
		case '\u01ce':b.append('a');break;//a hatchek (accent hirondelle)
		case '\u0227':b.append('a');break;//a point suscrit
		
		case '\u00e6':b.append("ae");break;//�
		case '\u01fd':b.append("ae");break;//� accent aigu
		case '\u01e3':b.append("ae");break;//� macron
		
		case '\u1e03':b.append('b');break;//b point suscrit
		case '\u1e05':b.append('b');break;//b point souscrit
		case '\u0253':b.append('b');break;//b crochet
		
		case '\u00e7':b.append('c');break;//� c�dille
		case '\u0107':b.append('c');break;//c accent aigu
		case '\u0109':b.append('c');break;//c accent circonflexe
		case '\u010b':b.append('c');break;//c point suscrit
		case '\u010d':b.append('c');break;//c hatchek
		case '\u0188':b.append('c');break;//c crochet
		case '\u00f0':b.append('c');break;//� barre
		
		case '\u010f':b.append('d');break;//d hatchek
		case '\u0257':b.append('d');break;//d crochet
		case '\u1e0b':b.append('d');break;//d point suscrit
		case '\u1e0d':b.append('d');break;//d point souscrit
		case '\u1e11':b.append('d');break;//d c�dille
		
		case '\u00e8':b.append('e');break;//� accent grave
		case '\u00e9':b.append('e');break;//� accent aigu
		case '\u00ea':b.append('e');break;//� accent circonflexe
		case '\u00eb':b.append('e');break;//� tr�ma
		case '\u0113':b.append('e');break;//e macron
		case '\u0115':b.append('e');break;//e br�ve
		case '\u0117':b.append('e');break;//e point suscrit
		case '\u0119':b.append('e');break;//e ogonek
		case '\u011b':b.append('e');break;//e hatchek
		case '\u0229':b.append('e');break;//e c�dille
		case '\u1eb9':b.append('e');break;//e point souscrit
		case '\u1ebd':b.append('e');break;//e tilde
		
		case '\u1e1f':b.append('f');break;//f point suscrit
		
		case '\u011d':b.append('g');break;//g accent circonflexe
		case '\u011f':b.append('g');break;//g br�ve
		case '\u0121':b.append('g');break;//g point suscrit
		case '\u0123':b.append('g');break;//g c�dille
		case '\u01e5':b.append('g');break;//g barre
		case '\u01e7':b.append('g');break;//g hatchek
		case '\u0260':b.append('g');break;//g crochet
		case '\u01f5':b.append('g');break;//g accent aigu
		case '\u1e21':b.append('g');break;//g macron
		
		case '\u0125':b.append('h');break;//h accent circonflexe
		case '\u0127':b.append('h');break;//h barre
		case '\u1e23':b.append('h');break;//h point suscrit
		case '\u1e25':b.append('h');break;//h point souscrit
		case '\u1e27':b.append('h');break;//h tr�ma
		case '\u1e29':b.append('h');break;//h c�dille
		case '\u021f':b.append('h');break;//h hatchek
		
		case '\u00ec':b.append('i');break;//� accent grave
		case '\u00ed':b.append('i');break;//� accent aigu
		case '\u00ee':b.append('i');break;//� accent circonflexe
		case '\u00ef':b.append('i');break;//� tr�ma
		case '\u01d0':b.append('i');break;//i hatchek
		case '\u0129':b.append('i');break;//i tilde
		case '\u012b':b.append('i');break;//i macron
		case '\u012d':b.append('i');break;//i br�ve
		case '\u012f':b.append('i');break;//i ogonek
		case '\u1ecb':b.append('i');break;//i point souscrit
		
		case '\u0135':b.append('j');break;//j accent circonflexe
		case '\u01f0':b.append('j');break;//j hatchek
		
		case '\u1e31':b.append('k');break;//k accent aigu
		case '\u1e33':b.append('k');break;//k point souscrit
		case '\u0137':b.append('k');break;//k c�dille
		case '\u0199':b.append('k');break;//k crochet
		case '\u01e9':b.append('k');break;//k hatchek
		
		case '\u1e37':b.append('l');break;//l point souscrit
		case '\u013a':b.append('l');break;//l accent aigu
		case '\u013c':b.append('l');break;//l c�dille
		case '\u013e':b.append('l');break;//l hatchek
		case '\u0142':b.append('l');break;//l barre
		
		case '\u1e3f':b.append('m');break;//m accent aigu
		case '\u1e41':b.append('m');break;//m point suscrit
		case '\u1e43':b.append('m');break;//m point souscrit
		
		case '\u1e45':b.append('n');break;//n point suscrit
		case '\u1e47':b.append('n');break;//n point souscrit
		case '\u01f9':b.append('n');break;//n accent grave
		case '\u00f1':b.append('n');break;//� tilde
		case '\u0144':b.append('n');break;//n accent aigu
		case '\u0146':b.append('n');break;//n c�dille
		case '\u0148':b.append('n');break;//n hatchek
		
		case '\u00f2':b.append('o');break;//� accent grave
		case '\u00f3':b.append('o');break;//� accent aigu
		case '\u00f4':b.append('o');break;//� accent circonflexe
		case '\u00f5':b.append('o');break;//� tilde
		case '\u00f6':b.append('o');break;//� tr�ma
		case '\u00f8':b.append('o');break;//� barre
		case '\u014d':b.append('o');break;//o macron
		case '\u014f':b.append('o');break;//o br�ve
		case '\u0151':b.append('o');break;//o double accent aigu
		case '\u01a1':b.append('o');break;//o corne
		case '\u01d2':b.append('o');break;//o hatchek
		case '\u01eb':b.append('o');break;//o ogonek
		case '\u022f':b.append('o');break;//o point suscrit
		case '\u1ecd':b.append('o');break;//o point souscrit
		
		case '\u0153':b.append("oe");break;//�

		case '\u1e55':b.append('p');break;//p accent aigu
		case '\u1e57':b.append('p');break;//p point suscrit
		case '\u01a5':b.append('p');break;//p crochet
		
		case '\u1e59':b.append('r');break;//r point suscrit
		case '\u1e5b':b.append('r');break;//r point souscrit
		case '\u0155':b.append('r');break;//r accent aigu
		case '\u0157':b.append('r');break;//r c�dille
		case '\u0159':b.append('r');break;//r hatchek
		
		case '\u1e61':b.append('s');break;//s point suscrit
		case '\u1e63':b.append('s');break;//s point souscrit
		case '\u015b':b.append('s');break;//s accent aigu
		case '\u015d':b.append('s');break;//s accent circonflexe
		case '\u015f':b.append('s');break;//s c�dille
		case '\u0161':b.append('s');break;//� hatchek
		case '\u0219':b.append('s');break;//s virgule
		
		case '\u1e6b':b.append('t');break;//t point suscrit
		case '\u1e6d':b.append('t');break;//t point souscrit
		case '\u0163':b.append('t');break;//t c�dille
		case '\u0165':b.append('t');break;//t hatchek
		case '\u0167':b.append('t');break;//t barre
		case '\u01ad':b.append('t');break;//t crochet
		case '\u021b':b.append('t');break;//t virgule
		case '\u1e97':b.append('t');break;//t tr�ma
		
		case '\u1ee5':b.append('u');break;//u point souscrit
		case '\u00f9':b.append('u');break;//� accent grave
		case '\u00fa':b.append('u');break;//� accent aigu
		case '\u00fb':b.append('u');break;//� accent circonflexe
		case '\u00fc':b.append('u');break;//� tr�ma
		case '\u0169':b.append('u');break;//u tilde
		case '\u016b':b.append('u');break;//u macron
		case '\u016d':b.append('u');break;//u br�ve
		case '\u016f':b.append('u');break;//u rond en chef
		case '\u0171':b.append('u');break;//u double accent aigu
		case '\u0173':b.append('u');break;//u ogonek
		case '\u01b0':b.append('u');break;//u corne
		case '\u01d4':b.append('u');break;//u hatchek
		
		case '\u1e7d':b.append('v');break;//v tilde
		case '\u1e7f':b.append('v');break;//v point souscrit
		
		case '\u0175':b.append('w');break;//w accent circonflexe
		case '\u1e81':b.append('w');break;//w accent grave
		case '\u1e83':b.append('w');break;//w accent aigu
		case '\u1e85':b.append('w');break;//w tr�ma
		case '\u1e87':b.append('w');break;//w point suscrit
		case '\u1e89':b.append('w');break;//w point souscrit
		case '\u1e98':b.append('w');break;//w rond en chef
		
		case '\u1e8b':b.append('x');break;//x point suscrit
		case '\u1e8d':b.append('x');break;//x tr�ma
		
		case '\u0177':b.append('y');break;//y accent circonflexe
		case '\u00ff':b.append('y');break;//� tr�ma
		case '\u00fd':b.append('y');break;//� accent aigu
		case '\u1e8f':b.append('y');break;//y point suscrit
		case '\u01b4':b.append('y');break;//y crochet
		case '\u0233':b.append('y');break;//y macron
		case '\u1ef5':b.append('y');break;//y point souscrit
		case '\u1ef9':b.append('y');break;//y tilde
		case '\u1ef3':b.append('y');break;//y accent grave
		case '\u1e99':b.append('y');break;//y rond en chef
		
		case '\u017a':b.append('z');break;//z accent aigu
		case '\u017c':b.append('z');break;//z point suscrit
		case '\u017e':b.append('z');break;//� hatchek
		case '\u01b6':b.append('z');break;//z barre
		case '\u1e91':b.append('z');break;//z accent circonflexe
		case '\u1e93':b.append('z');break;//z point souscrit
		
		// uppercase diacritics
		
		case '\u00c0':b.append('a');break;//� accent grave
		case '\u00c1':b.append('a');break;//� accent aigu
		case '\u00c2':b.append('a');break;//� accent circonflexe
		case '\u00c3':b.append('a');break;//� tilde
		case '\u00c4':b.append('a');break;//� tr�ma
		case '\u00c5':b.append('a');break;//� rond en chef
		case '\u0100':b.append('a');break;//A macron
		case '\u0102':b.append('a');break;//A br�ve
		case '\u0104':b.append('a');break;//A ogonek
		case '\u1ea0':b.append('a');break;//A point souscrit
		case '\u01cd':b.append('a');break;//A hatchek
		case '\u0226':b.append('a');break;//A point suscrit
		
		case '\u00c6':b.append("ae");break;//�
		case '\u01fc':b.append("ae");break;//� accent aigu
		case '\u01e2':b.append("ae");break;//� macron
		
		case '\u0243':b.append('b');break;//B barre
		case '\u1e02':b.append('b');break;//B point suscrit
		case '\u1e04':b.append('b');break;//B point souscrit
		case '\u0181':b.append('b');break;//B crochet
		
		case '\u00c7':b.append('c');break;//� c�dille
		case '\u0106':b.append('c');break;//C accent aigu
		case '\u0108':b.append('c');break;//C accent circonflexe
		case '\u010a':b.append('c');break;//C point suscrit
		case '\u010c':b.append('c');break;//C hatchek
		case '\u0187':b.append('c');break;//C crochet
		
		case '\u00d0':b.append('d');break;//D barre
		case '\u010e':b.append('d');break;//D hatchek
		case '\u018a':b.append('d');break;//D crochet
		case '\u1e0a':b.append('d');break;//D point suscrit
		case '\u1e0c':b.append('d');break;//D point souscrit
		case '\u1e10':b.append('d');break;//D c�dille
		
		case '\u00c8':b.append('e');break;//� accent grave
		case '\u00c9':b.append('e');break;//� accent aigu
		case '\u00ca':b.append('e');break;//� accent circonflexe
		case '\u00cb':b.append('e');break;//� tr�ma
		case '\u0112':b.append('e');break;//E macron
		case '\u0114':b.append('e');break;//E br�ve
		case '\u0116':b.append('e');break;//E point suscrit
		case '\u0118':b.append('e');break;//E ogonek
		case '\u011a':b.append('e');break;//E hatchek
		case '\u0228':b.append('e');break;//E c�dille
		case '\u1eb8':b.append('e');break;//E point souscrit
		case '\u1ebc':b.append('e');break;//E tilde
		
		case '\u1e1e':b.append('f');break;//F point suscrit
		
		case '\u011c':b.append('g');break;//G accent circonflexe
		case '\u011e':b.append('g');break;//G br�ve
		case '\u0120':b.append('g');break;//G point suscrit
		case '\u0122':b.append('g');break;//G c�dille
		case '\u01e4':b.append('g');break;//G barre
		case '\u01e6':b.append('g');break;//G hatchek
		case '\u0193':b.append('g');break;//G crochet
		case '\u01f4':b.append('g');break;//G accent aigu
		case '\u1e20':b.append('g');break;//G macron
		
		case '\u0124':b.append('h');break;//H accent circonflexe
		case '\u0126':b.append('h');break;//H barre
		case '\u1e22':b.append('h');break;//H point suscrit
		case '\u1e24':b.append('h');break;//H point souscrit
		case '\u1e26':b.append('h');break;//H tr�ma
		case '\u1e28':b.append('h');break;//H c�dille
		case '\u021e':b.append('h');break;//H hatchek
		
		case '\u00cc':b.append('i');break;//� accent grave
		case '\u00cd':b.append('i');break;//� accent aigu
		case '\u00ce':b.append('i');break;//� accent circonflexe
		case '\u00cf':b.append('i');break;//� tr�ma
		case '\u01cf':b.append('i');break;//I hatchek
		case '\u0128':b.append('i');break;//I tilde
		case '\u012a':b.append('i');break;//I macron
		case '\u012c':b.append('i');break;//I br�ve
		case '\u012e':b.append('i');break;//I ogonek
		case '\u0130':b.append('i');break;//I point suscrit
		case '\u1eca':b.append('i');break;//I point souscrit
		
		case '\u0134':b.append('j');break;//J accent circonflexe
		
		case '\u1e30':b.append('k');break;//K accent aigu
		case '\u1e32':b.append('k');break;//K point souscrit
		case '\u0136':b.append('k');break;//K c�dille
		case '\u0198':b.append('k');break;//K crochet
		case '\u01e8':b.append('k');break;//K hatchek
		
		case '\u1e36':b.append('l');break;//L point souscrit
		case '\u0139':b.append('l');break;//L accent aigu
		case '\u013b':b.append('l');break;//L c�dille
		case '\u013d':b.append('l');break;//L hatchek
		case '\u0141':b.append('l');break;//L barre
		
		case '\u1e3e':b.append('m');break;//M accent aigu
		case '\u1e40':b.append('m');break;//M point suscrit
		case '\u1e42':b.append('m');break;//M point souscrit
	
		case '\u1e44':b.append('n');break;//N point suscrit
		case '\u1e46':b.append('n');break;//N point souscrit
		case '\u01f8':b.append('n');break;//N accent grave
		case '\u00d1':b.append('n');break;//N tilde
		case '\u0143':b.append('n');break;//N accent aigu
		case '\u0145':b.append('n');break;//N c�dille
		case '\u0147':b.append('n');break;//N hatchek
		
		case '\u00d2':b.append('o');break;//� accent grave
		case '\u00d3':b.append('o');break;//� accent aigu
		case '\u00d4':b.append('o');break;//� accent circonflexe
		case '\u00d5':b.append('o');break;//� tilde
		case '\u00d6':b.append('o');break;//� tr�ma
		case '\u00d8':b.append('o');break;//� barre
		case '\u014c':b.append('o');break;//O macron
		case '\u014e':b.append('o');break;//O br�ve
		case '\u0150':b.append('o');break;//O double accent aigu
		case '\u01a0':b.append('o');break;//O corne
		case '\u01d1':b.append('o');break;//O hatchek
		case '\u01ea':b.append('o');break;//O ogonek
		case '\u022e':b.append('o');break;//O point suscrit
		case '\u1ecc':b.append('o');break;//O point souscrit
		
		case '\u0152':b.append("oe");break;//�
		
		case '\u1e54':b.append('p');break;//P accent aigu
		case '\u1e56':b.append('p');break;//P point suscrit
		case '\u01a4':b.append('p');break;//P crochet
		
		case '\u1e58':b.append('r');break;//R point suscrit
		case '\u1e5a':b.append('r');break;//R point souscrit
		case '\u0154':b.append('r');break;//R accent aigu
		case '\u0156':b.append('r');break;//R c�dille
		case '\u0158':b.append('r');break;//R hatchek
		
		case '\u1e60':b.append('s');break;//S point suscrit
		case '\u1e62':b.append('s');break;//S point souscrit
		case '\u015a':b.append('s');break;//S accent aigu
		case '\u015c':b.append('s');break;//S accent circonflexe
		case '\u015e':b.append('s');break;//S c�dille
		case '\u0160':b.append('s');break;//S hatchek
		case '\u0218':b.append('s');break;//S virgule
		
		case '\u1e6a':b.append('t');break;//T point suscrit
		case '\u1e6c':b.append('t');break;//T point souscrit
		case '\u0162':b.append('t');break;//T c�dille
		case '\u0164':b.append('t');break;//T hatchek
		case '\u0166':b.append('t');break;//T barre
		case '\u01ac':b.append('t');break;//T crochet
		case '\u021a':b.append('t');break;//T virgule
		
		case '\u1ee4':b.append('u');break;//U point souscrit
		case '\u00d9':b.append('u');break;//� accent grave
		case '\u00da':b.append('u');break;//� accent aigu
		case '\u00db':b.append('u');break;//� accent circonflexe
		case '\u00dc':b.append('u');break;//� tr�ma
		case '\u0168':b.append('u');break;//U tilde
		case '\u016a':b.append('u');break;//U macron
		case '\u016c':b.append('u');break;//U br�ve
		case '\u016e':b.append('u');break;//U rond en chef
		case '\u0170':b.append('u');break;//U double accent aigu
		case '\u0172':b.append('u');break;//U ogonek
		case '\u01af':b.append('u');break;//U corne
		case '\u01d3':b.append('u');break;//U hatchek
		
		case '\u1e7c':b.append('v');break;//V tilde
		case '\u1e7e':b.append('v');break;//V point souscrit
		
		case '\u0174':b.append('w');break;//W accent circonflexe
		case '\u1e80':b.append('w');break;//W accent grave
		case '\u1e82':b.append('w');break;//W accent aigu
		case '\u1e84':b.append('w');break;//W tr�ma
		case '\u1e86':b.append('w');break;//W point suscrit
		case '\u1e88':b.append('w');break;//W point souscrit
		
		case '\u1e8a':b.append('x');break;//X point suscrit
		case '\u1e8c':b.append('x');break;//X tr�ma
		
		case '\u0176':b.append('y');break;//Y accent circonflexe
		case '\u0178':b.append('y');break;//� tr�ma
		case '\u00dd':b.append('y');break;//� accent aigu
		case '\u1e8e':b.append('y');break;//Y point suscrit
		case '\u01b3':b.append('y');break;//Y crochet
		case '\u0232':b.append('y');break;//Y macron
		case '\u1ef4':b.append('y');break;//Y point souscrit
		case '\u1ef8':b.append('y');break;//Y tilde
		case '\u1ef2':b.append('y');break;//Y accent grave
		
		case '\u0179':b.append('z');break;//Z accent aigu
		case '\u017b':b.append('z');break;//Z point suscrit
		case '\u017d':b.append('z');break;//Z hatchek
		case '\u01b5':b.append('z');break;//Z barre
		case '\u1e90':b.append('z');break;//Z accent circonflexe
		case '\u1e92':b.append('z');break;//Z point souscrit
		
		default: b.append(delim);
		}
	}
}
