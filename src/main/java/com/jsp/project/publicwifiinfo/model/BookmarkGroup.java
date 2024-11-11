package com.jsp.project.publicwifiinfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkGroup {
    private int code;
    private String name;
    private int seq;
    private LocalDateTime regDt;
    private LocalDateTime updateDt;
}
