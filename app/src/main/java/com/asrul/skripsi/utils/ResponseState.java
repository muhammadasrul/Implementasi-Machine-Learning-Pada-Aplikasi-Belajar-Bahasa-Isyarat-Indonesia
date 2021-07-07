package com.asrul.skripsi.utils;

public class ResponseState<T> {
    private Status status;
    private T data;
    private T message;

    public ResponseState() {
        this.status = null;
        this.data = null;
        this.message = null;
    }

    public ResponseState<T> success(T data) {
        this.status = Status.SUCCESS;
        this.data = data;
        return this;
    }

    public ResponseState<T> failure(T message) {
        this.status = Status.FAILURE;
        this.message = message;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public T getMessage() {
        return message;
    }
}
