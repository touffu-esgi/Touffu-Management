package cat.touffu.management.kernel.database;

import kong.unirest.JsonNode;

import java.util.Map;


public interface JsonAdapter<To> {
    To adapt(JsonNode from);
    Map<String, Object> adapt(To to);
}
