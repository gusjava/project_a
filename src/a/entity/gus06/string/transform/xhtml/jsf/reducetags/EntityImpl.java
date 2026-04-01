package a.entity.gus06.string.transform.xhtml.jsf.reducetags;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201203";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		s = s.replaceAll("<i((\\s[^>]+)?)></i>","<i$1/>");
		s = s.replaceAll("<script((\\s[^>]+)?)></script>","<script$1/>");
		
		s = s.replaceAll("<ui:([^\\s>]+)([^>]*)></ui:\\1>","<ui:$1$2/>");
		s = s.replaceAll("<f:([^\\s>]+)([^>]*)></ui:\\1>","<f:$1$2/>");
		s = s.replaceAll("<p:([^\\s>]+)([^>]*)></ui:\\1>","<p:$1$2/>");
		s = s.replaceAll("<h:([^\\s>]+)([^>]*)></ui:\\1>","<h:$1$2/>");
		
		return s;
	}
}