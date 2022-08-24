package com.company;

import java.util.Scanner;

public class HelpController {
    public static void showEditingMenu(){
        System.out.println("""
                1. Изменить название.
                2. Изменить производителя.
                3. Изменить главную техническую характеристику.
                4. Изменить гарантию.
                5. Изменить цену.
                6. Отмена.
                """);
    }

    public static void showMainMenu(){
        System.out.println("""
                1. Показать список комплектующих компьютера.
                2. Добавить новое комплектующее компьютера.
                3. Изменить комплектующее.
                4. Удалить комплектующее компьютера.
                5. Упорядочить цены по возврастанию.
                6. Сохранить текущий список комплектующих.
                7. Открыть список комплектующих.
            
                8. Выход из программы.
                """);
    }

    public static String findMenuKey(Scanner input){
        return input.nextLine();
    }
}
