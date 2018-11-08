package com.rflash.magiccube.event;

/**
 * Created by lenovo on 2018/11/3.
 */

public class PositionMessage2 {
    int position;
    boolean isChecked;

    public PositionMessage2( int position,boolean isChecked){
        this.position=position;
        this.isChecked=isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean getChecked(){
        return isChecked;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
