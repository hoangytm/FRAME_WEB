package hoangytm.HandleExceptionSpringSecurity.controller;

import hoangytm.HandleExceptionSpringSecurity.entity.ApiResponse;
import hoangytm.HandleExceptionSpringSecurity.entity.User;
import hoangytm.HandleExceptionSpringSecurity.i18n.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.cache.annotation.CacheResult;

/**
 * @author PhanHoang
 * 2/8/2020
 */
@RestController
@RequestMapping("/private")
public class UserController {

    @Autowired
    Translator translator;

    @GetMapping("/test")
    public ResponseEntity<?> testApi() {
        User user = new User();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(translator.toLocale("label.password"));
        apiResponse.setData(user);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("test-cache")
    @CacheResult(cacheName = "people")
    public ResponseEntity<?> testCache() throws InterruptedException {
        ApiResponse apiResponse = new ApiResponse();
        Thread.sleep(5000);
        apiResponse.setCode(200);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}