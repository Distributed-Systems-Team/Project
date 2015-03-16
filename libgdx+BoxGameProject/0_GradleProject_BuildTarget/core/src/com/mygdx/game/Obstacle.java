/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: Obstacle.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: Matt Wylder
 * Contributors: David Kim
 * 
 */

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
		id = GameID.ID_obstacle;
		
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
