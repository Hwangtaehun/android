package com.example.app03_community.repository;

import com.example.app03_community.model.UserInfoModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;

public class UserInfoRepository {

    // 사용자 시퀀스 값이 저장되어 있는 문서를 반환한다.
    public static void getUserInfoSequence(OnSuccessListener onSuccessListener){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Sequence");
        DocumentReference documentReference = collectionReference.document("UserSequence");
        Task<DocumentSnapshot> documentSnapshotTask = documentReference.get();
        documentSnapshotTask.addOnSuccessListener(onSuccessListener);
    }

    // 사용자 시퀀스 값을 저장하는 메서드
    public static void setUserInfoSequence(int userSequence, OnSuccessListener onSuccessListener) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Sequence");
        DocumentReference documentReference = collectionReference.document("UserSequence");
        HashMap<String, Integer> dataMap = new HashMap<>();
        dataMap.put("value", userSequence);
        documentReference.set(dataMap).addOnSuccessListener(onSuccessListener);
    }

    // 사용자 정보를 저장한다.
    public static void addUserInfo(UserInfoModel userInfoModel, OnSuccessListener onSuccessListener) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("UserInfo");
        Task<DocumentReference> documentReferenceTask = collectionReference.add(userInfoModel);
        documentReferenceTask.addOnSuccessListener(onSuccessListener);
    }

    // 로그인 처리
    public static void checkLoginUser(String loginUserId, OnSuccessListener onSuccessListener) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("UserInfo");
        Query query = collectionReference.whereEqualTo("userId", loginUserId);
        query.get().addOnSuccessListener(onSuccessListener);
    }
}
