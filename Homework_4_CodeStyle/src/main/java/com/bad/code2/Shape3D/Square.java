package com.bad.code2.Shape3D;

import com.bad.code2.Shape2D.Shape2D;

/**
 * Класс для создания и работы с квадратами
 */
public class Square implements Shape2D {
    /**
     * Значение начальной координаты по оси X
     */
    private Double x;

    /**
     * Значение начальной координаты по оси Y
     */
    private Double y;

    /**
     * Длина стороны квадрата
     */
    private Double edgeSize;

    public Square(Double x, Double y, Double edgeSize) {
        this.x = x;
        this.y = y;
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Double getArea() {
        return edgeSize * edgeSize;
    }
}
