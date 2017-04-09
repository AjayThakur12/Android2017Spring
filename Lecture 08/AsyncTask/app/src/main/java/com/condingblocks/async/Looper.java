package com.condingblocks.async;

/**
 * Created by rishabhkhanna on 25/03/17.
 */

public class Looper {
    Looper l = new Looper(20);

    l.setOnTickListner(new onTickListner(){
        @Overrride
        void onTick(){

        }
    })

            l.start();

    l.stop();

}
