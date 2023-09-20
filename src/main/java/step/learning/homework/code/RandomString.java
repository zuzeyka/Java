package step.learning.homework.code;

import java.util.Random;
import java.util.Scanner;

public class RandomString {
    public static String generateString(int min, int max){

        StringBuilder randomString = new StringBuilder();

        Random random = new Random();

        // Генерируем случайную длину строки
        int length = random.nextInt(max - min + 1) + min;

        // Генерируем случайные символы и добавляем их к строке
        for (int i = 0; i < length; i++) {
            int randomAscii = random.nextInt(127 - 20 + 1) + 20;
            char randomChar = (char) randomAscii;
            randomString.append(randomChar);
        }

        // Выводим сгенерированную строку
        return randomString.toString();
    }
}
