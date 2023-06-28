package Tests.Units;

import BusinessLayer.Tiles.Point;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class PointTest {

    private Point point;
    int x;
    int y;

    @BeforeAll
    public void initTests(){
        x=0;
        y=0;
        point = new Point(x,y);
    }

    @Test
    public void rangeTestZero(){
        Assert.assertEquals(0.0 , point.range(new Point(x,y)));
    }

    @Test
    public void rangeTestThree(){
        Assert.assertEquals(3.0 , point.range(new Point(3,0)));
    }

    @Test
    public void rangeTestTwoSqrt(){
        Assert.assertEquals(Math.sqrt(2) , point.range(new Point(1,1)));
    }

    @Test
    public void moveTest(){
        point.move(1,1);
        Assert.assertEquals(1,point.getX());
        Assert.assertEquals(1,point.getY());
    }
}
