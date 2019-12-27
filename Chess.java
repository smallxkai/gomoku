import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 棋子类，用来表示棋盘中的棋子
 */
public class Chess extends Actor{
    //构造方法，设置棋子的图像
    public Chess(boolean color) {
        if (color) {
            setImage("white.png");
        } else {
            setImage("black.png");
        }
    }
}
