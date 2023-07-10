package com.ticket.t1.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SuccessVO {
   private int sucseq;
   private int mseq;
   private String id;
   private String pwd;
   private String title;
   private Timestamp indate;
   private String content;
   private String reply;
   private char repyn;
   private String image;
   private int readcount;
   private String imgfilename;
 
}