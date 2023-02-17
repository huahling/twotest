package com.encoder.zidingyi.file;

public class Msg {

    int nameLength;
    String name;
    int age;//字节长度 4个字节
    long contentLength;


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


    @Override
    public String toString() {
        return "Msg{" +
                "nameLength=" + nameLength +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contentLength=" + contentLength +
                '}';
    }

}
