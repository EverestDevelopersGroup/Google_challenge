package com.example.liveness

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Outline
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import com.example.liveness.core.*
import com.example.liveness.core.tasks.FacingDetectionTask
import com.example.liveness.core.tasks.MouthOpenDetectionTask
import com.example.liveness.core.tasks.ShakeDetectionTask
import com.example.liveness.core.tasks.SmileDetectionTask
import com.example.samandar_demo.Activities.Succes
import com.example.samandar_demo.Activities.VideoFrame
import com.example.samandar_demo.R
import com.example.samandar_demo.core.tasks.MouthCloseDetectionTask
import com.example.samandar_demo.databinding.ActivityLivenessBinding


import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class LivenessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLivenessBinding
    private lateinit var cameraController: LifecycleCameraController
    private var imageFiles = arrayListOf<String>()
    private var isPaused = false
    private var timer = Timer()
    private var attention: MediaPlayer? = null

    var isButtonLongPressed = false
    var handler = Handler()

    private var mCountDownTimer: CountDownTimer? = null
    private var time = 14000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLivenessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        attention = MediaPlayer.create(applicationContext, R.raw.tayyormisan)


        val videoName = intent.getStringExtra("videoName")
        val videoUrl = intent.getStringExtra("videoUrl")

        title = videoName // Activity sarlavhasini o'zgartirish
        val videoPath = videoUrl
        binding.videoViewVideo1.setZOrderMediaOverlay(true)
        binding.videoViewVideo1.setVideoURI(Uri.parse(videoPath))
        binding.videoViewVideo1.setOnPreparedListener { mp ->

            binding.progressBarVideo1.visibility = View.INVISIBLE
            binding.videoViewVideo1.start()
        }

        binding.videoViewVideo1.setOnCompletionListener {isPaused = false
            binding.playpauseMain1.setImageResource(R.drawable.play)
            attention?.start()
            binding.btnStart.isEnabled = true
            // O'zgarish kiritilsin
            binding.btnStart.setButtonColor(Color.GREEN)



        }


        binding.btnStart.setOnClickListener{
            binding.btnStart.isEnabled = false

        }

        binding.videoViewVideo1.setOnErrorListener { mp, what, extra ->
            binding.progressBarVideo1.visibility = View.INVISIBLE
            false
        }

        binding.playpauseMain1.setOnClickListener {
            if (isPaused) {

                binding.videoViewVideo1.start()
                isPaused = false
                binding.playpauseMain1.setImageResource(R.drawable.pause)
                binding.btnStart.isEnabled = false
            } else {

                binding.videoViewVideo1.pause()
                isPaused = true
                binding.playpauseMain1.setImageResource(R.drawable.play)
                binding.btnStart.isEnabled = true
            }
        }

        binding.restartMain1.setOnClickListener {
            binding.videoViewVideo1.start()
        }




        binding.restartMain1.setOnLongClickListener {
            isButtonLongPressed = true
            handler.postDelayed({
                if (isButtonLongPressed) {
//                    Toast.makeText(this@LivenessActivity, "Salom", Toast.LENGTH_SHORT).show()
//                    // Boshqa amallarni ham bajarishingiz mumkin
                    binding.guide.text = "Siz mashq bajarmayapsz"
                    timer.cancel()



                }
            }, 2000) // 5 sekunddan so'ng
            true
        }




//        binding.restartMain1.setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
//                    isButtonLongPressed = false
//                    handler.removeCallbacksAndMessages(null)
//                }
//            }
//            false
//        }




