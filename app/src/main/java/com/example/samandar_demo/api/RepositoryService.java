package com.example.samandar_demo.api;


import com.example.samandar_demo.response.FileResponse;

public interface RepositoryService
{
    void loadTroops(final FileResponse response);
    void loadError(final String message);
    //void loadTreasures(final TreasureCallback callback);
}