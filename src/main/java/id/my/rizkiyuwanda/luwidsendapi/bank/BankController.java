package id.my.rizkiyuwanda.luwidsendapi.bank;

import id.my.rizkiyuwanda.luwidsendapi.utility.ResponseDTO;
import id.my.rizkiyuwanda.luwidsendapi.utility.StringUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")

public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<Bank>> save(@Valid @RequestBody Bank bank, Errors errors) {
        List<String> messages = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                messages.add(objectError.getDefaultMessage());
            }
            ResponseDTO<Bank> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }

        Bank entity = bankService.save(bank);
        if(entity == null){
            messages.add(StringUtility.FAILED);
            ResponseDTO<Bank> responseDTO = new ResponseDTO<>(false, messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }else{
            messages.add(StringUtility.SUCCESS);
            ResponseDTO<Bank> responseDTO = new ResponseDTO<>(true, messages, entity);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

    }

    @DeleteMapping("/deletebyid/{id}")
    public void deleteById(@PathVariable("id") String id) {
        bankService.deleteById(id);
    }

    @GetMapping("/findbyid/{id}")
    public Optional<Bank> findById(@PathVariable("id") String id) {
        Optional<Bank> bank = bankService.findById(id);
        if (bank.isPresent() == false) {
            return null;
        } else {
            return bank;
        }
    }

    @GetMapping("/findall")
    public Iterable<Bank> findAll() {
        return bankService.findAll();
    }
}
