package a.entity.gus06.file.mp3.extract.prop;

import a.framework.*;
import java.io.File;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v1;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191226";}
	
	public static final String KEY_ALBUM = "album";
	public static final String KEY_ARTIST = "artist";
	public static final String KEY_TITLE = "title";
	public static final String KEY_TRACK_NB = "track_nb";
	public static final String KEY_COMMENT = "comment";
	
	public static final String KEY_GENRE = "genre";
	public static final String KEY_GENRE_CODE = "genre_code";
	public static final String KEY_IDENTIFIER = "identifier";
	public static final String KEY_YEAR = "year";



	private Service getGenres;
	private String[] genres;

	public EntityImpl() throws Exception
	{
		getGenres = Outside.service(this,"gus06.data.mp3.id3v1.genres");
		genres = (String[]) getGenres.g();
	}
	
	private String genre(int code)
	{
		if(code>=0 && code<genres.length) return genres[code];
		return null;
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map map = new HashMap();
		
		if(file==null || !file.isFile()) return map;
		MP3File mp3file = new MP3File(file);
		ID3v1 id3v1 = mp3file.getID3v1Tag();
		if(id3v1==null) return map;
		
		// ALBUM
		
		try {map.put(KEY_ALBUM,id3v1.getAlbum());}
		catch(UnsupportedOperationException e)
		{
			try {map.put(KEY_ALBUM,id3v1.getAlbumTitle());}
			catch(UnsupportedOperationException e0){}
		}
		
		// ARTIST
		
		try {map.put(KEY_ARTIST,id3v1.getArtist());}
		catch(UnsupportedOperationException e)
		{
			try {map.put(KEY_ARTIST,id3v1.getLeadArtist());}
			catch(UnsupportedOperationException e0) {}
		}
		
		// TITLE
		
		try {map.put(KEY_TITLE,id3v1.getTitle());}
		catch(UnsupportedOperationException e)
		{
			try {map.put(KEY_TITLE,id3v1.getSongTitle());}
			catch(UnsupportedOperationException e0) {}
		}
		
		// TRACK_NB
		
		try {map.put(KEY_TRACK_NB,id3v1.getTrackNumberOnAlbum());}
		catch(UnsupportedOperationException e) {}
		
		// COMMENT
		
		try {map.put(KEY_COMMENT,id3v1.getComment());}
		catch(UnsupportedOperationException e)
		{
			try {map.put(KEY_COMMENT,id3v1.getSongComment());}
			catch(UnsupportedOperationException e0) {}
		}
		
		// GENRE
		
		try
		{
			int code = id3v1.getGenre();
			String genre = genre(code);
			
			map.put(KEY_GENRE_CODE,""+code);
			if(genre!=null) map.put(KEY_GENRE,genre);
		}
		catch(UnsupportedOperationException e)
		{
			try
			{
				int code = Integer.parseInt(id3v1.getSongGenre());
				String genre = genre(code);
				
				map.put(KEY_GENRE_CODE,""+code);
				if(genre!=null) map.put(KEY_GENRE,genre);
			}
			catch(UnsupportedOperationException e0){}
		}
		
		// IDENTIFIER
		
		try {map.put(KEY_IDENTIFIER,id3v1.getIdentifier());}
		catch(UnsupportedOperationException e) {}
		
		// YEAR
		
		try {map.put(KEY_YEAR,id3v1.getYear());}
		catch(UnsupportedOperationException e)
		{
			try {map.put(KEY_YEAR,id3v1.getYearReleased());}
			catch(UnsupportedOperationException e0){}
		}
		
		return map;
	}
}