package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;





public class BoxGame extends ApplicationAdapter 
{
	public static int WIDTH = 800, HEIGHT = 600;
	
	BitmapFont font;
	SpriteBatch batch;
	
	OrthographicCamera cam;
	
	
	BoxCharacter myBox;
	Obstacle anObstacle;
	Obstacle ground;
	
	//Texture img;
	
	@Override
	public void create () 
	{
		//Setup up Camera
		cam = new OrthographicCamera();
		cam.setToOrtho( true, BoxGame.WIDTH, BoxGame.HEIGHT );
		
		
		batch = new SpriteBatch();
		font = new BitmapFont( true );
		
	
		myBox = new BoxCharacter();
		anObstacle = new Obstacle(true, Color.CYAN, new Vector2(40,100), new Vector2(400, 100) );
		ground = new Obstacle(false, Color.OLIVE, new Vector2(105,800), new Vector2(0, 0) );
	}

	@Override
	public void render () 
	{
		//ManagerUpdates
		InputManager.updateInputs();
		CollisionManager.checkCollisions();
		//
		
		//Camera Update + Setting up screen coordinates origin
		//to be top left
		cam.update();
		batch.setProjectionMatrix( cam.combined );
		
		//LibGdx uses openGL which has double buffering
		//SO, the viewable Screen needs to be cleared @ every render
		//before new images get drawn onto buffer
			//if not done, screen flickering happens.
		Gdx.gl.glClearColor( 0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		//Starting Actual Render
		batch.begin();	//Sets up render states
		font.draw( batch, "One" ,20, 20);

		font.draw( batch, "Two" ,400, 20);
		//batch.draw(myTexture, 0, 0);
		batch.end();	//Actually draws the render states
						//onto buffer
		
		
		ground.render();
		myBox.render();
		anObstacle.render();
	
	}
	
	@Override
	public void resize( int width, int height )
	{
		cam.setToOrtho( true, width, height );
	}
	
	@Override
	public void dispose()
	{
		batch.dispose();
		font.dispose();
		
		ground.destroy();
		myBox.destroy();
		anObstacle.destroy();
	}
}
