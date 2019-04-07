package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PointTest {

  @Test
  public void testDistance() {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(2,2);
    Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    Point p3 = new Point(2, 1);
    Point p4 = new Point(1,2);
    Assert.assertEquals(p3.distance(p4), 1.4142135623730951);
    Point p5 = new Point(5, 5);
    Point p6 = new Point(5,5);
    Assert.assertEquals(p5.distance(p6), 0.0);
    Point p7 = new Point(11, 1);
    Point p8 = new Point(111,1111);
    Assert.assertEquals(p7.distance(p8), 1114.495401515861);
    Point p9 = new Point(231, 3412);
    Point p10 = new Point(342,343);
    Assert.assertEquals(p9.distance(p10), 3071.0066753427936);
  }
}