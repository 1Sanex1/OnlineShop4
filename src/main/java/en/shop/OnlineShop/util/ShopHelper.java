package en.shop.OnlineShop.util;

public class ShopHelper {
    private String key;
    private String value;

    private String sign;

    public ShopHelper() {
    }

    public ShopHelper(String key, String value, String sign) {
        this.key = key;
        this.value = value;
        this.sign = sign;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
