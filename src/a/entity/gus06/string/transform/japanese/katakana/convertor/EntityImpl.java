package a.entity.gus06.string.transform.japanese.katakana.convertor;

import a.framework.*;

public class EntityImpl extends CodePointsKatakana implements Entity, T {

	public String creationDate() {return "20150927";}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		if(text==null) return null;
		if(text.equals("")) return "";
		
		Holder h = new Holder(text);
		while(h.length()>0) handleCodePoint(h, h.next());
		return h.toString();
	}
	
	
	
	private void handleCodePoint(Holder h, int codePoint)
	{
		if(codePoint==-1)return;
		
		if(codePoint==A){h.put("a");return;}
		if(codePoint==I){h.put("i");return;}
		if(codePoint==U){h.put("u");return;}
		if(codePoint==E){h.put("e");return;}
		if(codePoint==O){h.put("o");return;}
		
		if(codePoint==KA){h.put("ka");return;}
		if(codePoint==KI){ki(h, h.next());return;}
		if(codePoint==KU){h.put("ku");return;}
		if(codePoint==KE){h.put("ke");return;}
		if(codePoint==KO){h.put("ko");return;}
		
		if(codePoint==GA){h.put("ga");return;}
		if(codePoint==GI){gi(h, h.next());return;}
		if(codePoint==GU){h.put("gu");return;}
		if(codePoint==GE){h.put("ge");return;}
		if(codePoint==GO){h.put("go");return;}
		
		if(codePoint==MA){h.put("ma");return;}
		if(codePoint==MI){mi(h, h.next());return;}
		if(codePoint==MU){h.put("mu");return;}
		if(codePoint==ME){h.put("me");return;}
		if(codePoint==MO){h.put("mo");return;}
		
		if(codePoint==NA){h.put("na");return;}
		if(codePoint==NI){ni(h, h.next());return;}
		if(codePoint==NU){h.put("nu");return;}
		if(codePoint==NE){h.put("ne");return;}
		if(codePoint==NO){h.put("no");return;}

		if(codePoint==BA){h.put("ba");return;}
		if(codePoint==BI){bi(h, h.next());return;}
		if(codePoint==BU){h.put("bu");return;}
		if(codePoint==BE){h.put("be");return;}
		if(codePoint==BO){h.put("bo");return;}

		if(codePoint==PA){h.put("pa");return;}
		if(codePoint==PI){pi(h, h.next());return;}
		if(codePoint==PU){h.put("pu");return;}
		if(codePoint==PE){h.put("pe");return;}
		if(codePoint==PO){h.put("po");return;}

		if(codePoint==TA){h.put("ta");return;}
		if(codePoint==CHI){chi(h, h.next());return;}
		if(codePoint==TSU){h.put("tsu");return;}
		if(codePoint==TE){h.put("te");return;}
		if(codePoint==TO){h.put("to");return;}

		if(codePoint==DA){h.put("da");return;}
		if(codePoint==DJI){dji(h, h.next());return;}
		if(codePoint==DZU){h.put("dzu");return;}
		if(codePoint==DE){h.put("de");return;}
		if(codePoint==DO){h.put("do");return;}

		if(codePoint==RA){h.put("ra");return;}
		if(codePoint==RI){ri(h, h.next());return;}
		if(codePoint==RU){h.put("ru");return;}
		if(codePoint==RE){h.put("re");return;}
		if(codePoint==RO){h.put("ro");return;}

		if(codePoint==SA){h.put("sa");return;}
		if(codePoint==SHI){shi(h, h.next());return;}
		if(codePoint==SU){h.put("su");return;}
		if(codePoint==SE){h.put("se");return;}
		if(codePoint==SO){h.put("so");return;}

		if(codePoint==ZA){h.put("za");return;}
		if(codePoint==JI){ji(h, h.next());return;}
		if(codePoint==ZU){h.put("zu");return;}
		if(codePoint==ZE){h.put("ze");return;}
		if(codePoint==ZO){h.put("zo");return;}

		if(codePoint==HA){h.put("ha");return;}
		if(codePoint==HI){hi(h, h.next());return;}
		if(codePoint==FU){h.put("fu");return;}
		if(codePoint==HE){h.put("he");return;}
		if(codePoint==HO){h.put("ho");return;}

		if(codePoint==WA){h.put("wa");return;}
		if(codePoint==WI){h.put("wi");return;}
		if(codePoint==WE){h.put("we");return;}
		if(codePoint==WO){h.put("wo");return;}
		
		if(codePoint==YA){h.put("ya");return;}
		if(codePoint==YU){h.put("yu");return;}
		if(codePoint==YO){h.put("yo");return;}

		if(codePoint==N){h.put("n");return;}
		if(codePoint==TSU_SMALL){kkk(h, h.next());return;}
	}
	
	
	
	
	
	private void ki(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("kya");return;}
		if(codePoint==YU_SMALL){h.put("kyu");return;}
		if(codePoint==YO_SMALL){h.put("kyo");return;}

		h.put("ki");
		handleCodePoint(h, codePoint);
	}
	
	
	private void gi(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("gya");return;}
		if(codePoint==YU_SMALL){h.put("gyu");return;}
		if(codePoint==YO_SMALL){h.put("gyo");return;}

		h.put("gi");
		handleCodePoint(h, codePoint);
	}
	
	
	
	private void hi(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("hya");return;}
		if(codePoint==YU_SMALL){h.put("hyu");return;}
		if(codePoint==YO_SMALL){h.put("hyo");return;}

		h.put("hi");
		handleCodePoint(h, codePoint);
	}
	
	
	private void bi(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("bya");return;}
		if(codePoint==YU_SMALL){h.put("byu");return;}
		if(codePoint==YO_SMALL){h.put("byo");return;}

		h.put("bi");
		handleCodePoint(h, codePoint);
	}
	
	
	private void pi(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("pya");return;}
		if(codePoint==YU_SMALL){h.put("pyu");return;}
		if(codePoint==YO_SMALL){h.put("pyo");return;}

		h.put("pi");
		handleCodePoint(h, codePoint);
	}
	
	
	
	private void mi(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("mya");return;}
		if(codePoint==YU_SMALL){h.put("myu");return;}
		if(codePoint==YO_SMALL){h.put("myo");return;}

		h.put("mi");
		handleCodePoint(h, codePoint);
	}
	
	
	
	private void ni(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("nya");return;}
		if(codePoint==YU_SMALL){h.put("nyu");return;}
		if(codePoint==YO_SMALL){h.put("nyo");return;}

		h.put("ni");
		handleCodePoint(h, codePoint);
	}
	
	
	
	
	private void shi(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("sha");return;}
		if(codePoint==YU_SMALL){h.put("shu");return;}
		if(codePoint==YO_SMALL){h.put("sho");return;}

		h.put("shi");
		handleCodePoint(h, codePoint);
	}
	
	
	
	
	private void chi(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("cha");return;}
		if(codePoint==YU_SMALL){h.put("chu");return;}
		if(codePoint==YO_SMALL){h.put("cho");return;}

		h.put("chi");
		handleCodePoint(h, codePoint);
	}
	
	
	
	private void ji(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("ja");return;}
		if(codePoint==YU_SMALL){h.put("ju");return;}
		if(codePoint==YO_SMALL){h.put("jo");return;}

		h.put("ji");
		handleCodePoint(h, codePoint);
	}
	
	
	
	private void dji(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("dja");return;}
		if(codePoint==YU_SMALL){h.put("dju");return;}
		if(codePoint==YO_SMALL){h.put("djo");return;}

		h.put("dji");
		handleCodePoint(h, codePoint);
	}
	
	
	
	private void ri(Holder h, int codePoint)
	{
		if(codePoint==YA_SMALL){h.put("rya");return;}
		if(codePoint==YU_SMALL){h.put("ryu");return;}
		if(codePoint==YO_SMALL){h.put("ryo");return;}

		h.put("ri");
		handleCodePoint(h, codePoint);
	}
	
	
	
	
	private void kkk(Holder h, int codePoint)
	{
		if(codePoint==KA){h.put("kka");return;}
		if(codePoint==KI){h.put("k");ki(h, h.next());return;}
		if(codePoint==KU){h.put("kku");return;}
		if(codePoint==KE){h.put("kke");return;}
		if(codePoint==KO){h.put("kko");return;}
		
		if(codePoint==GA){h.put("gga");return;}
		if(codePoint==GI){h.put("g");gi(h, h.next());return;}
		if(codePoint==GU){h.put("ggu");return;}
		if(codePoint==GE){h.put("gge");return;}
		if(codePoint==GO){h.put("ggo");return;}
		
		if(codePoint==MA){h.put("mma");return;}
		if(codePoint==MI){h.put("m");mi(h, h.next());return;}
		if(codePoint==MU){h.put("mmu");return;}
		if(codePoint==ME){h.put("mme");return;}
		if(codePoint==MO){h.put("mmo");return;}
		
		if(codePoint==NA){h.put("nna");return;}
		if(codePoint==NI){h.put("n");ni(h, h.next());return;}
		if(codePoint==NU){h.put("nnu");return;}
		if(codePoint==NE){h.put("nne");return;}
		if(codePoint==NO){h.put("nno");return;}

		if(codePoint==BA){h.put("bba");return;}
		if(codePoint==BI){h.put("b");bi(h, h.next());return;}
		if(codePoint==BU){h.put("bbu");return;}
		if(codePoint==BE){h.put("bbe");return;}
		if(codePoint==BO){h.put("bbo");return;}

		if(codePoint==PA){h.put("ppa");return;}
		if(codePoint==PI){h.put("p");pi(h, h.next());return;}
		if(codePoint==PU){h.put("ppu");return;}
		if(codePoint==PE){h.put("ppe");return;}
		if(codePoint==PO){h.put("ppo");return;}

		if(codePoint==TA){h.put("tta");return;}
		if(codePoint==CHI){h.put("c");chi(h, h.next());return;}
		if(codePoint==TSU){h.put("ttsu");return;}
		if(codePoint==TE){h.put("tte");return;}
		if(codePoint==TO){h.put("tto");return;}

		if(codePoint==DA){h.put("dda");return;}
		if(codePoint==DJI){h.put("d");dji(h, h.next());return;}
		if(codePoint==DZU){h.put("ddzu");return;}
		if(codePoint==DE){h.put("dde");return;}
		if(codePoint==DO){h.put("ddo");return;}

		if(codePoint==RA){h.put("rra");return;}
		if(codePoint==RI){h.put("r");ri(h, h.next());return;}
		if(codePoint==RU){h.put("rru");return;}
		if(codePoint==RE){h.put("rre");return;}
		if(codePoint==RO){h.put("rro");return;}

		if(codePoint==SA){h.put("ssa");return;}
		if(codePoint==SHI){h.put("s");shi(h, h.next());return;}
		if(codePoint==SU){h.put("ssu");return;}
		if(codePoint==SE){h.put("sse");return;}
		if(codePoint==SO){h.put("sso");return;}

		if(codePoint==ZA){h.put("zza");return;}
		if(codePoint==JI){h.put("j");ji(h, h.next());return;}
		if(codePoint==ZU){h.put("zzu");return;}
		if(codePoint==ZE){h.put("zze");return;}
		if(codePoint==ZO){h.put("zzo");return;}

		if(codePoint==HA){h.put("hha");return;}
		if(codePoint==HI){h.put("h");hi(h, h.next());return;}
		if(codePoint==FU){h.put("ffu");return;}
		if(codePoint==HE){h.put("hhe");return;}
		if(codePoint==HO){h.put("hho");return;}
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
}