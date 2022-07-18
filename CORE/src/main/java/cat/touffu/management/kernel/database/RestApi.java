package cat.touffu.management.kernel.database;

import kong.unirest.*;

import java.util.HashMap;
import java.util.Map;

public class RestApi {
    private final static RestApi INSTANCE = new RestApi();
    private final static String apiUrl = "http://152.228.219.241:1234";

    private RestApi() {}

    public static RestApi instance() {
        return INSTANCE;
    }

    private static String fullUrlOf(String path) {
        return apiUrl + path;
    }

    public <T> T get(String route, JsonAdapter<T> adapter) {
        var response = Unirest
                .get(fullUrlOf(route))
                .asJson().getBody();
        return adapter.adapt(response);
    }

    public <T> void post(String route, JsonAdapter<T> adapter, T toSend) {
        var json = adapter.adapt(toSend);
        Unirest.post(fullUrlOf(route))
                .header("accept", "application/json")
                .fields(adapter.adapt(toSend))
                .asJson();
    }

    public <T> void patch(String route, JsonAdapter<T> adapter, T toSend) {
        var json = adapter.adapt(toSend);
        Unirest.patch(fullUrlOf(route))
                .header("accept", "application/json")
                .fields(adapter.adapt(toSend))
                .asJson();
    }
}
