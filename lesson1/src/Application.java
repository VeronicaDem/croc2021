public class Application {
    public static void main(String[] args) {
       // TESTS
        printBytes(23);
        printBytes(1024);
        printBytes(53692044905543L);
    }
    public static void printBytes(double bytes) {
        if(bytes < 0) {
            System.out.println("Введите положительное значение");
            return;
        }
        int k = 0;
        while (bytes >= 1024) {
            bytes /= 1024;
            k++;
        }
        String type;
        switch (k) {
            case 1:
                type = "KB";
                break;
            case 2:
                type = "MB";
                break;
            case 3:
                type = "GB";
                break;
            case 4:
                type = "TB";
                break;
            case 5:
                type = "PB";
                break;
            default:
                type = "B";
        }
        System.out.println(String.format("%.1f", bytes) + " " + type);
    }
}

