package com.company;

public class Lab_1 {

    public static void main(String[] args) {
        Point3d one = new Point3d(-2, 1, 2);
        Point3d two = new Point3d(-2, 1, 2);
        Point3d three = new Point3d(1, 0, 9);
        if(
                Point3d.sravtoch(one,two)==true  || Point3d.sravtoch(two,three)==true  || Point3d.sravtoch(one,two)==true)
            System.out.println("Координаты точек равны");
        else {
            double S = computeArea(one, two, three);
            System.out.println(S);
        }
    }

    public static double computeArea(Point3d a, Point3d b, Point3d c) {
        double xa = a.getXcoord();
        double xb = b.getXcoord();
        double xc = c.getXcoord();
        double ya = a.getYcoord();
        double yb = b.getYcoord();
        double yc = c.getYcoord();
        double za = a.getZcoord();
        double zb = b.getZcoord();
        double zc = c.getZcoord();
        double dab = Math.sqrt(Math.pow((xb - xa), 2) + Math.pow((yb - ya), 2) + Math.pow((zb - za), 2));
        double dac = Math.sqrt(Math.pow((xc - xa), 2) + Math.pow((yc - ya), 2) + Math.pow((zc - za), 2));
        double dbc = Math.sqrt(Math.pow((xc - xb), 2) + Math.pow((yc - yb), 2) + Math.pow((zc - zb), 2));
        double p = (dab + dac + dbc) / 2;
        double s = Math.sqrt(p * (p - dab) * (p - dac) * (p - dbc));
        return s;
    }
}
