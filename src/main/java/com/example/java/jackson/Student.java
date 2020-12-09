package com.example.java.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Liq
 * @date 2020-9-22
 */
@Data
public class Student {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate time;

    private String name;

    private String idCard;

    public LocalDate getTime() {
        return time;
    }

    public Student() {
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
