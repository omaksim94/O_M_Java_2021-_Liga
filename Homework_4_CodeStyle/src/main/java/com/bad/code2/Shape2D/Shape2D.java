package com.bad.code2.Shape2D;

/**
 * Интерфейс для создания 2D фигур
 */
public interface Shape2D {
    /**
     * Получение начальной координаты X объекта
     */
    Double getX();

    /**
     * Получение начальной координаты Y объекта
     */
    Double getY();

    /**
     * Получение площади фигуры
     *
     * @return площадь фигуры
     */
    public Double getArea();
}
