package com.belajar.restapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseData<T> {
    public boolean status;
    public List<String> messages = new ArrayList<>();
    public T payload;
}
