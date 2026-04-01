package a.entity.gus06.string.transform.japanese.romaji.builder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250722";}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		if(text==null) return null;
		if(text.equals("")) return "";
		
		Holder h = new Holder(text);
		while(h.length()>0) handleCodePoint(h, h.next());
		return h.toString();
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
	
		private int next()
		{
			if(input.length()==0) return -1;
			
			int codePoint = Character.codePointAt(input,0);
			input.deleteCharAt(0);
			return codePoint;
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
	
	
	private void handleCodePoint(Holder h, int codePoint)
	{
		if(codePoint==-1)return;
		
		if(codePoint==Hiragana.A){h.put("a");return;}
		if(codePoint==Hiragana.I){h.put("i");return;}
		if(codePoint==Hiragana.U){h.put("u");return;}
		if(codePoint==Hiragana.E){h.put("e");return;}
		if(codePoint==Hiragana.O){h.put("o");return;}
		
		if(codePoint==Hiragana.KA){h.put("ka");return;}
		if(codePoint==Hiragana.KI){ki1(h, h.next());return;}
		if(codePoint==Hiragana.KU){h.put("ku");return;}
		if(codePoint==Hiragana.KE){h.put("ke");return;}
		if(codePoint==Hiragana.KO){h.put("ko");return;}
		
		if(codePoint==Hiragana.GA){h.put("ga");return;}
		if(codePoint==Hiragana.GI){gi1(h, h.next());return;}
		if(codePoint==Hiragana.GU){h.put("gu");return;}
		if(codePoint==Hiragana.GE){h.put("ge");return;}
		if(codePoint==Hiragana.GO){h.put("go");return;}
		
		if(codePoint==Hiragana.MA){h.put("ma");return;}
		if(codePoint==Hiragana.MI){mi1(h, h.next());return;}
		if(codePoint==Hiragana.MU){h.put("mu");return;}
		if(codePoint==Hiragana.ME){h.put("me");return;}
		if(codePoint==Hiragana.MO){h.put("mo");return;}
		
		if(codePoint==Hiragana.NA){h.put("na");return;}
		if(codePoint==Hiragana.NI){ni1(h, h.next());return;}
		if(codePoint==Hiragana.NU){h.put("nu");return;}
		if(codePoint==Hiragana.NE){h.put("ne");return;}
		if(codePoint==Hiragana.NO){h.put("no");return;}

		if(codePoint==Hiragana.BA){h.put("ba");return;}
		if(codePoint==Hiragana.BI){bi1(h, h.next());return;}
		if(codePoint==Hiragana.BU){h.put("bu");return;}
		if(codePoint==Hiragana.BE){h.put("be");return;}
		if(codePoint==Hiragana.BO){h.put("bo");return;}

		if(codePoint==Hiragana.PA){h.put("pa");return;}
		if(codePoint==Hiragana.PI){pi1(h, h.next());return;}
		if(codePoint==Hiragana.PU){h.put("pu");return;}
		if(codePoint==Hiragana.PE){h.put("pe");return;}
		if(codePoint==Hiragana.PO){h.put("po");return;}

		if(codePoint==Hiragana.TA){h.put("ta");return;}
		if(codePoint==Hiragana.CHI){chi1(h, h.next());return;}
		if(codePoint==Hiragana.TSU){h.put("tsu");return;}
		if(codePoint==Hiragana.TE){h.put("te");return;}
		if(codePoint==Hiragana.TO){h.put("to");return;}

		if(codePoint==Hiragana.DA){h.put("da");return;}
		if(codePoint==Hiragana.DJI){dji1(h, h.next());return;}
		if(codePoint==Hiragana.DZU){h.put("dzu");return;}
		if(codePoint==Hiragana.DE){h.put("de");return;}
		if(codePoint==Hiragana.DO){h.put("do");return;}

		if(codePoint==Hiragana.RA){h.put("ra");return;}
		if(codePoint==Hiragana.RI){ri1(h, h.next());return;}
		if(codePoint==Hiragana.RU){h.put("ru");return;}
		if(codePoint==Hiragana.RE){h.put("re");return;}
		if(codePoint==Hiragana.RO){h.put("ro");return;}

		if(codePoint==Hiragana.SA){h.put("sa");return;}
		if(codePoint==Hiragana.SHI){shi1(h, h.next());return;}
		if(codePoint==Hiragana.SU){h.put("su");return;}
		if(codePoint==Hiragana.SE){h.put("se");return;}
		if(codePoint==Hiragana.SO){h.put("so");return;}

		if(codePoint==Hiragana.ZA){h.put("za");return;}
		if(codePoint==Hiragana.JI){ji1(h, h.next());return;}
		if(codePoint==Hiragana.ZU){h.put("zu");return;}
		if(codePoint==Hiragana.ZE){h.put("ze");return;}
		if(codePoint==Hiragana.ZO){h.put("zo");return;}

		if(codePoint==Hiragana.HA){h.put("ha");return;}
		if(codePoint==Hiragana.HI){hi1(h, h.next());return;}
		if(codePoint==Hiragana.FU){h.put("fu");return;}
		if(codePoint==Hiragana.HE){h.put("he");return;}
		if(codePoint==Hiragana.HO){h.put("ho");return;}

		if(codePoint==Hiragana.WA){h.put("wa");return;}
		if(codePoint==Hiragana.WI){h.put("wi");return;}
		if(codePoint==Hiragana.WE){h.put("we");return;}
		if(codePoint==Hiragana.WO){h.put("wo");return;}
		
		if(codePoint==Hiragana.YA){h.put("ya");return;}
		if(codePoint==Hiragana.YU){h.put("yu");return;}
		if(codePoint==Hiragana.YO){h.put("yo");return;}

		if(codePoint==Hiragana.N){h.put("n");return;}
		if(codePoint==Hiragana.TSU_SMALL){kkk1(h, h.next());return;}

		if(codePoint==Katakana.A){h.put("a");return;}
		if(codePoint==Katakana.I){h.put("i");return;}
		if(codePoint==Katakana.U){h.put("u");return;}
		if(codePoint==Katakana.E){h.put("e");return;}
		if(codePoint==Katakana.O){h.put("o");return;}
		
		if(codePoint==Katakana.KA){h.put("ka");return;}
		if(codePoint==Katakana.KI){ki2(h, h.next());return;}
		if(codePoint==Katakana.KU){h.put("ku");return;}
		if(codePoint==Katakana.KE){h.put("ke");return;}
		if(codePoint==Katakana.KO){h.put("ko");return;}
		
		if(codePoint==Katakana.GA){h.put("ga");return;}
		if(codePoint==Katakana.GI){gi2(h, h.next());return;}
		if(codePoint==Katakana.GU){h.put("gu");return;}
		if(codePoint==Katakana.GE){h.put("ge");return;}
		if(codePoint==Katakana.GO){h.put("go");return;}
		
		if(codePoint==Katakana.MA){h.put("ma");return;}
		if(codePoint==Katakana.MI){mi2(h, h.next());return;}
		if(codePoint==Katakana.MU){h.put("mu");return;}
		if(codePoint==Katakana.ME){h.put("me");return;}
		if(codePoint==Katakana.MO){h.put("mo");return;}
		
		if(codePoint==Katakana.NA){h.put("na");return;}
		if(codePoint==Katakana.NI){ni2(h, h.next());return;}
		if(codePoint==Katakana.NU){h.put("nu");return;}
		if(codePoint==Katakana.NE){h.put("ne");return;}
		if(codePoint==Katakana.NO){h.put("no");return;}

		if(codePoint==Katakana.BA){h.put("ba");return;}
		if(codePoint==Katakana.BI){bi2(h, h.next());return;}
		if(codePoint==Katakana.BU){h.put("bu");return;}
		if(codePoint==Katakana.BE){h.put("be");return;}
		if(codePoint==Katakana.BO){h.put("bo");return;}

		if(codePoint==Katakana.PA){h.put("pa");return;}
		if(codePoint==Katakana.PI){pi2(h, h.next());return;}
		if(codePoint==Katakana.PU){h.put("pu");return;}
		if(codePoint==Katakana.PE){h.put("pe");return;}
		if(codePoint==Katakana.PO){h.put("po");return;}

		if(codePoint==Katakana.TA){h.put("ta");return;}
		if(codePoint==Katakana.CHI){chi2(h, h.next());return;}
		if(codePoint==Katakana.TSU){h.put("tsu");return;}
		if(codePoint==Katakana.TE){h.put("te");return;}
		if(codePoint==Katakana.TO){h.put("to");return;}

		if(codePoint==Katakana.DA){h.put("da");return;}
		if(codePoint==Katakana.DJI){dji2(h, h.next());return;}
		if(codePoint==Katakana.DZU){h.put("dzu");return;}
		if(codePoint==Katakana.DE){h.put("de");return;}
		if(codePoint==Katakana.DO){h.put("do");return;}

		if(codePoint==Katakana.RA){h.put("ra");return;}
		if(codePoint==Katakana.RI){ri2(h, h.next());return;}
		if(codePoint==Katakana.RU){h.put("ru");return;}
		if(codePoint==Katakana.RE){h.put("re");return;}
		if(codePoint==Katakana.RO){h.put("ro");return;}

		if(codePoint==Katakana.SA){h.put("sa");return;}
		if(codePoint==Katakana.SHI){shi2(h, h.next());return;}
		if(codePoint==Katakana.SU){h.put("su");return;}
		if(codePoint==Katakana.SE){h.put("se");return;}
		if(codePoint==Katakana.SO){h.put("so");return;}

		if(codePoint==Katakana.ZA){h.put("za");return;}
		if(codePoint==Katakana.JI){ji2(h, h.next());return;}
		if(codePoint==Katakana.ZU){h.put("zu");return;}
		if(codePoint==Katakana.ZE){h.put("ze");return;}
		if(codePoint==Katakana.ZO){h.put("zo");return;}

		if(codePoint==Katakana.HA){h.put("ha");return;}
		if(codePoint==Katakana.HI){hi2(h, h.next());return;}
		if(codePoint==Katakana.FU){h.put("fu");return;}
		if(codePoint==Katakana.HE){h.put("he");return;}
		if(codePoint==Katakana.HO){h.put("ho");return;}

		if(codePoint==Katakana.WA){h.put("wa");return;}
		if(codePoint==Katakana.WI){h.put("wi");return;}
		if(codePoint==Katakana.WE){h.put("we");return;}
		if(codePoint==Katakana.WO){h.put("wo");return;}
		
		if(codePoint==Katakana.YA){h.put("ya");return;}
		if(codePoint==Katakana.YU){h.put("yu");return;}
		if(codePoint==Katakana.YO){h.put("yo");return;}

		if(codePoint==Katakana.N){h.put("n");return;}
		if(codePoint==Katakana.TSU_SMALL){kkk2(h, h.next());return;}
	}
	
	
	
	
	
	private void ki1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("kya");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("kyu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("kyo");return;}

		h.put("ki");
		handleCodePoint(h, codePoint);
	}
	
	private void gi1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("gya");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("gyu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("gyo");return;}

		h.put("gi");
		handleCodePoint(h, codePoint);
	}
	
	private void hi1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("hya");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("hyu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("hyo");return;}

		h.put("hi");
		handleCodePoint(h, codePoint);
	}
	
	private void bi1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("bya");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("byu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("byo");return;}

		h.put("bi");
		handleCodePoint(h, codePoint);
	}
	
	private void pi1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("pya");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("pyu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("pyo");return;}

		h.put("pi");
		handleCodePoint(h, codePoint);
	}
	
	private void mi1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("mya");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("myu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("myo");return;}

		h.put("mi");
		handleCodePoint(h, codePoint);
	}
	
	private void ni1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("nya");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("nyu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("nyo");return;}

		h.put("ni");
		handleCodePoint(h, codePoint);
	}
	
	private void shi1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("sha");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("shu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("sho");return;}

		h.put("shi");
		handleCodePoint(h, codePoint);
	}
	
	private void chi1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("cha");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("chu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("cho");return;}

		h.put("chi");
		handleCodePoint(h, codePoint);
	}
	
	private void ji1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("ja");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("ju");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("jo");return;}

		h.put("ji");
		handleCodePoint(h, codePoint);
	}
	
	private void dji1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("dja");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("dju");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("djo");return;}

		h.put("dji");
		handleCodePoint(h, codePoint);
	}
	
	private void ri1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.YA_SMALL){h.put("rya");return;}
		if(codePoint==Hiragana.YU_SMALL){h.put("ryu");return;}
		if(codePoint==Hiragana.YO_SMALL){h.put("ryo");return;}

		h.put("ri");
		handleCodePoint(h, codePoint);
	}
	
	private void kkk1(Holder h, int codePoint)
	{
		if(codePoint==Hiragana.KA){h.put("kka");return;}
		if(codePoint==Hiragana.KI){h.put("k");ki1(h, h.next());return;}
		if(codePoint==Hiragana.KU){h.put("kku");return;}
		if(codePoint==Hiragana.KE){h.put("kke");return;}
		if(codePoint==Hiragana.KO){h.put("kko");return;}
		
		if(codePoint==Hiragana.GA){h.put("gga");return;}
		if(codePoint==Hiragana.GI){h.put("g");gi1(h, h.next());return;}
		if(codePoint==Hiragana.GU){h.put("ggu");return;}
		if(codePoint==Hiragana.GE){h.put("gge");return;}
		if(codePoint==Hiragana.GO){h.put("ggo");return;}
		
		if(codePoint==Hiragana.MA){h.put("mma");return;}
		if(codePoint==Hiragana.MI){h.put("m");mi1(h, h.next());return;}
		if(codePoint==Hiragana.MU){h.put("mmu");return;}
		if(codePoint==Hiragana.ME){h.put("mme");return;}
		if(codePoint==Hiragana.MO){h.put("mmo");return;}
		
		if(codePoint==Hiragana.NA){h.put("nna");return;}
		if(codePoint==Hiragana.NI){h.put("n");ni1(h, h.next());return;}
		if(codePoint==Hiragana.NU){h.put("nnu");return;}
		if(codePoint==Hiragana.NE){h.put("nne");return;}
		if(codePoint==Hiragana.NO){h.put("nno");return;}

		if(codePoint==Hiragana.BA){h.put("bba");return;}
		if(codePoint==Hiragana.BI){h.put("b");bi1(h, h.next());return;}
		if(codePoint==Hiragana.BU){h.put("bbu");return;}
		if(codePoint==Hiragana.BE){h.put("bbe");return;}
		if(codePoint==Hiragana.BO){h.put("bbo");return;}

		if(codePoint==Hiragana.PA){h.put("ppa");return;}
		if(codePoint==Hiragana.PI){h.put("p");pi1(h, h.next());return;}
		if(codePoint==Hiragana.PU){h.put("ppu");return;}
		if(codePoint==Hiragana.PE){h.put("ppe");return;}
		if(codePoint==Hiragana.PO){h.put("ppo");return;}

		if(codePoint==Hiragana.TA){h.put("tta");return;}
		if(codePoint==Hiragana.CHI){h.put("c");chi1(h, h.next());return;}
		if(codePoint==Hiragana.TSU){h.put("ttsu");return;}
		if(codePoint==Hiragana.TE){h.put("tte");return;}
		if(codePoint==Hiragana.TO){h.put("tto");return;}

		if(codePoint==Hiragana.DA){h.put("dda");return;}
		if(codePoint==Hiragana.DJI){h.put("d");dji1(h, h.next());return;}
		if(codePoint==Hiragana.DZU){h.put("ddzu");return;}
		if(codePoint==Hiragana.DE){h.put("dde");return;}
		if(codePoint==Hiragana.DO){h.put("ddo");return;}

		if(codePoint==Hiragana.RA){h.put("rra");return;}
		if(codePoint==Hiragana.RI){h.put("r");ri1(h, h.next());return;}
		if(codePoint==Hiragana.RU){h.put("rru");return;}
		if(codePoint==Hiragana.RE){h.put("rre");return;}
		if(codePoint==Hiragana.RO){h.put("rro");return;}

		if(codePoint==Hiragana.SA){h.put("ssa");return;}
		if(codePoint==Hiragana.SHI){h.put("s");shi1(h, h.next());return;}
		if(codePoint==Hiragana.SU){h.put("ssu");return;}
		if(codePoint==Hiragana.SE){h.put("sse");return;}
		if(codePoint==Hiragana.SO){h.put("sso");return;}

		if(codePoint==Hiragana.ZA){h.put("zza");return;}
		if(codePoint==Hiragana.JI){h.put("j");ji1(h, h.next());return;}
		if(codePoint==Hiragana.ZU){h.put("zzu");return;}
		if(codePoint==Hiragana.ZE){h.put("zze");return;}
		if(codePoint==Hiragana.ZO){h.put("zzo");return;}

		if(codePoint==Hiragana.HA){h.put("hha");return;}
		if(codePoint==Hiragana.HI){h.put("h");hi1(h, h.next());return;}
		if(codePoint==Hiragana.FU){h.put("ffu");return;}
		if(codePoint==Hiragana.HE){h.put("hhe");return;}
		if(codePoint==Hiragana.HO){h.put("hho");return;}
	}

	private void ki2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("kya");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("kyu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("kyo");return;}

		h.put("ki");
		handleCodePoint(h, codePoint);
	}
	
	private void gi2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("gya");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("gyu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("gyo");return;}

		h.put("gi");
		handleCodePoint(h, codePoint);
	}
	
	private void hi2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("hya");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("hyu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("hyo");return;}

		h.put("hi");
		handleCodePoint(h, codePoint);
	}
	
	private void bi2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("bya");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("byu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("byo");return;}

		h.put("bi");
		handleCodePoint(h, codePoint);
	}
	
	private void pi2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("pya");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("pyu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("pyo");return;}

		h.put("pi");
		handleCodePoint(h, codePoint);
	}
	
	private void mi2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("mya");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("myu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("myo");return;}

		h.put("mi");
		handleCodePoint(h, codePoint);
	}
	
	private void ni2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("nya");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("nyu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("nyo");return;}

		h.put("ni");
		handleCodePoint(h, codePoint);
	}
	
	private void shi2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("sha");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("shu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("sho");return;}

		h.put("shi");
		handleCodePoint(h, codePoint);
	}
	
	private void chi2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("cha");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("chu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("cho");return;}

		h.put("chi");
		handleCodePoint(h, codePoint);
	}
	
	private void ji2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("ja");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("ju");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("jo");return;}

		h.put("ji");
		handleCodePoint(h, codePoint);
	}
	
	private void dji2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("dja");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("dju");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("djo");return;}

		h.put("dji");
		handleCodePoint(h, codePoint);
	}
	
	private void ri2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.YA_SMALL){h.put("rya");return;}
		if(codePoint==Katakana.YU_SMALL){h.put("ryu");return;}
		if(codePoint==Katakana.YO_SMALL){h.put("ryo");return;}

		h.put("ri");
		handleCodePoint(h, codePoint);
	}
	
	private void kkk2(Holder h, int codePoint)
	{
		if(codePoint==Katakana.KA){h.put("kka");return;}
		if(codePoint==Katakana.KI){h.put("k");ki2(h, h.next());return;}
		if(codePoint==Katakana.KU){h.put("kku");return;}
		if(codePoint==Katakana.KE){h.put("kke");return;}
		if(codePoint==Katakana.KO){h.put("kko");return;}
		
		if(codePoint==Katakana.GA){h.put("gga");return;}
		if(codePoint==Katakana.GI){h.put("g");gi2(h, h.next());return;}
		if(codePoint==Katakana.GU){h.put("ggu");return;}
		if(codePoint==Katakana.GE){h.put("gge");return;}
		if(codePoint==Katakana.GO){h.put("ggo");return;}
		
		if(codePoint==Katakana.MA){h.put("mma");return;}
		if(codePoint==Katakana.MI){h.put("m");mi2(h, h.next());return;}
		if(codePoint==Katakana.MU){h.put("mmu");return;}
		if(codePoint==Katakana.ME){h.put("mme");return;}
		if(codePoint==Katakana.MO){h.put("mmo");return;}
		
		if(codePoint==Katakana.NA){h.put("nna");return;}
		if(codePoint==Katakana.NI){h.put("n");ni2(h, h.next());return;}
		if(codePoint==Katakana.NU){h.put("nnu");return;}
		if(codePoint==Katakana.NE){h.put("nne");return;}
		if(codePoint==Katakana.NO){h.put("nno");return;}

		if(codePoint==Katakana.BA){h.put("bba");return;}
		if(codePoint==Katakana.BI){h.put("b");bi2(h, h.next());return;}
		if(codePoint==Katakana.BU){h.put("bbu");return;}
		if(codePoint==Katakana.BE){h.put("bbe");return;}
		if(codePoint==Katakana.BO){h.put("bbo");return;}

		if(codePoint==Katakana.PA){h.put("ppa");return;}
		if(codePoint==Katakana.PI){h.put("p");pi2(h, h.next());return;}
		if(codePoint==Katakana.PU){h.put("ppu");return;}
		if(codePoint==Katakana.PE){h.put("ppe");return;}
		if(codePoint==Katakana.PO){h.put("ppo");return;}

		if(codePoint==Katakana.TA){h.put("tta");return;}
		if(codePoint==Katakana.CHI){h.put("c");chi2(h, h.next());return;}
		if(codePoint==Katakana.TSU){h.put("ttsu");return;}
		if(codePoint==Katakana.TE){h.put("tte");return;}
		if(codePoint==Katakana.TO){h.put("tto");return;}

		if(codePoint==Katakana.DA){h.put("dda");return;}
		if(codePoint==Katakana.DJI){h.put("d");dji2(h, h.next());return;}
		if(codePoint==Katakana.DZU){h.put("ddzu");return;}
		if(codePoint==Katakana.DE){h.put("dde");return;}
		if(codePoint==Katakana.DO){h.put("ddo");return;}

		if(codePoint==Katakana.RA){h.put("rra");return;}
		if(codePoint==Katakana.RI){h.put("r");ri2(h, h.next());return;}
		if(codePoint==Katakana.RU){h.put("rru");return;}
		if(codePoint==Katakana.RE){h.put("rre");return;}
		if(codePoint==Katakana.RO){h.put("rro");return;}

		if(codePoint==Katakana.SA){h.put("ssa");return;}
		if(codePoint==Katakana.SHI){h.put("s");shi2(h, h.next());return;}
		if(codePoint==Katakana.SU){h.put("ssu");return;}
		if(codePoint==Katakana.SE){h.put("sse");return;}
		if(codePoint==Katakana.SO){h.put("sso");return;}

		if(codePoint==Katakana.ZA){h.put("zza");return;}
		if(codePoint==Katakana.JI){h.put("j");ji2(h, h.next());return;}
		if(codePoint==Katakana.ZU){h.put("zzu");return;}
		if(codePoint==Katakana.ZE){h.put("zze");return;}
		if(codePoint==Katakana.ZO){h.put("zzo");return;}

		if(codePoint==Katakana.HA){h.put("hha");return;}
		if(codePoint==Katakana.HI){h.put("h");hi2(h, h.next());return;}
		if(codePoint==Katakana.FU){h.put("ffu");return;}
		if(codePoint==Katakana.HE){h.put("hhe");return;}
		if(codePoint==Katakana.HO){h.put("hho");return;}
	}
}