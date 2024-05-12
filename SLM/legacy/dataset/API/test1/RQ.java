@Getter
public enum UpbitRequestType {
    MARKET_ALL_V1("v1/market/all", HttpMethod.GET)
    ;

    private String url;
    private HttpMethod method;

    UpbitRequestType(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }

    public static String getFullUrl(UpbitRequestType requestType) {
        return "https://api.upbit.com/" + requestType.getUrl();
    }
}