package com.example.app03_community.repository;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserInfoRepository {

    // 사용자 시퀀스 값이 저장되어 있는 문서를 반환한다.
    public static void getUserInfoSequence(OnSuccessListener onSuccessListener){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Sequence");
        DocumentReference documentReference = collectionReference.document("UserSequence");
        Task<DocumentSnapshot> documentSnapshotTask = documentReference.get();
        documentSnapshotTask.addOnSuccessListener(onSuccessListener);
    }

}
