package com.jsp.project.publicwifiinfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Bookmark {
    private int id;
    private String wifiMngrNo;
    private int groupCode;
    private LocalDateTime regDt;
}
