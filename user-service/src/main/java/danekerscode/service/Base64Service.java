package danekerscode.service;

public interface Base64Service {

    <T> T decode(String data, Class<T> to);

    <T> String encode(T t);

}
