package com.example.lms;

interface Shape {

    double area();

    double perimeter();

}

class Circle implements Shape {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle implements Shape {
    private int length, breadth;

    public Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public double area() {
        return length * breadth;
    }

    @Override
    public double perimeter() {
        return 2 * (length + breadth);
    }
}

public class Test {

    @org.testng.annotations.Test
    public void test() {
        Rectangle rectangle = new Rectangle(2, 5);


        System.out.println("Area of Rectangle : " + rectangle.area());
        System.out.println("Perimeter of Rectangle : " + rectangle.perimeter());

        Circle circle = new Circle(5);
        System.out.println("Area of Circle : " + circle.area());
        System.out.println("Perimeter of Rectangle : " + circle.perimeter());
    }
}