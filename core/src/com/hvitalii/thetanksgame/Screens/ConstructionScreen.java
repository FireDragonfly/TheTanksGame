package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.GameFieldModel;
import com.hvitalii.thetanksgame.MyOwn.MOAdvancedSprite;
import com.hvitalii.thetanksgame.MyOwn.MOButton;
import com.hvitalii.thetanksgame.MyOwn.MOImgRadioButton;
import com.hvitalii.thetanksgame.MyOwn.MOLabel.*;
import com.hvitalii.thetanksgame.MyOwn.MORadioButtonGroup;
import com.hvitalii.thetanksgame.TheTanksGame;
import com.hvitalii.thetanksgame.Utils.MathUtils;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;
import com.hvitalii.thetanksgame.View.GameFieldView;

public class ConstructionScreen extends ScreenAdapter{

    private ResourcesHandler resourcesHandler;
    private TheTanksGame game;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private MORadioButtonGroup buttonGroup;
    private GameFieldModel fieldModel;
    private GameFieldView fieldView;
    private Sprite[] sprites;
    private MainMenuScreen menuScreen;

    private MOButton clear;
    private MOButton save;
//    private MOButton load;
//    private MOButton play;
    private MOButton exit;

    private Sprite cursor;

    private int activeId;

    public ConstructionScreen(ResourcesHandler resourcesHandler, TheTanksGame game, MainMenuScreen menuScreen) {
        this.menuScreen = menuScreen;
        this.resourcesHandler = resourcesHandler;
        this.game = game;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
        viewport = new FitViewport(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT, camera);
        initUi();
    }

    @Override
    public void show() {
        super.show();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClearColor(0 ,0, 0, 1);
        buttonGroup.deactivate();
//        cursor.setSize(Size.TILE, Size.TILE);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        buttonGroup.update();
        activeId = buttonGroup.getActivatedId();
        activeId = (activeId == -1) ? 0 : activeId;

        drawOnField();

        if (clear.justTouched()) {
            fieldModel.clearVisibleLayers();
        } else if (save.justTouched()) {
            saveMap();
        } else if (exit.justTouched()) {
            exit();
        }

        draw();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        clear.dispose();
        exit.dispose();
        save.dispose();
//        load.dispose();
//        play.dispose();
    }

    private void draw() {
        Vector2 cursorPosition = MathUtils.getCursorPositionForFitViewport();
        if (activeId < 10) {
            cursorPosition.x = MathUtils.trimToGrid(cursorPosition.x);
            cursorPosition.y = MathUtils.trimToGrid(cursorPosition.y);
        } else {
            cursorPosition.x = MathUtils.clingToGrid(cursorPosition.x);
            cursorPosition.y = MathUtils.clingToGrid(cursorPosition.y);
        }
        batch.begin();
        fieldView.drawBottomLayers(batch);
        fieldView.drawTopLayers(batch);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.1f, 0.1f, 0.1f, 1);
        shapeRenderer.rect(Resolution.SCREEN_WIDTH - (Size.BLOCK * 2), Size.BLOCK * 7, Size.BLOCK * 2, Size.BLOCK * 6);
        shapeRenderer.end();

        batch.begin();
        buttonGroup.draw(batch);
        clear.draw(batch);
        save.draw(batch);
//        load.draw(batch);
//        play.draw(batch);
        exit.draw(batch);

        updateCursor();
        if (GameFieldModel.isInBattleField((int)cursorPosition.x / 8, (int)cursorPosition.y / 8)){
            cursor.draw(batch);
        }

