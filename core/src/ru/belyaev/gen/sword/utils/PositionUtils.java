package ru.belyaev.gen.sword.utils;

public final class PositionUtils {
    private PositionUtils() {
    }

    public static int center(int totalSize, int objectSize) {
        return totalSize / 2 - objectSize / 2;
    }
}
