package com.shakhee.contentproviderexample;

public class ContactModel {
    public ContactModel(String name, String numbers) {
        this.numbers = numbers;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String name,numbers;

}

