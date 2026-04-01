package a.entity.gus06.string.transform.japanese.hiragana.builder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl extends CodePointsHiragana implements Entity, T, G {

	public String creationDate() {return "20150305";}

	private Map m;
	
	public Object g() throws Exception
	{
		if(m==null) initMap();
		return m;
	}
	
	private void initMap()
	{
		m = new HashMap();
		
		m.put("a",toString(A));
		m.put("i",toString(I));
		m.put("u",toString(U));
		m.put("e",toString(E));
		m.put("o",toString(O));
		
		m.put("ka",toString(KA));
		m.put("ki",toString(KI));
		m.put("ku",toString(KU));
		m.put("ke",toString(KE));
		m.put("ko",toString(KO));
		
		m.put("ga",toString(GA));
		m.put("gi",toString(GI));
		m.put("gu",toString(GU));
		m.put("ge",toString(GE));
		m.put("go",toString(GO));
		
		m.put("sa",toString(SA));
		m.put("shi",toString(SHI));
		m.put("su",toString(SU));
		m.put("se",toString(SE));
		m.put("so",toString(SO));
		
		m.put("za",toString(ZA));
		m.put("ji",toString(JI));
		m.put("zu",toString(ZU));
		m.put("ze",toString(ZE));
		m.put("zo",toString(ZO));
		
		m.put("ta",toString(TA));
		m.put("chi",toString(CHI));
		m.put("tsu",toString(TSU));
		m.put("te",toString(TE));
		m.put("to",toString(TO));
		
		m.put("da",toString(DA));
		m.put("dji",toString(DJI));
		m.put("dzu",toString(DZU));
		m.put("de",toString(DE));
		m.put("do",toString(DO));
		
		m.put("ma",toString(MA));
		m.put("mi",toString(MI));
		m.put("mu",toString(MU));
		m.put("me",toString(ME));
		m.put("mo",toString(MO));
		
		m.put("na",toString(NA));
		m.put("ni",toString(NI));
		m.put("nu",toString(NU));
		m.put("ne",toString(NE));
		m.put("no",toString(NO));
		
		m.put("ra",toString(RA));
		m.put("ri",toString(RI));
		m.put("ru",toString(RU));
		m.put("re",toString(RE));
		m.put("ro",toString(RO));
		
		m.put("wa",toString(WA));
		m.put("wi",toString(WI));
		m.put("we",toString(WE));
		m.put("wo",toString(WO));
		
		m.put("ha",toString(HA));
		m.put("hi",toString(HI));
		m.put("fu",toString(FU));
		m.put("he",toString(HE));
		m.put("ho",toString(HO));
		
		m.put("ba",toString(BA));
		m.put("bi",toString(BI));
		m.put("bu",toString(BU));
		m.put("be",toString(BE));
		m.put("bo",toString(BO));
		
		m.put("pa",toString(PA));
		m.put("pi",toString(PI));
		m.put("pu",toString(PU));
		m.put("pe",toString(PE));
		m.put("po",toString(PO));
		
		m.put("ya",toString(YA));
		m.put("yu",toString(YU));
		m.put("yo",toString(YO));
		
		m.put("n",toString(N));
	}
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		if(text==null) return null;
		if(text.equals("")) return "";
		
		Holder h = new Holder(text);
		while(h.length()>0) handleChar(h, h.next());
		return h.toString();
	}
	
	
	
	private void handleChar(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(A);break;
		case 'i':h.put(I);break;
		case 'u':h.put(U);break;
		case 'e':h.put(E);break;
		case 'o':h.put(O);break;
		case '.':h.put(POINT);break;

		case 'y':y(h, h.next());break;
		case 'k':k(h, h.next());break;
		case 'g':g(h, h.next());break;
		case 's':s(h, h.next());break;
		case 'z':z(h, h.next());break;
		case 'j':j(h, h.next());break;
		case 't':t(h, h.next());break;
		case 'd':d(h, h.next());break;
		case 'm':m(h, h.next());break;
		case 'n':n(h, h.next());break;
		case 'r':r(h, h.next());break;
		case 'h':h(h, h.next());break;
		case 'f':f(h, h.next());break;
		case 'b':b(h, h.next());break;
		case 'p':p(h, h.next());break;
		case 'c':c(h, h.next());break;
		case 'w':w(h, h.next());break;
		
		default:h.put(c);
		}
	}

	/*
	 * y
	 */
	private void y(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(YA);break;
		case 'u':h.put(YU);break;
		case 'o':h.put(YO);break;
		
		case '@':h.put('y');break;
		default:h.put('y');h.put(c);
		}
	}
	
	/*
	 * k
	 */
	private void k(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(KA);break;
		case 'i':h.put(KI);break;
		case 'u':h.put(KU);break;
		case 'e':h.put(KE);break;
		case 'o':h.put(KO);break;
		
		case 'y':ky(h, h.next());break;
		case 'k':kk(h, h.next());break;
		
		case '@':h.put('k');break;
		default:h.put('k');h.put(c);
		}
	}
	
	/*
	 * ky
	 */
	private void ky(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(KI);h.put(YA_SMALL);break;
		case 'u':h.put(KI);h.put(YU_SMALL);break;
		case 'o':h.put(KI);h.put(YO_SMALL);break;

		case '@':h.put("ky");break;
		default:h.put("ky"+c);
		}
	}
	
	/*
	 * kk
	 */
	private void kk(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(KA);break;
		case 'i':h.put(TSU_SMALL);h.put(KI);break;
		case 'u':h.put(TSU_SMALL);h.put(KU);break;
		case 'e':h.put(TSU_SMALL);h.put(KE);break;
		case 'o':h.put(TSU_SMALL);h.put(KO);break;
		case 'y':kky(h, h.next());break;
		
		case '@':h.put("kk");break;
		default:h.put("kk"+c);
		}
	}
	
	/*
	 * kky
	 */
	private void kky(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(KI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(KI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(KI);h.put(YO_SMALL);break;
		
		case '@':h.put("kky");break;
		default:h.put("kky"+c);
		}
	}
	
	/*
	 * g
	 */
	private void g(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(GA);break;
		case 'i':h.put(GI);break;
		case 'u':h.put(GU);break;
		case 'e':h.put(GE);break;
		case 'o':h.put(GO);break;
		
		case 'y':gy(h, h.next());break;
		case 'g':gg(h, h.next());break;
		
		case '@':h.put('g');break;
		default:h.put('g');h.put(c);
		}
	}
	
	/*
	 * gy
	 */
	private void gy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(GI);h.put(YA_SMALL);break;
		case 'u':h.put(GI);h.put(YU_SMALL);break;
		case 'o':h.put(GI);h.put(YO_SMALL);break;

		case '@':h.put("gy");break;
		default:h.put("gy"+c);
		}
	}
	
	/*
	 * gg
	 */
	private void gg(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(GA);break;
		case 'i':h.put(TSU_SMALL);h.put(GI);break;
		case 'u':h.put(TSU_SMALL);h.put(GU);break;
		case 'e':h.put(TSU_SMALL);h.put(GE);break;
		case 'o':h.put(TSU_SMALL);h.put(GO);break;
		
		case 'y':ggy(h, h.next());break;
		
		case '@':h.put("gg");break;
		default:h.put("gg"+c);
		}
	}

	/*
	 * ggy
	 */
	private void ggy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(GI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(GI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(GI);h.put(YO_SMALL);break;
		
		case '@':h.put("ggy");break;
		default:h.put("ggy"+c);
		}
	}
	
	/*
	 * s
	 */
	private void s(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(SA);break;
		case 'u':h.put(SU);break;
		case 'e':h.put(SE);break;
		case 'o':h.put(SO);break;
		
		case 'y':sy(h, h.next());break;
		case 'h':sh(h, h.next());break;
		case 's':ss(h, h.next());break;
		
		case '@':h.put('s');break;
		default:h.put('s');h.put(c);
		}
	}
	
	/*
	 * sy
	 */
	private void sy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(SHI);h.put(YA_SMALL);break;
		case 'u':h.put(SHI);h.put(YU_SMALL);break;
		case 'o':h.put(SHI);h.put(YO_SMALL);break;
		
		case '@':h.put("sy");break;
		default:h.put("sy"+c);
		}
	}
	
	/*
	 * sh
	 */
	private void sh(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(SHI);break;
		case 'a':h.put(SHI);h.put(YA_SMALL);break;
		case 'u':h.put(SHI);h.put(YU_SMALL);break;
		case 'o':h.put(SHI);h.put(YO_SMALL);break;
		
		case 'y':shy(h, h.next());break;
		
		case '@':h.put("sh");break;
		default:h.put("sh"+c);
		}
	}
	
	/*
	 * shy
	 */
	private void shy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(SHI);h.put(YA_SMALL);break;
		case 'u':h.put(SHI);h.put(YU_SMALL);break;
		case 'o':h.put(SHI);h.put(YO_SMALL);break;
		
		case '@':h.put("shy");break;
		default:h.put("shy"+c);
		}
	}
	
	/*
	 * ss
	 */
	private void ss(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(TSU_SMALL);h.put(SHI);break;
		case 'a':h.put(TSU_SMALL);h.put(SA);break;
		case 'u':h.put(TSU_SMALL);h.put(SU);break;
		case 'e':h.put(TSU_SMALL);h.put(SE);break;
		case 'o':h.put(TSU_SMALL);h.put(SO);break;
		
		case 'y':ssy(h, h.next());break;
		case 'h':ssh(h, h.next());break;
		
		case '@':h.put("ss");break;
		default:h.put("ss"+c);
		}
	}

	/*
	 * ssy
	 */
	private void ssy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(SHI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(SHI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(SHI);h.put(YO_SMALL);break;
		
		case '@':h.put("ssy");break;
		default:h.put("ssy"+c);
		}
	}
	
	/*
	 * ssh
	 */
	private void ssh(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(TSU_SMALL);h.put(SHI);break;
		case 'a':h.put(TSU_SMALL);h.put(SHI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(SHI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(SHI);h.put(YO_SMALL);break;
		case 'y':sshy(h, h.next());break;
		
		case '@':h.put("ssh");break;
		default:h.put("ssh"+c);
		}
	}
	
	/*
	 * sshy
	 */
	private void sshy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(SHI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(SHI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(SHI);h.put(YO_SMALL);break;
		
		case '@':h.put("sshy");break;
		default:h.put("sshy"+c);
		}
	}
	
	/*
	 * z
	 */
	private void z(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(ZA);break;
		case 'u':h.put(ZU);break;
		case 'e':h.put(ZE);break;
		case 'o':h.put(ZO);break;
		
		case 'y':zy(h, h.next());break;
		case 'z':zz(h, h.next());break;
		
		case '@':h.put('z');break;
		default:h.put('z');h.put(c);
		}
	}
	
	/*
	 * zy
	 */
	private void zy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(JI);h.put(YA_SMALL);break;
		case 'u':h.put(JI);h.put(YU_SMALL);break;
		case 'o':h.put(JI);h.put(YO_SMALL);break;
		
		case '@':h.put("zy");break;
		default:h.put("zy"+c);
		}
	}
	
	/*
	 * zz
	 */
	private void zz(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(TSU_SMALL);h.put(SHI);break;
		case 'a':h.put(TSU_SMALL);h.put(SA);break;
		case 'u':h.put(TSU_SMALL);h.put(SU);break;
		case 'e':h.put(TSU_SMALL);h.put(SE);break;
		case 'o':h.put(TSU_SMALL);h.put(SO);break;
		case 'y':zzy(h, h.next());break;
		
		case '@':h.put("zz");break;
		default:h.put("zz"+c);
		}
	}

	/*
	 * zzy
	 */
	private void zzy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(JI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(JI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(JI);h.put(YO_SMALL);break;
		
		case '@':h.put("zzy");break;
		default:h.put("zzy"+c);
		}
	}
	
	/*
	 * j
	 */
	private void j(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(JI);break;
		case 'a':h.put(JI);h.put(YA_SMALL);break;
		case 'u':h.put(JI);h.put(YU_SMALL);break;
		case 'o':h.put(JI);h.put(YO_SMALL);break;
		
		case 'y':jy(h, h.next());break;
		case 'j':jj(h, h.next());break;
		
		case '@':h.put('j');break;
		default:h.put('j');h.put(c);
		}
	}
	
	/*
	 * jy
	 */
	private void jy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(JI);h.put(YA_SMALL);break;
		case 'u':h.put(JI);h.put(YU_SMALL);break;
		case 'o':h.put(JI);h.put(YO_SMALL);break;
		
		case '@':h.put("jy");break;
		default:h.put("jy"+c);
		}
	}
	
	/*
	 * jj
	 */
	private void jj(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(TSU_SMALL);h.put(JI);break;
		case 'a':h.put(TSU_SMALL);h.put(JI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(JI);h.put(YU_SMALL);break;
		case 'e':h.put(TSU_SMALL);h.put(JI);h.put(E_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(JI);h.put(YO_SMALL);break;
		
		case 'y':jjy(h, h.next());break;
		
		case '@':h.put("jj");break;
		default:h.put("jj"+c);
		}
	}

	/*
	 * jjy
	 */
	private void jjy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(JI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(JI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(JI);h.put(YO_SMALL);break;
		
		case '@':h.put("jjy");break;
		default:h.put("jjy"+c);
		}
	}
	
	/*
	 * t
	 */
	private void t(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TA);break;
		case 'i':h.put(CHI);break;
		case 'u':h.put(TSU);break;
		case 'e':h.put(TE);break;
		case 'o':h.put(TO);break;
		
		case 's':ts(h, h.next());break;
		case 'y':ty(h, h.next());break;
		case 'h':th(h, h.next());break;
		case 't':tt(h, h.next());break;

		case '@':h.put('t');break;
		default:h.put('t');h.put(c);
		}
	}
	
	/*
	 * ts
	 */
	private void ts(Holder h, char c)
	{
		switch(c)
		{
			case 'u':h.put(TSU);break;
			
			case '@':h.put("ts");break;
			default:h.put("ts"+c);
		}
	}
	
	/*
	 * ty
	 */
	private void ty(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(CHI);h.put(YA_SMALL);break;
		case 'u':h.put(CHI);h.put(YU_SMALL);break;
		case 'o':h.put(CHI);h.put(YO_SMALL);break;
		
		case '@':h.put("ty");break;
		default:h.put("ty"+c);
		}
	}
	
	/*
	 * th
	 */
	private void th(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TE);h.put(A_SMALL);break;
		case 'i':h.put(TE);h.put(I_SMALL);break;
		case 'u':h.put(TE);h.put(U_SMALL);break;
		case 'e':h.put(TE);h.put(E_SMALL);break;
		case 'o':h.put(TE);h.put(O_SMALL);break;
		
		case '@':h.put("th");break;
		default:h.put("th"+c);
		}
	}
	
	/*
	 * tt
	 */
	private void tt(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(TA);break;
		case 'i':h.put(TSU_SMALL);h.put(CHI);break;
		case 'u':h.put(TSU_SMALL);h.put(TSU);break;
		case 'e':h.put(TSU_SMALL);h.put(TE);break;
		case 'o':h.put(TSU_SMALL);h.put(TO);break;
		
		case 'y':tty(h, h.next());break;
		case 'h':tth(h, h.next());break; 
		case 's':tts(h, h.next());break;
		
		case '@':h.put("tt");break;
		default:h.put("tt"+c);
		}
	}

	/*
	 * tty
	 */
	private void tty(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(CHI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(CHI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(CHI);h.put(YO_SMALL);break;
		
		case '@':h.put("tty");break;
		default:h.put("tty"+c);
		}
	}
	
	/*
	 * tth
	 */
	private void tth(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(TE);h.put(A_SMALL);break;
		case 'i':h.put(TSU_SMALL);h.put(TE);h.put(I_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(TE);h.put(U_SMALL);break;
		case 'e':h.put(TSU_SMALL);h.put(TE);h.put(E_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(TE);h.put(O_SMALL);break;
		
		case '@':h.put("tth");break;
		default:h.put("tth"+c);
		}
	}
	
	/*
	 * tts
	 */
	private void tts(Holder h, char c)
	{
		switch(c)
		{
			case 'u':h.put(TSU_SMALL);h.put(TSU);break;
			
			case '@':h.put("tts");break;
			default:h.put("tts"+c);
		}
	}
	
	/*
	 * d
	 */
	private void d(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(DA);break;
		case 'i':h.put(DJI);break;
		case 'u':h.put(DZU);break;
		case 'e':h.put(DE);break;
		case 'o':h.put(DO);break;
		
		case 'y':dy(h, h.next());break;
		case 'h':dh(h, h.next());break;
		case 'z':dz(h, h.next());break;
		case 'd':dd(h, h.next());break;
		case 'j':dj(h, h.next());break;

		case '@':h.put('d');break;
		default:h.put('d');h.put(c);
		}
	}
	
	/*
	 * dz
	 */
	private void dz(Holder h, char c)
	{
		switch(c)
		{
		case 'u':h.put(DZU);break;

		case '@':h.put("dz");break;
		default:h.put("dz"+c);
		}
	}

	/*
	 * dy
	 */
	private void dy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(DJI);h.put(YA_SMALL);break;
		case 'u':h.put(DJI);h.put(YU_SMALL);break;
		case 'o':h.put(DJI);h.put(YO_SMALL);break;
		
		case '@':h.put("dy");break;
		default:h.put("dy"+c);
		}
	}
	
	/*
	 * dh
	 */
	private void dh(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(DE);h.put(A_SMALL);break;
		case 'i':h.put(DE);h.put(I_SMALL);break;
		case 'u':h.put(DE);h.put(U_SMALL);break;
		case 'e':h.put(DE);h.put(E_SMALL);break;
		case 'o':h.put(DE);h.put(O_SMALL);break;
		
		case '@':h.put("dh");break;
		default:h.put("dh"+c);
		}
	}
	
	/*
	 * dd
	 */
	private void dd(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(DA);break;
		case 'i':h.put(TSU_SMALL);h.put(DJI);break;
		case 'u':h.put(TSU_SMALL);h.put(DZU);break;
		case 'e':h.put(TSU_SMALL);h.put(DE);break;
		case 'o':h.put(TSU_SMALL);h.put(DO);break;
		case 'y':ddy(h, h.next());break;
		case 'h':ddh(h, h.next());break;
		case 'z':ddz(h, h.next());break;
		
		case '@':h.put("dd");break;
		default:h.put("dd"+c);
		}
	}
	
	/*
	 * dj
	 */
	private void dj(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(DJI);h.put(YA_SMALL);break;
		case 'u':h.put(DJI);h.put(YU_SMALL);break;
		case 'o':h.put(DJI);h.put(YO_SMALL);break;
		case 'i':h.put(DJI);break;
		
		case '@':h.put("dj");break;
		default:h.put("dj"+c);
		}
	}

	/*
	 * ddh
	 */
	private void ddh(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(DE);h.put(A_SMALL);break;
		case 'i':h.put(TSU_SMALL);h.put(DE);h.put(I_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(DE);h.put(U_SMALL);break;
		case 'e':h.put(TSU_SMALL);h.put(DE);h.put(E_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(DE);h.put(O_SMALL);break;
		
		case '@':h.put("ddh");break;
		default:h.put("ddh"+c);
		}
	}
	
	/*
	 * ddy
	 */
	private void ddy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(DJI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(DJI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(DJI);h.put(YO_SMALL);break;
		
		case '@':h.put("ddy");break;
		default:h.put("ddy"+c);
		}
	}
	
	/*
	 * ddz
	 */
	private void ddz(Holder h, char c)
	{
		switch(c)
		{
		case 'u':h.put(TSU_SMALL);h.put(DZU);break;

		case '@':h.put("ddz");break;
		default:h.put("ddz"+c);
		}
	}
	
	/*
	 * m
	 */
	private void m(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(MA);break;
		case 'i':h.put(MI);break;
		case 'u':h.put(MU);break;
		case 'e':h.put(ME);break;
		case 'o':h.put(MO);break;
		case 'y':my(h, h.next());break;
		case 'm':mm(h, h.next());break;

		case '@':h.put('m');break;
		default:h.put('m');h.put(c);
		}
	}
	
	/*
	 * my
	 */
	private void my(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(MI);h.put(YA_SMALL);break;
		case 'u':h.put(MI);h.put(YU_SMALL);break;
		case 'o':h.put(MI);h.put(YO_SMALL);break;
		
		case '@':h.put("my");break;
		default:h.put("my"+c);
		}
	}
	
	/*
	 * mm
	 */
	private void mm(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(MA);break;
		case 'i':h.put(TSU_SMALL);h.put(MI);break;
		case 'u':h.put(TSU_SMALL);h.put(MU);break;
		case 'e':h.put(TSU_SMALL);h.put(ME);break;
		case 'o':h.put(TSU_SMALL);h.put(MO);break;
		case 'y':mmy(h, h.next());break;
		
		case '@':h.put("mm");break;
		default:h.put("mm"+c);
		}
	}
	
	/*
	 * mmy
	 */
	private void mmy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(MI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(MI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(MI);h.put(YO_SMALL);break;
		
		case '@':h.put("mmy");break;
		default:h.put("mmy"+c);
		}
	}
	
	/*
	 * n
	 */
	private void n(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(NA);break;
		case 'i':h.put(NI);break;
		case 'u':h.put(NU);break;
		case 'e':h.put(NE);break;
		case 'o':h.put(NO);break;
		case 'y':ny(h,h.next());break;
		
		case 'k':h.put(N);k(h, h.next());break;
		case 'g':h.put(N);g(h, h.next());break;
		case 's':h.put(N);s(h, h.next());break;
		case 'z':h.put(N);z(h, h.next());break;
		case 'j':h.put(N);j(h, h.next());break;
		case 't':h.put(N);t(h, h.next());break;
		case 'd':h.put(N);d(h, h.next());break;
		case 'm':h.put(N);m(h, h.next());break;
		case 'n':h.put(N);n(h, h.next());break;
		case 'r':h.put(N);r(h, h.next());break;
		case 'h':h.put(N);h(h, h.next());break;
		case 'b':h.put(N);b(h, h.next());break;
		case 'p':h.put(N);p(h, h.next());break;
		case 'c':h.put(N);c(h, h.next());break;
		case 'w':h.put(N);w(h, h.next());break;
		case '.':h.put(N);h.put(POINT);break;

		case '\'':h.put(N);break;
		case '@':h.put(N);break;
		default:h.put(N);h.put(c);
		}
	}
	
	/*
	 * ny
	 */
	private void ny(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(NI);h.put(YA_SMALL);break;
		case 'u':h.put(NI);h.put(YU_SMALL);break;
		case 'o':h.put(NI);h.put(YO_SMALL);break;
		
		case '@':h.put("ny");break;
		default:h.put("ny"+c);
		}
	}
	
	/*
	 * r
	 */
	private void r(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(RA);break;
		case 'i':h.put(RI);break;
		case 'u':h.put(RU);break;
		case 'e':h.put(RE);break;
		case 'o':h.put(RO);break;
		case 'y':ry(h, h.next());break;
		case 'r':rr(h, h.next());break;
		
		case '@':h.put('r');break;
		default:h.put('r');h.put(c);
		}
	}
	
	/*
	 * ry
	 */
	private void ry(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(RI);h.put(YA_SMALL);break;
		case 'u':h.put(RI);h.put(YU_SMALL);break;
		case 'o':h.put(RI);h.put(YO_SMALL);break;
		
		case '@':h.put("ry");break;
		default:h.put("ry"+c);
		}
	}
	
	/*
	 * rr
	 */
	private void rr(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(RA);break;
		case 'i':h.put(TSU_SMALL);h.put(RI);break;
		case 'u':h.put(TSU_SMALL);h.put(RU);break;
		case 'e':h.put(TSU_SMALL);h.put(RE);break;
		case 'o':h.put(TSU_SMALL);h.put(RO);break;
		case 'y':rry(h, h.next());break;
		
		case '@':h.put("rr");break;
		default:h.put("rr"+c);
		}
	}

	/*
	 * rry
	 */
	private void rry(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(RI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(RI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(RI);h.put(YO_SMALL);break;
		
		case '@':h.put("rry");break;
		default:h.put("rry"+c);
		}
	}
	
	/*
	 * h
	 */
	private void h(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(HA);break;
		case 'i':h.put(HI);break;
		case 'u':h.put(FU);break;
		case 'e':h.put(HE);break;
		case 'o':h.put(HO);break;
		case 'y':hy(h, h.next());break;
		case 'h':hh(h, h.next());break;

		case '@':h.put('h');break;
		default:h.put('h');h.put(c);
		}
	}
	
	/*
	 * hy
	 */
	private void hy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(HI);h.put(YA_SMALL);break;
		case 'u':h.put(HI);h.put(YU_SMALL);break;
		case 'o':h.put(HI);h.put(YO_SMALL);break;
		
		case '@':h.put("hy");break;
		default:h.put("hy"+c);
		}
	}
	
	/*
	 * hh
	 */
	private void hh(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(TSU_SMALL);h.put(HI);break;
		case 'a':h.put(TSU_SMALL);h.put(HA);break;
		case 'u':h.put(TSU_SMALL);h.put(FU);break;
		case 'e':h.put(TSU_SMALL);h.put(HE);break;
		case 'o':h.put(TSU_SMALL);h.put(HO);break;
		case 'y':hhy(h, h.next());break;
		
		case '@':h.put("hh");break;
		default:h.put("hh"+c);
		}
	}
	
	/*
	 * hhy
	 */
	private void hhy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(HI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(HI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(HI);h.put(YO_SMALL);break;
		
		case '@':h.put("hhy");break;
		default:h.put("hhy"+c);
		}
	}
	
	/*
	 * f
	 */
	private void f(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(FU);h.put(A_SMALL);break;
		case 'i':h.put(FU);h.put(I_SMALL);break;
		case 'u':h.put(FU);break;
		case 'e':h.put(FU);h.put(E_SMALL);break;
		case 'o':h.put(FU);h.put(O_SMALL);break;
		case 'f':ff(h, h.next());break;

		case '@':h.put('f');break;
		default:h.put('f');h.put(c);
		}
	}
	
	/*
	 * ff
	 */
	private void ff(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(FU);h.put(A_SMALL);break;
		case 'i':h.put(TSU_SMALL);h.put(FU);h.put(I_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(FU);break;
		case 'e':h.put(TSU_SMALL);h.put(FU);h.put(E_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(FU);h.put(O_SMALL);break;
		
		case '@':h.put("ff");break;
		default:h.put("ff"+c);
		}
	}
	
	/*
	 * b
	 */
	private void b(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(BA);break;
		case 'i':h.put(BI);break;
		case 'u':h.put(BU);break;
		case 'e':h.put(BE);break;
		case 'o':h.put(BO);break;
		case 'y':by(h, h.next());break;
		case 'b':bb(h, h.next());break;
		
		case '@':h.put('b');break;
		default:h.put('b');h.put(c);
		}
	}
	
	/*
	 * by
	 */
	private void by(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(BI);h.put(YA_SMALL);break;
		case 'u':h.put(BI);h.put(YU_SMALL);break;
		case 'o':h.put(BI);h.put(YO_SMALL);break;
		
		case '@':h.put("by");break;
		default:h.put("by"+c);
		}
	}
	
	/*
	 * bb
	 */
	private void bb(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(BA);break;
		case 'i':h.put(TSU_SMALL);h.put(BI);break;
		case 'u':h.put(TSU_SMALL);h.put(BU);break;
		case 'e':h.put(TSU_SMALL);h.put(BE);break;
		case 'o':h.put(TSU_SMALL);h.put(BO);break;
		case 'y':bby(h, h.next());break;
		
		case '@':h.put("bb");break;
		default:h.put("bb"+c);
		}
	}
	
	/*
	 * bby
	 */
	private void bby(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(BI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(BI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(BI);h.put(YO_SMALL);break;
		
		case '@':h.put("bby");break;
		default:h.put("bby"+c);
		}
	}
	
	/*
	 * p
	 */
	private void p(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(PA);break;
		case 'i':h.put(PI);break;
		case 'u':h.put(PU);break;
		case 'e':h.put(PE);break;
		case 'o':h.put(PO);break;
		case 'y':py(h, h.next());break;
		case 'p':pp(h, h.next());break;

		case '@':h.put('p');break;
		default:h.put('p');h.put(c);
		}
	}
	
	/*
	 * py
	 */
	private void py(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(PI);h.put(YA_SMALL);break;
		case 'u':h.put(PI);h.put(YU_SMALL);break;
		case 'o':h.put(PI);h.put(YO_SMALL);break;

		case '@':h.put("py");break;
		default:h.put("py"+c);
		}
	}
	
	/*
	 * pp
	 */
	private void pp(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(PA);break;
		case 'i':h.put(TSU_SMALL);h.put(PI);break;
		case 'u':h.put(TSU_SMALL);h.put(PU);break;
		case 'e':h.put(TSU_SMALL);h.put(PE);break;
		case 'o':h.put(TSU_SMALL);h.put(PO);break;
		case 'y':ppy(h, h.next());break;
		
		case '@':h.put("pp");break;
		default:h.put("pp"+c);
		}
	}
	
	/*
	 * ppy
	 */
	private void ppy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(PI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(PI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(PI);h.put(YO_SMALL);break;
		
		case '@':h.put("ppy");break;
		default:h.put("ppy"+c);
		}
	}
	
	/*
	 * w
	 */
	private void w(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(WA);break;
		case 'o':h.put(WO);break;
		case 'e':h.put(WE);break;
		case 'i':h.put(WI);break;
		
		case '@':h.put('w');break;
		default:h.put('w');h.put(c);
		}
	}
	
	/*
	 * c
	 */
	private void c(Holder h, char c)
	{
		switch(c)
		{
		case 'h':ch(h, h.next());break;
		case 'c':cc(h, h.next());break;
		
		case '@':h.put('c');break;
		default:h.put('c');h.put(c);
		}
	}
	
	/*
	 * ch
	 */
	private void ch(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(CHI);break;
		case 'a':h.put(CHI);h.put(YA_SMALL);break;
		case 'u':h.put(CHI);h.put(YU_SMALL);break;
		case 'e':h.put(CHI);h.put(E_SMALL);break;
		case 'o':h.put(CHI);h.put(YO_SMALL);break;
		case 'y':chy(h, h.next());break;

		case '@':h.put("ch");break;
		default:h.put("ch"+c);
		}
	}
	
	/*
	 * chy
	 */
	private void chy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(CHI);h.put(YA_SMALL);break;
		case 'u':h.put(CHI);h.put(YU_SMALL);break;
		case 'o':h.put(CHI);h.put(YO_SMALL);break;
		
		case '@':h.put("chy");break;
		default:h.put("chy"+c);
		}
	}
	
	/*
	 * c
	 */
	private void cc(Holder h, char c)
	{
		switch(c)
		{
		case 'h':cch(h, h.next());break;
		
		case '@':h.put("cc");break;
		default:h.put("cc"+c);
		}
	}
	
	/*
	 * cch
	 */
	private void cch(Holder h, char c)
	{
		switch(c)
		{
		case 'i':h.put(TSU_SMALL);h.put(CHI);break;
		case 'a':h.put(TSU_SMALL);h.put(CHI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(CHI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(CHI);h.put(YO_SMALL);break;
		case 'y':cchy(h, h.next());break;

		case '@':h.put("cch");break;
		default:h.put("cch"+c);
		}
	}
	
	/*
	 * cchy
	 */
	private void cchy(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(TSU_SMALL);h.put(CHI);h.put(YA_SMALL);break;
		case 'u':h.put(TSU_SMALL);h.put(CHI);h.put(YU_SMALL);break;
		case 'o':h.put(TSU_SMALL);h.put(CHI);h.put(YO_SMALL);break;
		
		case '@':h.put("cchy");break;
		default:h.put("cchy"+c);
		}
	}
	
	
	private char[] toChars(int codePoint)
	{return Character.toChars(codePoint);}
	
	private String toString(int codePoint)
	{return new String(toChars(codePoint));}
	
	
	private class Holder
	{
		private StringBuffer input;
		private StringBuffer output;
		
		public Holder(String text)
		{
			input = new StringBuffer(text);
			output = new StringBuffer();
		}
	
		private char next()
		{
			if(input.length()==0) return '@';
			
			char c = input.charAt(0);
			input.deleteCharAt(0);
			return c;
		}
		
		private void put(int codePoint)
		{output.append(toChars(codePoint));}
		
		private void put(char c)
		{output.append(c);}
		
		private void put(String s)
		{output.append(s);}
		
		private int length()
		{return input.length();}
		
		public String toString()
		{return output.toString();}
	}
}