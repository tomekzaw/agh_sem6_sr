package data;

import lombok.Value;

@Value
public class ServerResponse {
    String productName;
    Integer price;
    Integer queries;

    @Override
    public String toString() {
        String priceInfo;
        if (price == null) {
            priceInfo = "No prices available for " + productName;
        } else {
            priceInfo = "Price of " + productName + " is " + Integer.toString(price);
        }

        String queriesInfo;
        if (queries == null) {
            queriesInfo = "unknown number of queries before";
        } else {
            queriesInfo = Integer.toString(queries) + " queries before";
        }

        return priceInfo + " (" + queriesInfo + ")";
    }
}
