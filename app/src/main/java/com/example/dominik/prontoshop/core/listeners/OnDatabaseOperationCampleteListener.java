package com.example.dominik.prontoshop.core.listeners;

public interface OnDatabaseOperationCampleteListener {
    void OnDatabaseOperationFaild(String error);
    void OnDatabaseOperationSuccesed(String message);
}
