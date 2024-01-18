package com.example.testproject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class hello {
    @RequestMapping("/hello")
    public String main(String[] args)  {

        return "AAAAAAAAAA";
    }

}