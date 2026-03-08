package com.example.mvvm.model;

public class TestModel {
    private int idxData;
    private String textData1;
    private String textData2;

    public TestModel(int idxData, String textData1, String textData2) {
        this.idxData = idxData;
        this.textData1 = textData1;
        this.textData2 = textData2;
    }

    public TestModel(String textData1, String textData2){
        this.textData1 = textData1;
        this.textData2 = textData2;
    }

    public int getIdxData() {
        return idxData;
    }

    public void setIdxData(int idxData) {
        this.idxData = idxData;
    }

    public String getTextData1() {
        return textData1;
    }

    public void setTextData1(String textData1) {
        this.textData1 = textData1;
    }

    public String getTextData2() {
        return textData2;
    }

    public void setTextData2(String textData2) {
        this.textData2 = textData2;
    }
}
