package a.entity.gus06.image.metadata.jpeg.extraction1.exifreading.essai1;

import java.util.HashSet;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}
	
	/**
	 * The number of bytes used per format descriptor.
	 */
	private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

	/**
	 * The number of formats known.
	 */
	private static final int MAX_FORMAT_CODE = 12;

	// Format types
	// Note: Cannot use the DataFormat enumeration in the case statement that uses these tags.
	//	   Is there a better way?
	private static final int FMT_BYTE = 1;
	private static final int FMT_STRING = 2;
	private static final int FMT_USHORT = 3;
	private static final int FMT_ULONG = 4;
	private static final int FMT_URATIONAL = 5;
	private static final int FMT_SBYTE = 6;
	private static final int FMT_UNDEFINED = 7;
	private static final int FMT_SSHORT = 8;
	private static final int FMT_SLONG = 9;
	private static final int FMT_SRATIONAL = 10;
	private static final int FMT_SINGLE = 11;
	private static final int FMT_DOUBLE = 12;

	public static final int TAG_EXIF_OFFSET = 0x8769;
	public static final int TAG_INTEROP_OFFSET = 0xA005;
	public static final int TAG_GPS_INFO_OFFSET = 0x8825;
	public static final int TAG_MAKER_NOTE = 0x927C;

	public static final int TIFF_HEADER_START_OFFSET = 6;

	

	
	private byte[] data;
	private boolean isMotorollaByteOrder;
	private HashSet directories_done;
	
	


	public Object t(Object obj) throws Exception
	{
		// ANALYZE "EXIF" SEGMENT
		
		data = (byte[]) obj;
		if(data.length<14) throw new Exception("Exif data segment must contain at least 14 bytes");
		
		// h1: 0-6
		String h1 = new String(data,0,6);
		if(!h1.equals("Exif\0\0"))
			throw new Exception("Exif data segment doesn't begin with 'Exif'");
		
		
		
		// h2: 6-8
		//byteOrderIdentifier a two-character string; either "MM" for Motorolla or "II" for Intel.
		String h2 = new String(data,6,2);
		if(!h2.equals("MM") && !h2.equals("II"))
			throw new Exception("Unclear distinction between Motorola/Intel byte ordering: "+h2);
		isMotorollaByteOrder = h2.equals("MM");
		
		
		// h3: 8-10
		int h3 = get16Bits(8);
		if(h3!=0x2a) throw new Exception("Invalid Exif start - should have 0x2A at offset 8 in Exif header");
		
		
		// directories
		
		int firstDirectoryOffset = get32Bits(10)+6;
		
		// David Ekholm sent an digital camera image that has this problem
		// First directory normally starts 14 bytes in -- try it here and catch another error in the worst case
		if(firstDirectoryOffset >= data.length-1) firstDirectoryOffset = 14;
		

		directories_done = new HashSet();

		// 0th IFD (we merge with Exif IFD)
		processDirectory(firstDirectoryOffset);

		// after the extraction process, if we have the correct tags, we may be able to store thumbnail information
		//storeThumbnailBytes(directory, tiffHeaderOffset);
		
		
		return null;
	}

	
	
   
	
	
	/**
	 * Process one of the nested Tiff IFD directories.
	 * 2 bytes: number of tags
	 * for each tag
	 *   2 bytes: tag type
	 *   2 bytes: format code
	 *   4 bytes: component count
	 * @throws Exception 
	 */
	private void processDirectory(int dirStartOffset) throws Exception
	{
		// check for directories we've already visited to avoid stack overflows when recursive/cyclic directory structures exist
		if(directories_done.contains(Integer.valueOf(dirStartOffset))) return;
		directories_done.add(Integer.valueOf(dirStartOffset));

		if (dirStartOffset>=data.length || dirStartOffset<0) {
			//directory.addError("Ignored directory marked to start outside data segement");
			return;
		}

		if (!isDirectoryLengthValid(dirStartOffset, TIFF_HEADER_START_OFFSET)) {
			//directory.addError("Illegally sized directory");
			return;
		}

		// First two bytes in the IFD are the number of tags in this directory
		int dirTagCount = get16Bits(dirStartOffset);

		// Handle each tag in this directory
		for(int tagNumber = 0; tagNumber<dirTagCount; tagNumber++)
		{
			final int tagOffset = calculateTagOffset(dirStartOffset, tagNumber);

			// 2 bytes for the tag type
			final int tagType = get16Bits(tagOffset);

			// 2 bytes for the format code
			final int formatCode = get16Bits(tagOffset + 2);
			if (formatCode<1 || formatCode>MAX_FORMAT_CODE) {
				//directory.addError("Invalid format code: " + formatCode);
				continue;
			}

			// 4 bytes dictate the number of components in this tag's data
			final int componentCount = get32Bits(tagOffset + 4);
			if (componentCount<0) {
				//directory.addError("Negative component count in EXIF");
				continue;
			}
			// each component may have more than one byte... calculate the total number of bytes
			final int byteCount = componentCount * BYTES_PER_FORMAT[formatCode];
			final int tagValueOffset = calculateTagValueOffset(byteCount, tagOffset, TIFF_HEADER_START_OFFSET);
			if (tagValueOffset<0 || tagValueOffset > data.length) {
				//directory.addError("Illegal pointer offset value in EXIF");
				continue;
			}

			// Check that this tag isn't going to allocate outside the bounds of the data array.
			// This addresses an uncommon OutOfMemoryError.
			if (byteCount < 0 || tagValueOffset + byteCount > data.length)
			{
				//directory.addError("Illegal number of bytes: " + byteCount);
				continue;
			}

			// Calculate the value as an offset for cases where the tag represents directory
			final int subdirOffset = TIFF_HEADER_START_OFFSET + get32Bits(tagValueOffset);

			switch (tagType)
			{
				case TAG_EXIF_OFFSET:
					processDirectory(subdirOffset);
					continue;
				case TAG_INTEROP_OFFSET:
					processDirectory(subdirOffset);
					continue;
				case TAG_GPS_INFO_OFFSET:
					processDirectory(subdirOffset);
					continue;
				case TAG_MAKER_NOTE:
					processMakerNote(tagValueOffset);
					continue;
				default:
					processTag(tagType, tagValueOffset, componentCount, formatCode);
					break;
			}
		}

		// at the end of each IFD is an optional link to the next IFD
		final int finalTagOffset = calculateTagOffset(dirStartOffset, dirTagCount);
		int nextDirectoryOffset = get32Bits(finalTagOffset);
		if (nextDirectoryOffset!=0) {
			nextDirectoryOffset += TIFF_HEADER_START_OFFSET;
			if (nextDirectoryOffset>=data.length) {
				// Last 4 bytes of IFD reference another IFD with an address that is out of bounds
				// Note this could have been caused by jhead 1.3 cropping too much
				return;
			} else if (nextDirectoryOffset < dirStartOffset) {
				// Last 4 bytes of IFD reference another IFD with an address that is before the start of this directory
				return;
			}
			// the next directory is of same type as this one
			processDirectory(nextDirectoryOffset);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void processTag(int tagType, int tagValueOffset, int componentCount, int formatCode) throws Exception
	{
		// Directory simply stores raw values
		// The display side uses a Descriptor class per directory to turn the raw values into 'pretty' descriptions
		switch (formatCode)
		{
			case FMT_UNDEFINED:
				// this includes exif user comments
				final byte[] tagBytes = new byte[componentCount];
				final int byteCount = componentCount * BYTES_PER_FORMAT[formatCode];
				for (int i=0; i<byteCount; i++)
					tagBytes[i] = data[tagValueOffset + i];
				println("setByteArray("+tagType+","+t(tagBytes)+")");
				break;
			case FMT_STRING:
				println("setString("+tagType+","+readString(tagValueOffset, componentCount)+")");
				break;
			case FMT_SRATIONAL:
			case FMT_URATIONAL:
				if (componentCount==1) {
					println("Rational("+get32Bits(tagValueOffset)+","+get32Bits(tagValueOffset+4)+")");
					println("setRational("+tagType+",rational)");
				} else {
					for(int i = 0; i<componentCount; i++)
						println("Rational"+i+"("+get32Bits(tagValueOffset+(8*i))+","+get32Bits(tagValueOffset+4+(8*i))+")");
					println("setRational("+tagType+",rationals)");
				}
				break;
			case FMT_SBYTE:
			case FMT_BYTE:
				if (componentCount==1) {
					// this may need to be a byte, but I think casting to int is fine
					int b = data[tagValueOffset];
					println("setInt("+tagType+","+b+")");
				} else {
					int[] bytes = new int[componentCount];
					for (int i = 0; i<componentCount; i++)
						bytes[i] = data[tagValueOffset+i];
					println("setIntArray(tagType, bytes)");
				}
				break;
			case FMT_SINGLE:
			case FMT_DOUBLE:
				if (componentCount==1) {
					int i = data[tagValueOffset];
					println("setInt(tagType, i)");
				} else {
					int[] ints = new int[componentCount];
					for (int i = 0; i<componentCount; i++)
						ints[i] = data[tagValueOffset + i];
					println("setIntArray(tagType, ints)");
				}
				break;
			case FMT_USHORT:
			case FMT_SSHORT:
				if (componentCount==1) {
					int i = get16Bits(tagValueOffset);
					println("setInt(tagType, i)");
				} else {
					int[] ints = new int[componentCount];
					for (int i = 0; i<componentCount; i++)
						ints[i] = get16Bits(tagValueOffset + (i * 2));
					println("setIntArray(tagType, ints)");
				}
				break;
			case FMT_SLONG:
			case FMT_ULONG:
				if (componentCount==1) {
					int i = get32Bits(tagValueOffset);
					println("setInt(tagType, i)");
				} else {
					int[] ints = new int[componentCount];
					for (int i = 0; i<componentCount; i++)
						ints[i] = get32Bits(tagValueOffset + (i * 4));
					println("setIntArray(tagType, ints)");
				}
				break;
			default:
				//directory.addError("Unknown format code " + formatCode + " for tag " + tagType);
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void processMakerNote(int subdirOffset)
	{
		// Determine the camera model and makernote format
		

//		String cameraModel = exifDirectory.getString(ExifDirectory.TAG_MAKE);
//		final String firstTwoChars = new String(data, subdirOffset, 2);
//		final String firstThreeChars = new String(data, subdirOffset, 3);
//		final String firstFourChars = new String(data, subdirOffset, 4);
//		final String firstFiveChars = new String(data, subdirOffset, 5);
//		final String firstSixChars = new String(data, subdirOffset, 6);
//		final String firstSevenChars = new String(data, subdirOffset, 7);
//		final String firstEightChars = new String(data, subdirOffset, 8);
//		if ("OLYMP".equals(firstFiveChars) || "EPSON".equals(firstFiveChars) || "AGFA".equals(firstFourChars))
//		{
//			// Olympus Makernote
//			// Epson and Agfa use Olypus maker note standard, see:
//			//	 http://www.ozhiker.com/electronics/pjmt/jpeg_info/
//			processDirectory(_metadata.getDirectory(OlympusMakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 8, tiffHeaderOffset);
//		}
//		else if (cameraModel!=null && cameraModel.trim().toUpperCase().startsWith("NIKON"))
//		{
//			if ("Nikon".equals(firstFiveChars))
//			{
//				/* There are two scenarios here:
//				 * Type 1:				  **
//				 * :0000: 4E 69 6B 6F 6E 00 01 00-05 00 02 00 02 00 06 00 Nikon...........
//				 * :0010: 00 00 EC 02 00 00 03 00-03 00 01 00 00 00 06 00 ................
//				 * Type 3:				  **
//				 * :0000: 4E 69 6B 6F 6E 00 02 00-00 00 4D 4D 00 2A 00 00 Nikon....MM.*...
//				 * :0010: 00 08 00 1E 00 01 00 07-00 00 00 04 30 32 30 30 ............0200
//				 */
//				if (data[subdirOffset+6]==1)
//					processDirectory(_metadata.getDirectory(NikonType1MakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 8, tiffHeaderOffset);
//				else if (data[subdirOffset+6]==2)
//					processDirectory(_metadata.getDirectory(NikonType2MakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 18, subdirOffset + 10);
//				else
//					exifDirectory.addError("Unsupported makernote data ignored.");
//			}
//			else
//			{
//				// The IFD begins with the first MakerNote byte (no ASCII name).  This occurs with CoolPix 775, E990 and D1 models.
//				processDirectory(_metadata.getDirectory(NikonType2MakernoteDirectory.class), processedDirectoryOffsets, subdirOffset, tiffHeaderOffset);
//			}
//		}
//		else if ("SONY CAM".equals(firstEightChars) || "SONY DSC".equals(firstEightChars))
//		{
//			processDirectory(_metadata.getDirectory(SonyMakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 12, tiffHeaderOffset);
//		}
//		else if ("KDK".equals(firstThreeChars))
//		{
//			processDirectory(_metadata.getDirectory(KodakMakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 20, tiffHeaderOffset);
//		}
//		else if ("Canon".equalsIgnoreCase(cameraModel))
//		{
//			processDirectory(_metadata.getDirectory(CanonMakernoteDirectory.class), processedDirectoryOffsets, subdirOffset, tiffHeaderOffset);
//		}
//		else if (cameraModel!=null && cameraModel.toUpperCase().startsWith("CASIO"))
//		{
//			if ("QVC\u0000\u0000\u0000".equals(firstSixChars))
//				processDirectory(_metadata.getDirectory(CasioType2MakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 6, tiffHeaderOffset);
//			else
//				processDirectory(_metadata.getDirectory(CasioType1MakernoteDirectory.class), processedDirectoryOffsets, subdirOffset, tiffHeaderOffset);
//		}
//		else if ("FUJIFILM".equals(firstEightChars) || "Fujifilm".equalsIgnoreCase(cameraModel))
//		{
//			// TODO make this field a passed parameter, to avoid threading issues
//			boolean byteOrderBefore = isMotorollaByteOrder;
//			// bug in fujifilm makernote ifd means we temporarily use Intel byte ordering
//			isMotorollaByteOrder = false;
//			// the 4 bytes after "FUJIFILM" in the makernote point to the start of the makernote
//			// IFD, though the offset is relative to the start of the makernote, not the TIFF
//			// header (like everywhere else)
//			int ifdStart = subdirOffset + get32Bits(subdirOffset + 8);
//			processDirectory(_metadata.getDirectory(FujifilmMakernoteDirectory.class), processedDirectoryOffsets, ifdStart, tiffHeaderOffset);
//			isMotorollaByteOrder = byteOrderBefore;
//		}
//		else if (cameraModel!=null && cameraModel.toUpperCase().startsWith("MINOLTA"))
//		{
//			// Cases seen with the model starting with MINOLTA in capitals seem to have a valid Olympus makernote
//			// area that commences immediately.
//			processDirectory(_metadata.getDirectory(OlympusMakernoteDirectory.class), processedDirectoryOffsets, subdirOffset, tiffHeaderOffset);
//		}
//		else if ("KC".equals(firstTwoChars) || "MINOL".equals(firstFiveChars) || "MLY".equals(firstThreeChars) || "+M+M+M+M".equals(firstEightChars))
//		{
//			// This Konica data is not understood.  Header identified in accordance with information at this site:
//			// http://www.ozhiker.com/electronics/pjmt/jpeg_info/minolta_mn.html
//			// TODO determine how to process the information described at the above website
//			exifDirectory.addError("Unsupported Konica/Minolta data ignored.");
//		}
//		else if ("KYOCERA".equals(firstSevenChars))
//		{
//			// http://www.ozhiker.com/electronics/pjmt/jpeg_info/kyocera_mn.html
//			processDirectory(_metadata.getDirectory(KyoceraMakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 22, tiffHeaderOffset);
//		}
//		else if ("Panasonic\u0000\u0000\u0000".equals(new String(_data, subdirOffset, 12)))
//		{
//			// NON-Standard TIFF IFD Data using Panasonic Tags. There is no Next-IFD pointer after the IFD
//			// Offsets are relative to the start of the TIFF header at the beginning of the EXIF segment
//			// more information here: http://www.ozhiker.com/electronics/pjmt/jpeg_info/panasonic_mn.html
//			processDirectory(_metadata.getDirectory(PanasonicMakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 12, tiffHeaderOffset);
//		}
//		else if ("AOC\u0000".equals(firstFourChars))
//		{
//			// NON-Standard TIFF IFD Data using Casio Type 2 Tags
//			// IFD has no Next-IFD pointer at end of IFD, and
//			// Offsets are relative to the start of the current IFD tag, not the TIFF header
//			// Observed for:
//			// - Pentax ist D
//			processDirectory(_metadata.getDirectory(CasioType2MakernoteDirectory.class), processedDirectoryOffsets, subdirOffset + 6, subdirOffset);
//		}
//		else if (cameraModel!=null && (cameraModel.toUpperCase().startsWith("PENTAX") || cameraModel.toUpperCase().startsWith("ASAHI")))
//		{
//			// NON-Standard TIFF IFD Data using Pentax Tags
//			// IFD has no Next-IFD pointer at end of IFD, and
//			// Offsets are relative to the start of the current IFD tag, not the TIFF header
//			// Observed for:
//			// - PENTAX Optio 330
//			// - PENTAX Optio 430
//			processDirectory(_metadata.getDirectory(PentaxMakernoteDirectory.class), processedDirectoryOffsets, subdirOffset, subdirOffset);
//		}
//		else
//		{
//			// TODO how to store makernote data when it's not from a supported camera model?
//			// this is difficult as the starting offset is not known.  we could look for it...
//			exifDirectory.addError("Unsupported makernote data ignored.");
//		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Get a 16 bit value from file's native byte order.  Between 0x0000 and 0xFFFF.
	 */
	private int get16Bits(int offset) throws Exception
	{
		if(offset<0 || offset+2>data.length)
			throw new Exception("attempt to read data outside of exif segment (index " + offset + " where max index is " + (data.length - 1) + ")");

		if(isMotorollaByteOrder)// Motorola - MSB first
			return (data[offset] << 8 & 0xFF00) | (data[offset+1] & 0xFF);
		// Intel ordering - LSB first
		return (data[offset + 1] << 8 & 0xFF00) | (data[offset] & 0xFF);
	}
	
	
	
	/**
	 * Get a 32 bit value from file's native byte order.
	 */
	private int get32Bits(int offset) throws Exception
	{
		if (offset<0 || offset+4>data.length)
			throw new ArrayIndexOutOfBoundsException("attempt to read data outside of exif segment (index " + offset + " where max index is " + (data.length - 1) + ")");

		if (isMotorollaByteOrder) {
			// Motorola - MSB first
			return (data[offset] << 24 & 0xFF000000) |
					(data[offset + 1] << 16 & 0xFF0000) |
					(data[offset + 2] << 8 & 0xFF00) |
					(data[offset + 3] & 0xFF);
		} else {
			// Intel ordering - LSB first
			return (data[offset + 3] << 24 & 0xFF000000) |
					(data[offset + 2] << 16 & 0xFF0000) |
					(data[offset + 1] << 8 & 0xFF00) |
					(data[offset] & 0xFF);
		}
	}
	
	
	
	
	
	
	private boolean isDirectoryLengthValid(int dirStartOffset, int tiffHeaderOffset) throws Exception
	{
		int dirTagCount = get16Bits(dirStartOffset);
		int dirLength = (2 + (12 * dirTagCount) + 4);
		if (dirLength + dirStartOffset + tiffHeaderOffset>=data.length) {
			// Note: Files that had thumbnails trimmed with jhead 1.3 or earlier might trigger this
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	/**
	 * Determine the offset at which a given InteropArray entry begins within the specified IFD.
	 * @param dirStartOffset the offset at which the IFD starts
	 * @param entryNumber the zero-based entry number
	 */
	private int calculateTagOffset(int dirStartOffset, int entryNumber)
	{
		// add 2 bytes for the tag count
		// each entry is 12 bytes, so we skip 12 * the number seen so far
		return dirStartOffset + 2 + (12 * entryNumber);
	}
	
	
	
	
	
	
	private int calculateTagValueOffset(int byteCount, int dirEntryOffset, int tiffHeaderOffset) throws Exception
	{
		if (byteCount>4) {
			// If its bigger than 4 bytes, the dir entry contains an offset.
			// dirEntryOffset must be passed, as some makernote implementations (e.g. FujiFilm) incorrectly use an
			// offset relative to the start of the makernote itself, not the TIFF segment.
			final int offsetVal = get32Bits(dirEntryOffset + 8);
			if (offsetVal + byteCount>data.length) {
				// Bogus pointer offset and / or bytecount value
				return -1; // signal error
			}
			return tiffHeaderOffset + offsetVal;
		} else {
			// 4 bytes or less and value is in the dir entry itself
			return dirEntryOffset + 8;
		}
	}
	
	
	
	
	
	
	
	/**
	 * Creates a String from the _data buffer starting at the specified offset,
	 * and ending where byte=='\0' or where length==maxLength.
	 */
	private String readString(int offset, int maxLength)
	{
		int length = 0;
		while((offset + length)<data.length && data[offset + length]!='\0' && length<maxLength) 
			length++;
		return new String(data,offset,length);
	}
	
	
	
	
	
	
	
	
	
	private void println(String m)
	{System.out.println(m);}
	
	private String t(byte[] b)
	{return new String(b);}
}
