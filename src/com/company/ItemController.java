package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class ItemController {
    public static void showItem(ItemOfList item){
        System.out.format("%-24s|",item.getDeviceName());
        System.out.format("%-25s|", item.getManufacturer());
        System.out.format("%-25s|", item.getMainTechSpec());
        System.out.format("%-13s|", item.getDateWarranty());
        System.out.format("%-8s|",item.getPrice());
    }

    private static boolean isValidStr(String str){
        return (str.length() != 0 && str.length() < 25);
    }

    private static boolean isValidPrice(String str){
        boolean isInCorrect = true;
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e){
            isInCorrect = false;
        }
        if (isInCorrect && Integer.parseInt(str) < 0){
            isInCorrect = false;
        }
        return isInCorrect;
    }

    private static String repeatInput(Scanner input){
        System.out.print("Введите корректно данные: ");
        return input.nextLine();
    }

    private static String findIndex(Scanner input){
        String str = input.nextLine();
        while(!isValidIndex(str)){
            str = repeatInput(input);
        }
        return str;
    }

    public static int findPrice(Scanner input){
        String str = input.nextLine();
        while (!isValidPrice(str)){
            str = repeatInput(input);
        }
        return Integer.parseInt(str);
    }

    public static String findLine(Scanner input){
        String str = input.nextLine();
        while (!isValidStr(str)){
            str = repeatInput(input);
        }
        return str;
    }

    public static String findDate(Scanner input){
        String str = input.nextLine();
        while (!isValidDate(str)){
            str = repeatInput(input);
        }
        return str;
    }

    private static boolean isValidDate(String str){
        boolean isValid = true;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.setLenient(false);
        try{
            format.parse(str);
        }catch (ParseException e){
            isValid = false;
        }

        return isValid;
    }

    private static boolean isValidIndex(String str){
        boolean isInCorrect = true;
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e){
            System.out.println("Введите индекс корректно: ");
            isInCorrect = false;
        }
        return isInCorrect;
    }

    public static void addItem(Scanner input, LinkedList<ItemOfList> list){
        ItemOfList item = new ItemOfList();
        System.out.print("Введите название: ");
        item.setDeviceName(findLine(input));
        System.out.print("Введите производителя: ");
        item.setManufacturer(findLine(input));
        System.out.print("Введите главную техническую характеристику: ");
        item.setMainTechSpec(findLine(input));
        System.out.print("Введите до какого числа гарантия в формате - \"dd-MM-yyyy\": ");
        item.setDateWarranty(findDate(input));
        System.out.print("Введите цену: ");
        item.setPrice(findPrice(input));
        ItemsList.addToListWithoutIndex(list, item);
        System.out.println("Успешно добавлено.");
    }

    public static void deleteItem(Scanner input, LinkedList<ItemOfList> list){
        ItemsList.showList(list);
        if (list.size() != 0) {
            System.out.println("Выберите индекс устройства: ");
            ItemsList.removeFromListByIndex(list, Integer.parseInt(findIndex(input))-1);
            System.out.println("Успешно удалено.");
        }
    }
}
