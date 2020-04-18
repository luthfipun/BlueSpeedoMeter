package studio.elevenbox.speedometer

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startAnimate()
        startTextAnimation()
    }

    private fun startTextAnimation() {
        val value = ValueAnimator()
        value.setObjectValues(0, 3200)
        value.addUpdateListener {
            total.text = it.animatedValue.toString()
        }
        value.duration = 2000
        value.start()
    }

    private fun startAnimate() {
        val rotate = ObjectAnimator.ofFloat(needle, "rotation", 0f, 120f)
        rotate.duration = 2000
        rotate.start()
    }
}
