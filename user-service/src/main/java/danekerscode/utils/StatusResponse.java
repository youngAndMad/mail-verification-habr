package danekerscode.utils;


import com.fasterxml.jackson.annotation.JsonInclude;

public record StatusResponse(
        Boolean success,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String message
) {
}
