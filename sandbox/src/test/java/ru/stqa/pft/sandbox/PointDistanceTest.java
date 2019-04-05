package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointDistanceTest {

  @Test
  public void testDistance() {
    PointDistance pd = new PointDistance(new Point(1,2), new Point(2, 1));
    Assert.assertEquals(pd.distance(), 1.4142135623730951);
    PointDistance pd1 = new PointDistance(new Point(-1,2), new Point(-2, 1));
    Assert.assertEquals(pd1.distance(), 1.4142135623730951);
    PointDistance pd2 = new PointDistance(new Point(5,5), new Point(5, 5));
    Assert.assertEquals(pd2.distance(), 0.0);
    PointDistance pd3 = new PointDistance(new Point(1,2), new Point(3, 4));
    Assert.assertEquals(pd3.distance(), 2.8284271247461903);
    PointDistance pd4 = new PointDistance(new Point(4,3), new Point(2, 1));
    Assert.assertEquals(pd4.distance(), 2.8284271247461903);
    PointDistance pd5 = new PointDistance(new Point(11,14), new Point(3, 6));
    Assert.assertEquals(pd5.distance(), 11.313708498984761);
  }
}