//    binding.toolbar.setNavigationOnClickListener { finish() }
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                startCamera()
            } else {
                Toast.makeText(this, "Permission deny", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.launch(Manifest.permission.CAMERA)

        binding.cameraPreview.clipToOutline = true
        binding.cameraPreview.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, view.height / 9.0f)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun startCamera() {
        cameraController = LifecycleCameraController(this)
        cameraController.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(this),
            FaceAnalyzer(buildLivenessDetector())
        )
        cameraController.bindToLifecycle(this)
        binding.cameraPreview.controller = cameraController
    }

    private fun buildLivenessDetector(): LivenessDetector {
        val listener = object : LivenessDetector.Listener {
            @SuppressLint("SetTextI18n")
            override fun onTaskStarted(task: DetectionTask) {
//                if (task is FacingDetectionTask) {
//                  binding.guide.text = "Boshladik..."
//
//          }
//

//                        onTaskCompleted(task , isLastTask)
//                if (task is MouthCloseDetectionTask)   {
//
//
////                    timer.cancel()
//                    binding.guide.text = "Og`iz yopiq"
////                        mCountDownTimer.cancel()
//                }

//                 if (task is MouthOpenDetectionTask || task is MouthCloseDetectionTask ) {
//                    binding.guide.text = "Mashq bajarilmoqda"
//                    vibrate()
//                    createCountDownTimer(time)
//                    randomBackgroundWithMusic()
//                    Toast.makeText(this@LivenessActivity, "Salom", Toast.LENGTH_SHORT).show()
//
//
//                }
//
//                else{
//                     binding.guide.text = "Og`iz yopiq"
//                 }
            }

            override fun onTaskCompleted(task: DetectionTask, isLastTask: Boolean) {
//                takePhoto(File(cacheDir, "${System.currentTimeMillis()}.jpg")) {
//                    imageFiles.add(it.absolutePath)
//                    if (isLastTask) {
//                        finishForResult()
//                    }
//                }


                if (task is MouthOpenDetectionTask) {
                    binding.guide.text = "Siz mashq bajarmoqdasiz."
                    vibrate()
                    createCountDownTimer(time)
                    randomBackgroundWithMusic()


//               if (task is MouthCloseDetectionTask){
//                        binding.guide.text = "Siz mashq bajarmayapsz"
//                        timer.cancel()
//                    }



//                        Toast.makeText(this@LivenessActivity, "Salom", Toast.LENGTH_SHORT).show()


                }

                if (task is MouthCloseDetectionTask){
                    binding.guide.text = "Siz mashq bajarmayapsz"
                    timer.cancel()
                }

                else {
//                    timer.cancel()
                    binding.guide.text = "Ajoyib!. Endi mashqni boshlang"

//                        onTaskStarted(task)
//                        mCountDownTimer.cancel()
                }



//                onTaskCompleted(task , isLastTask)
            }



            override fun onTaskFailed(task: DetectionTask, code: Int) {


                if (code == LivenessDetector.ERROR_NO_FACE) {
                    binding.guide.text = "Kameraga yuzingizni to`g`rilang"

//                    timer.cancel()

                }








            }
        }

        return LivenessDetector(
            FacingDetectionTask(),
//            ShakeDetectionTask(),
            MouthOpenDetectionTask(),
//            SmileDetectionTask()
        ).also { it.setListener(listener) }
    }


//    private fun pauseLivenessTasks() {
//        randomBackgroundWithMusic()
//        vibrate()
//        createCountDownTimer(time)
//    }


    fun randomBackgroundWithMusic() {
        val yourLinearLayout = findViewById<LinearLayout>(R.id.colorful_linear)
        val backgrounds = intArrayOf(R.drawable.oval_shape, R.drawable.oval_shape2, R.drawable.oval_shape3)
        val currentIndex = intArrayOf(0)
        val randomInterval = Random().nextInt(4000) + 2000 // 1-7 sekund oralig'ida tasodifiy intervalida
        val musicResources = intArrayOf(R.raw.yaxhi2, R.raw.wrong) // Muzika
        val mediaPlayer = MediaPlayer.create(this, musicResources[0])
        val mediaPlayer2 = MediaPlayer.create(this, musicResources[1])

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val nextBackground = backgrounds[currentIndex[0]]
                    yourLinearLayout.setBackgroundResource(nextBackground)
                    currentIndex[0] = (currentIndex[0] + 1) % backgrounds.size

                    if (nextBackground == R.drawable.oval_shape2) {
                        mediaPlayer.start() // Musiqa ijro etish
                    }
                    if (nextBackground == R.drawable.oval_shape3) {
                        mediaPlayer2.start()
                    }
                }
            }
        }, randomInterval.toLong(), randomInterval.toLong())
    }





    private fun createCountDownTimer(timeC: Int) {
        val times = intArrayOf(timeC)
        mCountDownTimer = object : CountDownTimer(times[0].toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                times[0] -= 1000
                time -= 1000
            }

            override fun onFinish() {
                times[0] = 0
                times[0] = 0

            }
        }.start()


        startCountDownTimerToSuccess()
    }

    private fun startCountDownTimerToSuccess() {
        mCountDownTimer = object : CountDownTimer(91000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                navigateToSuccessActivity()
            }
        }.start()
    }

    private fun navigateToSuccessActivity() {

        if (!isFinishing && !isDestroyed) {
            val intentResult = Intent(this@LivenessActivity, Succes::class.java)
            intentResult.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentResult)
            finish()

        }}

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect5: VibrationEffect

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            vibrationEffect5 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            vibrator.cancel()
            vibrator.vibrate(vibrationEffect5)
        }
    }

    override fun onPause() {
        super.onPause()
        if (mCountDownTimer != null) {
            mCountDownTimer!!.cancel()
        }

        timer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()

    }







    class ResultContract : ActivityResultContract<Any?, List<String>?>() {

        companion object {
            const val RESULT_KEY = "images"
        }

        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, LivenessActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): List<String>? {
            if (resultCode == RESULT_OK && intent != null) {
                return intent.getStringArrayListExtra(RESULT_KEY)
            }
            return null

        }
    }}
