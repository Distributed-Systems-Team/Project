package com.mygdx.game;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


import com.badlogic.gdx.math.Vector2;


public abstract class GameEntity extends InputEntity
{
	Vector2 position;
	
	Rectangle hitbox;
	ShapeRenderer sprite;
	
	//0 = box
    //1 = obstacle
	int id;
	
	public GameEntity(){}
	public void destroy()
	{
		sprite.dispose();
	}
	
	
	abstract void render();
	
	abstract void collided( GameEntity other );
	public void registerCollision()
	{
		CollisionManager.registerCollision(this);
	}
	public void deregisterCollision()
	{
		CollisionManager.deregisterCollision(this);
	}
	
	
}
