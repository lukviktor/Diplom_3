package stellarburgers.brauserall;

public enum Browser1 {

    CHROME("src/main/resources/chrome/chromedriver.exe", null),
    YANDEX("src/main/resources/yandex/chromedriver.exe", "C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");

    private final String driverPath;
    private final String binaryPath;

    Browser1(String driverPath, String binaryPath) {
        this.driverPath = driverPath;
        this.binaryPath = binaryPath;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getBinaryPath() {
        return binaryPath;
    }
}