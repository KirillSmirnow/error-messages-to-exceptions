package org.kirill.api.error.test.error;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.kirill.api.error.test.util.ParameterizedStringFormatter.format;

@Component
@RequiredArgsConstructor
public class ErrorMapper {

    private static final URL ERRORS_RESOURCE = ErrorMapper.class.getResource("/errors.yml");
    private static final String DEFAULT_ERROR_KEY = "default";

    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private final Map<String, String> errors = new HashMap<>();

    @PostConstruct
    public void loadErrorsDescriptions() throws Exception {
        collectErrorsDescriptions("", objectMapper.readTree(ERRORS_RESOURCE));
    }

    public Error getDefault() {
        return Error.of(DEFAULT_ERROR_KEY, errors.get(DEFAULT_ERROR_KEY));
    }

    public Error map(CoreException coreException) {
        String errorKey = coreException.getErrorKey();
        String errorMessage = format(errors.get(errorKey), coreException.getParams());
        return Error.of(errorKey, errorMessage);
    }

    private void collectErrorsDescriptions(String key, JsonNode root) {
        Iterator<Map.Entry<String, JsonNode>> iterator = root.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> element = iterator.next();
            if (element.getValue().isTextual()) {
                errors.put(key + element.getKey(), element.getValue().asText());
            } else {
                collectErrorsDescriptions(key + element.getKey() + ".", element.getValue());
            }
        }
    }
}
