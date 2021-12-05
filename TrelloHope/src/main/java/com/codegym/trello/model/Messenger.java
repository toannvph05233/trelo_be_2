package com.codegym.trello.model;

import lombok.Data;

@Data
public class Messenger {
    private String message;
    private String name;
    private long idBoard;
}
