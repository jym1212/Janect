public List<Market> getAllMarkets(MarketClause clause) {
        UpbitRequestType requestType = UpbitRequestType.MARKET_ALL_V1;
        UpbitRequestQuery query = UpbitRequestQuery.builder()
                .url(UpbitRequestType.getFullUrl(requestType))
                .body(null)
                .param("isDetails=" + clause.isDetails())
                .method(HttpMethod.GET)
                .build();
        String data = upbitHttpClient.request(query);
        List<UpbitMarket> result = jsonDeserializer.deserializeAsList(data, UpbitMarket.class);
        return marketConverter.convert(result);
}
