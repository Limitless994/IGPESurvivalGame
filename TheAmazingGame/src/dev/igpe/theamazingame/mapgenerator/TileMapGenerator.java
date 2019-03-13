package dev.igpe.theamazingame.mapgenerator;

import java.util.Random;

import dev.igpe.theamazingame.scenario.Scenario;

public final class TileMapGenerator {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        LayerSetting skyLayerSetting = new LayerSetting(128, 128, 16, 0, random);
        LayerSetting groundLayerSetting = new LayerSetting(128, 128, 16, 0, random);
        LayerSetting undergroundLayerSetting = new LayerSetting(128, 128, 16, 0, random);

        LayerGenerator skyGenerator = new LayerGenerator(skyLayerSetting);
        LayerGenerator groundGenerator = new LayerGenerator(groundLayerSetting);
        LayerGenerator undergroundGenerator = new LayerGenerator(undergroundLayerSetting);

        int widthScaleFactor = 4;
        int heightScaleFactor = 4;

        int attempt = 1;

        while (true){

            LayerMap skyMap = skyGenerator.doCreate(LayerType.SKY);
            LayerMap groundMap = groundGenerator.doCreate(LayerType.GROUND);
            LayerMap undergroundMap = undergroundGenerator.doCreate(LayerType.UNDERGROUND);

//            TileMapViewer.ViewMap("SKY - Attempt: " + attempt, heightScaleFactor, widthScaleFactor, new Scenario(skyMap.getWidth(), skyMap.getHeight(), skyMap.getMapData()));
//            TileMapViewer.ViewMap("GROUND - Attempt: " + attempt, heightScaleFactor, widthScaleFactor, new Scenario(groundMap.getWidth(), groundMap.getHeight(), groundMap.getMapData()));
//            TileMapViewer.ViewMap("UNDERGROUND - Attempt: " + attempt, heightScaleFactor, widthScaleFactor, new Scenario(undergroundMap.getWidth(), undergroundMap.getHeight(), undergroundMap.getMapData()));

            attempt++;
        }
    }

}
