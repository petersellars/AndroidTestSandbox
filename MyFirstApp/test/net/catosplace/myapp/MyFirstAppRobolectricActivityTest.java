package net.catosplace.myapp;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowIntent;

@RunWith(RobolectricTestRunner.class)

public class MyFirstAppRobolectricActivityTest {
	
	private MyFirstAppActivity startActivity;
	private EditText editText;
	private Button sendButton;
	
	@Before
    public void setUp() throws Exception {
        startActivity = new MyFirstAppActivity();
        startActivity.onCreate(null);
        editText = (EditText) startActivity.findViewById(R.id.edit_message);
        sendButton = (Button) startActivity.findViewById(R.id.send_button);
    }

    @Test
    public void shouldHaveHintInEditText() throws Exception {
        String editTextHint = startActivity.getResources().getString(R.string.edit_message);
        assertThat((String)editText.getHint(), equalTo(editTextHint));
    }
    
    @Test
    public void shouldHaveSendButton() throws Exception {
    	String sendButtonText = startActivity.getResources().getString(R.string.button_send);
    	assertThat((String)sendButton.getHint(), equalTo(sendButtonText));
    }
    
    @Test
    public void pressingSendButtonPopulatesIntent() throws Exception {
    	editText.setText("TEST");
    	sendButton.performClick();

        ShadowActivity shadowActivity = shadowOf(startActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);

        assertThat(shadowIntent.getComponent().getClassName(), equalTo(DisplayMessageActivity.class.getName()));
        assertThat((String)startedIntent.getStringExtra(MyFirstAppActivity.EXTRA_MESSAGE), equalTo("TEST"));
    }
    
}

