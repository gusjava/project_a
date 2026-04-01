package a.entity.gus06.web.allocine.convert.codetomovie1;
 
import a.framework.*;
import java.util.Map;
 
public class EntityImpl implements Entity, T {
 
    public String creationDate() {return "20191024";}
     
    public static final String KEY_MOVIE = "movie";
 
 
    private Service apiMovie;
 
    public EntityImpl() throws Exception
    {
        apiMovie = Outside.service(this,"gus06.web.allocine.api.movie.profile.small");
    }
     
     
    public Object t(Object obj) throws Exception
    {
        Map map = (Map) apiMovie.t(obj);
        return map.get(KEY_MOVIE);
    }
}