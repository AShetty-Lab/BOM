package com.bom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocumentResponse {
    private Long id;

    public DocumentResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    private String title;
    private String content;
}
