package baitap01;
import java.util.Scanner;

public class StudentManagement {
    //Khai báo mảng sinh viên - default
    static Student[] arrStudents = new Student[100];
    static int currentIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**************QUẢN LÝ SINH VIÊN*****************");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Cập nhật thông tin sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm sinh viên theo tên");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer. parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListStudents();
                    break;
                case 2:
                    inputListStudents(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    searchStudentByName(scanner);
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (true);
    }

    public static void displayListStudents() {
        for (int i = 0; i < currentIndex; i++) {
            arrStudents[i].displayData();
        }
    }

    public static void inputListStudents(Scanner scanner) {
        System.out.println("Nhập vào số sinh viên cần nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            //Khởi tạo phần từ currentIndex là 1 đối tượng sinh viên
            arrStudents[currentIndex] = new Student();
            //Nhập thông tin phần tử sinh viên có chỉ số currentIndex
            arrStudents[currentIndex].inputData(scanner);
            currentIndex++;
        }
    }

    public static void updateStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật thông tin:");
        String studentId = scanner.nextLine();
        int indexUpdate = findIndexById(studentId);
        if (indexUpdate == -1) {
            System.out.println("Không tồn tại mã sinh viên " + studentId);
        } else {
            //Tiến hành cập nhật thông tin sinh viên
            boolean isExist = true;
            do {
                System.out.println("1. Cập nhật tên sinh viên");
                System.out.println("2. Cập nhật tuổi sinh viên");
                System.out.println("3. Cập nhật chuyên ngành sinh viên");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên sinh viên mới:");
                        arrStudents[indexUpdate].setStudentName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào tuổi mới của sinh viên:");
                        arrStudents[indexUpdate].setAge(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.println("Nhập vào chuyên ngành mới của sinh viên:");
                        arrStudents[indexUpdate].setMajor(scanner.nextLine());
                        break;
                    case 4:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");
                }
            } while (isExist);
        }
    }

    public static int findIndexById(String studentId) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrStudents[i].getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần xóa:");
        String studentId = scanner.nextLine();
        int indexDelete = findIndexById(studentId);
        if (indexDelete == -1) {
            System.out.println("Không tồn tại mã sinh viên " + studentId);
        } else {
            for (int i = indexDelete; i < currentIndex; i++) {
                arrStudents[i] = arrStudents[i + 1];
            }
            currentIndex--;
        }
    }

    public static void searchStudentByName(Scanner scanner) {
        System.out.println("Nhập vào tên sinh viên cần tìm:");
        String studentName = scanner.nextLine();
        //Tìm gần đúng
        int cntStudents = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (arrStudents[i].getStudentName().toLowerCase().contains(studentName.toLowerCase())) {
                arrStudents[i].displayData();
                cntStudents++;
            }
        }
        System.out.printf("Có %d sinh viên thỏa mãn điều kiện tìm\n", cntStudents);
    }
}
