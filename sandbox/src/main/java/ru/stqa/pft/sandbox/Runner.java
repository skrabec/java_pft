package ru.stqa.pft.sandbox;

public class Runner {
  public static void main(String[] args) {
    PointDistance pd = new PointDistance(new Point(1,2), new Point(2, 1));
    System.out.println("Distance between points is: " + pd);
    PointDistance pd1 = new PointDistance(new Point(-1,2), new Point(-2, 1));
    System.out.println("Distance between points is: " + pd1);
    PointDistance pd2 = new PointDistance(new Point(5,5), new Point(5, 5));
    System.out.println("Distance between points is: " + pd2);
    PointDistance pd3 = new PointDistance(new Point(1,2), new Point(3, 4));
    System.out.println("Distance between points is: " + pd3);
    PointDistance pd4 = new PointDistance(new Point(4,3), new Point(2, 1));
    System.out.println("Distance between points is: " + pd4);
    PointDistance pd5 = new PointDistance(new Point(9,8), new Point(8, 9));
    System.out.println("Distance between points is: " + pd5);
    PointDistance pd6 = new PointDistance(new Point(11,14), new Point(3, 6));
    System.out.println("Distance between points is: " + pd6);
  }
}
