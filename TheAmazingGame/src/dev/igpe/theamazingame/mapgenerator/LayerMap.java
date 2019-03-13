package dev.igpe.theamazingame.mapgenerator;

public final class LayerMap {

    private  LayerSetting m_setting;

    private byte[] m_mapData;
    
    public LayerMap() {}

    public LayerMap(LayerSetting setting, byte[] mapData) {
        this.m_setting = setting;
        this.m_mapData = mapData;
    }

    public byte[] getMapData(){
        return this.m_mapData;
    }

    public int getHeight(){
        return this.m_setting.getHeight();
    }

    public int getWidth(){
        return this.m_setting.getWidth();
    }
    
}
