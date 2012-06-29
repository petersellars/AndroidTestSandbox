package net.catosplace.myapp.test;

import net.catosplace.myapp.MyFirstAppActivity;
import net.catosplace.myapp.R;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;

public class MyFirstAppActivityTest extends
		ActivityInstrumentationTestCase2<MyFirstAppActivity> {
	
	private static final String TEST_MESSAGE = "Test Message";
	
	private MyFirstAppActivity mActivity;
	private EditText mEditText;
	private String editTextMessage;
	private Button mSendButton;
	private String sendButtonLabel;

	/**
	 * Constructor - passes information to the super classes constructor
	 */
	public MyFirstAppActivityTest() {
		super("net.catosplace.myapp", MyFirstAppActivity.class);
	}

	/**
	 * 
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		mActivity = getActivity();
		mEditText = (EditText) mActivity.findViewById(R.id.edit_message);
		editTextMessage = mActivity.getString(net.catosplace.myapp.R.string.edit_message);
		mSendButton = (Button) mActivity.findViewById(R.id.send_button);
		sendButtonLabel = mActivity.getString(net.catosplace.myapp.R.string.button_send);
	}
	
	/**
	 * Verify that the application under test initialized correctly
	 */
	public void testPreConditions() {
		assertNotNull(mEditText);
		assertEquals(editTextMessage, mEditText.getHint());
		assertTrue(mEditText.isFocused());
		assertNotNull(mSendButton);
		assertEquals(sendButtonLabel, mSendButton.getHint());
    	assertTrue(mSendButton.isClickable());
	}
	
	/**
	 * Test the Edit Text UI Element
	 */
	@UiThreadTest
	public void testEditTextUI() {
		mEditText.setText(TEST_MESSAGE);
		assertEquals(TEST_MESSAGE, mEditText.getText().toString());
	}
	
	/**
	 * Test that the state remains when the activity is destroyed
	 */
	@UiThreadTest
	public void testStateDestory() {
		mEditText.setText(TEST_MESSAGE);
		mActivity.finish();
		mActivity = this.getActivity();
		assertEquals(TEST_MESSAGE, mEditText.getText().toString());
	}
	
	/**
	 * Test that the state remains when the activity is interrupted
	 */
	@UiThreadTest
	public void testStatePaused() {
		Instrumentation mInstr = this.getInstrumentation();
		mEditText.setText(TEST_MESSAGE);
		mInstr.callActivityOnPause(mActivity);
		// Not sure how to change to make this test worthwhile!!
		mInstr.callActivityOnResume(mActivity);
		assertEquals(TEST_MESSAGE, mEditText.getText().toString());
	}
	
}