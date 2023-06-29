package com.dto;

import java.sql.Timestamp;

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
   
   public int getReadcount() {
	return readcount;
}
public void setReadcount(int readcount) {
	this.readcount = readcount;
}
public int getSucseq() {
      return sucseq;
   }
   public void setSucseq(int sucseq) {
      this.sucseq = sucseq;
   }
   public int getMseq() {
      return mseq;
   }
   public void setMseq(int mseq) {
      this.mseq = mseq;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPwd() {
      return pwd;
   }
   public void setPwd(String pwd) {
      this.pwd = pwd;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public Timestamp getIndate() {
      return indate;
   }
   public void setIndate(Timestamp indate) {
      this.indate = indate;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getReply() {
      return reply;
   }
   public void setReply(String reply) {
      this.reply = reply;
   }
   public char getRepyn() {
      return repyn;
   }
   public void setRepyn(char repyn) {
      this.repyn = repyn;
   }
   public String getImage() {
      return image;
   }
   public void setImage(String image) {
      this.image = image;
   }
}