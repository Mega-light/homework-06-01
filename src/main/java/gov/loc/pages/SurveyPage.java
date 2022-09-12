package gov.loc.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

// page_url = https://www.research.net/r/libraryofcongress?site=homepage
public class SurveyPage extends BasePage{
    private final String baseUrl = "https://www.research.net/r/libraryofcongress?site=homepage";
    @FindBy(css = "h4[id^='question-title-']")
    private List<WebElement> hdrQuestions;

    @FindBy(name = "surveyForm")
    private WebElement frmSurvey;

    @FindBy(css = "fieldset.question-fieldset")
    private List<WebElement> rdoQuestions;

    private By lnkFinishSurvey = By.cssSelector("a.survey-submit-actions");

    private By divFeedback = By.cssSelector("div.thanks-message");

    public SurveyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfQuestions(){
        return hdrQuestions.size();
    }

    public HomePage fillSurvey(@NotNull AnswerToQuestion1 answerToQuestion1,
                               @NotNull AnswerToQuestion2 answerToQuestion2,
                               @NotNull AnswerToQuestion3 answerToQuestion3){
        answerQuestion1(answerToQuestion1);
        answerQuestion2(answerToQuestion2);
        answerQuestion3(answerToQuestion3);
        submitSurvey();
        finishSurvey();
        return new HomePage(driver);
    }

    public String submitSurvey(){
        frmSurvey.submit();
        return find(divFeedback).getText();
    }

    public void finishSurvey(){
        find(lnkFinishSurvey).click();
    }

    public boolean answerQuestion1(AnswerToQuestion1 answerToQuestion1){
        WebElement element = rdoQuestions.get(0).findElement(answerToQuestion1.getBy());
        click(element);
        return element.isSelected();
    }

    public boolean answerQuestion2(AnswerToQuestion2 answerToQuestion2){
        WebElement element = rdoQuestions.get(0).findElement(answerToQuestion2.getBy());
        click(element);
        return element.isSelected();
    }

    public boolean answerQuestion3(AnswerToQuestion3 answerToQuestion3){
        WebElement element = rdoQuestions.get(0).findElement(answerToQuestion3.getBy());
        click(element);
        return element.isSelected();
    }

    public enum OptionsComponent {
        ;
        private By by;

        OptionsComponent(By by){
            this.by = by;
        }
    }

    public enum AnswerToQuestion1{
        YES(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(1)")),
        NO(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(2)"));

        private By by;

        AnswerToQuestion1(By by) {
            this.by = by;
        }

        public By getBy(){
            return by;
        }
    }

    public enum AnswerToQuestion2{
        I_AM_A_RESEARCHER_OR_ACADEMIC(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(1)")),
        I_AM_AN_ARTIST_OR_CREATIVE_TYPE_LOOKING_FOR_INSPIRATION(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(2)")),
        I_AM_PLANNING_TO_VISIT_THE_LIBRARY(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(3)")),
        I_AM_A_K12_EDUCATOR_LOOKING_FOR_RESOURCES(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(4)")),
        I_ENJOY_BROWSING_HISTORICAL_MATERIALS_ONLINE(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(5)")),
        I_AM_A_LIBRARIAN_LOOKING_FOR_STANDARDS_AND_PROFESSIONAL_TOOLS(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(6)")),
        I_FOLLOW_POLITICS_AND_LEGISLATION_FOR_PROFESSIONAL_OR_PERSONAL_REASONS(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(7)")),
        I_AM_A_COPYRIGHT_APPLICANT_HOLDER_OR_PROFESSIONAL(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(8)")),
        I_AM_A_STUDENT(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(9)")),
        I_AM_A_GENEALOGIST_OR_RESEARCHING_FAMILY_HISTORY(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(10)"));

        AnswerToQuestion2(By by) {
            this.by = by;
        }

        private By by;

        public By getBy(){
            return by;
        }
    }

    public enum AnswerToQuestion3{
        I_HAVE_EXTENSIVE_EXPERIENCE(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(1)")),
        I_HAVE_SOME_EXPERIENCE(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(2)")),
        I_HAVE_USED_THE_LIBRARY_BEFORE_BUT_NOT_REGULARLY(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(3)")),
        I_HAVE_NOT_USED_THE_LIBRARY_PRIOR_TO_TODAY(By.cssSelector("div.question-body > div > div.answer-option-cell:nth-child(4)"));

        private By by;

        AnswerToQuestion3(By by){
            this.by = by;
        }

        public By getBy(){
            return by;
        }
    }
}