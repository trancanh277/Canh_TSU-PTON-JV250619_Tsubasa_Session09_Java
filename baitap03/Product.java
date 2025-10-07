package ra.entity;

import ra.presentation.ShopManagement;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IShop {
    //1. Attributes
    private String productId;
    private String productName;
    private float price;
    private String description;
    private int catalogId;
    private int productStatus;
    //2. Constructors

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    //3. Methods

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner) {
        this.productId = inputProductId(scanner, ShopManagement.arrProducts, ShopManagement.currentIndexProduct);
        this.productName = inputProductName(scanner, ShopManagement.arrProducts, ShopManagement.currentIndexProduct);
        this.price = inputPrice(scanner);
        this.description = inputDescription(scanner);
        this.catalogId = choiceCatalogId(scanner, ShopManagement.arrCategories, ShopManagement.currentIndexCatalog);
        this.productStatus = inputProductStatus(scanner);
    }

    private String inputProductId(Scanner scanner, Product[] arrProducts, int currentIndexProduct) {
        String productIdRegex = "^(C|S|A)\\d{3}$";
        System.out.println("Nhập vào mã sản phẩm:");
        do {
            String productId = scanner.nextLine();
            if (!Pattern.matches(productIdRegex, productId)) {
                System.err.println("Mã sản phẩm phải bắt đầu là C|S|A, 3 ký tự sau là số, vui lòng nhập lại");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < currentIndexProduct; i++) {
                if (arrProducts[i].getProductId().equals(productId)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                continue;
            }
            return productId;
        } while (true);
    }

    private String inputProductName(Scanner scanner, Product[] arrProducts, int currentIndexProduct) {
        String productNameRegex = "^\\w{10,50}$";
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            if (!Pattern.matches(productNameRegex, productName)) {
                System.err.println("Tên sản phẩm có từ 10-50 ký tự, vui lòng nhập lại");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < currentIndexProduct; i++) {
                if (arrProducts[i].getProductName().equals(productName)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                continue;
            }
            return productName;
        } while (true);
    }

    private float inputPrice(Scanner scanner) {
        System.out.println("Nhập vào giá sản phẩm:");
        do {
            if (scanner.hasNextFloat()) {
                float price = Float.parseFloat(scanner.nextLine());
                if (price > 0) {
                    return price;
                }
                System.err.println("Giá sản phẩm phải lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    private String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả sản phẩm:");
        return scanner.nextLine();
    }

    public int choiceCatalogId(Scanner scanner, Categories[] arrCategories, int currentIndexCatalog) {
        //1. Hiển thị các danh mục đang lưu trữ trong mảng arrCategories
        /*
         * 1. Đồ uống
         * 2. Đồ ăn nhanh
         * 3. Cafe
         * Lựa chọn của bạn: 2 --> return mã danh mục của đồ ăn nhanh
         * */
        //2. Cho phép người dùng chọn danh mục của sản phẩm
        for (int i = 0; i < currentIndexCatalog; i++) {
            System.out.printf("%d. %s\n", i + 1, arrCategories[i].getCatalogName());
        }
        System.out.print("Lựa chọn của bạn:");
        int choice = Integer.parseInt(scanner.nextLine());
        return arrCategories[choice - 1].getCatalogId();
    }

    private int inputProductStatus(Scanner scanner) {
        System.out.println("Chọn trạng thái của sản phẩm:");
        System.out.println("1. Đang bán");
        System.out.println("2. Hết hàng");
        System.out.println("3. Không bán");
        System.out.print("Lựa chọn của bạn:");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Mã SP: %s - Tên SP: %s - Giá: %.1f - Mô tả: %s - Danh mục: %s - Trạng thái: %s\n",
                this.productId, this.productName, this.price, this.description,
                getCatalogNameById(this.catalogId, ShopManagement.arrCategories, ShopManagement.currentIndexCatalog),
                this.productStatus == 1 ? "Đang bán" : (this.productStatus == 2 ? "Hết hàng" : "Không bán"));
    }

    private String getCatalogNameById(int catalogId, Categories[] arrCategories, int currentIndexCatalog) {
        for (int i = 0; i < currentIndexCatalog; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return arrCategories[i].getCatalogName();
            }
        }
        return "";
    }
}
