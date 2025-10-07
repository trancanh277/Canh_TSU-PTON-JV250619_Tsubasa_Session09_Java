package baitap02;

import java.util.Scanner;

public class PetManagement {
    static Scanner sc = new Scanner(System.in);
    static Pet[] arrPet = new Pet[100];
    static int currentIndex = 0;

    public static void main(String[] args) {
        do {
            System.out.println("1. Hiển thị danh sách thú cưng ");
            System.out.println("2. Thêm thú cưng ");
            System.out.println("3. Gọi tiếng kêu ");
            System.out.println("4. Xóa thú cưng ");
            System.out.println("5. Tìm thú cưng theo tên ");
            System.out.println("6. Thoát ");
            System.out.println(" Lựa chọn của bạn : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    petListDisplay();
                    break;
                case 2:
                    addPet(sc);
                    break;
                case 3:
                    speakPet();
                    break;
                case 4:
                    deletePet(sc);
                    break;
                case 5:
                    searchPet(sc);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Lựa chọn phải từ 1-6");
            }
        } while (true);

    }

    //  petListDisplay method
    public static void petListDisplay() {
        if (currentIndex == 0) {
            System.out.println("Danh sách pet trống !");
            return;
        } else {
            for (int i = 0; i < currentIndex; i++) {
                arrPet[i].displayData();
            }
        }
    }

    // Add pet method
    public static void addPet(Scanner sc) {
        System.out.println("Hãy nhập vào số thú cưng bạn muốn nhập :");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập loại thú cưng bạn muốn thêm C.cat | D.dog");
            String type = sc.nextLine();
            if (type.equalsIgnoreCase("D")) {
                arrPet[currentIndex] = new Dog();
            } else if (type.equalsIgnoreCase("C")) {
                arrPet[currentIndex] = new Cat();
            }
            arrPet[currentIndex].inputData(sc);
            currentIndex++;
        }
    }

    // sound method

    public static void speakPet() {
        for (int i = 0; i < currentIndex; i++) {
            char firstChar = arrPet[i].getPetId().charAt(0);
            if (firstChar == 'D') {
                Dog d = new Dog();
                System.out.println(arrPet[i].getPetName() + " kêu :");
                d.speak();
            } else if (firstChar == 'C') {
                Cat c = new Cat();
                System.out.println(arrPet[i].getPetName() + " kêu :");
                c.speak();
            } else {
                System.out.println("Thú cưng không xác định !");
            }
        }
    }

    // FindIndex by ID
    public static int findIndexByPetId(String petId) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrPet[i].getPetId().equals(petId)) {
                return i;
            }
        }
        return -1;
    }


    // DeletePet method
    public static void deletePet(Scanner sc) {
        System.out.println("Nhập ID thú cưng bạn muốn xóa ");
        String deletePetId = sc.nextLine();
        int indexDelete = findIndexByPetId(deletePetId);
        if (indexDelete == -1) {
            System.out.println("Không tìm thấy thú cưng với ID " + deletePetId);
        } else {
            for (int i = indexDelete; i < currentIndex; i++) {
                arrPet[i] = arrPet[i + 1];
            }
            currentIndex--;
            System.out.println("Thú cưng được xóa thành công !");
        }
    }

    // SearchPet by name
    public static void searchPet(Scanner sc) {
        System.out.println("Nhập vào tên thú cưng cần tìm :");
        String searchPet = sc.nextLine();
        for (int i = 0; i < currentIndex; i++) {
            if (arrPet[i].getPetName().toLowerCase().contains(searchPet.toLowerCase())) {
                arrPet[i].displayData();
            }
        }
    }


}
