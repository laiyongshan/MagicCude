package com.rflash.magiccube.event;

/**
 * @author lys
 * @time 2018/11/2 11:25
 * @desc:
 */

public class PositionMessage {

    int position;
    boolean isChecked;

    public PositionMessage( int position,boolean isChecked){
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
