package com.bank.alfalah.commons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class ServiceResponse<T> {

    public T Data;
    public String Message;
    public String Code;
    public String Error;

    public ServiceResponse() {

    }
}
