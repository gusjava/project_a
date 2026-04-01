package a.entity.gus06.string.transform.japanese.kana.builder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20250726";}

	private Map m;
	
	public Object g() throws Exception
	{
		if(m==null) initMap();
		return m;
	}
	
	private void initMap()
	{
		m = new HashMap();
		
		m.put("a",toString(Hiragana.A));
		m.put("i",toString(Hiragana.I));
		m.put("u",toString(Hiragana.U));
		m.put("e",toString(Hiragana.E));
		m.put("o",toString(Hiragana.O));
		
		m.put("ka",toString(Hiragana.KA));
		m.put("ki",toString(Hiragana.KI));
		m.put("ku",toString(Hiragana.KU));
		m.put("ke",toString(Hiragana.KE));
		m.put("ko",toString(Hiragana.KO));
		
		m.put("ga",toString(Hiragana.GA));
		m.put("gi",toString(Hiragana.GI));
		m.put("gu",toString(Hiragana.GU));
		m.put("ge",toString(Hiragana.GE));
		m.put("go",toString(Hiragana.GO));
		
		m.put("sa",toString(Hiragana.SA));
		m.put("shi",toString(Hiragana.SHI));
		m.put("su",toString(Hiragana.SU));
		m.put("se",toString(Hiragana.SE));
		m.put("so",toString(Hiragana.SO));
		
		m.put("za",toString(Hiragana.ZA));
		m.put("ji",toString(Hiragana.JI));
		m.put("zu",toString(Hiragana.ZU));
		m.put("ze",toString(Hiragana.ZE));
		m.put("zo",toString(Hiragana.ZO));
		
		m.put("ta",toString(Hiragana.TA));
		m.put("chi",toString(Hiragana.CHI));
		m.put("tsu",toString(Hiragana.TSU));
		m.put("te",toString(Hiragana.TE));
		m.put("to",toString(Hiragana.TO));
		
		m.put("da",toString(Hiragana.DA));
		m.put("dji",toString(Hiragana.DJI));
		m.put("dzu",toString(Hiragana.DZU));
		m.put("de",toString(Hiragana.DE));
		m.put("do",toString(Hiragana.DO));
		
		m.put("ma",toString(Hiragana.MA));
		m.put("mi",toString(Hiragana.MI));
		m.put("mu",toString(Hiragana.MU));
		m.put("me",toString(Hiragana.ME));
		m.put("mo",toString(Hiragana.MO));
		
		m.put("na",toString(Hiragana.NA));
		m.put("ni",toString(Hiragana.NI));
		m.put("nu",toString(Hiragana.NU));
		m.put("ne",toString(Hiragana.NE));
		m.put("no",toString(Hiragana.NO));
		
		m.put("ra",toString(Hiragana.RA));
		m.put("ri",toString(Hiragana.RI));
		m.put("ru",toString(Hiragana.RU));
		m.put("re",toString(Hiragana.RE));
		m.put("ro",toString(Hiragana.RO));
		
		m.put("wa",toString(Hiragana.WA));
		m.put("wi",toString(Hiragana.WI));
		m.put("we",toString(Hiragana.WE));
		m.put("wo",toString(Hiragana.WO));
		
		m.put("ha",toString(Hiragana.HA));
		m.put("hi",toString(Hiragana.HI));
		m.put("fu",toString(Hiragana.FU));
		m.put("he",toString(Hiragana.HE));
		m.put("ho",toString(Hiragana.HO));
		
		m.put("ba",toString(Hiragana.BA));
		m.put("bi",toString(Hiragana.BI));
		m.put("bu",toString(Hiragana.BU));
		m.put("be",toString(Hiragana.BE));
		m.put("bo",toString(Hiragana.BO));
		
		m.put("pa",toString(Hiragana.PA));
		m.put("pi",toString(Hiragana.PI));
		m.put("pu",toString(Hiragana.PU));
		m.put("pe",toString(Hiragana.PE));
		m.put("po",toString(Hiragana.PO));
		
		m.put("ya",toString(Hiragana.YA));
		m.put("yu",toString(Hiragana.YU));
		m.put("yo",toString(Hiragana.YO));
		
		m.put("n",toString(Hiragana.N));
		
		m.put("A",toString(Katakana.A));
		m.put("I",toString(Katakana.I));
		m.put("U",toString(Katakana.U));
		m.put("E",toString(Katakana.E));
		m.put("O",toString(Katakana.O));
		
		m.put("KA",toString(Katakana.KA));
		m.put("KI",toString(Katakana.KI));
		m.put("KU",toString(Katakana.KU));
		m.put("KE",toString(Katakana.KE));
		m.put("KO",toString(Katakana.KO));
		
		m.put("GA",toString(Katakana.GA));
		m.put("GI",toString(Katakana.GI));
		m.put("GU",toString(Katakana.GU));
		m.put("GE",toString(Katakana.GE));
		m.put("GO",toString(Katakana.GO));
		
		m.put("SA",toString(Katakana.SA));
		m.put("SHI",toString(Katakana.SHI));
		m.put("SU",toString(Katakana.SU));
		m.put("SE",toString(Katakana.SE));
		m.put("SO",toString(Katakana.SO));
		
		m.put("ZA",toString(Katakana.ZA));
		m.put("JI",toString(Katakana.JI));
		m.put("ZU",toString(Katakana.ZU));
		m.put("ZE",toString(Katakana.ZE));
		m.put("ZO",toString(Katakana.ZO));
		
		m.put("TA",toString(Katakana.TA));
		m.put("CHI",toString(Katakana.CHI));
		m.put("TSU",toString(Katakana.TSU));
		m.put("TE",toString(Katakana.TE));
		m.put("TO",toString(Katakana.TO));
		
		m.put("DA",toString(Katakana.DA));
		m.put("DJI",toString(Katakana.DJI));
		m.put("DZU",toString(Katakana.DZU));
		m.put("DE",toString(Katakana.DE));
		m.put("DO",toString(Katakana.DO));
		
		m.put("MA",toString(Katakana.MA));
		m.put("MI",toString(Katakana.MI));
		m.put("MU",toString(Katakana.MU));
		m.put("ME",toString(Katakana.ME));
		m.put("MO",toString(Katakana.MO));
		
		m.put("NA",toString(Katakana.NA));
		m.put("NI",toString(Katakana.NI));
		m.put("NU",toString(Katakana.NU));
		m.put("NE",toString(Katakana.NE));
		m.put("NO",toString(Katakana.NO));
		
		m.put("RA",toString(Katakana.RA));
		m.put("RI",toString(Katakana.RI));
		m.put("RU",toString(Katakana.RU));
		m.put("RE",toString(Katakana.RE));
		m.put("RO",toString(Katakana.RO));
		
		m.put("WA",toString(Katakana.WA));
		m.put("WI",toString(Katakana.WI));
		m.put("WE",toString(Katakana.WE));
		m.put("WO",toString(Katakana.WO));
		
		m.put("HA",toString(Katakana.HA));
		m.put("HI",toString(Katakana.HI));
		m.put("FU",toString(Katakana.FU));
		m.put("HE",toString(Katakana.HE));
		m.put("HO",toString(Katakana.HO));
		
		m.put("BA",toString(Katakana.BA));
		m.put("BI",toString(Katakana.BI));
		m.put("BU",toString(Katakana.BU));
		m.put("BE",toString(Katakana.BE));
		m.put("BO",toString(Katakana.BO));
		
		m.put("PA",toString(Katakana.PA));
		m.put("PI",toString(Katakana.PI));
		m.put("PU",toString(Katakana.PU));
		m.put("PE",toString(Katakana.PE));
		m.put("PO",toString(Katakana.PO));
		
		m.put("YA",toString(Katakana.YA));
		m.put("YU",toString(Katakana.YU));
		m.put("YO",toString(Katakana.YO));
		
		m.put("N",toString(Katakana.N));
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
		case 'a':h.put(Hiragana.A);break;
		case 'i':h.put(Hiragana.I);break;
		case 'u':h.put(Hiragana.U);break;
		case 'e':h.put(Hiragana.E);break;
		case 'o':h.put(Hiragana.O);break;
		
		case 'A':h.put(Katakana.A);break;
		case 'I':h.put(Katakana.I);break;
		case 'U':h.put(Katakana.U);break;
		case 'E':h.put(Katakana.E);break;
		case 'O':h.put(Katakana.O);break;
		
		case '.':h.put(Hiragana.POINT);break;

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
		
		case 'Y':Y(h, h.next());break;
		case 'K':K(h, h.next());break;
		case 'G':G(h, h.next());break;
		case 'S':S(h, h.next());break;
		case 'Z':Z(h, h.next());break;
		case 'J':J(h, h.next());break;
		case 'T':T(h, h.next());break;
		case 'D':D(h, h.next());break;
		case 'M':M(h, h.next());break;
		case 'N':N(h, h.next());break;
		case 'R':R(h, h.next());break;
		case 'H':H(h, h.next());break;
		case 'F':F(h, h.next());break;
		case 'B':B(h, h.next());break;
		case 'P':P(h, h.next());break;
		case 'C':C(h, h.next());break;
		case 'W':W(h, h.next());break;
		
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
		case 'a':h.put(Hiragana.YA);break;
		case 'u':h.put(Hiragana.YU);break;
		case 'o':h.put(Hiragana.YO);break;
		
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
		case 'a':h.put(Hiragana.KA);break;
		case 'i':h.put(Hiragana.KI);break;
		case 'u':h.put(Hiragana.KU);break;
		case 'e':h.put(Hiragana.KE);break;
		case 'o':h.put(Hiragana.KO);break;
		
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
		case 'a':h.put(Hiragana.KI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.KI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.KI);h.put(Hiragana.YO_SMALL);break;

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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.KA);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.KI);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.KU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.KE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.KO);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.KI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.KI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.KI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.GA);break;
		case 'i':h.put(Hiragana.GI);break;
		case 'u':h.put(Hiragana.GU);break;
		case 'e':h.put(Hiragana.GE);break;
		case 'o':h.put(Hiragana.GO);break;
		
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
		case 'a':h.put(Hiragana.GI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.GI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.GI);h.put(Hiragana.YO_SMALL);break;

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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.GA);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.GI);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.GU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.GE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.GO);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.GI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.GI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.GI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.SA);break;
		case 'u':h.put(Hiragana.SU);break;
		case 'e':h.put(Hiragana.SE);break;
		case 'o':h.put(Hiragana.SO);break;
		
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
		case 'a':h.put(Hiragana.SHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.SHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.SHI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'i':h.put(Hiragana.SHI);break;
		case 'a':h.put(Hiragana.SHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.SHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.SHI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.SHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.SHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.SHI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);break;
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SA);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SO);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);break;
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YO_SMALL);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.ZA);break;
		case 'u':h.put(Hiragana.ZU);break;
		case 'e':h.put(Hiragana.ZE);break;
		case 'o':h.put(Hiragana.ZO);break;
		
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
		case 'a':h.put(Hiragana.JI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.JI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.JI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SHI);break;
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SA);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.SO);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'i':h.put(Hiragana.JI);break;
		case 'a':h.put(Hiragana.JI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.JI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.JI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.JI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.JI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.JI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);break;
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YU_SMALL);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.E_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.JI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TA);break;
		case 'i':h.put(Hiragana.CHI);break;
		case 'u':h.put(Hiragana.TSU);break;
		case 'e':h.put(Hiragana.TE);break;
		case 'o':h.put(Hiragana.TO);break;
		
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
			case 'u':h.put(Hiragana.TSU);break;
			
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
		case 'a':h.put(Hiragana.CHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.CHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.CHI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TE);h.put(Hiragana.A_SMALL);break;
		case 'i':h.put(Hiragana.TE);h.put(Hiragana.I_SMALL);break;
		case 'u':h.put(Hiragana.TE);h.put(Hiragana.U_SMALL);break;
		case 'e':h.put(Hiragana.TE);h.put(Hiragana.E_SMALL);break;
		case 'o':h.put(Hiragana.TE);h.put(Hiragana.O_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TA);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TSU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TO);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TE);h.put(Hiragana.A_SMALL);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TE);h.put(Hiragana.I_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TE);h.put(Hiragana.U_SMALL);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TE);h.put(Hiragana.E_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TE);h.put(Hiragana.O_SMALL);break;
		
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
			case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.TSU);break;
			
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
		case 'a':h.put(Hiragana.DA);break;
		case 'i':h.put(Hiragana.DJI);break;
		case 'u':h.put(Hiragana.DZU);break;
		case 'e':h.put(Hiragana.DE);break;
		case 'o':h.put(Hiragana.DO);break;
		
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
		case 'u':h.put(Hiragana.DZU);break;

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
		case 'a':h.put(Hiragana.DJI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.DJI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.DJI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.DE);h.put(Hiragana.A_SMALL);break;
		case 'i':h.put(Hiragana.DE);h.put(Hiragana.I_SMALL);break;
		case 'u':h.put(Hiragana.DE);h.put(Hiragana.U_SMALL);break;
		case 'e':h.put(Hiragana.DE);h.put(Hiragana.E_SMALL);break;
		case 'o':h.put(Hiragana.DE);h.put(Hiragana.O_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DA);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DJI);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DZU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DO);break;
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
		case 'a':h.put(Hiragana.DJI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.DJI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.DJI);h.put(Hiragana.YO_SMALL);break;
		case 'i':h.put(Hiragana.DJI);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DE);h.put(Hiragana.A_SMALL);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DE);h.put(Hiragana.I_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DE);h.put(Hiragana.U_SMALL);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DE);h.put(Hiragana.E_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DE);h.put(Hiragana.O_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DJI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DJI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DJI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.DZU);break;

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
		case 'a':h.put(Hiragana.MA);break;
		case 'i':h.put(Hiragana.MI);break;
		case 'u':h.put(Hiragana.MU);break;
		case 'e':h.put(Hiragana.ME);break;
		case 'o':h.put(Hiragana.MO);break;
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
		case 'a':h.put(Hiragana.MI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.MI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.MI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.MA);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.MI);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.MU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.ME);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.MO);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.MI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.MI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.MI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.NA);break;
		case 'i':h.put(Hiragana.NI);break;
		case 'u':h.put(Hiragana.NU);break;
		case 'e':h.put(Hiragana.NE);break;
		case 'o':h.put(Hiragana.NO);break;
		case 'y':ny(h,h.next());break;
		
		case 'k':h.put(Hiragana.N);k(h, h.next());break;
		case 'g':h.put(Hiragana.N);g(h, h.next());break;
		case 's':h.put(Hiragana.N);s(h, h.next());break;
		case 'z':h.put(Hiragana.N);z(h, h.next());break;
		case 'j':h.put(Hiragana.N);j(h, h.next());break;
		case 't':h.put(Hiragana.N);t(h, h.next());break;
		case 'd':h.put(Hiragana.N);d(h, h.next());break;
		case 'm':h.put(Hiragana.N);m(h, h.next());break;
		case 'n':h.put(Hiragana.N);n(h, h.next());break;
		case 'r':h.put(Hiragana.N);r(h, h.next());break;
		case 'h':h.put(Hiragana.N);h(h, h.next());break;
		case 'b':h.put(Hiragana.N);b(h, h.next());break;
		case 'p':h.put(Hiragana.N);p(h, h.next());break;
		case 'c':h.put(Hiragana.N);c(h, h.next());break;
		case 'w':h.put(Hiragana.N);w(h, h.next());break;
		case '.':h.put(Hiragana.N);h.put(Hiragana.POINT);break;

		case '\'':h.put(Hiragana.N);break;
		case '@':h.put(Hiragana.N);break;
		default:h.put(Hiragana.N);h.put(c);
		}
	}
	
	/*
	 * ny
	 */
	private void ny(Holder h, char c)
	{
		switch(c)
		{
		case 'a':h.put(Hiragana.NI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.NI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.NI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.RA);break;
		case 'i':h.put(Hiragana.RI);break;
		case 'u':h.put(Hiragana.RU);break;
		case 'e':h.put(Hiragana.RE);break;
		case 'o':h.put(Hiragana.RO);break;
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
		case 'a':h.put(Hiragana.RI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.RI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.RI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.RA);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.RI);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.RU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.RE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.RO);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.RI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.RI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.RI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.HA);break;
		case 'i':h.put(Hiragana.HI);break;
		case 'u':h.put(Hiragana.FU);break;
		case 'e':h.put(Hiragana.HE);break;
		case 'o':h.put(Hiragana.HO);break;
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
		case 'a':h.put(Hiragana.HI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.HI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.HI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.HI);break;
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.HA);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.FU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.HE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.HO);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.HI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.HI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.HI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.FU);h.put(Hiragana.A_SMALL);break;
		case 'i':h.put(Hiragana.FU);h.put(Hiragana.I_SMALL);break;
		case 'u':h.put(Hiragana.FU);break;
		case 'e':h.put(Hiragana.FU);h.put(Hiragana.E_SMALL);break;
		case 'o':h.put(Hiragana.FU);h.put(Hiragana.O_SMALL);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.FU);h.put(Hiragana.A_SMALL);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.FU);h.put(Hiragana.I_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.FU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.FU);h.put(Hiragana.E_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.FU);h.put(Hiragana.O_SMALL);break;
		
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
		case 'a':h.put(Hiragana.BA);break;
		case 'i':h.put(Hiragana.BI);break;
		case 'u':h.put(Hiragana.BU);break;
		case 'e':h.put(Hiragana.BE);break;
		case 'o':h.put(Hiragana.BO);break;
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
		case 'a':h.put(Hiragana.BI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.BI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.BI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.BA);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.BI);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.BU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.BE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.BO);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.BI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.BI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.BI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.PA);break;
		case 'i':h.put(Hiragana.PI);break;
		case 'u':h.put(Hiragana.PU);break;
		case 'e':h.put(Hiragana.PE);break;
		case 'o':h.put(Hiragana.PO);break;
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
		case 'a':h.put(Hiragana.PI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.PI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.PI);h.put(Hiragana.YO_SMALL);break;

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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.PA);break;
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.PI);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.PU);break;
		case 'e':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.PE);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.PO);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.PI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.PI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.PI);h.put(Hiragana.YO_SMALL);break;
		
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
		case 'a':h.put(Hiragana.WA);break;
		case 'o':h.put(Hiragana.WO);break;
		case 'e':h.put(Hiragana.WE);break;
		case 'i':h.put(Hiragana.WI);break;
		
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
		case 'i':h.put(Hiragana.CHI);break;
		case 'a':h.put(Hiragana.CHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.CHI);h.put(Hiragana.YU_SMALL);break;
		case 'e':h.put(Hiragana.CHI);h.put(Hiragana.E_SMALL);break;
		case 'o':h.put(Hiragana.CHI);h.put(Hiragana.YO_SMALL);break;
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
		case 'a':h.put(Hiragana.CHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.CHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.CHI);h.put(Hiragana.YO_SMALL);break;
		
		case '@':h.put("chy");break;
		default:h.put("chy"+c);
		}
	}
	
	/*
	 * cc
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
		case 'i':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);break;
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YO_SMALL);break;
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
		case 'a':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YA_SMALL);break;
		case 'u':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YU_SMALL);break;
		case 'o':h.put(Hiragana.TSU_SMALL);h.put(Hiragana.CHI);h.put(Hiragana.YO_SMALL);break;
		
		case '@':h.put("cchy");break;
		default:h.put("cchy"+c);
		}
	}
	
	/*
	 * Y
	 */
	private void Y(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.YA);break;
		case 'U':h.put(Katakana.YU);break;
		case 'O':h.put(Katakana.YO);break;
		
		case '@':h.put('Y');break;
		default:h.put('Y');h.put(c);
		}
	}
	
	/*
	 * K
	 */
	private void K(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.KA);break;
		case 'I':h.put(Katakana.KI);break;
		case 'U':h.put(Katakana.KU);break;
		case 'E':h.put(Katakana.KE);break;
		case 'O':h.put(Katakana.KO);break;
		
		case 'Y':KY(h, h.next());break;
		case 'K':KK(h, h.next());break;
		
		case '@':h.put('K');break;
		default:h.put('K');h.put(c);
		}
	}
	
	/*
	 * KY
	 */
	private void KY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.KI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.KI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.KI);h.put(Katakana.YO_SMALL);break;

		case '@':h.put("KY");break;
		default:h.put("KY"+c);
		}
	}
	
	/*
	 * KK
	 */
	private void KK(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.KA);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.KI);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.KU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.KE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.KO);break;
		case 'Y':KKY(h, h.next());break;
		
		case '@':h.put("KK");break;
		default:h.put("KK"+c);
		}
	}
	
	/*
	 * KKY
	 */
	private void KKY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.KI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.KI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.KI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("KKY");break;
		default:h.put("KKY"+c);
		}
	}
	
	/*
	 * G
	 */
	private void G(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.GA);break;
		case 'I':h.put(Katakana.GI);break;
		case 'U':h.put(Katakana.GU);break;
		case 'E':h.put(Katakana.GE);break;
		case 'O':h.put(Katakana.GO);break;
		
		case 'Y':GY(h, h.next());break;
		case 'G':GG(h, h.next());break;
		
		case '@':h.put('G');break;
		default:h.put('G');h.put(c);
		}
	}
	
	/*
	 * GY
	 */
	private void GY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.GI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.GI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.GI);h.put(Katakana.YO_SMALL);break;

		case '@':h.put("GY");break;
		default:h.put("GY"+c);
		}
	}
	
	/*
	 * GG
	 */
	private void GG(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.GA);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.GI);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.GU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.GE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.GO);break;
		
		case 'Y':GGY(h, h.next());break;
		
		case '@':h.put("GG");break;
		default:h.put("GG"+c);
		}
	}
	
	/*
	 * GGY
	 */
	private void GGY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.GI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.GI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.GI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("GGY");break;
		default:h.put("GGY"+c);
		}
	}
	
	/*
	 * S
	 */
	private void S(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.SA);break;
		case 'U':h.put(Katakana.SU);break;
		case 'E':h.put(Katakana.SE);break;
		case 'O':h.put(Katakana.SO);break;
		
		case 'Y':SY(h, h.next());break;
		case 'H':SH(h, h.next());break;
		case 'S':SS(h, h.next());break;
		
		case '@':h.put('S');break;
		default:h.put('S');h.put(c);
		}
	}
	
	/*
	 * SY
	 */
	private void SY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.SHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.SHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.SHI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("SY");break;
		default:h.put("SY"+c);
		}
	}
	
	/*
	 * SH
	 */
	private void SH(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.SHI);break;
		case 'A':h.put(Katakana.SHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.SHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.SHI);h.put(Katakana.YO_SMALL);break;
		
		case 'Y':SHY(h, h.next());break;
		
		case '@':h.put("SH");break;
		default:h.put("SH"+c);
		}
	}
	
	/*
	 * SHY
	 */
	private void SHY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.SHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.SHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.SHI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("SHY");break;
		default:h.put("SHY"+c);
		}
	}
	
	/*
	 * SS
	 */
	private void SS(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);break;
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.SA);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.SU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.SE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.SO);break;
		
		case 'Y':SSY(h, h.next());break;
		case 'H':SSH(h, h.next());break;
		
		case '@':h.put("SS");break;
		default:h.put("SS"+c);
		}
	}
	
	/*
	 * SSY
	 */
	private void SSY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("SSY");break;
		default:h.put("SSY"+c);
		}
	}
	
	/*
	 * SSH
	 */
	private void SSH(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);break;
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YO_SMALL);break;
		case 'Y':SSHY(h, h.next());break;
		
		case '@':h.put("SSH");break;
		default:h.put("SSH"+c);
		}
	}
	
	/*
	 * SSHY
	 */
	private void SSHY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("SSHY");break;
		default:h.put("SSHY"+c);
		}
	}
	
	/*
	 * Z
	 */
	private void Z(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.ZA);break;
		case 'U':h.put(Katakana.ZU);break;
		case 'E':h.put(Katakana.ZE);break;
		case 'O':h.put(Katakana.ZO);break;
		
		case 'Y':ZY(h, h.next());break;
		case 'Z':ZZ(h, h.next());break;
		
		case '@':h.put('Z');break;
		default:h.put('Z');h.put(c);
		}
	}
	
	/*
	 * ZY
	 */
	private void ZY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.JI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.JI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.JI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("ZY");break;
		default:h.put("ZY"+c);
		}
	}
	
	/*
	 * ZZ
	 */
	private void ZZ(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.SHI);break;
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.SA);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.SU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.SE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.SO);break;
		case 'Y':ZZY(h, h.next());break;
		
		case '@':h.put("ZZ");break;
		default:h.put("ZZ"+c);
		}
	}

	/*
	 * ZZY
	 */
	private void ZZY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("ZZY");break;
		default:h.put("ZZY"+c);
		}
	}
	
	/*
	 * J
	 */
	private void J(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.JI);break;
		case 'A':h.put(Katakana.JI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.JI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.JI);h.put(Katakana.YO_SMALL);break;
		
		case 'Y':JY(h, h.next());break;
		case 'J':JJ(h, h.next());break;
		
		case '@':h.put('J');break;
		default:h.put('J');h.put(c);
		}
	}
	
	/*
	 * JY
	 */
	private void JY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.JI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.JI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.JI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("JY");break;
		default:h.put("JY"+c);
		}
	}
	
	/*
	 * JJ
	 */
	private void JJ(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);break;
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YU_SMALL);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.E_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YO_SMALL);break;
		
		case 'Y':JJY(h, h.next());break;
		
		case '@':h.put("JJ");break;
		default:h.put("JJ"+c);
		}
	}

	
	/*
	 * JJY
	 */
	private void JJY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.JI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("JJY");break;
		default:h.put("JJY"+c);
		}
	}
	
	/*
	 * T
	 */
	private void T(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TA);break;
		case 'I':h.put(Katakana.CHI);break;
		case 'U':h.put(Katakana.TSU);break;
		case 'E':h.put(Katakana.TE);break;
		case 'O':h.put(Katakana.TO);break;
		
		case 'S':TS(h, h.next());break;
		case 'Y':TY(h, h.next());break;
		case 'H':TH(h, h.next());break;
		case 'T':TT(h, h.next());break;

		case '@':h.put('T');break;
		default:h.put('T');h.put(c);
		}
	}
	
	/*
	 * TS
	 */
	private void TS(Holder h, char c)
	{
		switch(c)
		{
			case 'U':h.put(Katakana.TSU);break;
			
			case '@':h.put("TS");break;
			default:h.put("TS"+c);
		}
	}
	
	/*
	 * TY
	 */
	private void TY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.CHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.CHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.CHI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("TY");break;
		default:h.put("TY"+c);
		}
	}
	
	/*
	 * TH
	 */
	private void TH(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TE);h.put(Katakana.A_SMALL);break;
		case 'I':h.put(Katakana.TE);h.put(Katakana.I_SMALL);break;
		case 'U':h.put(Katakana.TE);h.put(Katakana.U_SMALL);break;
		case 'E':h.put(Katakana.TE);h.put(Katakana.E_SMALL);break;
		case 'O':h.put(Katakana.TE);h.put(Katakana.O_SMALL);break;
		
		case '@':h.put("TH");break;
		default:h.put("TH"+c);
		}
	}
	
	/*
	 * TT
	 */
	private void TT(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.TA);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.TSU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.TE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.TO);break;
		
		case 'Y':TTY(h, h.next());break;
		case 'H':TTH(h, h.next());break; 
		case 'S':TTS(h, h.next());break;
		
		case '@':h.put("TT");break;
		default:h.put("TT"+c);
		}
	}
	
	/*
	 * TTY
	 */
	private void TTY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("TTY");break;
		default:h.put("TTY"+c);
		}
	}
	
	/*
	 * TTH
	 */
	private void TTH(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.TE);h.put(Katakana.A_SMALL);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.TE);h.put(Katakana.I_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.TE);h.put(Katakana.U_SMALL);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.TE);h.put(Katakana.E_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.TE);h.put(Katakana.O_SMALL);break;
		
		case '@':h.put("TTH");break;
		default:h.put("TTH"+c);
		}
	}
	
	/*
	 * TTS
	 */
	private void TTS(Holder h, char c)
	{
		switch(c)
		{
			case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.TSU);break;
			
			case '@':h.put("TTS");break;
			default:h.put("TTS"+c);
		}
	}
	
	/*
	 * D
	 */
	private void D(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.DA);break;
		case 'I':h.put(Katakana.DJI);break;
		case 'U':h.put(Katakana.DZU);break;
		case 'E':h.put(Katakana.DE);break;
		case 'O':h.put(Katakana.DO);break;
		
		case 'Y':DY(h, h.next());break;
		case 'H':DH(h, h.next());break;
		case 'Z':DZ(h, h.next());break;
		case 'D':DD(h, h.next());break;
		case 'J':DJ(h, h.next());break;

		case '@':h.put('D');break;
		default:h.put('D');h.put(c);
		}
	}
	
	/*
	 * DZ
	 */
	private void DZ(Holder h, char c)
	{
		switch(c)
		{
		case 'U':h.put(Katakana.DZU);break;

		case '@':h.put("DZ");break;
		default:h.put("DZ"+c);
		}
	}

	/*
	 * DY
	 */
	private void DY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.DJI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.DJI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.DJI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("DY");break;
		default:h.put("DY"+c);
		}
	}
	
	/*
	 * DH
	 */
	private void DH(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.DE);h.put(Katakana.A_SMALL);break;
		case 'I':h.put(Katakana.DE);h.put(Katakana.I_SMALL);break;
		case 'U':h.put(Katakana.DE);h.put(Katakana.U_SMALL);break;
		case 'E':h.put(Katakana.DE);h.put(Katakana.E_SMALL);break;
		case 'O':h.put(Katakana.DE);h.put(Katakana.O_SMALL);break;
		
		case '@':h.put("DH");break;
		default:h.put("DH"+c);
		}
	}
	
	/*
	 * DD
	 */
	private void DD(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.DA);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.DJI);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.DZU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.DE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.DO);break;
		case 'Y':DDY(h, h.next());break;
		case 'H':DDH(h, h.next());break;
		case 'Z':DDZ(h, h.next());break;
		
		case '@':h.put("DD");break;
		default:h.put("DD"+c);
		}
	}
	
	/*
	 * DJ
	 */
	private void DJ(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.DJI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.DJI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.DJI);h.put(Katakana.YO_SMALL);break;
		case 'I':h.put(Katakana.DJI);break;
		
		case '@':h.put("DJ");break;
		default:h.put("DJ"+c);
		}
	}

	/*
	 * DDH
	 */
	private void DDH(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.DE);h.put(Katakana.A_SMALL);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.DE);h.put(Katakana.I_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.DE);h.put(Katakana.U_SMALL);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.DE);h.put(Katakana.E_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.DE);h.put(Katakana.O_SMALL);break;
		
		case '@':h.put("DDH");break;
		default:h.put("DDH"+c);
		}
	}
	
	/*
	 * DDY
	 */
	private void DDY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.DJI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.DJI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.DJI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("DDY");break;
		default:h.put("DDY"+c);
		}
	}
	
	/*
	 * DDZ
	 */
	private void DDZ(Holder h, char c)
	{
		switch(c)
		{
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.DZU);break;

		case '@':h.put("DDZ");break;
		default:h.put("DDZ"+c);
		}
	}
	
	/*
	 * M
	 */
	private void M(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.MA);break;
		case 'I':h.put(Katakana.MI);break;
		case 'U':h.put(Katakana.MU);break;
		case 'E':h.put(Katakana.ME);break;
		case 'O':h.put(Katakana.MO);break;
		case 'Y':MY(h, h.next());break;
		case 'M':MM(h, h.next());break;

		case '@':h.put('M');break;
		default:h.put('M');h.put(c);
		}
	}
	
	/*
	 * MY
	 */
	private void MY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.MI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.MI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.MI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("MY");break;
		default:h.put("MY"+c);
		}
	}
	
	/*
	 * MM
	 */
	private void MM(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.MA);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.MI);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.MU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.ME);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.MO);break;
		case 'Y':MMY(h, h.next());break;
		
		case '@':h.put("MM");break;
		default:h.put("MM"+c);
		}
	}

	/*
	 * MMY
	 */
	private void MMY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.MI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.MI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.MI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("MMY");break;
		default:h.put("MMY"+c);
		}
	}
	
	/*
	 * N
	 */
	private void N(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.NA);break;
		case 'I':h.put(Katakana.NI);break;
		case 'U':h.put(Katakana.NU);break;
		case 'E':h.put(Katakana.NE);break;
		case 'O':h.put(Katakana.NO);break;
		case 'Y':NY(h,h.next());break;
		
		case 'K':h.put(Katakana.N);k(h, h.next());break;
		case 'G':h.put(Katakana.N);g(h, h.next());break;
		case 'S':h.put(Katakana.N);s(h, h.next());break;
		case 'Z':h.put(Katakana.N);z(h, h.next());break;
		case 'J':h.put(Katakana.N);j(h, h.next());break;
		case 'T':h.put(Katakana.N);t(h, h.next());break;
		case 'D':h.put(Katakana.N);d(h, h.next());break;
		case 'M':h.put(Katakana.N);m(h, h.next());break;
		case 'N':h.put(Katakana.N);n(h, h.next());break;
		case 'R':h.put(Katakana.N);r(h, h.next());break;
		case 'H':h.put(Katakana.N);h(h, h.next());break;
		case 'B':h.put(Katakana.N);b(h, h.next());break;
		case 'P':h.put(Katakana.N);p(h, h.next());break;
		case 'C':h.put(Katakana.N);c(h, h.next());break;
		case 'W':h.put(Katakana.N);w(h, h.next());break;
		case '.':h.put(Katakana.N);h.put(Katakana.POINT);break;

		case '\'':h.put(Katakana.N);break;
		case '@':h.put(Katakana.N);break;
		default:h.put(Katakana.N);h.put(c);
		}
	}
	
	/*
	 * NY
	 */
	private void NY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.NI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.NI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.NI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("NY");break;
		default:h.put("NY"+c);
		}
	}
	
	/*
	 * R
	 */
	private void R(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.RA);break;
		case 'I':h.put(Katakana.RI);break;
		case 'U':h.put(Katakana.RU);break;
		case 'E':h.put(Katakana.RE);break;
		case 'O':h.put(Katakana.RO);break;
		case 'Y':RY(h, h.next());break;
		case 'R':RR(h, h.next());break;
		
		case '@':h.put('R');break;
		default:h.put('R');h.put(c);
		}
	}
	
	/*
	 * RY
	 */
	private void RY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.RI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.RI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.RI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("RY");break;
		default:h.put("RY"+c);
		}
	}
	
	/*
	 * RR
	 */
	private void RR(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.RA);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.RI);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.RU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.RE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.RO);break;
		case 'Y':RRY(h, h.next());break;
		
		case '@':h.put("RR");break;
		default:h.put("RR"+c);
		}
	}
	
	/*
	 * RRY
	 */
	private void RRY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.RI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.RI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.RI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("RRY");break;
		default:h.put("RRY"+c);
		}
	}
	
	/*
	 * H
	 */
	private void H(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.HA);break;
		case 'I':h.put(Katakana.HI);break;
		case 'U':h.put(Katakana.FU);break;
		case 'E':h.put(Katakana.HE);break;
		case 'O':h.put(Katakana.HO);break;
		case 'Y':HY(h, h.next());break;
		case 'H':HH(h, h.next());break;

		case '@':h.put('H');break;
		default:h.put('H');h.put(c);
		}
	}
	
	/*
	 * HY
	 */
	private void HY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.HI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.HI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.HI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("HY");break;
		default:h.put("HY"+c);
		}
	}
	
	/*
	 * HH
	 */
	private void HH(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.HI);break;
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.HA);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.FU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.HE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.HO);break;
		case 'Y':HHY(h, h.next());break;
		
		case '@':h.put("HH");break;
		default:h.put("HH"+c);
		}
	}
	
	/*
	 * HHY
	 */
	private void HHY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.HI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.HI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.HI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("HHY");break;
		default:h.put("HHY"+c);
		}
	}
	
	/*
	 * F
	 */
	private void F(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.FU);h.put(Katakana.A_SMALL);break;
		case 'I':h.put(Katakana.FU);h.put(Katakana.I_SMALL);break;
		case 'U':h.put(Katakana.FU);break;
		case 'E':h.put(Katakana.FU);h.put(Katakana.E_SMALL);break;
		case 'O':h.put(Katakana.FU);h.put(Katakana.O_SMALL);break;
		case 'F':FF(h, h.next());break;

		case '@':h.put('F');break;
		default:h.put('F');h.put(c);
		}
	}
	
	/*
	 * FF
	 */
	private void FF(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.FU);h.put(Katakana.A_SMALL);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.FU);h.put(Katakana.I_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.FU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.FU);h.put(Katakana.E_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.FU);h.put(Katakana.O_SMALL);break;
		
		case '@':h.put("FF");break;
		default:h.put("FF"+c);
		}
	}
	
	/*
	 * B
	 */
	private void B(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.BA);break;
		case 'I':h.put(Katakana.BI);break;
		case 'U':h.put(Katakana.BU);break;
		case 'E':h.put(Katakana.BE);break;
		case 'O':h.put(Katakana.BO);break;
		case 'Y':BY(h, h.next());break;
		case 'B':BB(h, h.next());break;
		
		case '@':h.put('B');break;
		default:h.put('B');h.put(c);
		}
	}
	
	/*
	 * BY
	 */
	private void BY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.BI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.BI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.BI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("BY");break;
		default:h.put("BY"+c);
		}
	}
	
	/*
	 * BB
	 */
	private void BB(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.BA);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.BI);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.BU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.BE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.BO);break;
		case 'Y':BBY(h, h.next());break;
		
		case '@':h.put("BB");break;
		default:h.put("BB"+c);
		}
	}
	
	/*
	 * BBY
	 */
	private void BBY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.BI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.BI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.BI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("BBY");break;
		default:h.put("BBY"+c);
		}
	}
	
	/*
	 * P
	 */
	private void P(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.PA);break;
		case 'I':h.put(Katakana.PI);break;
		case 'U':h.put(Katakana.PU);break;
		case 'E':h.put(Katakana.PE);break;
		case 'O':h.put(Katakana.PO);break;
		case 'Y':PY(h, h.next());break;
		case 'P':PP(h, h.next());break;

		case '@':h.put('P');break;
		default:h.put('P');h.put(c);
		}
	}
	
	/*
	 * PY
	 */
	private void PY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.PI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.PI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.PI);h.put(Katakana.YO_SMALL);break;

		case '@':h.put("PY");break;
		default:h.put("PY"+c);
		}
	}
	
	/*
	 * PP
	 */
	private void PP(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.PA);break;
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.PI);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.PU);break;
		case 'E':h.put(Katakana.TSU_SMALL);h.put(Katakana.PE);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.PO);break;
		case 'Y':PPY(h, h.next());break;
		
		case '@':h.put("PP");break;
		default:h.put("PP"+c);
		}
	}

	/*
	 * PPY
	 */
	private void PPY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.PI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.PI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.PI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("PPY");break;
		default:h.put("PPY"+c);
		}
	}
	
	/*
	 * W
	 */
	private void W(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.WA);break;
		case 'O':h.put(Katakana.WO);break;
		case 'E':h.put(Katakana.WE);break;
		case 'I':h.put(Katakana.WI);break;
		
		case '@':h.put('W');break;
		default:h.put('W');h.put(c);
		}
	}
	
	/*
	 * C
	 */
	private void C(Holder h, char c)
	{
		switch(c)
		{
		case 'H':ch(h, h.next());break;
		case 'C':CC(h, h.next());break;
		
		case '@':h.put('C');break;
		default:h.put('C');h.put(c);
		}
	}
	
	/*
	 * CH
	 */
	private void CH(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.CHI);break;
		case 'A':h.put(Katakana.CHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.CHI);h.put(Katakana.YU_SMALL);break;
		case 'E':h.put(Katakana.CHI);h.put(Katakana.E_SMALL);break;
		case 'O':h.put(Katakana.CHI);h.put(Katakana.YO_SMALL);break;
		case 'Y':CHY(h, h.next());break;

		case '@':h.put("ch");break;
		default:h.put("ch"+c);
		}
	}
	
	/*
	 * CHY
	 */
	private void CHY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.CHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.CHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.CHI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("CHY");break;
		default:h.put("CHY"+c);
		}
	}
	
	/*
	 * CC
	 */
	private void CC(Holder h, char c)
	{
		switch(c)
		{
		case 'H':CCH(h, h.next());break;
		
		case '@':h.put("CC");break;
		default:h.put("CC"+c);
		}
	}
	
	/*
	 * CCH
	 */
	private void CCH(Holder h, char c)
	{
		switch(c)
		{
		case 'I':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);break;
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YO_SMALL);break;
		case 'Y':CCHY(h, h.next());break;

		case '@':h.put("CCH");break;
		default:h.put("CCH"+c);
		}
	}
	
	/*
	 * CCHY
	 */
	private void CCHY(Holder h, char c)
	{
		switch(c)
		{
		case 'A':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YA_SMALL);break;
		case 'U':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YU_SMALL);break;
		case 'O':h.put(Katakana.TSU_SMALL);h.put(Katakana.CHI);h.put(Katakana.YO_SMALL);break;
		
		case '@':h.put("CCHY");break;
		default:h.put("CCHY"+c);
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