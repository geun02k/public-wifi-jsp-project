package com.jsp.project.publicwifiinfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Location {
    private int id;
    private double lat;
    private double lnt;
    private LocalDateTime searchDt;
}
