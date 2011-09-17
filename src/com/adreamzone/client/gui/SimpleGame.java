package com.adreamzone.client.gui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class SimpleGame extends BasicGame{
	
	private Image land;
	private float x = 0;
    private float y = 0;
 
    public SimpleGame()
    {
        super("Slick2DPath2Glory - SimpleGame");
        land = null;
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	land = new Image("resources/background/sky-33.jpg");
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	Input input = gc.getInput();
    	 

        if(input.isKeyDown(Input.KEY_Q))
        {
            land.rotate(-0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
        	land.rotate(0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_Z))
        {
            float hip = 0.4f * delta;
 
            float rotation = land.getRotation();
 
            x+= hip * Math.sin(Math.toRadians(rotation));
            y-= hip * Math.cos(Math.toRadians(rotation));
        }
 
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	land.draw(x, y);
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new SimpleGame());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
