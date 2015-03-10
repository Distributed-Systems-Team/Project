package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Goal extends Obstacle
{

	public Goal(boolean setCollision, Color color, Vector2 size, Vector2 pos) 
	{
		super(setCollision, color, size, pos);
		

		id = GameID.ID_goal;
		
	}

}
