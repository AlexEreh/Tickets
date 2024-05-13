package ru.alexereh.tickets.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import junit.framework.TestCase.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        rule.collect(
            packageName = InstrumentationRegistry.getArguments().getString("targetAppId")
                ?: throw Exception("targetAppId not passed as instrumentation runner arg"),
            includeInStartupProfile = true
        ) {
            pressHome()
            startActivityAndWait()

            val recyclerSelector = By.clazz("androidx.recyclerview.widget.RecyclerView")
            if (!device.wait(Until.hasObject(recyclerSelector), 5_000)) {
                fail("Could not find resource in time")
            }
            val recycler = device.findObject(recyclerSelector)
            recycler.setGestureMargin(recycler.visibleBounds.height() / 2)
            recycler.fling(Direction.RIGHT)
            recycler.fling(Direction.RIGHT)
            recycler.fling(Direction.LEFT)
        }
    }
}