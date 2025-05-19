package com.example.vstest.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController

public class test {
    @Autowired

    private ObjectMapper objectMapper;

    public static class MyPojo {
        private String name;
        private int age;

        public MyPojo() {
        }

        public MyPojo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @GetMapping("test")
    public String getMethodName(@RequestParam String param) {
        MyPojo pojo = new MyPojo("JoeBiden".concat(param), 99);
        try {
            // converting pojo to json
            String json = objectMapper.writeValueAsString(pojo);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return "error converting";
        }
    }

}
