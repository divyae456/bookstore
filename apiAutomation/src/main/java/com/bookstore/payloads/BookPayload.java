package com.bookstore.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookPayload {
    private String name;
    private String author;
    
    @JsonProperty("published_year")
    private int publishedYear;
    
    @JsonProperty("book_summary")
    private String bookSummary;
    
    private Integer id;
} 