package com.stanmarek.springusos.service;

import com.stanmarek.springusos.model.Status;
import lombok.Data;

@Data
public class EditStudent {
    private Double points;
    private Status status;
}
