package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputPOS {
    private int id;
    private List<String> poses;
}
