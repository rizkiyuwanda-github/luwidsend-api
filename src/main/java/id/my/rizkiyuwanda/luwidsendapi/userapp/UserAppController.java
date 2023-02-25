package id.my.rizkiyuwanda.luwidsendapi.userapp;

import id.my.rizkiyuwanda.luwidsendapi.utility.ResponseDTO;
import id.my.rizkiyuwanda.luwidsendapi.utility.StringUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userapp")
public class UserAppController {
    @Autowired
    private UserAppService userAppService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserApp>> register(@Valid @RequestBody UserApp userApp, Errors errors){
        List<String> messages = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                messages.add(objectError.getDefaultMessage());
            }
            ResponseDTO<UserApp> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }

        UserApp entity = userAppService.register(userApp);
        if(entity == null){
            messages.add(StringUtility.FAILED);
            ResponseDTO<UserApp> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }else{
            messages.add(StringUtility.SUCCESS);
            ResponseDTO<UserApp> responseDTO = new ResponseDTO<>(true, messages, entity);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
    }
}
