package a.entity.gus06.string.transform.encoding.datastring.encode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s==null) return null;
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			int x = s.codePointAt(i);
			
			switch(x)
			{
			case 9: b.append("\\t");break;
			case 10: b.append("\\n");break;
			case 12: b.append("\\f");break;
			case 13: b.append("\\r");break;
			case 34: b.append("\\\"");break;
			case 92: b.append("\\\\");break;
			
			default:
				if(x<=31 || x>=127)
				{
					String hexa = Integer.toHexString(x);
					while(hexa.length()<4) hexa="0"+hexa;
					b.append("\\u"+hexa);
				}
				else b.append(s.charAt(i));
			}
		}
		return b.toString();
	}
}