package com.app.multithread;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Eod implements Serializable {

    private String id;

    private String nama;

    private String age;

    private String balanced;

    private String no2bThreadNo;

    private String no3ThreadNo;

    private String previousBalanced;

    private String averageBalanced;

    private String no1ThreadNo;

    private String freeTransfer;

    private String no2aThreadNo;

    transient String balancedOld;
}
