package com.example.licenta6;

public class Model_parcare {
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    public Model_parcare(){ }
    public Model_parcare(String status) {
        this.status = status;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
