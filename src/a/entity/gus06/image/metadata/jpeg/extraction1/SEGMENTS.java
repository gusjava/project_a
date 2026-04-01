package a.entity.gus06.image.metadata.jpeg.extraction1;

public class SEGMENTS {

	/** APP0 Jpeg segment identifier -- Jfif data. */
	public static final byte SEGMENT_APP0 = (byte)0xE0;
	/** APP1 Jpeg segment identifier -- where Exif data is kept. */
	public static final byte SEGMENT_APP1 = (byte)0xE1;
	/** APP2 Jpeg segment identifier. */
	public static final byte SEGMENT_APP2 = (byte)0xE2;
	/** APP3 Jpeg segment identifier. */
	public static final byte SEGMENT_APP3 = (byte)0xE3;
	/** APP4 Jpeg segment identifier. */
	public static final byte SEGMENT_APP4 = (byte)0xE4;
	/** APP5 Jpeg segment identifier. */
	public static final byte SEGMENT_APP5 = (byte)0xE5;
	/** APP6 Jpeg segment identifier. */
	public static final byte SEGMENT_APP6 = (byte)0xE6;
	/** APP7 Jpeg segment identifier. */
	public static final byte SEGMENT_APP7 = (byte)0xE7;
	/** APP8 Jpeg segment identifier. */
	public static final byte SEGMENT_APP8 = (byte)0xE8;
	/** APP9 Jpeg segment identifier. */
	public static final byte SEGMENT_APP9 = (byte)0xE9;
	/** APPA Jpeg segment identifier -- can hold Unicode comments. */
	public static final byte SEGMENT_APPA = (byte)0xEA;
	/** APPB Jpeg segment identifier. */
	public static final byte SEGMENT_APPB = (byte)0xEB;
	/** APPC Jpeg segment identifier. */
	public static final byte SEGMENT_APPC = (byte)0xEC;
	/** APPD Jpeg segment identifier -- IPTC data in here. */
	public static final byte SEGMENT_APPD = (byte)0xED;
	/** APPE Jpeg segment identifier. */
	public static final byte SEGMENT_APPE = (byte)0xEE;
	/** APPF Jpeg segment identifier. */
	public static final byte SEGMENT_APPF = (byte)0xEF;
	/** Start Of Image segment identifier. */
	public static final byte SEGMENT_SOI = (byte)0xD8;
	/** Define Quantization Table segment identifier. */
	public static final byte SEGMENT_DQT = (byte)0xDB;
	/** Define Huffman Table segment identifier. */
	public static final byte SEGMENT_DHT = (byte)0xC4;
	/** Start-of-Frame Zero segment identifier. */
	public static final byte SEGMENT_SOF0 = (byte)0xC0;
	/** Jpeg comment segment identifier. */
	public static final byte SEGMENT_COM = (byte)0xFE;

}
