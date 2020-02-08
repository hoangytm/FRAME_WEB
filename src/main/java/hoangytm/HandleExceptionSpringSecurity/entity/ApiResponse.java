package hoangytm.HandleExceptionSpringSecurity.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author PhanHoang
 * 2/6/2020
 */
@NoArgsConstructor
@Data

public class ApiResponse {
private  int code;
    private int status;
    private String message;
    private Object result;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
