package tests;

import gov.loc.pages.HomePage;
import gov.loc.pages.SurveyPage;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SurveyTest extends BaseTest{

    private HomePage homePage;
    private SurveyPage surveyPage;

    @BeforeClass
    public void setUp(){
        homePage = new HomePage(driver);
    }
    @Test
    @Description("This test validates the access to the survey")
    public void scrollToSurvey(){
        homePage.scrollToSurvey();
        surveyPage = homePage.goToSurveyPage();
    }

    @Test(dependsOnMethods = {"scrollToSurvey"})
    @Description("This test validates the number of questions in the survey")
    public void validateNumberOfQuestions(){
        int actual = surveyPage.getNumberOfQuestions();
        int expected = 3;
        assertEquals(actual, expected, "Number of questions is not " + expected);
    }

    @Test(dependsOnMethods = {"validateNumberOfQuestions"})
    @Description("This test validates the questions are answered in the survey")
    public void validateQuestionIsAnswered() {
        boolean isQuestion1Answered = surveyPage.answerQuestion1(SurveyPage.AnswerToQuestion1.YES);
        assertTrue(isQuestion1Answered, "Question 1 is not answered");
        boolean isQuestion2Answered = surveyPage.answerQuestion2(SurveyPage.AnswerToQuestion2.I_AM_A_STUDENT);
        assertTrue(isQuestion2Answered, "Question 2 is not answered");
        boolean isQuestion3Answered = surveyPage.answerQuestion3(SurveyPage.AnswerToQuestion3.I_HAVE_NOT_USED_THE_LIBRARY_PRIOR_TO_TODAY);
        assertTrue(isQuestion3Answered, "Question 3 is not answered");
    }

    @Test(dependsOnMethods = {"validateQuestionIsAnswered"})
    public void validateFeedback() {
        String feedbackMessage = surveyPage.submitSurvey();
        assertNotNull(feedbackMessage, "Feedback not found");
    }
}
