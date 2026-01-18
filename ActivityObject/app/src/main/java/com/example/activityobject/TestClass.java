package com.example.activityobject;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TestClass implements Parcelable {
    private int data1 = 0;
    private String data2 = "";

    public TestClass(int data1, String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    protected TestClass(Parcel in) {
        data1 = in.readInt();
        data2 = in.readString();
    }

    // getParcelableExtra 메서드를 호출해 객체를 추출하는 작업을 할 때
    // CREATOR.createFromParcel 메서드를 호출해 메서드가 반환하는 객체를 전달해준다.
    // 이헤, 이 메서드에서 객체를 새롭게 생성하고 Parcel에 저장되어 있는
    // 값을 추출해 객체의 변수에 담아넣어서 객체를 복원하고 반환하는 작업을 수행한다.
    public static final Creator<TestClass> CREATOR = new Creator<TestClass>() {
        @Override
        public TestClass createFromParcel(Parcel source) {
            return new TestClass(source);
        }

        @Override
        public TestClass[] newArray(int size) {
            return new TestClass[size];
        }
    };

    public int getData1() {
        return data1;
    }

    public void setData1(int data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // 객체를 Intent에 저장하려고 할 때 자동으로 호출되는 메서드
    // 이 메서드의 첫 번째 매개변수로 전달되는 Parcel 객체가 Intent에 담기게 되고
    // 이 Parcel 객체가 다른 Activity로 전달된다.
    // 여기에서는 다른 Activity에서 객체를 생성하고 그 객체에 담을 값을 저장하는 작업을 수행한다.
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(data1);
        dest.writeString(data2);
    }
}
