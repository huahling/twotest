package com.encoder.zidingyi.faxiaoxi;

public class Msg {

    int nameLength;
    String name;
    int age;//字节长度 4个字节
    long contentLength;
    String content;// 在这里无法接  大一点的文件内容  因为可能会内存溢出


    public int getNameLength() {
        return nameLength;
    }

    public void setNameLength(int nameLength) {
        this.nameLength = nameLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "nameLength=" + nameLength +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contentLength=" + contentLength +
                ", content='" + content + '\'' +
                '}';
    }

}
