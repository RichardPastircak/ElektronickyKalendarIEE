package com.application.calendar;

import com.application.parser.Parser;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    private final Parser parser;

    public CalendarService() {
        this.parser = new Parser();
    }

    //This is the function for loading days requested by emoplyee and provided by parser
    public void getParserData(){

    }
}
