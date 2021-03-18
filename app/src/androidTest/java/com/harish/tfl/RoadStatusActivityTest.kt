package com.harish.tfl

import android.content.Context
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.harish.tfl.ui.RoadStatusActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoadStatusActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<RoadStatusActivity>()

    @get:Rule
    var activityRule: ActivityScenarioRule<RoadStatusActivity>
            = ActivityScenarioRule(RoadStatusActivity::class.java)

    private fun getString(id: Int): String {
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.resources.getString(id)
    }

    @Test
    fun Submitting_a_valid_road_id_and_checking_for_the_name() {
        //Given I am on the Home Screen
        launchScreen()

        //When I enter a valid road ID
        enterValidRoadId()

        //Then I see displayName is displayed
        checkDisplayNameDisplayed()
    }

    @Test
    fun Submitting_a_valid_road_id_and_checking_for_the_status() {
        //Given I am on the Home Screen
        launchScreen()

        //When I enter a valid road ID
        enterValidRoadId()

        //Then I see statusSeverity is displayed
        checkStatusSeverityDisplayed()
    }

    @Test
    fun Submitting_a_valid_road_id_and_checking_for_the_description() {
        //Given I am on the Home Screen
        launchScreen()

        //When I enter a valid road ID
        enterValidRoadId()

        //Then I see statusSeverityDescription is displayed
        checkStatusSeverityDescDisplayed()
    }

    @Test
    fun Submitting_an_invalid_road_id_and_checking_for_the_error() {
        //Given I am on the Home Screen
        launchScreen()

        //When I enter an invalid road ID
        enterInValidRoadId()

        //Then I see an informativeError is displayed
        checkErrorDescriptionDisplayed()
    }

    private fun launchScreen() {
        ActivityScenario.launch(RoadStatusActivity::class.java)
    }

    private fun checkDisplayNameDisplayed() {
        composeTestRule.onNodeWithText(VALID_ROAD_ID)
    }

    private fun checkStatusSeverityDisplayed() {
        composeTestRule.onNodeWithText(STATUS_SEVERITY)
    }

    private fun checkStatusSeverityDescDisplayed() {
        composeTestRule.onNodeWithText(STATUS_SEVERITY_DESC)
    }

    private fun checkErrorDescriptionDisplayed() {
        composeTestRule.onNodeWithText(ERROR_DESCRIPTION)
    }

    private fun enterValidRoadId() {
        composeTestRule.onNode(hasText(getString(R.string.road_label))).performTextInput(VALID_ROAD_ID)
    }

    private fun enterInValidRoadId() {
        composeTestRule.onNode(hasText(getString(R.string.road_label))).performTextInput(INVALID_ROAD_ID)
    }

    companion object {
        const val VALID_ROAD_ID = "A1"
        const val INVALID_ROAD_ID = "A99"
        const val STATUS_SEVERITY = "Good"
        const val STATUS_SEVERITY_DESC = "No Exceptional Delays"
        const val ERROR_DESCRIPTION = "The following road id is not recognised: A99"
    }
}