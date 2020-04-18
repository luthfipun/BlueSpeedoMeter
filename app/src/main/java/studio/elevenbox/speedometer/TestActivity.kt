package studio.elevenbox.speedometer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        speedo1.setTitle("Total Produk")
        speedo1.setNeedleAnimate(3000)
        speedo1.setNeedleAnimateFrom(0F)
        speedo1.setNeedleAnimateTo(120F)
        speedo1.setTotal(1250)
        speedo1.setTotalAnimate(2000)
        speedo1.setIsAnimate(true)
        speedo1.playAll()

        speedo2.setTitle("Produk 1")
        speedo2.setNeedleAnimate(4000)
        speedo2.setNeedleAnimateFrom(0F)
        speedo2.setNeedleAnimateTo(150F)
        speedo2.setTotal(930)
        speedo2.setTotalAnimate(4000)
        speedo2.setIsAnimate(true)
        speedo2.playAll()

        speedo3.setTitle("Produk 2")
        speedo3.setNeedleAnimate(1000)
        speedo3.setNeedleAnimateFrom(0F)
        speedo3.setNeedleAnimateTo(30f)
        speedo3.setTotal(11250)
        speedo3.setTotalAnimate(1000)
        speedo3.setIsAnimate(true)
        speedo3.playAll()

        speedo4.setTitle("Produk 3")
        speedo4.setNeedleAnimate(2350)
        speedo4.setNeedleAnimateFrom(0F)
        speedo4.setNeedleAnimateTo(90F)
        speedo4.setTotal(1590)
        speedo4.setTotalAnimate(2000)
        speedo4.setIsAnimate(true)
        speedo4.playAll()
    }
}
