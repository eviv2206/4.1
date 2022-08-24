package com.company;


import java.io.Serializable;

public class ItemOfList implements Comparable<ItemOfList>, Serializable {
    private String deviceName;
    private String manufacturer;
    private String mainTechSpec;
    private String dateWarranty;
    private int price;

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setMainTechSpec(String mainTechSpec) {
        this.mainTechSpec = mainTechSpec;
    }

    public String getMainTechSpec() {
        return mainTechSpec;
    }

    public String getDateWarranty() {
        return dateWarranty;
    }

    public void setDateWarranty(String dateWarranty){
            this.dateWarranty = dateWarranty;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int compareTo(ItemOfList o) {
        return this.getPrice() - o.getPrice();
    }
}
