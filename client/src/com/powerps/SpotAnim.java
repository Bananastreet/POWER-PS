package com.powerps;

public final class SpotAnim {

	public static void unpackConfig(StreamLoader streamLoader) {
		Stream stream = new Stream(streamLoader.getDataForName("spotanim.dat"));
		int length = stream.readUnsignedWord();
		if (cache == null)
			cache = new SpotAnim[length];
		for (int j = 0; j < length; j++) {
			if (cache[j] == null)
				cache[j] = new SpotAnim();
			cache[j].anInt404 = j;
			cache[j].readValues(stream);
			// model file: 13289
			if (j == 2034) {
				cache[2034].anInt405 = 13289;
				cache[2034].anInt406 = 15449;
				cache[2034].aAnimation_407 = Animation.anims[15449];
			}

			// model file: 13289
			if (j == 2035) {
				cache[2035].anInt405 = 13289;
				cache[2035].anInt406 = 15450;
				cache[2035].aAnimation_407 = Animation.anims[15450];
			}

			// model file: 13289
			if (j == 2036) {
				cache[2036].anInt405 = 13289;
				cache[2036].anInt406 = 15451;
				cache[2036].aAnimation_407 = Animation.anims[15451];
			}

			if (j == 1247) {
				cache[j].anInt405 = 60776;// modelId
				cache[j].anInt406 = 4001;// animId
				cache[j].aAnimation_407 = Animation.anims[4001];// 4001->animId
			}
			if (j == 1248) {
				cache[j].anInt405 = 60776;// modelId
				cache[j].anInt406 = 4002;// animId
				cache[j].aAnimation_407 = Animation.anims[4002];// 4002->animId
			}
		}

	}

	/*
	 * public void leechModel(int i2) { System.out.print((new
	 * StringBuilder()).append("Attempting coping model ").append(i2).append("...").
	 * toString()); try { DataOutputStream out = new DataOutputStream(new
	 * FileOutputStream((new
	 * StringBuilder()).append("C:/Users/EricW/Desktop/webclient/models/").append(i2
	 * ).append(".dat").toString())); out.write(FileOperations.ReadFile((new
	 * StringBuilder()).
	 * append("G:/Users/RCW/Desktop/rsps/601 project/Revision 1 -BK/cache/models/").
	 * append(i2).append(".mdl").toString())); out.flush(); out.close(); }
	 * catch(Exception e) { } System.out.println("COMPLETED"); }
	 */

	private void readValues(Stream stream) {
		anInt406 = stream.readUnsignedWord();
		anInt405 = stream.readUnsignedWord();
		if (Animation.anims != null && anInt406 != 65535 && anInt406 != -1)
			aAnimation_407 = Animation.anims[anInt406];
		anInt410 = stream.readUnsignedByte();
		anInt411 = stream.readUnsignedByte();
		int j = stream.readUnsignedWord();
		if (j != 65535) {
			for (int k = 0; k < j; k++)
				anIntArray409[k] = stream.readUnsignedWord();

			for (int k = 0; k < j; k++)
				anIntArray408[k] = stream.readUnsignedWord();

		}
	}

	private static int getCorrectColours(int i) {
		String s = Integer.toHexString(i).toUpperCase();
		String str;
		if (s.length() > 4)
			str = s.substring(4);
		else
			str = s;
		int i2 = Integer.parseInt(str, 16);
		return i2;
	}

	public Model getModel() {
		Model model = (Model) aMRUNodes_415.insertFromCache(anInt404);
		if (model != null)
			return model;
		model = Model.method462(anInt405);
		if (model == null)
			return null;
		for (int i = 0; i < 6; i++)
			if (anIntArray408[0] != 0)
				model.method476(anIntArray408[i], anIntArray409[i]);

		aMRUNodes_415.removeFromCache(model, anInt404);
		return model;
	}

	private SpotAnim() {
		anInt406 = -1;
		anInt410 = 128;
		anInt411 = 128;
	}

	private final int anInt400 = 9;
	public static SpotAnim cache[];
	private int anInt404;
	private int anInt405;
	private int anInt406;
	public Animation aAnimation_407;
	private final int anIntArray408[] = new int[6];
	private final int anIntArray409[] = new int[6];
	public int anInt410;
	public int anInt411;
	public int anInt412;
	public int anInt413;
	public int anInt414;
	public static MRUNodes aMRUNodes_415 = new MRUNodes(30);

}
