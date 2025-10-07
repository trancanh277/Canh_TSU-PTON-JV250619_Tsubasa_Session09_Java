package baitap01;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        //1. Khởi tạo đối tượng sinh viên mà không khởi tạo bất cứ thông tin nào của đối tượng
        //ClassName objectName = new Constructor(arguments)
        Student student1 = new Student();
        //2. Khởi tạo đối tượng sinh viên và khởi tạo thông tin mã sinh viên và tên sinh viên
        Student student2 = new Student("SV002", "Nguyễn Văn B");
        //3. Khởi tạo đối tượng sinh viên và khởi tạo tất cả thông tin sinh viên
        Student student3 = new Student("SV003", "Nguyễn Văn C", 22, "CNTT");
        //4. Khởi tạo đối tượng sinh viên và khởi tạo thông tin mã sinh viên, tên sinh viên, tuổi
        Student student4 = new Student("SV004", "Nguyễn Văn D", 25);
        //6. Nhập thông tin cho sinh viên 1
        Scanner scanner = new Scanner(System.in);
        student1.inputData(scanner);
        //7. Nhập thông tin tuổi và chuyên ngành cho sinh viên 2 - Sử dụng các phương thức setter để gán giá trị cho thuộc tính
        System.out.println("Nhập tuổi sinh viên 2:");
        student2.setAge(Integer.parseInt(scanner.nextLine()));
        System.out.println("Nhập chuyên ngành sinh viên 2:");
        student2.setMajor(scanner.nextLine());
        //8. Tính tuổi trung bình của sinh viên 1,2,3: dùng phương thức getter để lấy thông tin thuộc tính
        System.out.println("Tuổi trung bình sinh viên 1,2,3: " + (student1.getAge() + student2.getAge() + student3.getAge()) / 3);
        //5. In thông tin các sinh viên
        System.out.println("THÔNG TIN SINH VIÊN 1:");
        student1.displayData();
        System.out.println("THÔNG TIN SINH VIÊN 2:");
        student2.displayData();
        System.out.println("THÔNG TIN SINH VIÊN 3:");
        student3.displayData();
        System.out.println("THÔNG TIN SINH VIÊN 4:");
        student4.displayData();
    }
}