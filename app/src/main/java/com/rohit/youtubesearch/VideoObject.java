package com.rohit.youtubesearch;

public class VideoObject {
    private String title;
    private String channel ;
    private String description;
    private String thumbnail;
    public VideoObject(String title, String channel, String description, String thumbnail) {
        this.title = title ;
        this.channel = channel ;
        this.description = description ;
        this.thumbnail = thumbnail ;
    }
    public String getChannel() {
        return channel;
    }
    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

}
