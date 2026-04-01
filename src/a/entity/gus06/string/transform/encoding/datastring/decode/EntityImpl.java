package a.entity.gus06.string.transform.encoding.datastring.decode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}



	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s==null) return null;
		StringBuffer b = new StringBuffer();
		
		int state = 0;
		StringBuffer hexCode = new StringBuffer(4);
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(state==0)
			{
				if(c=='\\') state=1;
				else b.append(c);
			}
			else if(state==1)
			{
				switch(c)
				{
				case 't':b.append('\t');state=0;break;
				case 'n':b.append('\n');state=0;break;
				case 'f':b.append('\f');state=0;break;
				case 'r':b.append('\r');state=0;break;
				case '"':b.append('"');state=0;break;
				case '\\':b.append('\\');state=0;break;
				case 'u':state=2;break;
				default:b.append("\\"+c);state=0;
				}
			}
			else if(state==2)
			{
				hexCode.append(c);
				if(hexCode.length()==4)
				{
					int codePoint = Integer.parseInt(hexCode.toString(),16);
					b.append(Character.toChars(codePoint));
					hexCode = new StringBuffer(4);
					state=0;
				}
			}
		}
		return b.toString();
	}


}
