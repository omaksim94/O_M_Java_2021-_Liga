package com.bad.code2.Shape3D;

/**
 * Базовый интерфейс для создания 3D объектов
 */

public interface Shape3D {
    /**
     * Получение начальной координаты X объекта
     */
    Double getX();

    /**
     * Получение начальной координаты Y объекта
     */
    Double getY();

    /**
     * Получение начальной координаты Z объекта
     */
    Double getZ();

    /**
     * Получение объема фигуры
     *
     * @return Объем фигуры
     */
    Double getVolume();
}
