package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.graphics.Color;



public class Obstacle extends GameEntity
{
	float obstacleHeight;
	float obstacleWidth;
	
	public Obstacle(boolean setCollision, Color color, Vector2 size, Vector2 pos)
	{
		//Setup ID: 1 is obstacle
		id = 1;
		
		//Setup hitbox and rectangle
		sprite = new ShapeRenderer();
			sprite.setColor(color);
		position = pos;
		
		obstacleHeight = size.y;
		obstacleWidth = size.x;
		
		hitbox = new Rectangle( position.x, position.y, obstacleHeight, obstacleWidth );
		
		//RegisterCollision
		if( setCollision )
			registerCollision();
	}
	

	public void render()
	{	
		//Render box Shape
		sprite.begin( ShapeType.Filled );
		
		sprite.rect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		
		sprite.end();
	}
	
	
	//Collision stuff
	public void collided( GameEntity other )
	{
		System.out.println("Obstacle colliding");
	}
	
	//Input Stuff
	void keyPressed( int key )
	{
	}
	void keyReleased( int key )
	{
	}
	void keyDown( int key )
	{
	}
}
