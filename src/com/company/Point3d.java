package com.company;

public class Point3d {
    private double xcoord;
    private double ycoord;
    private double zcoord;

    public Point3d(double x, double y, double z) {
        xcoord = x;
        ycoord = y;
        zcoord = z;
    }

    public Point3d() {
        this(0, 0, 0);
    }

    public double getXcoord() {
        return xcoord;
    }

    public double getYcoord() {
        return ycoord;
    }

    public double getZcoord() {
        return zcoord;
    }

    public void setXcoord(double a) {
        xcoord = a;
    }

    public void setYcoord(double a) {
        ycoord = a;
    }

    public void setZcoord(double a) {
        zcoord = a;
    }

    public static   boolean sravtoch(Point3d a, Point3d b){
        boolean s=false;
        if (a.xcoord==b.xcoord && a.zcoord==b.zcoord && a.ycoord==b.ycoord) s=true;
        return s;
    }
    public double distanceTo(Point3d a, Point3d b) {
        double xa=a.getXcoord();
        double xb=b.getXcoord();
        double ya=a.getYcoord();
        double yb=b.getYcoord();
        double za=a.getZcoord();
        double zb=b.getZcoord();
        return Math.sqrt(( Math.pow((xb-xa), 2))+(Math.pow((yb-ya), 2))+(Math.pow((zb-za), 2)));


    }
}

