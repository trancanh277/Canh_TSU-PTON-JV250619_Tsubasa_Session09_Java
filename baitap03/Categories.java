package ra.entity;

import ra.presentation.ShopManagement;

import java.util.Scanner;

public class Categories implements IShop {
    //1. Attributes/Fields/Properties
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    //2. Constructor
    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    //3. Methods
    //3.1. Getter/Setter
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    //3.2. Method: Hành vi của đối tượng
    public void inputData(Scanner scanner) {
        this.catalogId = autoIncrementCatalogId(ShopManagement.arrCategories, ShopManagement.currentIndexCatalog);
        this.catalogName = inputCatalogName(scanner, ShopManagement.arrCategories, ShopManagement.currentIndexCatalog);
        this.description = inputDescription(scanner);
        this.catalogStatus = inputCatalogStatus(scanner);
    }

    //Phương thức tăng tự động cho mã danh mục
    private int autoIncrementCatalogId(Categories[] arrCategories, int currentIndexCatalog) {
        //Lấy mã danh mục lớn nhất hiện có trong arrCategories + 1
        if (currentIndexCatalog == 0) {
            //Đây là danh mục đầu tiên nhập vào --> mã = 1
            return 1;
        }
        //Trường hợp đã chứa danh mục --> maxId + 1
        int maxId = arrCategories[0].catalogId;
        for (int i = 1; i < currentIndexCatalog; i++) {
            if (arrCategories[i].catalogId > maxId) {
                maxId = arrCategories[i].catalogId;
            }
        }
        return maxId + 1;
    }

    private String inputCatalogName(Scanner scanner, Categories[] arrCategories, int currentIndexCatalog) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            String catalogName = scanner.nextLine();
            //<=50
            if (catalogName.length() > 50) {
                System.err.println("Tên danh mục tối đa 50 ký tự, vui lòng nhập lại");
                continue;
            }
            //Không trùng lặp
            boolean isExist = false;
            for (int i = 0; i < currentIndexCatalog; i++) {
                if (arrCategories[i].catalogName.equals(catalogName)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
                continue;
            }
            return catalogName;
        } while (true);
    }

    private String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả của danh mục:");
        return scanner.nextLine();
    }

    private boolean inputCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục (true|false):");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            }
        } while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("Mã DM: %d - Tên DM: %s - Mô tả: %s - Trạng thái: %s\n",
                this.catalogId, this.catalogName, this.description, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
