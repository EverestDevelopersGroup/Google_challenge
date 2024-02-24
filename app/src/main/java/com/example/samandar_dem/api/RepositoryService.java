package com.example.samandar_dem.api;


import com.example.samandar_dem.response.FileResponse;

public interface RepositoryService
{
    void loadTroops(final FileResponse response);
    void loadError(final String message);
    //void loadTreasures(final TreasureCallback callback);
}