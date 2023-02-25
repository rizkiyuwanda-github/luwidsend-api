package id.my.rizkiyuwanda.luwidsendapi.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDTO<T>{
    private boolean status;
    private List<String> messages;
    private T entity;

}
