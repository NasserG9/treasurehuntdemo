package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);

		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2- (gp.tileSize/2);
		
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefultX = solidArea.x;
		solidAreaDefultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 4;
		worldY = gp.tileSize * 3;
		speed = 6;
		direction = "down";
		
	}
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/R1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/R4.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/L1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/L4.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/L1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/L4.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/R1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/R4.png"));
		
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true 
				|| keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";	
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cCheaker.checkTile(this);
			gp.cCheaker.checkObject(this, false);
			//gp.cCheaker.checkPlayer(this);
			
			//CHECK OBJECT COLLSION
			int objindex = gp.cCheaker.checkObject(this, true);
			pickUpObject(objindex);
			
			//CHECK NPC COLLSION
			int npcIndex = gp.cCheaker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE 
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX +=speed;
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
				
			}	
		
		}	
	
	}
	public void interactNPC(int i) {
		if(i != 999) {
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.enterPressed = false;
	}
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName){
			case "Key":
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a key!");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("You opened the door!");
				}
				else {
					gp.ui.showMessage("You need a Key!");
				}
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				
				break;
			}
			
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
        
        switch(direction) {
        case "up":
        	if(spriteNum == 1) {
        		image = up1;
        	}
        	if(spriteNum == 2) {
        		image = up2;
        	}
        	break;
        case "down":
        	if(spriteNum == 1) {
        		image = down1;
        	}
        	if(spriteNum == 2) {
        		image = down2;
        	}
        	break;
        case "left":
        	if(spriteNum == 1) {
            	image = left1;
        	}
        	if(spriteNum == 2) {
            	image = left2;
        	}
        	break;
        case "right":
        	if(spriteNum == 1) {
        		image = right1;
        	}
        	if(spriteNum == 2) {
        		image = right2;
        	}
        	break; 	        
        }
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);	
	
	}
}

