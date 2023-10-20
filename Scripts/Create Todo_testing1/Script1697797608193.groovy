import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.logging.KeywordLogger


KeywordLogger log = new KeywordLogger()
Mobile.callTestCase(findTestCase('Test Cases/Login'), null)

Mobile.waitForElementPresent(findTestObject('Object Repository/HomePage/todoButton'), 10)

Mobile.tap(findTestObject('Object Repository/HomePage/todoButton'), 10)

//Read data from excel
def nameValue = findTestData('MST/DataInput').getValue('Name',1)

def calendarValue = findTestData('MST/DataInput').getValue('Date',1) //2023-08-01

String yearExcel = calendarValue.split("-")[0]

String monthExcel = calendarValue.split("-")[1]

int monthExcelNum = Integer.parseInt(monthExcel) // August 8

String dateExcel = calendarValue.split("-")[2]

def typeValue = findTestData('MST/DataInput').getValue('Type',1)

def descriptionValue = findTestData('MST/DataInput').getValue('Description',1)

Mobile.waitForElementPresent(findTestObject('Object Repository/HomePage/AddToDoPage/inputName'), 10)

Mobile.setText(findTestObject('Object Repository/HomePage/AddToDoPage/inputName'), nameValue, 10)

Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/calendarButton'), 10)

Mobile.waitForElementPresent(findTestObject('Object Repository/HomePage/AddToDoPage/MonthYear'), 10)

String cMonthYear = Mobile.getText(findTestObject('Object Repository/HomePage/AddToDoPage/MonthYear'), 10)

String cMonth = cMonthYear.split(" ")[0]  

String cYear = cMonthYear.split(" ")[1]

int monthNumApps = Integer.parseInt(CustomKeywords.'month.pickerMonth.getMonthNum'(cMonth))//October = 10
log.logInfo("monthExcel "+monthExcel)
log.logInfo("monthExcelNum "+monthExcelNum)

log.logInfo("cMonth "+cMonth)
log.logInfo("monthNumApps "+monthNumApps)

boolean isCheck = true

while(isCheck) {
	if (monthNumApps>=monthExcelNum) {
		monthNumApps--
		Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/leftArrowCalendarButton'), 10)
		if(monthNumApps==monthExcelNum) {
			isCheck = false
		}
	}else if(monthNumApps<=monthExcelNum) {
		monthNumApps++
		Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/rightArrowCalendarButton'), 10)
		if(monthNumApps==monthExcelNum) {
			isCheck = false
		}
	}
}


Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/dateText',['text':CustomKeywords.'month.pickerMonth.removeZero'(dateExcel)]), 10)

Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/okDate'), 10)

Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/selectType'), 10)

Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/typeText',['text':typeValue]), 10)

Mobile.setText(findTestObject('Object Repository/HomePage/AddToDoPage/inputDescription'), descriptionValue, 10)

Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/uploadFile'), 10)

Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/photoImg'), 10)

Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/addTodoConfirmButton'), 10)

Mobile.tap(findTestObject('Object Repository/HomePage/AddToDoPage/textElement',['text':nameValue]),10)

Mobile.verifyElementExist(findTestObject('Object Repository/HomePage/AddToDoPage/textElement',['text':nameValue]),10)

Mobile.verifyElementExist(findTestObject('Object Repository/HomePage/AddToDoPage/textElement',['text':typeValue]),10)

Mobile.verifyElementExist(findTestObject('Object Repository/HomePage/AddToDoPage/textElement',['text':descriptionValue]),10)

Mobile.verifyElementExist(findTestObject('Object Repository/HomePage/AddToDoPage/imgElment'),10)

