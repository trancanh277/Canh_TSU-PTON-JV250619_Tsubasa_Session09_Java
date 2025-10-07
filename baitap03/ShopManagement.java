package ra.presentation;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {
    public static Categories[] arrCategories = new Categories[100];
    public static int currentIndexCatalog = 0;
    public static Product[] arrProducts = new Product[100];
    public static int currentIndexProduct = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***********SHOP MENU****************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayCatalogMenu(scanner);
                    break;
                case 2:
                    displayProductMenu(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayCatalogMenu(Scanner scanner) {
        do {
            System.out.println("***********CATEGORIES MENU******************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inputListCategories(scanner);
                    break;
                case 2:
                    displayListCategories();
                    break;
                case 3:
                    updateCatalog(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    updateCatalogStatus(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (true);
    }

    public static void inputListCategories(Scanner scanner) {
        System.out.println("Nhập vào số danh mục cần nhập thông tin:");
        int cntCategories = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < cntCategories; i++) {
            arrCategories[currentIndexCatalog] = new Categories();
            arrCategories[currentIndexCatalog].inputData(scanner);
            currentIndexCatalog++;
        }
    }

    public static void displayListCategories() {
        for (int i = 0; i < currentIndexCatalog; i++) {
            arrCategories[i].displayData();
        }
    }

    public static void updateCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = findIndexByCatalogId(catalogId);
        if (indexUpdate == -1) {
            System.out.println("Mã danh mục không tồn tại");
            return;
        }
        //Tiến hành cập nhật
        boolean isNotExit = true;
        do {
            System.out.println("1. Cập nhật tên danh mục");
            System.out.println("2. Cập nhật mô tả danh mục");
            System.out.println("3. Cập nhật trạng thái danh mục");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.printf("Tên danh mục hiện tại: %s. Nhập vào tên danh mục mới:\n", arrCategories[indexUpdate].getCatalogName());
                    arrCategories[indexUpdate].setCatalogName(scanner.nextLine());
                    break;
                case 2:
                    System.out.printf("Mô tả danh mục hiện tại: %s. Nhập vào mô tả mới của danh mục:\n", arrCategories[indexUpdate].getDescription());
                    arrCategories[indexUpdate].setDescription(scanner.nextLine());
                    break;
                case 3:
                    System.out.printf("Trạng thái danh mục hiện tại: %s. Nhập trạng thái mới cảu danh mục:\n",
                            arrCategories[indexUpdate].isCatalogStatus() ? "Hoạt động" : "Không hoạt động");
                    arrCategories[indexUpdate].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
                    break;
                case 4:
                    isNotExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-4");
            }
        } while (isNotExit);
    }

    public static int findIndexByCatalogId(int catalogId) {
        for (int i = 0; i < currentIndexCatalog; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexDelete = findIndexByCatalogId(catalogId);
        if (indexDelete == -1) {
            System.err.println("Mã danh mục không tồn tại");
            return;
        }
        //Danh mục đã chứa sản phẩm chưa --> không chứa sản phẩm thì mới thực hiện xóa
        boolean hasProduct = false;
        //currentIndexProdct = số sản phẩm mình đang lưu trữ trong mảng product
        for (int i = 0; i < currentIndexProduct; i++) {
            if (arrProducts[i].getCatalogId() == catalogId) {
                hasProduct = true;
                break;
            }
        }
        if (hasProduct) {
            System.err.println("Danh mục đã chứa sản phẩm, không thể xóa được");
            return;
        }
        //Thực hiện xóa
        for (int i = indexDelete; i < currentIndexCatalog; i++) {
            arrCategories[i] = arrCategories[i + 1];
        }
        currentIndexCatalog--;
    }

    public static void updateCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật trạng thái:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = findIndexByCatalogId(catalogId);
        if (indexUpdate == -1) {
            System.err.println("Mã danh mục không tồn tại.");
            return;
        }
        //Cập nhật trạng thái: true --> false và ngược lại
        arrCategories[indexUpdate].setCatalogStatus(!arrCategories[indexUpdate].isCatalogStatus());
    }

    public static void displayProductMenu(Scanner scanner) {
        do {
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inputListProduct(scanner);
                    break;
                case 2:
                    displayListProduct();
                    break;
                case 3:
                    sortProductByPrice();
                    break;
                case 4:
                    updateProduct(scanner);
                    break;
                case 5:
                    deleteProduct(scanner);
                    break;
                case 6:
                    searchProductByName(scanner);
                    break;
                case 7:
                    searchProductBetweenPrice(scanner);
                    break;
                case 8:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        } while (true);
    }

    public static void inputListProduct(Scanner scanner) {
        System.out.println("Nhập số sản phẩm cần nhập thông tin:");
        int cntProduct = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < cntProduct; i++) {
            arrProducts[currentIndexProduct] = new Product();
            arrProducts[currentIndexProduct].inputData(scanner);
            currentIndexProduct++;
        }
    }

    public static void displayListProduct() {
        for (int i = 0; i < currentIndexProduct; i++) {
            arrProducts[currentIndexProduct].displayData();
        }
    }

    public static void sortProductByPrice() {
        for (int i = 0; i < currentIndexProduct - 1; i++) {
            for (int j = i + 1; j < currentIndexProduct; j++) {
                if (arrProducts[i].getPrice() > arrProducts[j].getPrice()) {
                    Product temp = arrProducts[i];
                    arrProducts[i] = arrProducts[j];
                    arrProducts[j] = temp;
                }
            }
        }
    }

    public static void updateProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật:");
        String productId = scanner.nextLine();
        int indexUpdate = findIndexByProductId(productId);
        if (indexUpdate == -1) {
            System.err.println("Mã sản phẩm không tồn tại, vui lòng nhập lại");
            return;
        }
        //Cập nhật
        boolean isNotExit = true;
        do {
            System.out.println("1. Cập nhật tên sản phẩm");
            System.out.println("2. Cập nhật giá sản phẩm");
            System.out.println("3. Cập nhật mô tả sản phẩm");
            System.out.println("4. Cập nhật danh mục sản phẩm");
            System.out.println("5. Cập nhật trạng thái sản phẩm");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập vào tên sản phẩm mới:");
                    arrProducts[indexUpdate].setProductName(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Nhập vào giá sản phẩm mới:");
                    arrProducts[indexUpdate].setPrice(Float.parseFloat(scanner.nextLine()));
                    break;
                case 3:
                    System.out.println("Nhập vào mô tả mới của sản phẩm:");
                    arrProducts[indexUpdate].setDescription(scanner.nextLine());
                    break;
                case 4:
                    int catalogIdUpdate = arrProducts[indexUpdate].choiceCatalogId(scanner, arrCategories, currentIndexCatalog);
                    arrProducts[indexUpdate].setCatalogId(catalogIdUpdate);
                    break;
                case 5:
                    isNotExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (isNotExit);
    }

    public static int findIndexByProductId(String productId) {
        for (int i = 0; i < currentIndexProduct; i++) {
            if (arrProducts[i].getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần xóa:");
        String productId = scanner.nextLine();
        int indexDelete = findIndexByProductId(productId);
        if (indexDelete == -1) {
            System.err.println("Mã sản phẩm không tồn tại, vui lòng nhập lại");
            return;
        }
        for (int i = indexDelete; i < currentIndexProduct; i++) {
            arrProducts[i] = arrProducts[i + 1];
        }
        currentIndexProduct--;
    }

    public static void searchProductByName(Scanner scanner) {
        int cntProduct = 0;
        System.out.println("Nhập vào tên sản phẩm cần tìm:");
        String productName = scanner.nextLine();
        for (int i = 0; i < currentIndexProduct; i++) {
            if (arrProducts[i].getProductName().toLowerCase().contains(productName.toLowerCase())) {
//                System.out.printf("Tên SP: %s - Giá: %.1f - Trạng thái: %s\n",
//                        arrProducts[i].getProductName(), arrProducts[i].getPrice(),
//                        arrProducts[i].getProductStatus() == 1 ? "Đang bán" : (arrProducts[i].getProductStatus() == 2 ? "Hết hàng" : "Không bán"));
                arrProducts[i].displayData();
                cntProduct++;
            }
        }
        System.out.printf("Tìm thấy %d sản phẩm thỏa mãn yêu cầu\n", cntProduct);
    }

    public static void searchProductBetweenPrice(Scanner scanner) {
        //Bắt buộc fromPrice phải nhỏ hơn hoặc bằng toPrice
        float fromPrice, toPrice;
        do {
            System.out.println("Nhập vào giá từ:");
            fromPrice = Float.parseFloat(scanner.nextLine());
            System.out.println("Nhập vào giá đến:");
            toPrice = Float.parseFloat(scanner.nextLine());
            if (fromPrice <= toPrice) {
                break;
            }
            System.err.println("Giá từ phải nhỏ hơn hoặc bằng giá đến, vui lòng nhập lại");
        } while (true);

        for (int i = 0; i < currentIndexProduct; i++) {
            if (arrProducts[i].getPrice() >= fromPrice && arrProducts[i].getPrice() <= toPrice) {
                arrProducts[i].displayData();
            }
        }
    }
}
