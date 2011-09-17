package com.adreamzone.client.gui;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ADreamZoneGUI extends BasicGame
{
     static int height = 600;
     static int width = 800;
 
     static boolean fullscreen = false;
 
     static boolean showFPS = true;
 
     static String title = "Slick Basic Game Template";
 
     static int fpslimit = 60;
     private Texture texture;
 
     public ADreamZoneGUI(String title)
     {
          super(title);
     }
     
 	/**
 	 * Initialise the GL display
 	 * 
 	 * @param width The width of the display
 	 * @param height The height of the display
 	 */
 	private void initGL(int width, int height) {

 		GL11.glEnable(GL11.GL_TEXTURE_2D);               
         
 		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
         
         	// enable alpha blending
         	GL11.glEnable(GL11.GL_BLEND);
         	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         
         	GL11.glViewport(0,0,width,height);
 		GL11.glMatrixMode(GL11.GL_MODELVIEW);

 		GL11.glMatrixMode(GL11.GL_PROJECTION);
 		GL11.glLoadIdentity();
 		GL11.glOrtho(0, width, height, 0, 1, -1);
 		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 	}
 
     public void init(GameContainer gc) throws SlickException
     {
  		initGL(800,600);
  		try {
    		 // load texture from PNG file
    		 texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(
    		 		"resources/textures/bluestickmanquiz3.png"));
    		 System.out.println("Texture loaded: "+texture);
    		 System.out.println(">> Image width: "+texture.getImageWidth());
    		 System.out.println(">> Image height: "+texture.getImageHeight());
    		 System.out.println(">> Texture width: "+texture.getTextureWidth());
    		 System.out.println(">> Texture height: "+texture.getTextureHeight());
    		 System.out.println(">> Texture ID: "+texture.getTextureID());
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
     }
 
     public void update(GameContainer gc, int delta) throws SlickException
     {
 
     }
 
     public void render(GameContainer gc, Graphics g) throws SlickException
     {
    	 Color.white.bind();
    	 texture.bind(); // or GL11.glBindTexture(0, texture.getTextureID())	;
    	 GL11.glBegin(GL11.GL_QUADS);
    	 GL11.glTexCoord2f(0,0);
    	 GL11.glVertex2f(100,100);
    	 GL11.glTexCoord2f(1,0);
    	 GL11.glVertex2f(100+texture.getTextureWidth(),100);
    	 GL11.glTexCoord2f(1,1);
    	 GL11.glVertex2f(100+texture.getTextureWidth(),100+texture.getTextureHeight());
    	 GL11.glTexCoord2f(0,1);
    	 GL11.glVertex2f(100,100+texture.getTextureHeight());
    	 GL11.glEnd();
     }
 
     public static void main(String[] args) throws SlickException
     {
          AppGameContainer app = new AppGameContainer(new ADreamZoneGUI(title));
          app.setDisplayMode(width, height, fullscreen);
          app.setSmoothDeltas(true);
          app.setTargetFrameRate(fpslimit);
          app.setShowFPS(showFPS);
          app.start();
     }
}