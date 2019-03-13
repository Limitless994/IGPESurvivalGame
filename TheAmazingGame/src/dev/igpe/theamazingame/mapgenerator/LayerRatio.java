package dev.igpe.theamazingame.mapgenerator;

public final class LayerRatio {

    public final class SKY {

        public static final double NOISE_CLOUDCACTUS_HIGHER = -0.25;
        public static final double NOISE_INFINITEFALL_LOWER = -0.25;

        public static final int RATIO_CLOUDCACTUS = 50;

        public static final int MAX_STAIRS_COUNT = 2;
    }

    public final class GROUND {

        public static final double NOISE_WATER_LOWER = -0.5;

        public static final double NOISE_ROCK_LOWER = -1.5;
        public static final double NOISE_ROCK_HIGHER = 0.5;

        public static final int RATIO_SAND = 2800;
        public static final int RATIO_TREE = 400;
        public static final int RATIO_FLOWER = 400;
        public static final int RATIO_CACTUS = 100;


        public static final int MAX_STAIRS_COUNT = 2;
    }

    public final class UNDERGROUND{
        public static final int DEPTH_LAVA = 2;

        public static final double NOISE_LIQUID_HIGHER = -2;

        public static final double NOISE_DIRT_HIGHER = -2;

        public static final double NOISE_DIRT_MVAL = -1.7;
        public static final double NOISE_DIRT_NVAL = -1.4;

        public static final int RATIO_IRONORE = 400;

        public static final int MAX_STAIRS_COUNT = 4;
    }

}
