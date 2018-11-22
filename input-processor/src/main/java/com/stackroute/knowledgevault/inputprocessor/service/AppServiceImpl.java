package com.stackroute.knowledgevault.inputprocessor.service;

import com.stackroute.knowledgevault.domain.Input;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl implements AppService {

    @Override
    public Input getInput(String path) {
        return new Input(path);
    }
}
