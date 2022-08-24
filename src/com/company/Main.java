package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        showWelcomeMsg();
        Scanner input = new Scanner(System.in);
        LinkedList<ItemOfList> list = ItemsList.createList();
        String key;
        do {
            HelpController.showMainMenu();
            key = HelpController.findMenuKey(input);
            switch (key){
                case ("1") -> ItemsList.showList(list);
                case ("2") -> ItemController.addItem(input, list);
                case ("3") -> ItemsList.editInListWithIndex(list, input);
                case ("4") -> ItemController.deleteItem(input, list);
                case ("5") -> ItemsList.sortList(list);
                case ("6") -> ItemsList.saveList(list, input);
                case ("7") -> list = ItemsList.readList(input);
                case ("8") -> System.out.println("Выход...");
                default -> System.out.println("Выберите корректный пункт меню.");
            }
        } while (!key.equals("8"));
        input.close();
    }


    public static void showWelcomeMsg(){
        System.out.println("Добро пожловать в приложение.");
    }
}
