package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_Imp extends Entity{
	
	
	
	public NPC_Imp (GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImpImage();
		setDialogues ();
	}
	
     public void getImpImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/npc/L3 copy.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/npc/L3 copy.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/npc/L6 copy.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/npc/L6 copy.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/R3 copy.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/R3 copy.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/R6 copy.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/R6 copy.png"));

		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
     public void setDialogues () {
    	 
    	 dialogues[0] = "\nHey you! What are you doing here ????";
    	 dialogues[1] = ". . . . .";
    	 dialogues[2] = "\n If you want to proceed, you need to  find my lost \n \ntreasure.";
    	 dialogues[3] = "\n I would find it by myself. . . but I am dead LOL";
     }
    public void setAction() {
    	 
    	 actionLockCounter++;
    	 
    	 if(actionLockCounter == 120) {
    		 Random random = new Random();
        	 int i = random.nextInt(100);
        	 
        	 if(i <= 25) {
        		 direction = "up";
        	 }
        	 if(i > 25 && i <= 50) {
        		 direction = "down";
        	 }
        	 if(i > 50 && i <= 75) {
        		 direction = "left";
        	 }
        	 if(i > 75 && i <= 100) {
        		 direction = "right";
        	 }
        	 
        	 actionLockCounter = 0;
    	 }
    	 
    	 
     }
     public void speak() {
    	 super.speak();

     }

}
