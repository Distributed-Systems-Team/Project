/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: Boundary.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: Stipo Perisa
 * Contributors: David Kim
 * 
 */

package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.graphics.Color;



public class Boundary extends GameEntity
{
	float obstacleHeight;
	float obstacleWidth;
	
	public Boundary(boolean setCollision, Color color, float Pos, boolean isWall, boolean isLeft)
	{
		//Setup hitbox and rectangle
		sprite = new ShapeRenderer();
			sprite.setColor(color);
			
		if( !isWall )
		{
			id = GameID.ID_ground;
		
			obstacleHeight = Pos;
			obstacleWidth = BoxGame.WIDTH;
			
			position = new Vector2( 0, 0 );
			
			hitbox = new Rectangle( position.x, position.y, obstacleWidth, obstacleHeight );
		}
		else
		{
			id = GameID.ID_wall;
			
			obstacleHeight = Pos;
			obstacleWidth = BoxGame.WIDTH/14;
			
			if(isLeft)
				position = new Vector2( 0, GameScreen.groundPos );
			else
				position = new Vector2( BoxGame.WIDTH - obstacleWidth,  GameScreen.groundPos);
			
			hitbox = new Rectangle( position.x, position.y, obstacleWidth, obstacleHeight );
		}
		
		
		
		
		
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