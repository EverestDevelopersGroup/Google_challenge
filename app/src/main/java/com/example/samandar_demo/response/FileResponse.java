package com.example.samandar_demo.response;

import java.util.List;

public class FileResponse {
    private List<List<Object>> natija;

    public FileResponse(List<List<Object>> natija) {
        this.natija = natija;
    }

    public List<List<Object>> getResponse() {
        return natija;
    }

    public void setResponse(List<List<Object>> natija) {
        this.natija = natija;
    }

    @Override
    public String toString() {
        return "FileResponse{" +
                "natija=" + natija +
                '}';
    }
}
