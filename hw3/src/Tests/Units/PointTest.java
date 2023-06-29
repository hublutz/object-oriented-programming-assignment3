package Tests.Units;

import BusinessLayer.Tiles.Point;
import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;


public class PointTest {

    private Point point;
    int x;
    int y;

    @BeforeClass
    public void initTests(){
        x=0;
        y=0;
        point = new Point(x,y);
    }

    @Test
    public void rangeTestZero(){
        Assert.assertEquals("range should be 0",0.0 , point.range(new Point(x,y)));
    }

    @Test
    public void rangeTestThree(){
        Assert.assertEquals("range should be 3",3.0 , point.range(new Point(3,0)));
    }

    @Test
    public void rangeTestTwoSqrt(){
        Assert.assertEquals("range should be 2^0.5",Math.sqrt(2) , point.range(new Point(1,1)));
    }

    @Test
    public void moveTest(){
        point.move(1,1);
        Assert.assertEquals("x should be 1",1,point.getX());
        Assert.assertEquals("y should be 1",1,point.getY());
    }
}
