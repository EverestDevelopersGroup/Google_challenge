package com.example.liveness.core

import com.google.mlkit.vision.face.Face

interface DetectionTask {

    fun start() {

    }


    fun process(face: Face): Boolean
}