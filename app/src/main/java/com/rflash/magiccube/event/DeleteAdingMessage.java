package com.rflash.magiccube.event;

/**
 * Created by lenovo on 2018/11/3.
 */

public class DeleteAdingMessage {
    int position;

    public DeleteAdingMessage( int position){
        this.position=position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
