package id.my.rizkiyuwanda.luwidsendapi.bank;

import id.my.rizkiyuwanda.luwidsendapi.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<Bank>> create(@Valid @RequestBody Bank bank, Errors errors){
        ResponseDTO<Bank>responseDTO = new ResponseDTO<>();
        if(errors.hasErrors()){
            List<String> messages = new ArrayList<>();
            for(ObjectError objectError: errors.getAllErrors()){
                messages.add(objectError.getDefaultMessage());
            }
            responseDTO.setMessages(messages);
            responseDTO.setStatus(false);
            responseDTO.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }else {
            responseDTO.setMessages(null);
            responseDTO.setStatus(true);
            responseDTO.setPayload(bankService.save(bank));
            return ResponseEntity.ok(responseDTO);
        }
    }


    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable("id") String id){
        bankService.remove(id);
    }

    @GetMapping("/findall")
    public Iterable<Bank>findAll(){
        return bankService.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<Bank> find(@PathVariable("id") String id){
        Optional<Bank>bank = bankService.find(id);
        if(bank.isPresent() == false){
            return null;
        }else{
            return bank;
        }
    }
}
