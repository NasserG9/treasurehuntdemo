package main;

import entity.NPC_Imp;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter (GamePanel gp) {
		
		this.gp =gp;
	}
	
	public void setObject(){
		
		gp.obj[0] = new OBJ_key();
		gp.obj[0].worldX = 9 * gp.tileSize;
		gp.obj[0].worldY = 2 * gp.tileSize;
		
		gp.obj[1] = new OBJ_key();
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 10 * gp.tileSize;
		

		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 8 * gp.tileSize;
		gp.obj[3].worldY = 4 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 8 * gp.tileSize;
		gp.obj[4].worldY = 19 * gp.tileSize;
		
		
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 8 * gp.tileSize;
		gp.obj[6].worldY = 36 * gp.tileSize;
	}
	public void setNPC() {
		
	gp.npc[0] = new NPC_Imp(gp);
	gp.npc[0].worldX = gp.tileSize *2;
	gp.npc[0].worldY = gp.tileSize*2;
	
	}

}
