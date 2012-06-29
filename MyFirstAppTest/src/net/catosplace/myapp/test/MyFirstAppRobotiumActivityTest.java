package net.catosplace.myapp.test;

import net.catosplace.myapp.MyFirstAppActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

public class MyFirstAppRobotiumActivityTest extends
		ActivityInstrumentationTestCase2<MyFirstAppActivity> {

	private static final String TEST_MESSAGE = "Test Message";
	
	private Solo solo;
	
	/**
	 * Constructor - passes information to the super classes constructor
	 */
	public MyFirstAppRobotiumActivityTest() {
		super("net.catosplace.myapp", MyFirstAppActivity.class);
	}
	
	/**
	 * 
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	/**
	 * Verify that the application under test initialized correctly
	 * @throws ExceptionMy
	 */
	@Smoke
	public void testPreConditions() throws Exception {
		//Assert that NoteEditor activity is opened
		solo.assertCurrentActivity("Expected MyFirstAppActivity Activity", "MyFirstAppActivity");
		assertNotNull(solo.getEditText(0));
		assertNotNull(solo.getButton(0));
	}
	
	/**
	 * Test the message entered is displayed
	 * @throws Exception
	 */
	@Smoke
	public void testMessageDisplay() throws Exception {
		//In text field 0, add Test Message
		solo.enterText(0, TEST_MESSAGE);
		solo.clickOnButton(0);
		solo.assertCurrentActivity("Expected DisplayMessageActivity Activity", "DisplayMessageActivity");
		assertEquals(TEST_MESSAGE,
				solo.getCurrentActivity().getIntent().getStringExtra(MyFirstAppActivity.EXTRA_MESSAGE));
		assertEquals(TEST_MESSAGE,
				((TextView)solo.getCurrentTextViews(solo.getView(0)).get(1)).getText().toString());
		assertEquals(40F,
				((TextView)solo.getCurrentTextViews(solo.getView(0)).get(1)).getTextSize());
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
