package com.players.gif.item;

public class SubtitleItem {
    String username;
    String imgName;
    String insideWrite;
    public SubtitleItem(){

    }

    public SubtitleItem(String imgName, String username, String insideWrite){
        this.imgName = imgName;
        this.username = username;
        this.insideWrite = insideWrite;
    }
    public String getUsername(){ return username; }
    public String getInsideWrite() { return insideWrite; }
    public String getImgName(){ return imgName; }

    public void setUsername(String username) { this.username = username; }
    public void setInsideWrite(String insideWrite) { this.insideWrite = insideWrite; }
    public void setImgName(String imgName) { this.imgName = imgName; }
}
