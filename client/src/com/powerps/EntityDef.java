package com.powerps;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EntityDef.java

import java.io.PrintStream;

public final class EntityDef {

	public static EntityDef forID(int i) {
		for (int j = 0; j < 20; j++)
			if (cache[j].type == (long) i)
				return cache[j];

		anInt56 = (anInt56 + 1) % 20;
		EntityDef entityDef = cache[anInt56] = new EntityDef();
		stream.currentOffset = streamIndices[i];
		entityDef.type = i;
		entityDef.readValues(stream);
		if (i == 4677) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.name = "Elvarg";
			entityDef.combatLevel = 150;
		}
		int myass = 4247;
		if (i == myass) {
			entityDef.name = "Construction Manager";
			entityDef.description = "Power-Ps Official Construction Seller!".getBytes();
			entityDef.itemActions[2] = "Visit House";
			entityDef.itemActions[3] = "Buy Skillcape";
		}

		if (i == 46) {
			entityDef.name = "Megaman";
			entityDef.anIntArray94 = new int[] { 75105 };
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.combatLevel = 200;
		}
		if (i == 6691) {
			entityDef.name = "Slayer DarkBeast";
			entityDef.combatLevel = 120;
		}
		if (i == 6715) {
			entityDef.name = "Slayer Imp";
			entityDef.combatLevel = 150;
		}
		if (i == 347) {
			entityDef.name = "@gre@Weapon Guard";
			entityDef.itemActions[1] = "Attack";
			entityDef.combatLevel = 175;
		}
		if (i == 1885) {
			entityDef.name = "@yel@Gold Chest Guard";
			entityDef.itemActions[1] = "Attack";
			entityDef.description = "Gold Chest Guard.".getBytes();
			entityDef.combatLevel = 300;
		}
		if (i == 817) {
			entityDef.name = "@red@Weapon God Quest";

		}
		if (i == 746) {
			entityDef.name = "@gre@Skilling Quest";
		}
		if (i == 6689) {
			entityDef.name = "Slayer Demon";
			entityDef.combatLevel = 175;
		}
		if (i == 6724) {
			entityDef.name = "Slayer WereWolf";
			entityDef.combatLevel = 215;
		}
		if (i == 7903) {
			entityDef.name = "Power-Ps Impling";
		}

		if (i == 1699) {
			entityDef.itemActions[3] = "Achievements";
			entityDef.itemActions[2] = "Titles";
			entityDef.name = "Achievements Manager";
		}
		if (i == 250) {
			entityDef.name = "@cya@1b Ticket Whore";
		}
		if (i == 1337) {
			entityDef.name = "@cya@2b Ticket Whore";

		}
		if (i == 10040) {
			entityDef.itemActions[1] = "Attack";
			entityDef.combatLevel = 521;
		}

