package com.pravvich.lecturehibernate.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilter {

    private String name = "";

    private Integer ageFrom = 0;

    private Integer ageTo = 150;

    public String getName() {
        return "%" + name + "%";
    }
}
