package a.entity.gus06.convert.stringtodate;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141106";}




	public Object t(Object obj) throws Exception
	{
		String s = prepareString((String) obj);
		SimpleDateFormat[] sdf_ = findSDF(s);
		
		if(sdf_!=null)
		for(SimpleDateFormat sdf:sdf_)
		{
			sdf.setLenient(false);
			Date d = parse(sdf,s);
			if(d!=null) return d;
		}
		
		try{return new Date(Long.parseLong(s));}
		catch(NumberFormatException e){}
		
		throw new Exception("Unknown date syntax: "+s);
	}
	
	
	private String prepareString(String s)
	{
		s = s
		 .replace("janvier","01")
		 .replaceAll("janv\\.?","01")
		 .replaceAll("jan\\.?","01")
		
		 .replace("f�vrier","02")
		 .replaceAll("f�vr\\.?","02")
		 .replaceAll("f�v\\.?","02")
		
		 .replace("mars","03")
		
		 .replace("avril","04")
		 .replaceAll("avr\\.?","04")
		
		 .replace("mai","05")
		
		 .replace("juin","06")
		
		 .replace("juillet","07")
		 .replaceAll("juil\\.?","07")
		
		 .replace("ao�t","08")
		
		 .replace("septembre","09")
		 .replaceAll("sept\\.?","09")
		
		 .replace("octobre","10")
		 .replaceAll("oct\\.?","10")
		
		 .replace("novembre","11")
		 .replaceAll("nov\\.?","11")
		
		 .replace("d�cembre","12")
		 .replaceAll("d�c\\.?","12");
		
		s = s.replaceAll("[_ \t]+"," ");
		return s;
	}
	
	
	private Date parse(SimpleDateFormat sdf, String s)
	{
		try{return sdf.parse(s);}
		catch(Exception e) {return null;}
	}
	
	
	private SimpleDateFormat[] findSDF(String s)
	{
		switch(s.length()) {
			case 21:return sdf21;
			case 19:return sdf19;
			case 18:return sdf18;
			case 17:return sdf17;
			case 16:return sdf16;
			case 15:return sdf15;
			case 14:return sdf14;
			case 13:return sdf13;
			case 12:return sdf12;
			case 11:return sdf11;
			case 10:return sdf10;
			case 9:return sdf9;
			case 8:return sdf8;
			case 7:return sdf7;
			case 6:return sdf6;
			case 4:return sdf4;
			default:return null;
		}
	}
	
	
	


	private SimpleDateFormat[] sdf21 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0"),
		new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.0"),
		new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.0")
	};
	
	
	private SimpleDateFormat[] sdf19 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
		new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"),
		new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
	};
	
	
	
	private SimpleDateFormat[] sdf18 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy-MM-dd HH:mm:s"),
		new SimpleDateFormat("yyyy.MM.dd HH:mm:s"),
		new SimpleDateFormat("dd/MM/yyyy HH:mm:s"),
		
		new SimpleDateFormat("yyyy-MM-dd HH:m:ss"),
		new SimpleDateFormat("yyyy.MM.dd HH:m:ss"),
		new SimpleDateFormat("dd/MM/yyyy HH:m:ss"),
		
		new SimpleDateFormat("yyyy-MM-dd H:mm:ss"),
		new SimpleDateFormat("yyyy.MM.dd H:mm:ss"),
		new SimpleDateFormat("dd/MM/yyyy H:mm:ss"),
		
		new SimpleDateFormat("yyyy-MM-d HH:mm:ss"),
		new SimpleDateFormat("yyyy.MM.d HH:mm:ss"),
		new SimpleDateFormat("d/MM/yyyy HH:mm:ss"),
		
		new SimpleDateFormat("yyyy-M-dd HH:mm:ss"),
		new SimpleDateFormat("yyyy.M.dd HH:mm:ss"),
		new SimpleDateFormat("dd/M/yyyy HH:mm:ss")
	};
	
	
	
	private SimpleDateFormat[] sdf17 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyyMMdd HHmmssSS"),
		
		
		new SimpleDateFormat("yyyy-MM-dd HH:m:s"),
		new SimpleDateFormat("yyyy.MM.dd HH:m:s"),
		new SimpleDateFormat("dd/MM/yyyy HH:m:s"),
		
		new SimpleDateFormat("yyyy-MM-dd H:mm:s"),
		new SimpleDateFormat("yyyy.MM.dd H:mm:s"),
		new SimpleDateFormat("dd/MM/yyyy H:mm:s"),
		
		new SimpleDateFormat("yyyy-MM-d HH:mm:s"),
		new SimpleDateFormat("yyyy.MM.d HH:mm:s"),
		new SimpleDateFormat("d/MM/yyyy HH:mm:s"),
		
		new SimpleDateFormat("yyyy-M-dd HH:mm:s"),
		new SimpleDateFormat("yyyy.M.dd HH:mm:s"),
		new SimpleDateFormat("dd/M/yyyy HH:mm:s"),
		
		
		
		new SimpleDateFormat("yyyy-MM-dd H:m:ss"),
		new SimpleDateFormat("yyyy.MM.dd H:m:ss"),
		new SimpleDateFormat("dd/MM/yyyy H:m:ss"),
		
		new SimpleDateFormat("yyyy-MM-d HH:m:ss"),
		new SimpleDateFormat("yyyy.MM.d HH:m:ss"),
		new SimpleDateFormat("d/MM/yyyy HH:m:ss"),
		
		new SimpleDateFormat("yyyy-M-dd HH:m:ss"),
		new SimpleDateFormat("yyyy.M.dd HH:m:ss"),
		new SimpleDateFormat("dd/M/yyyy HH:m:ss"),
		
		
		
		new SimpleDateFormat("yyyy-MM-d H:mm:ss"),
		new SimpleDateFormat("yyyy.MM.d H:mm:ss"),
		new SimpleDateFormat("dd/M/yyyy H:mm:ss"),
		
		new SimpleDateFormat("yyyy-M-dd H:mm:ss"),
		new SimpleDateFormat("yyyy.M.dd H:mm:ss"),
		new SimpleDateFormat("dd/M/yyyy H:mm:ss"),
		
		
		
		new SimpleDateFormat("yyyy-M-d HH:mm:ss"),
		new SimpleDateFormat("yyyy.M.d HH:mm:ss"),
		new SimpleDateFormat("d/M/yyyy HH:mm:ss"),
	};
	
	
	
	private SimpleDateFormat[] sdf16 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy-MM-d H:m:ss"),
		new SimpleDateFormat("yyyy.MM.d H:m:ss"),
		new SimpleDateFormat("d/MM/yyyy H:m:ss"),
		
		new SimpleDateFormat("yyyy-M-dd H:m:ss"),
		new SimpleDateFormat("yyyy.M.dd H:m:ss"),
		new SimpleDateFormat("dd/M/yyyy H:m:ss"),
		
		new SimpleDateFormat("yyyy-MM-d H:mm:s"),
		new SimpleDateFormat("yyyy.MM.d H:mm:s"),
		new SimpleDateFormat("d/MM/yyyy H:mm:s"),
		
		new SimpleDateFormat("yyyy-M-dd H:mm:s"),
		new SimpleDateFormat("yyyy.M.dd H:mm:s"),
		new SimpleDateFormat("dd/M/yyyy H:mm:s"),
		
		new SimpleDateFormat("yyyy-MM-d HH:m:s"),
		new SimpleDateFormat("yyyy.MM.d HH:m:s"),
		new SimpleDateFormat("d/MM/yyyy HH:m:s"),
		
		new SimpleDateFormat("yyyy-M-dd HH:m:s"),
		new SimpleDateFormat("yyyy.M.dd HH:m:s"),
		new SimpleDateFormat("dd/M/yyyy HH:m:s"),
		
		new SimpleDateFormat("yyyy-MM-dd H:m:s"),
		new SimpleDateFormat("yyyy.MM.dd H:m:s"),
		new SimpleDateFormat("dd/MM/yyyy H:m:s"),
		
		new SimpleDateFormat("yyyy-MM-dd HH:mm"),
		new SimpleDateFormat("yyyy.MM.dd HH:mm"),
		new SimpleDateFormat("dd/MM/yyyy HH:mm"),
		
		new SimpleDateFormat("yyyy-MM-dd HH.mm"),
		new SimpleDateFormat("yyyy.MM.dd HH.mm"),
		new SimpleDateFormat("dd/MM/yyyy HH.mm"),
		
		new SimpleDateFormat("yyyy-MM-dd HH-mm"),
		new SimpleDateFormat("yyyy.MM.dd HH-mm"),
		new SimpleDateFormat("dd/MM/yyyy HH-mm")
	};
	
	
	
	private SimpleDateFormat[] sdf15 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyyMMdd HHmmss"),
		
		
		new SimpleDateFormat("yyyy-M-dd H:m:s"),
		new SimpleDateFormat("yyyy.M.dd H:m:s"),
		new SimpleDateFormat("dd/M/yyyy H:m:s"),
		
		new SimpleDateFormat("yyyy-MM-d H:m:s"),
		new SimpleDateFormat("yyyy.MM.d H:m:s"),
		new SimpleDateFormat("d/MM/yyyy H:m:s"),
		
		
		new SimpleDateFormat("yyyy-M-d H:mm:s"),
		new SimpleDateFormat("yyyy.M.d H:mm:s"),
		new SimpleDateFormat("d/M/yyyy H:mm:s"),
		
		new SimpleDateFormat("yyyy-M-d H:m:ss"),
		new SimpleDateFormat("yyyy.M.d H:m:ss"),
		new SimpleDateFormat("d/M/yyyy H:m:ss"),
		
		new SimpleDateFormat("yyyy-MM-d HH:m:s"),
		new SimpleDateFormat("yyyy.MM.d HH:m:s"),
		new SimpleDateFormat("d/MM/yyyy HH:m:s"),
		
		
		new SimpleDateFormat("yyyy-M-dd HH:mm"),
		new SimpleDateFormat("yyyy.M.dd HH:mm"),
		new SimpleDateFormat("dd/M/yyyy HH:mm"),
		
		new SimpleDateFormat("yyyy-M-dd HH.mm"),
		new SimpleDateFormat("yyyy.M.dd HH.mm"),
		new SimpleDateFormat("dd/M/yyyy HH.mm"),
		
		new SimpleDateFormat("yyyy-M-dd HH-mm"),
		new SimpleDateFormat("yyyy.M.dd HH-mm"),
		new SimpleDateFormat("dd/M/yyyy HH-mm"),
		
		
		new SimpleDateFormat("yyyy-MM-d HH:mm"),
		new SimpleDateFormat("yyyy.MM.d HH:mm"),
		new SimpleDateFormat("d/MM/yyyy HH:mm"),
		
		new SimpleDateFormat("yyyy-MM-d HH.mm"),
		new SimpleDateFormat("yyyy.MM.d HH.mm"),
		new SimpleDateFormat("d/MM/yyyy HH.mm"),
		
		new SimpleDateFormat("yyyy-MM-d HH-mm"),
		new SimpleDateFormat("yyyy.MM.d HH-mm"),
		new SimpleDateFormat("d/MM/yyyy HH-mm")
	};
	
	
	
	private SimpleDateFormat[] sdf14 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy-M-d H:m:s"),
		new SimpleDateFormat("yyyy.M.d H:m:s"),
		new SimpleDateFormat("d/M/yyyy H:m:s"),
		
		
		new SimpleDateFormat("yyyy-M-d HH:mm"),
		new SimpleDateFormat("yyyy.M.d HH:mm"),
		new SimpleDateFormat("d/M/yyyy HH:mm"),
		
		new SimpleDateFormat("yyyy-M-d HH.mm"),
		new SimpleDateFormat("yyyy.M.d HH.mm"),
		new SimpleDateFormat("d/M/yyyy HH.mm"),
		
		new SimpleDateFormat("yyyy-M-d HH-mm"),
		new SimpleDateFormat("yyyy.M.d HH-mm"),
		new SimpleDateFormat("d/M/yyyy HH-mm"),
		
		
		new SimpleDateFormat("yyyy-M-dd H:mm"),
		new SimpleDateFormat("yyyy.M.dd H:mm"),
		new SimpleDateFormat("dd/M/yyyy H:mm"),
		
		new SimpleDateFormat("yyyy-M-dd H.mm"),
		new SimpleDateFormat("yyyy.M.dd H.mm"),
		new SimpleDateFormat("dd/M/yyyy H.mm"),
		
		new SimpleDateFormat("yyyy-M-dd H-mm"),
		new SimpleDateFormat("yyyy.M.dd H-mm"),
		new SimpleDateFormat("dd/M/yyyy H-mm"),
		
		
		new SimpleDateFormat("yyyy-MM-d H:mm"),
		new SimpleDateFormat("yyyy.MM.d H:mm"),
		new SimpleDateFormat("d/MM/yyyy H:mm"),
		
		new SimpleDateFormat("yyyy-MM-d H.mm"),
		new SimpleDateFormat("yyyy.MM.d H.mm"),
		new SimpleDateFormat("d/MM/yyyy H.mm"),
		
		new SimpleDateFormat("yyyy-MM-d H-mm"),
		new SimpleDateFormat("yyyy.MM.d H-mm"),
		new SimpleDateFormat("d/MM/yyyy H-mm"),
		
		
		new SimpleDateFormat("yyyy-M-dd HH:m"),
		new SimpleDateFormat("yyyy.M.dd HH:m"),
		new SimpleDateFormat("dd/M/yyyy HH:m"),
		
		new SimpleDateFormat("yyyy-M-dd HH.m"),
		new SimpleDateFormat("yyyy.M.dd HH.m"),
		new SimpleDateFormat("dd/M/yyyy HH.m"),
		
		new SimpleDateFormat("yyyy-M-dd HH-m"),
		new SimpleDateFormat("yyyy.M.dd HH-m"),
		new SimpleDateFormat("dd/M/yyyy HH-m"),
		
		
		new SimpleDateFormat("yyyy-MM-d HH:m"),
		new SimpleDateFormat("yyyy.MM.d HH:m"),
		new SimpleDateFormat("d/MM/yyyy HH:m"),
		
		new SimpleDateFormat("yyyy-MM-d HH.m"),
		new SimpleDateFormat("yyyy.MM.d HH.m"),
		new SimpleDateFormat("d/MM/yyyy HH.m"),
		
		new SimpleDateFormat("yyyy-MM-d HH-m"),
		new SimpleDateFormat("yyyy.MM.d HH-m"),
		new SimpleDateFormat("d/MM/yyyy HH-m")
	};
	
	
	
	private SimpleDateFormat[] sdf13 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyyMMdd HHmm"),
		
		new SimpleDateFormat("yyyy-MM-dd HH"),
		new SimpleDateFormat("yyyy.MM.dd HH"),
		new SimpleDateFormat("dd/MM/yyyy HH"),
		
		
		new SimpleDateFormat("yyyy-M-d H:mm"),
		new SimpleDateFormat("yyyy.M.d H:mm"),
		new SimpleDateFormat("d/M/yyyy H:mm"),
		
		new SimpleDateFormat("yyyy-M-d HH:m"),
		new SimpleDateFormat("yyyy.M.d HH:m"),
		new SimpleDateFormat("d/M/yyyy HH:m"),
		
		
		new SimpleDateFormat("yyyy-M-dd H:m"),
		new SimpleDateFormat("yyyy.M.dd H:m"),
		new SimpleDateFormat("dd/M/yyyy H:m"),
		
		new SimpleDateFormat("yyyy-MM-d H:m"),
		new SimpleDateFormat("yyyy.MM.d H:m"),
		new SimpleDateFormat("d/MM/yyyy H:m")
	};
	
	
	
	private SimpleDateFormat[] sdf12 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy-M-dd HH"),
		new SimpleDateFormat("yyyy.M.dd HH"),
		new SimpleDateFormat("dd/M/yyyy HH"),
		
		new SimpleDateFormat("yyyy-MM-d HH"),
		new SimpleDateFormat("yyyy.MM.d HH"),
		new SimpleDateFormat("d/MM/yyyy HH"),
		
		
		new SimpleDateFormat("yyyy-MM-dd H"),
		new SimpleDateFormat("yyyy.MM.dd H"),
		new SimpleDateFormat("dd/MM/yyyy H"),
		
		
		new SimpleDateFormat("yyyy-M-d H:m"),
		new SimpleDateFormat("yyyy.M.d H:m"),
		new SimpleDateFormat("d/M/yyyy H:m")
	};
	
	
	
	private SimpleDateFormat[] sdf11 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyyMMdd HH"),
		
		new SimpleDateFormat("yyyy-M-d HH"),
		new SimpleDateFormat("yyyy.M.d HH"),
		new SimpleDateFormat("d/M/yyyy HH"),
		
		new SimpleDateFormat("yyyy-M-dd H"),
		new SimpleDateFormat("yyyy.M.dd H"),
		new SimpleDateFormat("dd/M/yyyy H"),
		
		new SimpleDateFormat("yyyy-MM-d H"),
		new SimpleDateFormat("yyyy.MM.d H"),
		new SimpleDateFormat("d/MM/yyyy H")
	};
	
	
	
	private SimpleDateFormat[] sdf10 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy-MM-dd"),
		new SimpleDateFormat("yyyy.MM.dd"),
		new SimpleDateFormat("yyyy MM dd"),
		new SimpleDateFormat("dd/MM/yyyy"),
		new SimpleDateFormat("dd MM yyyy"),
		
		new SimpleDateFormat("yyyyMMdd H"),
		new SimpleDateFormat("yyyy-M-d H"),
		new SimpleDateFormat("yyyy.M.d H"),
		new SimpleDateFormat("d/M/yyyy H")
	};
	
	
	
	private SimpleDateFormat[] sdf9 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("dd M yyyy"),
		new SimpleDateFormat("d MM yyyy"),
		new SimpleDateFormat("yyyy MM d"),
		new SimpleDateFormat("yyyy M dd"),
		
		new SimpleDateFormat("yyyy-M-dd"),
		new SimpleDateFormat("yyyy-MM-d"),
		
		new SimpleDateFormat("yyyy.M.dd"),
		new SimpleDateFormat("yyyy.MM.d"),
		
		new SimpleDateFormat("dd/M/yyyy"),
		new SimpleDateFormat("d/MM/yyyy")
	};
	
	
	
	private SimpleDateFormat[] sdf8 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyyMMdd"),
		new SimpleDateFormat("yy-MM-dd"),
		new SimpleDateFormat("yy.MM.dd"),
		new SimpleDateFormat("dd/MM/yy")
	};
	
	
	
	private SimpleDateFormat[] sdf7 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy MM"),
		new SimpleDateFormat("MM yyyy"),
		
		new SimpleDateFormat("yyyy-MM"),
		new SimpleDateFormat("yy-MM-d"),
		new SimpleDateFormat("yy-M-dd"),
		
		new SimpleDateFormat("yyyy.MM"),
		new SimpleDateFormat("yy.MM.d"),
		new SimpleDateFormat("yy.M.dd"),
		
		new SimpleDateFormat("MM/yyyy"),
		new SimpleDateFormat("d/MM/yy"),
		new SimpleDateFormat("dd/M/yy")
	};
	
	
	
	private SimpleDateFormat[] sdf6 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy M"),
		new SimpleDateFormat("M yyyy"),
		
		new SimpleDateFormat("yyyy-M"),
		new SimpleDateFormat("yy-M-d"),
		
		new SimpleDateFormat("yyyy.M"),
		new SimpleDateFormat("yy.M.d"),
		
		new SimpleDateFormat("M/yyyy"),
		new SimpleDateFormat("d/M/yy")
	};
	
	
	
	private SimpleDateFormat[] sdf4 = new SimpleDateFormat[]{
		new SimpleDateFormat("yyyy")
	};
}