package ru.stqa.pft.sandbox;

public class Point {
  private double x;
  private double y;

  public void setX(double x) {
      this.x = x;
    }

  public double getX() {
    return x;
  }

  public void setY(double y) {
      this.y = y;
  }

  public double getY() {
    return y;
  }

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double distance (Point p){
    return Math.sqrt(
            (p.getX() - this.getX()) * (p.getX() - this.getX()) +
                    (p.getY() - this.getY()) * (p.getY() - this.getY())
    );
  }



}
