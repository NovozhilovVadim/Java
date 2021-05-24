package ru.geekbrains;

public class MyArraySizeException extends Exception {
    public MyArraySizeException() {
    }

    @Override
    public String toString() {
        return "MyArraySizeException{ Ошибка Размера Архива }";
    }
}
