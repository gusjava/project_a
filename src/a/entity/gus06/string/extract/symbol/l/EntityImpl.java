package a.entity.gus06.string.extract.symbol.l;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170601";}


	private Service symbols;
	private String symbolsStr;


	public EntityImpl() throws Exception
	{
		symbols = Outside.service(this,"gus06.data.character.symbols1");
		symbolsStr = (String) symbols.g();
	}



	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		
		String r = null;
		for(int i=0;i<text.length();i++)
		{
			String c = ""+text.charAt(i);
			if(symbolsStr.indexOf(c)>=0) r = c;
		}
		return r;
	}
}
