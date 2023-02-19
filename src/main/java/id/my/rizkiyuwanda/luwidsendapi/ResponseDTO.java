package id.my.rizkiyuwanda.luwidsendapi;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO<T>{
    private boolean status;
    private List<String> messages;
    private T payload;
}