        batch.end();
    }

    private void updateCursor() {
        Vector2 cursorPosition = MathUtils.getCursorPositionForFitViewport();
        if (activeId < TilesTypes.EAGLE_0_0) {
            cursorPosition.x = MathUtils.trimToGrid(cursorPosition.x) - 1;
            cursorPosition.y = MathUtils.trimToGrid(cursorPosition.y) - 1;
        } else {
            cursorPosition.x = MathUtils.clingToGrid(cursorPosition.x) - 9;
            cursorPosition.y = MathUtils.clingToGrid(cursorPosition.y) - 9;
        }
        if (buttonGroup.isChanged()) {
            TextureAtlas atlas = resourcesHandler.getAssetManager().get(Files.ATLASES_LOCATION + Files.ATLAS_NAME);
            if (activeId < TilesTypes.EAGLE_0_0) {
                cursor.setRegion(atlas.findRegion("cursor", 0));
                cursor.setSize(Size.TILE + 2, Size.TILE + 2);
            } else {
                cursor.setRegion(atlas.findRegion("cursor", 1));
                cursor.setSize(Size.BLOCK + 2, Size.BLOCK + 2);
            }
        }
        cursor.setPosition(cursorPosition.x, cursorPosition.y);
    }

    private void drawOnField() {
        if (Gdx.input.isTouched()) {
            Vector2 cursorPosition = MathUtils.getCursorPositionForFitViewport();
            switch (activeId) {
                case TilesTypes.NULL:
                case TilesTypes.BRICK:
                case TilesTypes.DESTROYED_BRICK:
                case TilesTypes.CONCRETE:
                case TilesTypes.WATER_1:
                case TilesTypes.ICE:
                case TilesTypes.GRASS:
                    cursorPosition.x = MathUtils.trimToGrid(cursorPosition.x) / 8;
                    cursorPosition.y = MathUtils.trimToGrid(cursorPosition.y) / 8;
                    if ((cursorPosition.x >= 2 && cursorPosition.x <= (Resolution.FIELD_WIDTH - 5))
                            && (cursorPosition.y >= 1 && cursorPosition.y <= (Resolution.FIELD_HEIGHT - 2))){
                        fieldModel.reset((byte) activeId, (int)cursorPosition.x, (int)cursorPosition.y);
                    }
                    break;
                case TilesTypes.EAGLE_0_0:
                    cursorPosition.x = MathUtils.clingToGrid(cursorPosition.x) / 8;
                    cursorPosition.y = MathUtils.clingToGrid(cursorPosition.y) / 8;
                    if (fieldModel.isNoBaseAt((int) cursorPosition.x, (int) cursorPosition.y)
                            && fieldModel.isNoBaseAt((int) cursorPosition.x - 1, (int) cursorPosition.y)
                            && fieldModel.isNoBaseAt((int) cursorPosition.x, (int) cursorPosition.y - 1)
                            && fieldModel.isNoBaseAt((int) cursorPosition.x - 1, (int) cursorPosition.y - 1)) {

                        if ((cursorPosition.x >= 2 && cursorPosition.x <= (Resolution.FIELD_WIDTH - 5))
                                && (cursorPosition.y >= 1 && cursorPosition.y <= (Resolution.FIELD_HEIGHT - 2))){
                            fieldModel.drawBase((int)cursorPosition.x - 1, (int)cursorPosition.y - 1);
                        }
                    }
                    break;
                case TilesTypes.BRICK * 10:
                case TilesTypes.DESTROYED_BRICK * 10:
                case TilesTypes.CONCRETE * 10:
                case TilesTypes.WATER_1 * 10:
                case TilesTypes.ICE * 10:
                case TilesTypes.GRASS * 10:
                    cursorPosition.x = MathUtils.clingToGrid(cursorPosition.x) / 8;
                    cursorPosition.y = MathUtils.clingToGrid(cursorPosition.y) / 8;
                    if ((cursorPosition.x >= 2 && cursorPosition.x <= (Resolution.FIELD_WIDTH - 5))
                            && (cursorPosition.y >= 1 && cursorPosition.y <= (Resolution.FIELD_HEIGHT - 2))){
                        addBlock((byte) (activeId / 10), (int)cursorPosition.x - 1, (int)cursorPosition.y - 1);
                    }
                    break;
            }
        }
    }

    private void addBlock(byte type, int x, int y) {
        if (x >= Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.x) {
            x = Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.x - 1;
        } else if (x < Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.x) {
            x = Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.x;
        }
        if (y >= Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.y) {
            y = Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.y - 1;
        } else if (y < Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.y) {
            y = Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.y;
        }
        fieldModel.reset(type, x, y);
        fieldModel.reset(type, x + 1, y);
        fieldModel.reset(type, x, y + 1);
        fieldModel.reset(type, x + 1, y + 1);
    }

    private void initUi() {
        TextureAtlas atlas = resourcesHandler.getAssetManager().get(Files.ATLASES_LOCATION + Files.ATLAS_NAME);
        fieldModel = new GameFieldModel();
        fieldView = new GameFieldView(atlas, fieldModel);
        sprites = fieldView.getSprites();

        clear = new MOButton(resourcesHandler.font32, "CLEAR", Resolution.SCREEN_WIDTH - 1, Size.BLOCK * 6);
        clear.setAlignment(Align.right);
        clear.setColor(Color.BLACK);
        clear.setHoverColor(UiColors.TTG_RED);
        clear.setScaleY(0.25f);
        clear.setScaleX(0.23f);

        save = new MOButton(resourcesHandler.font32, "SAVE", Resolution.SCREEN_WIDTH - 2, Size.TILE * 3);
        save.setAlignment(Align.right);
        save.setColor(Color.BLACK);
        save.setHoverColor(Color.GREEN);
        save.setScaleY(0.27f);
        save.setScaleX(0.27f);

        exit = new MOButton(resourcesHandler.font32, "EXIT", Resolution.SCREEN_WIDTH - 2, Size.TILE * 1);
        exit.setAlignment(Align.right);
        exit.setColor(Color.BLACK);
        exit.setHoverColor(UiColors.TTG_RED);
        exit.setScale(0.3f);

        initBlocksButtons(atlas);

        cursor = new Sprite(atlas.findRegion("cursor", 0));
        cursor.setBounds(0, 0 , Size.TILE + 2, Size.TILE + 2);
    }

    private void initBlocksButtons(TextureAtlas atlas) {
        buttonGroup = new MORadioButtonGroup();

        //Row 1
        MOImgRadioButton brick_block = new MOImgRadioButton(sprites[TilesTypes.BRICK], buttonGroup);
        brick_block.setId(TilesTypes.BRICK * 10);
        brick_block.setRepeat(2);
        brick_block.setPosition(Resolution.SCREEN_WIDTH - (Size.BLOCK * 2) + 1, Size.BLOCK * 11 + 1);

        MOImgRadioButton destroyed_brick_block = new MOImgRadioButton(sprites[TilesTypes.DESTROYED_BRICK], buttonGroup);
        destroyed_brick_block.setId(TilesTypes.DESTROYED_BRICK * 10);
        destroyed_brick_block.setRepeat(2);
        destroyed_brick_block.setPosition(Resolution.SCREEN_WIDTH - Size.BLOCK + 1, Size.BLOCK * 11 + 1);

        //Row 2
        MOImgRadioButton concrete_block = new MOImgRadioButton(sprites[TilesTypes.CONCRETE], buttonGroup);
        concrete_block.setId(TilesTypes.CONCRETE * 10);
        concrete_block.setRepeat(2);
        concrete_block.setPosition(Resolution.SCREEN_WIDTH - (Size.BLOCK * 2) + 1, Size.BLOCK * 10 + 1);

        MOImgRadioButton water_block = new MOImgRadioButton(sprites[TilesTypes.WATER_1], buttonGroup);
        water_block.setId(TilesTypes.WATER_1 * 10);
        water_block.setRepeat(2);
        water_block.setPosition(Resolution.SCREEN_WIDTH - Size.BLOCK + 1, Size.BLOCK * 10 + 1);

        //Row 3
        MOImgRadioButton ice_block = new MOImgRadioButton(sprites[TilesTypes.ICE], buttonGroup);
        ice_block.setId(TilesTypes.ICE * 10);
        ice_block.setRepeat(2);
        ice_block.setPosition(Resolution.SCREEN_WIDTH - (Size.BLOCK * 2) + 1, Size.BLOCK * 9 + 1);

        MOImgRadioButton grass_block = new MOImgRadioButton(sprites[TilesTypes.GRASS], buttonGroup);
        grass_block.setId(TilesTypes.GRASS * 10);
        grass_block.setRepeat(2);
        grass_block.setPosition(Resolution.SCREEN_WIDTH - Size.BLOCK + 1, Size.BLOCK * 9 + 1);

        Array<MOImgRadioButton> buttons = buttonGroup.getButtons();
        for (int i = 0; i < buttons.size; i++) {
            buttons.get(i).setSize(Size.TILE - 1, Size.TILE - 1);
        }

        TextureRegion region = new TextureRegion(atlas.findRegion("eagle"));
        MOImgRadioButton eagle = new MOImgRadioButton(region, buttonGroup);
        eagle.setId(TilesTypes.EAGLE_0_0);
        eagle.setPosition(Resolution.SCREEN_WIDTH - Size.BLOCK - (Size.BLOCK / 2) + 1, Size.BLOCK * 12 + 1);
        eagle.setSize(Size.BLOCK - 2, Size.BLOCK - 2);

        //Row 1
        MOImgRadioButton brick = new MOImgRadioButton(sprites[TilesTypes.BRICK], buttonGroup);
        brick.setId(TilesTypes.BRICK);
        brick.setPosition(Resolution.SCREEN_WIDTH - (Size.BLOCK * 2) + 2, (Size.BLOCK * 8) + (Size.TILE / 2));

        MOImgRadioButton destroyed_brick = new MOImgRadioButton(sprites[TilesTypes.DESTROYED_BRICK], buttonGroup);
        destroyed_brick.setId(TilesTypes.DESTROYED_BRICK);
        destroyed_brick.setPosition(Resolution.SCREEN_WIDTH - Size.BLOCK - (Size.TILE / 2), (Size.BLOCK * 8) + (Size.TILE / 2));

        MOImgRadioButton concrete = new MOImgRadioButton(sprites[TilesTypes.CONCRETE], buttonGroup);
        concrete.setId(TilesTypes.CONCRETE);
        concrete.setPosition(Resolution.SCREEN_WIDTH - 2 - Size.TILE , (Size.BLOCK * 8) + (Size.TILE / 2));

        //Row 2
        MOImgRadioButton water = new MOImgRadioButton(sprites[TilesTypes.WATER_1], buttonGroup);
        water.setId(TilesTypes.WATER_1);
        water.setPosition(Resolution.SCREEN_WIDTH - (Size.BLOCK * 2) + 2, (Size.BLOCK * 7) + (Size.TILE / 2));

        MOImgRadioButton grass = new MOImgRadioButton(sprites[TilesTypes.GRASS], buttonGroup);
        grass.setId(TilesTypes.GRASS);
        grass.setPosition(Resolution.SCREEN_WIDTH - Size.BLOCK - (Size.TILE / 2), (Size.BLOCK * 7) + (Size.TILE / 2));

        MOImgRadioButton ice = new MOImgRadioButton(sprites[TilesTypes.ICE], buttonGroup);
        ice.setId(TilesTypes.ICE);
        ice.setPosition(Resolution.SCREEN_WIDTH - 2 - Size.TILE, (Size.BLOCK * 7) + (Size.TILE / 2));

        for (int i = 0; i < buttons.size; i++) {
            buttons.get(i).setColor(UiColors.TTG_LIGHT_GREY);
            buttons.get(i).setActiveColor(Color.WHITE);
            buttons.get(i).setHoverColor(Color.WHITE);
        }
    }

    private boolean saveMap() {
        byte[][] map = fieldModel.getMap();
        if (Gdx.files.isExternalStorageAvailable()) {
            String mapName = "map_" + Math.abs(map.hashCode());
            if (!Gdx.files.external(Files.EXTERNAL_MAPS_LOCATION + mapName + Files.MAP_SUFFIX).exists()) {
                StringBuilder builder = new StringBuilder();
                for (int i =  map.length - 1; i >= 0; i--) {
                    for (int j = 0; j < map[0].length; j++) {
                        builder.append(map[i][j]);
                        builder.append(' ');
                    }
                    builder.append("\n");
                }
                Gdx.files.external(Files.EXTERNAL_MAPS_LOCATION + mapName + Files.MAP_SUFFIX).writeString(builder.toString(),false);
                return true;
            }
        }
        return false;
    }

    private void exit() {
        game.setScreen(menuScreen);
    }
}
