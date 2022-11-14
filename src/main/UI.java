package main;

import java.awt.image.BufferedImage;

import object.OBJ_key;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.io.IOException;
import java.io.InputStream;


public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font purisaB ,arial_40;
	BufferedImage keyImage;
	public boolean messegeOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue ="";
	public int commandNum = 0;
	
	public UI (GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		OBJ_key key = new OBJ_key();
		keyImage = key.image;
	}
	public void showMessage(String text) {
		message = text;
		messegeOn = true;
	}
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x;
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void deawDialogueScreen() {
		
		//WINDOW
		int x = gp.tileSize*2; 
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = (gp.tileSize*4);
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		x += gp.tileSize;
		y += gp.tileSize;
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
	    g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public void drawTitleScreen() {
		
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Dungeon & Treasure";
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		int y = gp.tileSize*3;
		
		//SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		//main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//Charter image
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		//Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		text = "Start Game";
	    length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y = gp.tileSize*8;
		g2.drawString(text, x , y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		text = "Quit";
	    length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y = gp.tileSize*9;
		g2.drawString(text, x , y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}

		
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 =g2;
		
		g2.setFont(purisaB);
		g2.setColor(Color.white);
		//TITLE STATE
		if(gp.gameState == gp.titleState ) {
			drawTitleScreen();
		}
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			//Do playState 
		}
		//PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		//DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			deawDialogueScreen();
		}
		
		if(gameFinished == true) {
			
			g2.setFont(purisaB);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y+100);
			
			g2.setFont(arial_40);
			g2.setColor(Color.yellow);
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_40);
			g2.setColor(Color.yellow);
			text = "Game made by : \n"
					+ "Saad Alharbi /  Nasser Alghamdi!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2)+100;
			g2.drawString(text, x, y);
			
			gp.gameThread = null;

			
		}
		else {
			if(gp.gameState == gp.playState) {
			g2.setFont(purisaB);
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
			g2.drawImage(keyImage, (gp.tileSize/2) , (gp.tileSize/2) , gp.tileSize, gp.tileSize, null);
			g2.drawString(" " + gp.player.hasKey, 50, 80);
			}
			//message 
			if(messegeOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
				
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messegeOn = false;
			
				}
			}
			
		}
		
	}

}
