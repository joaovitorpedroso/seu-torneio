package com.java.development.entities.operation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationMessage {

    public String message;
    public String status;
    public LocalDateTime dateTime;

    public OperationMessage informarSucesso(String message){
        OperationMessage op = new OperationMessage();
        op.setMessage(message);
        op.setStatus("OK");
        op.setDateTime(LocalDateTime.now());
        return op;
    }
}
