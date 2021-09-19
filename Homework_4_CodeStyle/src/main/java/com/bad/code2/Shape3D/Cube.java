package com.bad.code2.Shape3D;

import com.bad.code2.Shape3D.Shape3D;

/**
 * Класс для создания и работы с кубами
 */
public class Cube implements Shape3D {
    /**
     * Значение начальной координаты по оси X
     */
    private Double x;

    /**
     * Значение начальной координаты по оси Y
     */
    private Double y;

    /**
     * Значение начальной координаты по оси Z
     */
    private Double z;

    /**
     * Длина грани куба
     */
    private Double edgeSize;

    public Cube(Double centerX, Double centerY, Double centerZ, Double s) {
        this.x = centerX;
        this.y = centerY;
        this.z = centerZ;
        this.edgeSize = s;
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
    public Double getZ() {
        return z;
    }

    @Override
    public Double getVolume() {
        return Math.pow(edgeSize, 3);
    }
}
