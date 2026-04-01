package a.entity.gus06.web.allocine.api.movie.profile.small;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200109";}
	
	public static final String PROFILE = "small";
	

	private Service apiMovie;

	public EntityImpl() throws Exception
	{
		apiMovie = Outside.service(this,"gus06.web.allocine.api.movie");
	}

	public Object t(Object obj) throws Exception
	{
		String code = (String) obj;
		return apiMovie.t(new String[]{code,PROFILE});
	}
}
