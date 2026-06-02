package ru.rezonans_lab.lordofsands.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Product {
        private String name;
        private String description;
        private int price;

}