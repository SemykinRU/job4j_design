package ru.job4j.ood.srp;

public class ExampleTwo {
    /**
     *Наш класс нарушает принцип spr, так как знает как вычислять периметр квадрата и периметр прямоугольника,
     * нам нужно разбить данный класс, на класс который будет отвечать за реализации по квадрату и второй класс,
     * который будет отвечать за реализацию по прямоугольнику.
     *
     * */
    public int squarePerimeter(int a) {
        return 4 * a;
    }

    public int rectanglePerimeter(int a, int b) {
        return 2 * (a + b);
    }
}
