package com.example.samandar_demo.core.tasks

import com.example.liveness.core.DetectionTask
import com.example.liveness.core.DetectionUtils
import com.google.mlkit.vision.face.Face

class MouthCloseDetectionTask : DetectionTask {

    override fun process(face: Face): Boolean {
        return DetectionUtils.isFacing(face) && DetectionUtils.isMouthClosed(face)
    }
}