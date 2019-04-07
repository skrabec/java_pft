package ru.stqa.pft.sandbox;

public class Runner {
  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(2,1);

    System.out.println("Distance between points is: " + p1.distance(p2));
  }
}
