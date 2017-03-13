package com.example.dominik.prontoshop.core.listeners;

public interface OnDatabaseOperationCompleteListener {
    void OnDatabaseOperationFailed(String error);
    void OnDatabaseOperationSuccesed(String message);
}