		if (i == 167) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35223;
			entityDef.name = "Pikachu";
			entityDef.combatLevel = 140;
			entityDef.description = "It's Pikachu.".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 168) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35222;
			entityDef.name = "Spongebob";
			entityDef.combatLevel = 170;
			entityDef.description = "It's Spongebob.".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 9922) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 86045;
			entityDef.name = "Elite Black Warrior";
			entityDef.combatLevel = 420;
			entityDef.description = "Elite Black Warrior.".getBytes();
			entityDef.standAnim = 808;
		}
		if (i == 9989) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Rape";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 40060;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "@red@Mega Present Chest";
			entityDef.combatLevel = 172;
			entityDef.description = "Present Chest.".getBytes();
		}
		if (i == 9913) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Rape";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 40060;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "Private Present";
			entityDef.combatLevel = 175;
			entityDef.description = "Present.".getBytes();
		}
		if (i == 9990) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 40060;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "@blu@Sponsor@gre@ Presenets.";
			entityDef.combatLevel = 175;
			entityDef.description = "Present Chest. Drop rate 1:250.".getBytes();
		}
		if (i == 5247) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 40955;
			entityDef.standAnim = 10056;
			entityDef.walkAnim = 10055;
			entityDef.name = "Corporeal Beast";
			entityDef.combatLevel = 785;
			entityDef.description = "A vision of supernatural horror.".getBytes();
		}
		if (i == 9955) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Kill";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 40060;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "@gre@Regular Present Chest";
			entityDef.combatLevel = 172;
			entityDef.description = "Reg Present Chest.".getBytes();
		}

		if (i == 268) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35253;
			entityDef.name = "Luigi";
			entityDef.combatLevel = 100;
			entityDef.description = "It's Luigi.".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 2642) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
		}

		if (i == 98) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35222;
			entityDef.name = "@gre@SpongeBob";
			entityDef.combatLevel = 130;
			entityDef.description = "It's SpongeBob.".getBytes();
			entityDef.standAnim = 66;
		}

		if (i == 40) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35225;
			entityDef.name = "Power-Ps Beast";
			entityDef.combatLevel = 1337;
			entityDef.description = "It's Huge!".getBytes();

		}
		if (i == 41) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35223;
			entityDef.name = "Power-Ps Wrecker";
			entityDef.combatLevel = 1400;
			entityDef.description = "It's HUGE.".getBytes();
		}

		if (i == 99) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35230;
			entityDef.name = "Squirtle";
			entityDef.combatLevel = 130;
			entityDef.description = "Squirtle :D".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 96) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35231;
			entityDef.name = "Charmeleon";
			entityDef.combatLevel = 85;
			entityDef.description = "Charmeleon".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 95) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35232;
			entityDef.name = "Onix";
			entityDef.combatLevel = 95;
			entityDef.description = "Onix".getBytes();
			entityDef.standAnim = 66;
		}

		if (i == 94) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35233;
			entityDef.name = "Ho-Oh";
			entityDef.combatLevel = 100;
			entityDef.description = "Ho-Oh".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 93) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35234;
			entityDef.name = "Mewtwo";
			entityDef.combatLevel = 120;
			entityDef.description = "Mewtwo".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 92) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35235;
			entityDef.name = "Geodude";
			entityDef.combatLevel = 130;
			entityDef.description = "Geodude".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 91) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35236;
			entityDef.name = "Articuno";
			entityDef.combatLevel = 130;
			entityDef.description = "Articuno.".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 100) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35250;
			entityDef.name = "Zapdos";
			entityDef.combatLevel = 185;
			entityDef.description = "Zapdos".getBytes();
			entityDef.standAnim = 66;
		}

		if (i == 97) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35223;
			entityDef.name = "@blu@Pikachu";
			entityDef.combatLevel = 105;
			entityDef.description = "It's Pikachu.".getBytes();
			entityDef.standAnim = 66;
		}

		if (i == 251) {
			entityDef.name = "King Milestone";
			entityDef.itemActions[2] = "Milestone";
			entityDef.description = "Power-Ps Official MilestoneCape Seller".getBytes();
		}

		if (i == 9713) {
			entityDef.itemActions[2] = "Buy Skillcape";
		}
		if (i == 666) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[0] = "Pick-up";
			entityDef.itemActions[2] = "Talk";
			entityDef.itemActions[3] = "Interact-with";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 44751;
			entityDef.name = "Tzrek-jad";
			entityDef.description = "A fearsome obsidian beast... Aww, so cute.".getBytes();
		}
		if (i == 2221) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 1;
			entityDef.readValues(stream);
			entityDef.itemActions = new String[5];
			entityDef.itemActions[0] = "Drive";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 52041;
			entityDef.name = "Car";
			entityDef.description = "Hack3r`s Pink Car LikeaBoss".getBytes();
		}
		if (i == 2586) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[2];
			entityDef.anIntArray94[0] = 62747;
			entityDef.anIntArray94[1] = 62734;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "Power-Ps Protector";
			entityDef.combatLevel = 285;
		}
		if (i == 5001) {
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 62717;
			entityDef.name = "Nex";
			entityDef.aByte68 = 3;
			entityDef.standAnim = 6320;
			entityDef.walkAnim = 6319;
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.combatLevel = 1001;
			// entityDef.headicon = 19;
		}

		if (i == 9957) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 20188;
			entityDef.name = "Dragonbone Beast";
			entityDef.combatLevel = 9999;
			entityDef.description = "Dragonbone".getBytes();
			entityDef.standAnim = 66;
		}
		if (i == 9956) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Kill";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 40059;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "@gre@Christmas Cracker";
			entityDef.combatLevel = 125;
			entityDef.description = "Christmas Cracker.".getBytes();
		}
		if (i == 7557) {
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35228;
			entityDef.name = "Mr. Krabbs";
			entityDef.standAnim = 6320;
			entityDef.walkAnim = 6319;
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Talk To";
			entityDef.itemActions[3] = "Teleport";
			// entityDef.headicon = 19;
		}
		if (i == 596) {
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 35229;
			entityDef.name = "Homer";
			entityDef.standAnim = 6320;
			entityDef.walkAnim = 6319;
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Talk To";
			entityDef.itemActions[3] = "Customs Shop";
		}
		if (i == 3919) {
			// entityDef.anIntArray94 = new int[1];
			// entityDef.anIntArray94[0] = 35229;
			entityDef.name = "Miss Prestige";
			// entityDef.standAnim = 6320;
			// entityDef.walkAnim = 6319;
			entityDef.itemActions = new String[5];
			entityDef.itemActions[0] = "Talk To";
			// entityDef.itemActions[3] = "Customs Shop";
		}

		if (i == 5006) {
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 13888;
			entityDef.name = "Ganodermic beast";
			entityDef.aByte68 = 3;
			entityDef.standAnim = 6320;
			entityDef.walkAnim = 6319;
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.combatLevel = 280;
		}
		if (i == 502) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 87205;// Npc's Cape
			entityDef.anIntArray94[1] = 87201;// Npc's Head
			entityDef.anIntArray94[2] = 87194;// Npc's Plate
			entityDef.anIntArray94[3] = 87196;// Npc's Legs
			entityDef.anIntArray94[4] = 87204;// Npc's Weapon
			entityDef.anIntArray94[5] = 87199;// Npc's Gloves
			entityDef.anIntArray94[6] = 87198;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@cya@Cloud torva";
			entityDef.combatLevel = 175;// Combat Level
			entityDef.description = "Cloud torva.".getBytes();
		}
		if (i == 9921) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 80;// Npc's Cape
			entityDef.anIntArray94[1] = 334;// Npc's Head
			entityDef.anIntArray94[2] = 333;// Npc's Plate
			entityDef.anIntArray94[3] = 335;// Npc's Legs
			entityDef.anIntArray94[4] = 69005;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Well torva";
			entityDef.combatLevel = 420;// Combat Level
			entityDef.description = "Well torva.".getBytes();
		}
		if (i == 9964) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 55042;// Npc's Cape
			entityDef.anIntArray94[1] = 75507;// Npc's Head*
			entityDef.anIntArray94[2] = 75504;// Npc's Plate*
			entityDef.anIntArray94[3] = 75506;// Npc's Legs*
			entityDef.anIntArray94[4] = 50226;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Mini@yel@Gold torva";
			entityDef.combatLevel = 700;// Combat Level
			entityDef.description = "Gold torva.".getBytes();
		}
		if (i == 9966) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 86081;// Npc's Cape
			entityDef.anIntArray94[1] = 86074;// Npc's Head*
			entityDef.anIntArray94[2] = 10521;// Npc's Plate*
			entityDef.anIntArray94[3] = 10522;// Npc's Legs*
			entityDef.anIntArray94[4] = 86152;// Npc's Weapon
			entityDef.anIntArray94[5] = 86081;// Npc's Gloves
			entityDef.anIntArray94[6] = 10520;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@cya@Sayian";
			entityDef.combatLevel = 1000;// Combat Level
			entityDef.description = "Super Sayian".getBytes();
		}
		if (i == 9984) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 31415;// Npc's Cape
			entityDef.anIntArray94[1] = 85004;// Npc's Head
			entityDef.anIntArray94[2] = 85005;// Npc's Plate
			entityDef.anIntArray94[3] = 85003;// Npc's Legs
			entityDef.anIntArray94[4] = 82961;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@gre@Troll torva";
			entityDef.combatLevel = 700;// Combat Level
			entityDef.description = "Troll torva.".getBytes();
		}
		if (i == 9983) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 16014;// Npc's Cape
			entityDef.anIntArray94[1] = 85001;// Npc's Head
			entityDef.anIntArray94[2] = 85002;// Npc's Plate
			entityDef.anIntArray94[3] = 85000;// Npc's Legs
			entityDef.anIntArray94[4] = 22;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Aram torva";
			entityDef.combatLevel = 1000;// Combat Level
			entityDef.description = "Aram torva.".getBytes();
		}
		if (i == 9982) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 5166;// Npc's Cape
			entityDef.anIntArray94[1] = 85013;// Npc's Head
			entityDef.anIntArray94[2] = 85014;// Npc's Plate
			entityDef.anIntArray94[3] = 85012;// Npc's Legs
			entityDef.anIntArray94[4] = 14010;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Brown torva";
			entityDef.combatLevel = 999;// Combat Level
			entityDef.description = "Brown torva.".getBytes();
		}
		if (i == 9981) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 83019;// Npc's Cape
			entityDef.anIntArray94[1] = 85034;// Npc's Head
			entityDef.anIntArray94[2] = 85035;// Npc's Plate
			entityDef.anIntArray94[3] = 85033;// Npc's Legs
			entityDef.anIntArray94[4] = 15351;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Boss torva";
			entityDef.combatLevel = 1100;// Combat Level
			entityDef.description = "Boss torva.".getBytes();
		}
		if (i == 9980) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 64058;// Npc's Cape
			entityDef.anIntArray94[1] = 85007;// Npc's Head
			entityDef.anIntArray94[2] = 85008;// Npc's Plate
			entityDef.anIntArray94[3] = 85006;// Npc's Legs
			entityDef.anIntArray94[4] = 64019;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Pelicans torva";
			entityDef.combatLevel = 1200;// Combat Level
			entityDef.description = "Pelicans torva.".getBytes();
		}
		if (i == 9979) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 10; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 64047;// Npc's Cape
			entityDef.anIntArray94[1] = 85010;// Npc's Head
			entityDef.anIntArray94[2] = 85011;// Npc's Plate
			entityDef.anIntArray94[3] = 85009;// Npc's Legs
			entityDef.anIntArray94[4] = 72138;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@God torva";
			entityDef.combatLevel = 1300;// Combat Level
			entityDef.description = "God torva.".getBytes();
		}
		if (i == 9967) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 86138;// Npc's Cape
			entityDef.anIntArray94[1] = 64017;// Npc's Head
			entityDef.anIntArray94[2] = 86133;// Npc's Plate
			entityDef.anIntArray94[3] = 86137;// Npc's Legs
			entityDef.anIntArray94[4] = 86053;// Npc's Weapon
			entityDef.anIntArray94[5] = 86135;// Npc's Gloves
			entityDef.anIntArray94[6] = 86134;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@WAR Boss";
			entityDef.combatLevel = 1300;// Combat Level
			entityDef.description = "WAR Boss torva.".getBytes();
		}
		if (i == 9968) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 86124;// Npc's Cape
			entityDef.anIntArray94[1] = 64017;// Npc's Head
			entityDef.anIntArray94[2] = 86119;// Npc's Plate
			entityDef.anIntArray94[3] = 86120;// Npc's Legs
			entityDef.anIntArray94[4] = 86028;// Npc's Weapon
			entityDef.anIntArray94[5] = 86122;// Npc's Gloves
			entityDef.anIntArray94[6] = 86121;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@cya@WARGOD Boss";
			entityDef.combatLevel = 5000;// Combat Level
			entityDef.description = "WARGOD Boss torva.".getBytes();
		}
		if (i == 9985) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 1; // Original model color
			entityDef.anIntArray70[0] = 1; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 75862;// Npc's Cape
			entityDef.anIntArray94[1] = 77020;// Npc's Head
			entityDef.anIntArray94[2] = 77016;// Npc's Plate
			entityDef.anIntArray94[3] = 77018;// Npc's Legs
			entityDef.anIntArray94[4] = 82854;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 1;// Npc's Stand Emote
			entityDef.name = "@red@Vortex";
			entityDef.combatLevel = 50;// Combat Level
			entityDef.description = "Vortex.".getBytes();
		}
		if (i == 503) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 87340;// Npc's Cape
			entityDef.anIntArray94[1] = 87336;// Npc's Head
			entityDef.anIntArray94[2] = 87330;// Npc's Plate
			entityDef.anIntArray94[3] = 87332;// Npc's Legs
			entityDef.anIntArray94[4] = 87339;// Npc's Weapon
			entityDef.anIntArray94[5] = 87334;// Npc's Gloves
			entityDef.anIntArray94[6] = 87333;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Mcdonalds torva";
			entityDef.combatLevel = 300;// Combat Level
			entityDef.description = "Mcdonalds torva.".getBytes();
		}
		if (i == 9915) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 100;// Npc's Cape
			entityDef.anIntArray94[1] = 96;// Npc's Head
			entityDef.anIntArray94[2] = 24;// Npc's Plate
			entityDef.anIntArray94[3] = 26;// Npc's Legs
			entityDef.anIntArray94[4] = 99;// Npc's Weapon
			entityDef.anIntArray94[5] = 29;// Npc's Gloves
			entityDef.anIntArray94[6] = 28;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@gre@Weed torva";
			entityDef.combatLevel = 420;// Combat Level
			entityDef.description = "Weed torva.".getBytes();
		}
		if (i == 9914) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 69027;// Npc's Cape
			entityDef.anIntArray94[1] = 40154;// Npc's Head
			entityDef.anIntArray94[2] = 40158;// Npc's Plate
			entityDef.anIntArray94[3] = 40156;// Npc's Legs
			entityDef.anIntArray94[4] = 40160;// Npc's Weapon
			entityDef.anIntArray94[5] = 69019;// Npc's Gloves
			entityDef.anIntArray94[6] = 69017;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@gre@Chaotic Valgor";
			entityDef.combatLevel = 140;// Combat Level
			entityDef.description = "Chaotic Valgor torva.".getBytes();
		}
		if (i == 504) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 87271;// Npc's Cape
			entityDef.anIntArray94[1] = 87267;// Npc's Head
			entityDef.anIntArray94[2] = 87260;// Npc's Plate
			entityDef.anIntArray94[3] = 87262;// Npc's Legs
			entityDef.anIntArray94[4] = 87270;// Npc's Weapon
			entityDef.anIntArray94[5] = 87264;// Npc's Gloves
			entityDef.anIntArray94[6] = 87266;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Candy torva";
			entityDef.combatLevel = 185;// Combat Level
			entityDef.description = "Candy torva.".getBytes();
		}
		if (i == 5555) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 18985;// Npc's Cape
			entityDef.anIntArray94[1] = 15002;// Npc's Head
			entityDef.anIntArray94[2] = 15004;// Npc's Plate
			entityDef.anIntArray94[3] = 15005;// Npc's Legs
			entityDef.anIntArray94[4] = 12316;// Npc's Weapon
			entityDef.anIntArray94[5] = 13307;// Npc's Gloves
			entityDef.anIntArray94[6] = 13565;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@bla@Aiyana";
			entityDef.combatLevel = 315;// Combat Level
			entityDef.description = "Aiyana Monster.".getBytes();
			entityDef.anInt91 = 150;
			entityDef.anInt86 = 150;
		}
		if (i == 507) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 53;// Npc's Cape
			entityDef.anIntArray94[1] = 50;// Npc's Head
			entityDef.anIntArray94[2] = 43;// Npc's Plate
			entityDef.anIntArray94[3] = 45;// Npc's Legs
			entityDef.anIntArray94[4] = 52;// Npc's Weapon
			entityDef.anIntArray94[5] = 48;// Npc's Gloves
			entityDef.anIntArray94[6] = 47;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Crimson torva";
			entityDef.combatLevel = 500;// Combat Level
			entityDef.description = "Crimson torva.".getBytes();
		}
		if (i == 9960) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 58027;// Npc's Cape
			entityDef.anIntArray94[1] = 85025;// Npc's Head
			entityDef.anIntArray94[2] = 85026;// Npc's Plate
			entityDef.anIntArray94[3] = 85024;// Npc's Legs
			entityDef.anIntArray94[4] = 58050;// Npc's Weapon
			entityDef.anIntArray94[5] = 93;// Npc's Gloves
			entityDef.anIntArray94[6] = 90;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Nice torva";
			entityDef.combatLevel = 1000;// Combat Level
			entityDef.description = "Nice torva.".getBytes();
		}
		if (i == 9969) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 69027;// Npc's Cape
			entityDef.anIntArray94[1] = 69022;// Npc's Head
			entityDef.anIntArray94[2] = 69016;// Npc's Plate
			entityDef.anIntArray94[3] = 69024;// Npc's Legs
			entityDef.anIntArray94[4] = 69026;// Npc's Weapon
			entityDef.anIntArray94[5] = 69019;// Npc's Gloves
			entityDef.anIntArray94[6] = 69017;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Sponsor torva";
			entityDef.combatLevel = 175;// Combat Level
			entityDef.description = "Sponsor torva.".getBytes();
		}

		if (i == 7790) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 87299;// Npc's Cape
			entityDef.anIntArray94[1] = 87295;// Npc's Head
			entityDef.anIntArray94[2] = 87288;// Npc's Plate
			entityDef.anIntArray94[3] = 87290;// Npc's Legs
			entityDef.anIntArray94[4] = 87298;// Npc's Weapon
			entityDef.anIntArray94[5] = 87293;// Npc's Gloves
			entityDef.anIntArray94[6] = 87292;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Orange Juice Torva";
			entityDef.combatLevel = 9999;// Combat Level
			entityDef.description = "Orange juice torva.".getBytes();
		}
		if (i == 7793) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 87327;// Npc's Cape
			entityDef.anIntArray94[1] = 87323;// Npc's Head
			entityDef.anIntArray94[2] = 87316;// Npc's Plate
			entityDef.anIntArray94[3] = 87318;// Npc's Legs
			entityDef.anIntArray94[4] = 87326;// Npc's Weapon
			entityDef.anIntArray94[5] = 87321;// Npc's Gloves
			entityDef.anIntArray94[6] = 87320;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Play-Doh torva";
			entityDef.combatLevel = 3000;// Combat Level
			entityDef.description = "Play-Doh torva.".getBytes();
		}
		if (i == 508) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 87313;// Npc's Cape
			entityDef.anIntArray94[1] = 87309;// Npc's Head
			entityDef.anIntArray94[2] = 87302;// Npc's Plate
			entityDef.anIntArray94[3] = 87304;// Npc's Legs
			entityDef.anIntArray94[4] = 87312;// Npc's Weapon
			entityDef.anIntArray94[5] = 87307;// Npc's Gloves
			entityDef.anIntArray94[6] = 87306;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Viking torva";
			entityDef.combatLevel = 210;// Combat Level
			entityDef.description = "Viking torva.".getBytes();
		}
		if (i == 509) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 87285;// Npc's Cape
			entityDef.anIntArray94[1] = 87281;// Npc's Head
			entityDef.anIntArray94[2] = 87274;// Npc's Plate
			entityDef.anIntArray94[3] = 87276;// Npc's Legs
			entityDef.anIntArray94[4] = 87284;// Npc's Weapon
			entityDef.anIntArray94[5] = 87279;// Npc's Gloves
			entityDef.anIntArray94[6] = 87278;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "Cryptic torva";
			entityDef.combatLevel = 400;// Combat Level
			entityDef.description = "Cryptic torva.".getBytes();
		}
		if (i == 9986) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 86129;// Npc's Cape
			entityDef.anIntArray94[1] = 12843;// Npc's Head*
			entityDef.anIntArray94[2] = 86125;// Npc's Plate*
			entityDef.anIntArray94[3] = 86127;// Npc's Legs*
			entityDef.anIntArray94[4] = 86130;// Npc's Weapon
			entityDef.anIntArray94[5] = 86131;// Npc's Gloves
			entityDef.anIntArray94[6] = 86128;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Chaseblade Warrior";
			entityDef.combatLevel = 999999;// Combat Level
			entityDef.description = "Chaseblade Warrior.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9991) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 80;// Npc's Cape
			entityDef.anIntArray94[1] = 85016;// Npc's Head*
			entityDef.anIntArray94[2] = 85017;// Npc's Plate*
			entityDef.anIntArray94[3] = 85015;// Npc's Legs*
			entityDef.anIntArray94[4] = 84999;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@White Torva Boss";
			entityDef.combatLevel = 9999;// Combat Level
			entityDef.description = "White torva boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}

		if (i == 3) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 80;// Npc's Cape
			entityDef.anIntArray94[1] = 87180;// Npc's Head*
			entityDef.anIntArray94[2] = 87177;// Npc's Plate*
			entityDef.anIntArray94[3] = 87181;// Npc's Legs*
			entityDef.anIntArray94[4] = 87182;// Npc's Weapon
			entityDef.anIntArray94[5] = 87179;// Npc's Gloves
			entityDef.anIntArray94[6] = 87178;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Hellacious @bla@ Boss";
			entityDef.combatLevel = 9999;// Combat Level
			entityDef.description = "Hellacious Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}

		if (i == 7777) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 64062;// Npc's Cape
			entityDef.anIntArray94[1] = 82363;// Npc's Head*
			entityDef.anIntArray94[2] = 82416;// Npc's Plate*
			entityDef.anIntArray94[3] = 82368;// Npc's Legs*
			entityDef.anIntArray94[4] = 13544;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Tetsu";
			entityDef.combatLevel = 6000;// Combat Level
			entityDef.description = "Tetsu boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 7778) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 83019;// Npc's Cape
			entityDef.anIntArray94[1] = 83071;// Npc's Head*
			entityDef.anIntArray94[2] = 83073;// Npc's Plate*
			entityDef.anIntArray94[3] = 83075;// Npc's Legs*
			entityDef.anIntArray94[4] = 82973;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@blu@Spiderman Torva";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Spiderman Torva.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9999) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 16021;// Npc's Cape
			entityDef.anIntArray94[1] = 62856;// Npc's Head*
			entityDef.anIntArray94[2] = 62852;// Npc's Plate*
			entityDef.anIntArray94[3] = 62854;// Npc's Legs*
			entityDef.anIntArray94[4] = 82959;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Valgor Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Valgor Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9957) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 3287;// Npc's Cape
			entityDef.anIntArray94[1] = 70662;// Npc's Head*
			entityDef.anIntArray94[2] = 70669;// Npc's Plate*
			entityDef.anIntArray94[3] = 70665;// Npc's Legs*
			entityDef.anIntArray94[4] = 20185;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 70653;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Dragonbone Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Dragonbone Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9958) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 40137;// Npc's Cape
			entityDef.anIntArray94[1] = 40131;// Npc's Head*
			entityDef.anIntArray94[2] = 40125;// Npc's Plate*
			entityDef.anIntArray94[3] = 40133;// Npc's Legs*
			entityDef.anIntArray94[4] = 82955;// Npc's Weapon
			entityDef.anIntArray94[5] = 40129;// Npc's Gloves
			entityDef.anIntArray94[6] = 40128;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Dumblas Torva Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Dumblas Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9959) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 40151;// Npc's Cape
			entityDef.anIntArray94[1] = 40145;// Npc's Head*
			entityDef.anIntArray94[2] = 40139;// Npc's Plate*
			entityDef.anIntArray94[3] = 40147;// Npc's Legs*
			entityDef.anIntArray94[4] = 82957;// Npc's Weapon
			entityDef.anIntArray94[5] = 40143;// Npc's Gloves
			entityDef.anIntArray94[6] = 40142;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Imperial Torva Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Imperial Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 291) {
			stream.currentOffset = streamIndices[1];
			entityDef.aByte68 = 1;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 5; // Original model color
			entityDef.anIntArray70[0] = 5; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 60012;// Npc's Cape
			entityDef.anIntArray94[1] = 62770;// Npc's Head*
			entityDef.anIntArray94[2] = 62766;// Npc's Plate*
			entityDef.anIntArray94[3] = 62772;// Npc's Legs*
			entityDef.anIntArray94[4] = 22;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Ares boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Ares boss".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9965) {
			stream.currentOffset = streamIndices[1];
			entityDef.aByte68 = 1;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 5; // Original model color
			entityDef.anIntArray70[0] = 5; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 55042;// Npc's Cape
			entityDef.anIntArray94[1] = 75507;// Npc's Head*
			entityDef.anIntArray94[2] = 75504;// Npc's Plate*
			entityDef.anIntArray94[3] = 75506;// Npc's Legs*
			entityDef.anIntArray94[4] = 50226;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Gold Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Gold boss".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9998) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 11118;// Npc's Cape
			entityDef.anIntArray94[1] = 82948;// Npc's Head*
			entityDef.anIntArray94[2] = 82949;// Npc's Plate*
			entityDef.anIntArray94[3] = 11115;// Npc's Legs*
			entityDef.anIntArray94[4] = 82959;// Npc's Weapon
			entityDef.anIntArray94[5] = 11114;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Asgardian Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Asg Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9978) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 78091;// Npc's Cape
			entityDef.anIntArray94[1] = 85019;// Npc's Head*
			entityDef.anIntArray94[2] = 85020;// Npc's Plate*
			entityDef.anIntArray94[3] = 85018;// Npc's Legs*
			entityDef.anIntArray94[4] = 78015;// Npc's Weapon
			entityDef.anIntArray94[5] = 11114;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Hulk Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Hulk Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9988) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 3287;// Npc's Cape
			entityDef.anIntArray94[1] = 66409;// Npc's Head*
			entityDef.anIntArray94[2] = 66624;// Npc's Plate*
			entityDef.anIntArray94[3] = 66511;// Npc's Legs*
			entityDef.anIntArray94[4] = 62788;// Npc's Weapon
			entityDef.anIntArray94[5] = 11114;// Npc's Gloves
			entityDef.anIntArray94[6] = 13222;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@yel@Hell Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Hell Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9987) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 31415;// Npc's Cape
			entityDef.anIntArray94[1] = 85031;// Npc's Head*
			entityDef.anIntArray94[2] = 85032;// Npc's Plate*
			entityDef.anIntArray94[3] = 85030;// Npc's Legs*
			entityDef.anIntArray94[4] = 72138;// Npc's Weapon
			entityDef.anIntArray94[5] = 72323;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Black Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "Black Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 9977) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 80;// Npc's Cape
			entityDef.anIntArray94[1] = 338;// Npc's Head*
			entityDef.anIntArray94[2] = 337;// Npc's Plate*
			entityDef.anIntArray94[3] = 339;// Npc's Legs*
			entityDef.anIntArray94[4] = 84999;// Npc's Weapon
			entityDef.anIntArray94[5] = 11114;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@blu@White Torva Blue Boss";
			entityDef.combatLevel = 99999;// Combat Level
			entityDef.description = "White torva blue Boss.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 7780) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 70533;// Npc's Cape
			entityDef.anIntArray94[1] = 83047;// Npc's Head*
			entityDef.anIntArray94[2] = 83049;// Npc's Plate*
			entityDef.anIntArray94[3] = 83051;// Npc's Legs*
			entityDef.anIntArray94[4] = 82973;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@gre@420 Torva";
			entityDef.combatLevel = 9999;// Combat Level
			entityDef.description = "420 Torva.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 7782) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 70533;// Npc's Cape
			entityDef.anIntArray94[1] = 83041;// Npc's Head*
			entityDef.anIntArray94[2] = 83043;// Npc's Plate*
			entityDef.anIntArray94[3] = 83045;// Npc's Legs*
			entityDef.anIntArray94[4] = 82973;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@Bood Torva";
			entityDef.combatLevel = 9999;// Combat Level
			entityDef.description = "Blood Torva.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 7789) {
			stream.currentOffset = streamIndices[630];
			entityDef.aByte68 = 3;
			entityDef.readValues(stream); // Collect original NPC data
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			int changedColors = 1; // Number of model colors changed on the NPC
			entityDef.anIntArray70 = new int[changedColors];
			entityDef.anIntArray76 = new int[changedColors];
			entityDef.anIntArray76[0] = 40; // Original model color
			entityDef.anIntArray70[0] = 34; // Changed model color
			entityDef.anIntArray94 = new int[7];
			entityDef.anIntArray94[0] = 70533;// Npc's Cape
			entityDef.anIntArray94[1] = 55031;// Npc's Head*
			entityDef.anIntArray94[2] = 55030;// Npc's Plate*
			entityDef.anIntArray94[3] = 55034;// Npc's Legs*
			entityDef.anIntArray94[4] = 55089;// Npc's Weapon
			entityDef.anIntArray94[5] = 15018;// Npc's Gloves
			entityDef.anIntArray94[6] = 15016;// Npc's Boots
			// entityDef.anIntArray94[7] = 17461;//Npc's Darkness(Shadow)
			// entityDef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
			entityDef.standAnim = 808;// Npc's Stand Emote
			entityDef.name = "@red@American Torva";
			entityDef.combatLevel = 9999;// Combat Level
			entityDef.description = "American Torva.".getBytes();
			entityDef.anInt91 = 300;
			entityDef.anInt86 = 300;

		}
		if (i == 600) {
			entityDef.name = "Moderator's shop";
			entityDef.description = "Moderator's shop".getBytes();
		}
		if (i == 601) {
			entityDef.name = "Administrator's shop";
			entityDef.description = "Administrator's shop".getBytes();
		}
		if (i == 602) {
			entityDef.name = "Owner's shop";
			entityDef.description = "Owner's shop".getBytes();
		}
		if (i == 2587) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[2];
			entityDef.anIntArray94[0] = 62747;
			entityDef.anIntArray94[1] = 62734;
			entityDef.anIntArray70 = new int[10];
			entityDef.anIntArray76 = new int[10];
			entityDef.anIntArray76[0] = 33893;
			entityDef.anIntArray70[0] = 828;
			entityDef.anIntArray76[1] = 34853;
			entityDef.anIntArray70[1] = 795;
			entityDef.anIntArray76[2] = 35214;
			entityDef.anIntArray70[2] = 780;
			entityDef.anIntArray76[3] = 32882;
			entityDef.anIntArray70[3] = 828;
			entityDef.anIntArray76[4] = 32832;
			entityDef.anIntArray70[4] = 828;
			entityDef.anIntArray76[5] = 35461;
			entityDef.anIntArray70[5] = 774;
			entityDef.anIntArray76[6] = 35216;
			entityDef.anIntArray70[6] = 782;
			entityDef.anIntArray76[7] = 30885;
			entityDef.anIntArray70[7] = 794;
			entityDef.anIntArray76[8] = 34568;
			entityDef.anIntArray70[8] = 780;
			entityDef.anIntArray76[9] = 34454;
			entityDef.anIntArray70[9] = 794;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "Cruor";
			entityDef.combatLevel = 285;
		}
		if (i == 2588) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[2];
			entityDef.anIntArray94[0] = 62747;
			entityDef.anIntArray94[1] = 62734;
			entityDef.anIntArray70 = new int[10];
			entityDef.anIntArray76 = new int[10];
			entityDef.anIntArray76[0] = 33893;
			entityDef.anIntArray70[0] = 80;
			entityDef.anIntArray76[1] = 34853;
			entityDef.anIntArray70[1] = 45;
			entityDef.anIntArray76[2] = 35214;
			entityDef.anIntArray70[2] = 25;
			entityDef.anIntArray76[3] = 32882;
			entityDef.anIntArray70[3] = 68;
			entityDef.anIntArray76[4] = 32832;
			entityDef.anIntArray70[4] = 68;
			entityDef.anIntArray76[5] = 35461;
			entityDef.anIntArray70[5] = 20;
			entityDef.anIntArray76[6] = 35216;
			entityDef.anIntArray70[6] = 30;
			entityDef.anIntArray76[7] = 30885;
			entityDef.anIntArray70[7] = 45;
			entityDef.anIntArray76[8] = 34568;
			entityDef.anIntArray70[8] = 30;
			entityDef.anIntArray76[9] = 34454;
			entityDef.anIntArray70[9] = 45;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "Fumus";
			entityDef.combatLevel = 285;
		}
		if (i == 2589) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.anIntArray94 = new int[2];
			entityDef.anIntArray94[0] = 62747;
			entityDef.anIntArray94[1] = 62734;
			entityDef.anIntArray70 = new int[10];
			entityDef.anIntArray76 = new int[10];
			entityDef.anIntArray76[0] = 33893;
			entityDef.anIntArray70[0] = 50360;
			entityDef.anIntArray76[1] = 34853;
			entityDef.anIntArray70[1] = 48550;
			entityDef.anIntArray76[2] = 35214;
			entityDef.anIntArray70[2] = 15;
			entityDef.anIntArray76[3] = 32882;
			entityDef.anIntArray70[3] = 48540;
			entityDef.anIntArray76[4] = 32832;
			entityDef.anIntArray70[4] = 48540;
			entityDef.anIntArray76[5] = 35461;
			entityDef.anIntArray70[5] = 10;
			entityDef.anIntArray76[6] = 35216;
			entityDef.anIntArray70[6] = 4506;
			entityDef.anIntArray76[7] = 30885;
			entityDef.anIntArray70[7] = 48550;
			entityDef.anIntArray76[8] = 34568;
			entityDef.anIntArray70[8] = 50074;
			entityDef.anIntArray76[9] = 34454;
			entityDef.anIntArray70[9] = 48550;
			entityDef.aByte68 = 1;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 808;// stand animation
			entityDef.walkAnim = 819;// walk animation
			entityDef.name = "Umbra";
			entityDef.combatLevel = 285;
		}
		if (i == 2636) {
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 62717;
			entityDef.name = "Nex";
			entityDef.aByte68 = 3;
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.aBoolean87 = true;
			entityDef.combatLevel = 1001;
			entityDef.aBoolean93 = true;
			entityDef.anInt85 = 10;
			entityDef.anInt92 = 50;
			entityDef.anInt75 = 15;
			entityDef.anInt79 = 32;
			entityDef.aBoolean84 = true;
		}
		if (i == 1167) {
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}
		if (i == 553) {
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}

		if (i == 7143) {
			entityDef.name = "FireMaker";
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}
		if (i == 794) {
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}
		if (i == 219) {
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}
		if (i == 3920) {
			entityDef.name = "Fletcher";
			entityDef.itemActions[3] = "Buy Skillcape";
		}
		if (i == 1911) {
			entityDef.name = "Phoenix";
			entityDef.itemActions[1] = "Interact";
		}

		if (i == 4295) {
			entityDef.name = "Thief";
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}

		if (i == 455) {
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}
		if (i == 9085) {
			entityDef.itemActions[3] = "FrostDrags";
		}
		if (i == 3299) {
			entityDef.name = "Farmer";
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}
		if (i == 4906) {
			entityDef.name = "Woodcutter";
			entityDef.itemActions[2] = "Trade";
			entityDef.itemActions[3] = "Buy Skillcape";
		}

		if (i == 569) {
			entityDef.name = "Crafter";
			entityDef.itemActions[3] = "Buy Skillcape";
		}

		if (i == 33) {
			entityDef.name = "Bank server";
			entityDef.combatLevel = 9000;
		}
		if (i == 1225) {
			entityDef.name = "Bryan the Vampire";
			entityDef.combatLevel = 341;
		}
		if (i == 3109) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.name = "I don't remember my name";
			entityDef.combatLevel = 389;
		}
		if (i == 4477) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.name = "Barebones";
			entityDef.combatLevel = 941;
		}
		if (i == 879) {
			entityDef.name = "Pim the Delrith";
			entityDef.combatLevel = 341;
		}
		if (i == 2134) {
			entityDef.name = "Terrance the Rage";
			entityDef.combatLevel = 119;
		}
		if (i == 2579) {
			entityDef.name = "Mandrith";
			entityDef.aByte68 = 1;
			entityDef.standAnim = 2715;
			entityDef.walkAnim = 2715;
			entityDef.itemActions = new String[5];
			entityDef.itemActions[0] = "Sell Artifacts";
			entityDef.combatLevel = 0;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 30182;
		}
		if (i == 2577) {
			entityDef.name = "Harry";
			entityDef.aByte68 = 1;
			entityDef.combatLevel = 0;
			entityDef.anInt91 = 130;
			entityDef.walkAnim = 819;
			entityDef.anInt86 = 180;
			entityDef.standAnim = 2715;
			entityDef.walkAnim = 2715;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 29909;
		}
		if (i == 2578) {
			entityDef.name = "Nastroth";
			entityDef.aByte68 = 1;
			entityDef.combatLevel = 0;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.standAnim = 2715;
			entityDef.walkAnim = 2715;
			entityDef.itemActions = new String[5];
			entityDef.itemActions[0] = "Open SSP Shop";
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 40058;
		}
		if (i == 2580) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.name = "Lucien";
			entityDef.aByte68 = 4;
			entityDef.combatLevel = 0;
			entityDef.anInt91 = 130;
			entityDef.anInt86 = 130;
			entityDef.walkAnim = 10764;
			entityDef.standAnim = 10763;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 44701;
		}
		if (i == 3592) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Special Move";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 31101;
			entityDef.standAnim = 6374;
			entityDef.walkAnim = 6373;
			entityDef.name = "Unicorn Stallion";
			entityDef.combatLevel = 70;
			entityDef.description = "A summoning monster.".getBytes();
		}
		if (i == 1282) {
			entityDef.name = "Summoning Master";
			entityDef.itemActions[2] = "Refill-BoB";
			entityDef.itemActions[3] = "Open Pouch Shop";
		}
		if (i == 243) {
			entityDef.name = "Dungeoneering Master";
			entityDef.itemActions[2] = "Start Dungeoneering";
			entityDef.itemActions[3] = "Open Token Shop";
		}
		if (i == 3591) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Special Move";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 30469;
			entityDef.standAnim = 8186;
			entityDef.walkAnim = 8189;
			entityDef.name = "Steel Titan";
			entityDef.combatLevel = 175;
			entityDef.description = "A summoning monster.".getBytes();
		}
		if (i == 3587) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Take BoB";
			entityDef.itemActions[2] = "Store";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 30460;
			entityDef.standAnim = 8284;
			entityDef.walkAnim = 8281;
			entityDef.name = "War tortoise";
			entityDef.anInt91 = 129;
			entityDef.anInt86 = 129;
			entityDef.combatLevel = 86;
			entityDef.description = "A summoning monster.".getBytes();
		}
		if (i == 3588) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Teleport";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 31132;
			entityDef.standAnim = 5225;
			entityDef.walkAnim = 5226;
			entityDef.name = "Spirit Ghraak";
			entityDef.anInt91 = 129;
			entityDef.anInt86 = 129;
			entityDef.combatLevel = 51;
			entityDef.description = "A summoning monster.".getBytes();
		}
		if (i == 3593) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Special move";
			entityDef.anInt91 = 129;
			entityDef.anInt86 = 129;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 30475;
			entityDef.standAnim = 8301;
			entityDef.walkAnim = 8302;
			entityDef.aBoolean87 = false;
			entityDef.name = "Wolpertinger";
			entityDef.combatLevel = 51;
			entityDef.description = "A summoning monster.".getBytes();
		}
		if (i == 3590) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Take-Supplies";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 30460;
			entityDef.standAnim = 8284;
			entityDef.walkAnim = 8281;
			entityDef.name = "War tortoise";
			entityDef.anInt91 = 129;
			entityDef.anInt86 = 129;
			entityDef.combatLevel = 86;
			entityDef.description = "A summoning monster.".getBytes();
		}
		if (i == 3586) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Take BoB";
			entityDef.itemActions[2] = "Store";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 31096;
			entityDef.standAnim = 1008;
			entityDef.walkAnim = 1007;
			entityDef.anInt91 = 129;
			entityDef.anInt86 = 129;
			entityDef.name = "Spirit terrorbird";
			entityDef.combatLevel = 62;
			entityDef.description = "A Bank That Follows You.".getBytes();
		}
		if (i == 3596) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Take-Supplies";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 31096;
			entityDef.standAnim = 1008;
			entityDef.walkAnim = 1007;
			entityDef.anInt91 = 129;
			entityDef.anInt86 = 129;
			entityDef.name = "Spirit terrorbird";
			entityDef.combatLevel = 62;
			entityDef.description = "A Bank That Follows You.".getBytes();
		}
		if (i == 3594) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[2] = "Take-Supplies";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 23892;
			entityDef.standAnim = 5785;
			entityDef.walkAnim = 5781;
			entityDef.name = "Pack-yak";
			entityDef.combatLevel = 175;
			entityDef.description = "Summoning Bank.".getBytes();
		}
		if (i == 3595) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Take BoB";
			entityDef.itemActions[2] = "Store";
			entityDef.aBoolean87 = false;
			entityDef.anIntArray94 = new int[1];
			entityDef.anIntArray94[0] = 23892;
			entityDef.standAnim = 5785;
			entityDef.walkAnim = 5781;
			entityDef.name = "Pack-yak";
			entityDef.combatLevel = 175;
			entityDef.description = "Summoning Bank.".getBytes();
		}
		if (i == 1155) {
			entityDef.itemActions = new String[5];
			entityDef.itemActions[1] = "Attack";
			entityDef.name = "Tormented Demon";
			entityDef.combatLevel = 450;
			entityDef.anIntArray94 = new int[1];
			entityDef.standAnim = 10921;
			entityDef.walkAnim = 10920;
			entityDef.anIntArray94[0] = 44733;
			entityDef.description = "Dangerous shit bro...".getBytes();
		}
		return entityDef;
	}

	public Model method160() {
		if (childrenIDs != null) {
			EntityDef entityDef = method161();
			if (entityDef == null)
				return null;
			else
				return entityDef.method160();
		}
		if (anIntArray73 == null)
			return null;
		boolean flag1 = false;
		for (int i = 0; i < anIntArray73.length; i++)
			if (!Model.method463(anIntArray73[i]))
				flag1 = true;

		if (flag1)
			return null;
		Model aclass30_sub2_sub4_sub6s[] = new Model[anIntArray73.length];
		for (int j = 0; j < anIntArray73.length; j++)
			aclass30_sub2_sub4_sub6s[j] = Model.method462(anIntArray73[j]);

		Model model;
		if (aclass30_sub2_sub4_sub6s.length == 1)
			model = aclass30_sub2_sub4_sub6s[0];
		else
			model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
		if (anIntArray76 != null) {
			for (int k = 0; k < anIntArray76.length; k++)
				model.method476(anIntArray76[k], anIntArray70[k]);

		}
		return model;
	}

	public EntityDef method161() {
		int j;
		try {
			j = -1;
			if (anInt57 != -1) {
				VarBit varBit = VarBit.cache[anInt57];
				int k = varBit.anInt648;
				int l = varBit.anInt649;
				int i1 = varBit.anInt650;
				int j1 = Client.anIntArray1232[i1 - l];
				j = clientInstance.variousSettings[k] >> l & j1;
			} else if (anInt59 != -1)
				j = clientInstance.variousSettings[anInt59];
			if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1)
				return null;
		} catch (Exception e) {
			return null;
		}
		return forID(childrenIDs[j]);
	}

	public static void unpackConfig(StreamLoader streamLoader) {
		stream = new Stream(streamLoader.getDataForName("npc.dat"));
		Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
		int totalNPCs = stream2.readUnsignedWord();
		System.out.println((new StringBuilder()).append("602 NPC Amount: ").append(totalNPCs).toString());
		streamIndices = new int[totalNPCs];
		int i = 2;
		for (int j = 0; j < totalNPCs; j++) {
			streamIndices[j] = i;
			i += stream2.readUnsignedWord();
		}

		cache = new EntityDef[20];
		for (int k = 0; k < 20; k++)
			cache[k] = new EntityDef();

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

	public static void nullLoader() {
		mruNodes = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public Model method164(int j, int k, int ai[]) {
		if (childrenIDs != null) {
			EntityDef entityDef = method161();
			if (entityDef == null)
				return null;
			else
				return entityDef.method164(j, k, ai);
		}
		Model model = (Model) mruNodes.insertFromCache(type);
		if (model == null) {
			boolean flag = false;
			for (int i1 = 0; i1 < anIntArray94.length; i1++)
				if (!Model.method463(anIntArray94[i1]))
					flag = true;

			if (flag)
				return null;
			Model aclass30_sub2_sub4_sub6s[] = new Model[anIntArray94.length];
			for (int j1 = 0; j1 < anIntArray94.length; j1++)
				aclass30_sub2_sub4_sub6s[j1] = Model.method462(anIntArray94[j1]);

			if (aclass30_sub2_sub4_sub6s.length == 1)
				model = aclass30_sub2_sub4_sub6s[0];
			else
				model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
			if (anIntArray76 != null) {
				for (int k1 = 0; k1 < anIntArray76.length; k1++)
					model.method476(anIntArray76[k1], anIntArray70[k1]);

			}
			model.method469();
			model.method479(64 + anInt85, 850 + anInt92, -30, -50, -30, true);
			mruNodes.removeFromCache(model, type);
		}
		Model model_1 = Model.aModel_1621;
		model_1.method464(model, Frame.method532(k) & Frame.method532(j));
		if (k != -1 && j != -1)
			model_1.method471(ai, j, k);
		else if (k != -1)
			model_1.method470(k);
		if (anInt91 != 128 || anInt86 != 128)
			model_1.method478(anInt91, anInt91, anInt86);
		model_1.method466();
		model_1.anIntArrayArray1658 = (int[][]) null;
		model_1.anIntArrayArray1657 = (int[][]) null;
		if (aByte68 == 1)
			model_1.aBoolean1659 = true;
		return model_1;
	}

	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				int j = stream.readUnsignedByte();
				anIntArray94 = new int[j];
				int j1 = 0;
				while (j1 < j) {
					anIntArray94[j1] = stream.readUnsignedWord();
					if (type == 55L)
						System.out.println(anIntArray94[j1]);
					j1++;
				}
			} else if (i == 2)
				name = stream.readString();
			else if (i == 3)
				description = stream.readBytes();
			else if (i == 12)
				aByte68 = stream.readSignedByte();
			else if (i == 13)
				standAnim = stream.readUnsignedWord();
			else if (i == 14)
				walkAnim = stream.readUnsignedWord();
			else if (i == 17) {
				walkAnim = stream.readUnsignedWord();
				anInt58 = stream.readUnsignedWord();
				anInt83 = stream.readUnsignedWord();
				anInt55 = stream.readUnsignedWord();
				if (walkAnim == 65535)
					walkAnim = -1;
				if (anInt58 == 65535)
					anInt58 = -1;
				if (anInt83 == 65535)
					anInt83 = -1;
				if (anInt55 == 65535)
					anInt55 = -1;
			} else if (i >= 30 && i < 40) {
				if (itemActions == null)
					itemActions = new String[5];
				itemActions[i - 30] = stream.readString();
				if (itemActions[i - 30].equalsIgnoreCase("hidden"))
					itemActions[i - 30] = null;
			} else if (i == 40) {
				int k = stream.readUnsignedByte();
				anIntArray70 = new int[k];
				anIntArray76 = new int[k];
				int k1 = 0;
				while (k1 < k) {
					anIntArray76[k1] = stream.readUnsignedWord();
					anIntArray70[k1] = stream.readUnsignedWord();
					k1++;
				}
			} else if (i == 60) {
				int l = stream.readUnsignedByte();
				anIntArray73 = new int[l];
				int l1 = 0;
				while (l1 < l) {
					anIntArray73[l1] = stream.readUnsignedWord();
					l1++;
				}
			} else if (i == 90)
				stream.readUnsignedWord();
			else if (i == 91)
				stream.readUnsignedWord();
			else if (i == 92)
				stream.readUnsignedWord();
			else if (i == 93)
				aBoolean87 = false;
			else if (i == 95)
				combatLevel = stream.readUnsignedWord();
			else if (i == 97)
				anInt91 = stream.readUnsignedWord();
			else if (i == 98)
				anInt86 = stream.readUnsignedWord();
			else if (i == 99)
				aBoolean93 = true;
			else if (i == 100)
				anInt85 = stream.readSignedByte();
			else if (i == 101)
				anInt92 = stream.readSignedByte() * 5;
			else if (i == 102)
				anInt75 = stream.readUnsignedWord();
			else if (i == 103)
				anInt79 = stream.readUnsignedWord();
			else if (i == 106) {
				anInt57 = stream.readUnsignedWord();
				if (anInt57 == 65535)
					anInt57 = -1;
				anInt59 = stream.readUnsignedWord();
				if (anInt59 == 65535)
					anInt59 = -1;
				int i1 = stream.readUnsignedByte();
				childrenIDs = new int[i1 + 1];
				int i2 = 0;
				while (i2 <= i1) {
					childrenIDs[i2] = stream.readUnsignedWord();
					if (childrenIDs[i2] == 65535)
						childrenIDs[i2] = -1;
					i2++;
				}
			} else if (i == 107)
				aBoolean84 = false;
		} while (true);
	}

	public EntityDef() {
		anInt55 = -1;
		anInt57 = -1;
		anInt58 = -1;
		anInt59 = -1;
		combatLevel = -1;
		walkAnim = -1;
		aByte68 = 1;
		anInt75 = -1;
		standAnim = -1;
		type = -1L;
		anInt79 = 32;
		anInt83 = -1;
		aBoolean84 = true;
		anInt86 = 128;
		aBoolean87 = true;
		anInt91 = 128;
		aBoolean93 = false;
	}

	public static int NPCAMOUNT = 11599;
	public int anInt55;
	public static int anInt56;
	public int anInt57;
	public int anInt58;
	public int anInt59;
	public static Stream stream;
	public int combatLevel;
	public final int anInt64 = 1834;
	public String name;
	public String itemActions[];
	public int walkAnim;
	public byte aByte68;
	public int anIntArray70[];
	public static int streamIndices[];
	public int anIntArray73[];
	public int anInt75;
	public int anIntArray76[];
	public int standAnim;
	public long type;
	public int anInt79;
	public static EntityDef cache[];
	public static Client clientInstance;
	public int anInt83;
	public boolean aBoolean84;
	public int anInt85;
	public int anInt86;
	public boolean aBoolean87;
	public int childrenIDs[];
	public byte description[];
	public int anInt91;
	public int anInt92;
	public boolean aBoolean93;
	public int anIntArray94[];
	public static MRUNodes mruNodes = new MRUNodes(30);

}
