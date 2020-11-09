package com.pravvich.lecturehibernate.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilter {

    @Setter
    private String name;

    @Setter
    private Integer ageFrom;

    @Setter
    private Integer ageTo;

    public Optional<String> getName() {
        return Objects.nonNull(name) ? Optional.of(name) : Optional.empty();
    }

    public Optional<Integer> getAgeFrom() {
        return Objects.nonNull(ageFrom) ? Optional.of(ageFrom) : Optional.empty();
    }

    public Optional<Integer> getAgeTo() {
        return Objects.nonNull(ageTo) ? Optional.of(ageTo) : Optional.empty();
    }

}
