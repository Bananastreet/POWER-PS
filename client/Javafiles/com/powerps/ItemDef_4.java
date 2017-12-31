package com.powerps;

public class ItemDef_4 {

	private ItemDef_4() {
	}

	public static ItemDef itemDef(int i, ItemDef itemdef) {

		if (i == 15078) {
			itemdef.name = "Ring of Morphing";
			itemdef.description = "Wear this and operate it and select an NPC to transform into";
			itemdef.itemActions[1] = "Equip";
			itemdef.modelID = 5163;
			itemdef.modelZoom = 350;
			itemdef.modelRotation1 = 322;
			itemdef.modelRotation2 = 135;
			itemdef.modelOffset1 = 5;
			itemdef.modelOffset2 = 1;
		}
		/*
		 * testing out rdc
		 */
		if (i == 6528) { // obby maul
			// itemdef.almighty = 31300; //ice
			itemdef.almighty2 = 255782;
			itemdef.maleEquip1 = 9344;
			itemdef.femaleEquip1 = 9344;
		}
		if (i == 13896) {
			// itemdef.almighty = 31300; //ice //statius helmet
			itemdef.almighty2 = 255782;
			itemdef.maleEquip1 = 42639;
			itemdef.femaleEquip1 = 42655;
		}
		if (i == 13884) {
			// itemdef.almighty = 31300; //ice //statius plate
			itemdef.almighty2 = 255782;
			itemdef.maleEquip1 = 42625;
			itemdef.femaleEquip1 = 42641;
		}
		if (i == 13890) {
			// itemdef.almighty = 31300; //ice //statius legs
			itemdef.almighty2 = 255782;
			itemdef.maleEquip1 = 42632;
			itemdef.femaleEquip1 = 42647;
		}

		// dragon + brutal whip + dragon kite
		if (i == 19931) { // Brutal whip
			itemdef.almighty2 = 58333;
			itemdef.maleEquip1 = 19587;
			itemdef.femaleEquip1 = 19587;
		}
		if (i == 11335) { // dragon helm
			itemdef.almighty2 = 58333;
			itemdef.maleEquip1 = 26632;
			itemdef.femaleEquip1 = 26658;
		}
		if (i == 14479) { // dragon plate
			itemdef.almighty2 = 58333;
			itemdef.maleEquip1 = 40207;
			itemdef.femaleEquip1 = 40427;
		}
		if (i == 4087) { // dragon plate legs
			itemdef.almighty2 = 58333;
			itemdef.maleEquip1 = 5024;
			itemdef.femaleEquip1 = 5025;
		}
		if (i == 5184) { // dragon kite
			itemdef.almighty2 = 58333;
			itemdef.maleEquip1 = 13700;
			itemdef.femaleEquip1 = 13700;
		}
		// end

		switch (itemdef.id) {
		// bluelightning set
		case 19063:
			itemdef.modelID = 64016;
			itemdef.name = "@bla@Blue Lightning full helm@bla@";
			itemdef.description = "Tzhaar full helm.";
			itemdef.modelZoom = 800;
			itemdef.modelRotation1 = 406;
			itemdef.modelRotation2 = 2041;
			itemdef.modelOffset1 = 0;
			itemdef.modelOffset2 = -4;
			itemdef.maleEquip1 = 64017;
			itemdef.femaleEquip1 = 64017;
			itemdef.groundActions = new String[5];
			itemdef.groundActions[2] = "Take";
			itemdef.itemActions = new String[5];
			itemdef.itemActions[1] = "Wear";
			itemdef.itemActions[2] = "Check-charges";
			itemdef.itemActions[4] = "Drop";
			break;
		case 19062:
			itemdef.modelID = 64008;
			itemdef.name = "@bla@BlueLightning platebody@bla@";
			itemdef.description = "Tzhaar Platebody.";
			itemdef.modelZoom = 1513;
			itemdef.modelRotation1 = 566;
			itemdef.modelRotation2 = 0;
			itemdef.modelOffset1 = 1;
			itemdef.modelOffset2 = -8;
			itemdef.maleEquip1 = 64009;
			itemdef.femaleEquip1 = 64009;
			itemdef.groundActions = new String[5];
			itemdef.groundActions[2] = "Take";
			itemdef.itemActions = new String[5];
			itemdef.itemActions[1] = "Wear";
			itemdef.itemActions[2] = "Check-charges";
			itemdef.itemActions[4] = "Drop";
			break;
		case 19061:
			itemdef.modelID = 64010;
			itemdef.name = "@bla@BlueLightning platelegs@bla@";
			itemdef.description = "Tzhaar platelegs.";
			itemdef.modelZoom = 1550;
			itemdef.modelRotation1 = 406;
			itemdef.modelRotation2 = 2041;
			itemdef.modelOffset1 = 5;
			itemdef.modelOffset2 = 11;
			itemdef.maleEquip1 = 64011;
			itemdef.femaleEquip1 = 64011;
			itemdef.groundActions = new String[5];
			itemdef.groundActions[2] = "Take";
			itemdef.itemActions = new String[5];
			itemdef.itemActions[1] = "Wear";
			itemdef.itemActions[2] = "Check-charges";
			itemdef.itemActions[4] = "Drop";
			break;
		case 14036:
			itemdef.modelID = 86152;
			itemdef.name = "@bla@Blue Lightning Sword@Bla@";
			itemdef.description = "Sword";
			itemdef.modelZoom = 2100;
			itemdef.modelRotation1 = 431;
			itemdef.modelRotation2 = 545;
			itemdef.modelOffset1 = 0;
			itemdef.modelOffset2 = 1;
			itemdef.maleEquip1 = 86152;
			itemdef.femaleEquip1 = 86152;
			itemdef.groundActions = new String[5];
			itemdef.groundActions[2] = "Take";
			itemdef.itemActions = new String[5];
			itemdef.itemActions[1] = "Wear";
			itemdef.stackable = false;
			break;
		case 19087:
			itemdef.modelID = 62775;
			itemdef.name = "@bla@Blue Lightning shield@bla@";
			itemdef.description = "Ares Shield.";
			itemdef.modelZoom = 1616;
			itemdef.modelRotation1 = 396;
			itemdef.modelRotation2 = 1050;
			itemdef.modelOffset2 = -3;
			itemdef.modelOffset1 = 4;
			itemdef.maleEquip1 = 62774;
			itemdef.femaleEquip1 = 62774;
			itemdef.groundActions = new String[5];
			itemdef.groundActions[2] = "Take";
			itemdef.itemActions = new String[5];
			itemdef.itemActions[1] = "Wear";
			itemdef.itemActions[2] = "Check-charges";
			itemdef.itemActions[4] = "Drop";
			break;
		// end
		case 17848:
			itemdef.modelID = 78021;
			itemdef.name = "Blazing Sword";
			itemdef.description = "What in the world ? You really got that ? Your amazing dude! ";
			itemdef.modelZoom = 2000;
			itemdef.modelRotation1 = 228;
			itemdef.modelRotation2 = 1985;
			itemdef.modelOffset1 = 5;
			itemdef.modelOffset2 = -55;
			// localJI.D = true;
			itemdef.maleEquip1 = 78020;
			itemdef.femaleEquip1 = 78020;
			itemdef.itemActions = new String[5];
			itemdef.itemActions[1] = "Wear";
			itemdef.stackable = false;
			break;
		case 989:
			itemdef.stackable = true;
			break;

		}
		return itemdef;
	}
}