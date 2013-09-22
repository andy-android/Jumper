package com.testGame.manager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.testGame.GameActivity;

	public class ResourceManager
	{
	    //---------------------------------------------
	    // VARIABLES
	    //---------------------------------------------
	    
	    private static final ResourceManager INSTANCE = new ResourceManager();
	    
	    public Engine engine;
	    public GameActivity activity;
	    public BoundCamera camera;
	    public VertexBufferObjectManager vbom;
	    
	    //player
	    public ITiledTextureRegion player_region;
	    
	    //game
	    // Game Texture
	    public BuildableBitmapTextureAtlas gameTextureAtlas;
	        
	    // Game Texture Regions
	    public ITextureRegion platform1_region;
	    public ITextureRegion platform2_region;
	    public ITextureRegion platform3_region;
	    public ITextureRegion coin_region;
	    
	    //splash
	    public ITextureRegion splash_region;
	    private BitmapTextureAtlas splashTextureAtlas;
	    
	    //menu
	    public ITextureRegion menu_background_region;
	    public ITextureRegion play_region;
	    public ITextureRegion options_region;
	        
	    private BuildableBitmapTextureAtlas menuTextureAtlas;
	    
	    //font
	    public Font font;

	    private void loadMenuFonts()
	    {
	        FontFactory.setAssetBasePath("font/");
	        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

	        font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "Gingersnaps.ttf", 50, true, 2);
	        font.load();
	    }
	    
	    //---------------------------------------------
	    // TEXTURES & TEXTURE REGIONS
	    //---------------------------------------------
	    
	    //---------------------------------------------
	    // CLASS LOGIC
	    //---------------------------------------------

	    public void loadMenuResources()
	    {
	        loadMenuGraphics();
	        loadMenuAudio();
	        loadMenuFonts();
	    }
	    
	    public void loadGameResources()
	    {
	        loadGameGraphics();
	        loadGameAudio();
	    }
	    
	    private void loadMenuGraphics()
	    {
	    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
	    	menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
	    	menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_background.png");
	    	play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "play.png");
	    	options_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "options.png");
	    	       
	    	try 
	    	{
	    	    this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
	    	    this.menuTextureAtlas.load();
	    	} 
	    	catch (final TextureAtlasBuilderException e)
	    	{
	    	        Debug.e(e);
	    	}
	    }
	    
	    private void loadMenuAudio()
	    {
	        
	    }

	    private void loadGameGraphics()
	    {
	    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
	        gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
	        
	        platform1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "platform1.png");
	        platform2_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "platform2.png");
	        platform3_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "platform3.png");
	        coin_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "coin.png");
	        player_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "player.png", 3, 1);
	        try 
	        {
	            this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
	            this.gameTextureAtlas.load();
	        } 
	        catch (final TextureAtlasBuilderException e)
	        {
	            Debug.e(e);
	        }
	    }
	    
	    private void loadGameAudio()
	    {
	        
	    }
	    
	    public void loadSplashScreen()
	    {
	    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	    	splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
	    	splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png", 0, 0);
	    	splashTextureAtlas.load();
	    }
	    
	    public void unloadSplashScreen()
	    {
	    	splashTextureAtlas.unload();
	    	splash_region = null;
	    }
	    
	    public void unloadMenuTextures()
	    {
	        menuTextureAtlas.unload();
	    }
	        
	    public void loadMenuTextures()
	    {
	        menuTextureAtlas.load();
	    }
	    
	    public void unloadGameTextures()
	    {
	        // TODO (Since we did not create any textures for game scene yet)
		    }
	    
	      
	    /**
	     * We use this method at beginning of game loading, to prepare Resources Manager properly,
	     * setting all needed parameters, so we can latter access them from different classes (eg. scenes)
	     */
	    public static void prepareManager(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom)
	    {
	        getInstance().engine = engine;
	        getInstance().activity = activity;
	        getInstance().camera = (BoundCamera) camera;
	        getInstance().vbom = vbom;
	    }
	    
	    //---------------------------------------------
	    // GETTERS AND SETTERS
	    //---------------------------------------------
	    
	    public static ResourceManager getInstance()
	    {
	        return INSTANCE;
	    }
}

