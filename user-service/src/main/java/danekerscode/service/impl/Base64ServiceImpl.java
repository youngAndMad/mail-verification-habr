package danekerscode.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import danekerscode.exception.Base64OperationException;
import danekerscode.service.Base64Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Base64.*;


@Service
@RequiredArgsConstructor
public class Base64ServiceImpl implements Base64Service {

    private final Encoder encoder = Base64.getEncoder();
    private final Decoder decoder = Base64.getDecoder();

    private final ObjectMapper objectMapper;

    @Override
    public <T> T decode(String data, Class<T> to) {
        try {

            var decodedBytes = Base64.getDecoder().decode(data);

            var decodedString = new String(decodedBytes);

            return objectMapper.readValue(decodedString, to);

        } catch (Exception e) {
            throw new Base64OperationException("Failed to decode or convert the data", e);
        }
    }

    @Override
    public <T> String encode(T t) {
        var dataToEncode = t.toString();

        var encodedBytes = Base64.getEncoder().encode(dataToEncode.getBytes());

        return new String(encodedBytes);
    }
}
