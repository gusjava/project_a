package a.entity.gus06.string.transform.format.telephone.fr;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160317";}

	
	public static final int TEL_LENGTH = 10;


	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<text.length();i++)
		{
			char c = text.charAt(i);
			if(Character.isDigit(c)) b.append(c);
		}
		
		if(b.length()!=TEL_LENGTH) return null;
		
		return b.subSequence(0,2)+
				"."+b.subSequence(2,4)+
				"."+b.subSequence(4,6)+
				"."+b.subSequence(6,8)+
				"."+b.subSequence(8,10);
	}
}
