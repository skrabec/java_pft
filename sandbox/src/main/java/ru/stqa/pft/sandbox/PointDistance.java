package ru.stqa.pft.sandbox;

public class PointDistance {
  Point p1;
  Point p2;
  public PointDistance(Point x, Point y){
    this.p1 = x;
    this.p2 = y;
  }
  public double distance (){
    return Math.sqrt(
            (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
                    (p1.getY() - p2.getY()) * (p1.getY() - p2.getY())
    );
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append((distance()));
    return sb.toString();
  }
}
