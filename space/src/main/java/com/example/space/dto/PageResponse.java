package com.example.space.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int number;
    private int size;
    private boolean first;
    private boolean last;

    public static <T> PageResponse<T> from(org.springframework.data.domain.Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setContent(page.getContent());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setNumber(page.getNumber());
        response.setSize(page.getSize());
        response.setFirst(page.isFirst());
        response.setLast(page.isLast());
        return response;
    }

}
