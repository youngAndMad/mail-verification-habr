package danekerscode.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import danekerscode.exception.Base64OperationException;
import danekerscode.service.Base64Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;


@Service
@RequiredArgsConstructor
public class Base64ServiceImpl implements Base64Service {


    private final ObjectMapper objectMapper;

    @Override
    public <T> T decode(String data, Class<T> to) {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {

            byte[] decodedBytes = Base64.getDecoder().decode(data);

            String jsonData = new String(decodedBytes);

            return objectMapper.readValue(jsonData,to);

        } catch (Exception e) {
            throw new Base64OperationException("Failed to decode or convert the data", e);
        }
    }

    @Override
    public <T> String encode(T t) {
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new Base64OperationException(e.getMessage());
        }
        return Base64.getEncoder().encodeToString(jsonData.getBytes());
    }
}
