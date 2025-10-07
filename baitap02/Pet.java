package baitap02;

import java.util.Scanner;

public abstract class Pet {
    // Fields
    private String petId;
    private String petName;
    private int petAge;

    // Constructors
    // Constructor default
    public Pet() {
    }

    // Constructor parameters
    public Pet(String petId, String petName, int petAge) {
        this.petId = petId;
        this.petName = petName;
        this.petAge = petAge;
    }

    // Method getter/setter
    public String getPetId() {
        return petId;
    }

    // Validation petId
    public boolean setPetId(String petId) {

        if (petId != null && petId.matches("[CD][0-9]{3}")) {
            this.petId = petId;
            return true;
        } else {
            System.err.println("Pet ID gồm 4 ký tự bắt đầu từ C hoặc D , 3 ký tự sau là số ");
            return false;
        }
    }

    public String getPetName() {
        return petName;
    }

    // Validation petName
    public boolean setPetName(String petName) {
        if (petName != null && petName.length() >= 2 && petName.length() <= 50) {
            this.petName = petName;
            return true;
        } else {
            System.err.println("Tên thú cưng phải từ 20-50 ký tự");
            return false;
        }
    }

    public int getPetAge() {
        return petAge;
    }

    // Validation petAge
    public boolean setPetAge(int petAge) {
        if (petAge > 0) {
            this.petAge = petAge;
            return true;
        } else {
            System.err.println("Tuổi của thú cưng phải lớn hơn 0");
            return false;
        }
    }

    // Method behavior
    // inputData method
    public void inputData(Scanner sc) {
        System.out.println("Nhập mã của thú cưng : ");
        while (true) {
            if (setPetId(sc.nextLine())) {
                break;
            }
        }
        System.out.println("Nhập tên của thú cưng : ");
        while (true) {
            if (setPetName(sc.nextLine())) {
                break;
            }
        }
        System.out.println("Nhập tuổi của thú cưng : ");
        while (true) {
            if (setPetAge(Integer.parseInt(sc.nextLine()))) {
                break;
            }
        }
    }

    // DisplayData method
    public void displayData() {
        System.out.println("***** Thông tin thú cưng *****");
        System.out.println("Mã ID : " + this.petId);
        System.out.println("Tên : " + this.petName);
        System.out.println("Tuổi : " + this.petAge);
        System.out.println("******************************");
    }

    // Abstract method
    public abstract void speak();

}