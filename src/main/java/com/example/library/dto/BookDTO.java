package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NonNull
    private String title;
    @NonNull
    private String author;
    private int quantity;
}
