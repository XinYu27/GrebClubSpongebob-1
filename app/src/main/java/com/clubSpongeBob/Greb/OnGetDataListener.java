package com.clubSpongeBob.Greb;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface OnGetDataListener {
    // for callbacks
    void onSuccess(DataSnapshot dataSnapshot);
    void onStart();
    void onFailure(DatabaseError databaseError);
}
