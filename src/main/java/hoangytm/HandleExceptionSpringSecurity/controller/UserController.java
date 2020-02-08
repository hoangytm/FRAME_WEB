package hoangytm.HandleExceptionSpringSecurity.controller;

import hoangytm.HandleExceptionSpringSecurity.entity.ApiResponse;
import hoangytm.HandleExceptionSpringSecurity.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PhanHoang
 * 2/8/2020
 */
@RestController
@RequestMapping("/private")
public class UserController {
    @GetMapping("/test")
    public ResponseEntity<?> testApi() {
        User user = new User();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("new Api");
        apiResponse.setResult(user);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
