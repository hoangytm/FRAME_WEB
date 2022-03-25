package hoangytm.HandleExceptionSpringSecurity.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Slf4j
public class Utils {

    public static String givenRequestParam_whenUTF8Scheme_thenEncode(String baseUrl, Map<String, ?> requestParams) {
        return requestParams.keySet().stream()
                .map(key -> {
                    if (requestParams.get(key) instanceof List) {
                        return joinList((List) requestParams.get(key), key);
                    }
                    return key + "=" + encodeValue(requestParams.get(key).toString());
                })
                .collect(joining("&", baseUrl + "?", ""));

    }

    private static String encodeValue(String value) {
        try {

            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public static List<String> split(String input, String delimiter) {
        if (!H.isTrue(input)) return null;
        return Arrays.stream(input.split(delimiter)).filter(H::isTrue).collect(Collectors.toList());
    }

    public static String joinList(List lst, String key) {
        if (H.isTrue(lst) && H.isTrue(key)) {
            return lst.stream().map(item -> key + "=" + encodeValue(item.toString())).collect(joining("&", "", "")).toString();
        }
        return "";
    }
}
