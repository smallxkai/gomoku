import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Font;

/**
 * 棋盘类，用来提供下棋的场所
 */
public class Board extends World{
    private int[][] value = new int[15][15];  
    private boolean color=false;            

    //构造方法，初始化棋盘
    public Board(){    
        super(15, 15, 50);   
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                value[i][j] = -1;
            }
        }

    }

    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(this)) {  
            int x = mouse.getX();
            int y = mouse.getY();
            if (x>=15) return;  
            if (value[x][y]==-1 ) {                
                if (color) {  //下白子
                    value[x][y] =1;
                    addObject(new Chess(true), x, y);
                } else {  //下黑子
                    value[x][y] =0;
                    addObject(new Chess(false), x, y);                    
                }
                color = !color;  
            }
            if(this.isWin(x,y)){
                if(value[x][y] == 1){
                    showText("White Win!" , getWidth()/2 , getHeight()/2);
                }else {
                    showText("Black Win!" , getWidth()/2 , getHeight()/2);
                }
                Greenfoot.stop();
            }
        }
    }

    public boolean isWin(int x,int y){
        boolean flag = false;
        int count = 1;  //用来保存共有相同颜色多少棋子相连，初始值为1
        int color = value[x][y];  //color = 1 (黑子) color = 2(白子)

        //判断横向是否有5个棋子相连
        count = this.checkCount(1,0,color,x,y);
        if(count >= 5){
            flag = true;
        }else {
            //判断纵向
            count = this.checkCount(0,1,color,x,y);
            if(count >= 5){
                flag = true;
            }else {
                //判断右上,左下
                count = this.checkCount(1,-1,color,x,y);
                if(count >= 5){
                    flag = true;
                }else {
                    //判断右下,左上
                    count = this.checkCount(1,1,color,x,y);
                    if(count >= 5){
                        flag =  true;
                    }
                }
            }
        }

        return flag;
    }

    public int checkCount(int xChange , int yChenge ,int color,int x,int y){
        int count = 1;
        int tempX = xChange;
        int tempy = yChenge; 
        while(x + xChange >=0 && x+xChange <15  && y+yChenge >=0 && 
        y+yChenge < 15 && color == value[x+xChange][y+yChenge]){
            // allChess[x+xChange][y+yChenge] 表示移动一个坐标来进行判断是否为同一颜色
            //例如横向判断checkCount(1,0,color); 传入xchange = 1,ychange = 0;
            //   即每次向右移动一次，如果满足同一颜色就继续判断，不满足则返回

            count++;
            if(xChange != 0)  xChange++;     // 满足条件继续向右判断
            if(yChenge != 0 ){      
                if(yChenge != 0){
                    if(yChenge > 0) {   
                        yChenge++;		//使棋子沿着右下一条线移动，进行判断	
                    }else {
                        yChenge--;		// 使棋子沿着右上一条线移动，进行判断
                    }
                }
            }

        }

        xChange = tempX;
        yChenge = tempy;   // 恢复初始值

        while(x-xChange >=0 && x-xChange <15 && y-yChenge >=0 &&
        y-yChenge <15 && color == value[x-xChange][y-yChenge]){
            count++;
            if(xChange != 0){
                xChange++;
            }
            if(yChenge != 0){
                if (yChenge > 0) {
                    yChenge++;			//使棋子沿着左上一条线移动，进行判断
                }else {
                    yChenge--;			//使棋子沿着左下一条线移动，进行判断
                }
            }
        }

        return count;
    }
}
