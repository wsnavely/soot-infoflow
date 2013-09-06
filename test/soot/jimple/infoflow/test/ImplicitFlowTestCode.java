package soot.jimple.infoflow.test;

import soot.jimple.infoflow.test.android.ConnectionManager;
import soot.jimple.infoflow.test.android.TelephonyManager;

/**
 * Code for testing the implicit flow feature
 * @author Steven Arzt
 *
 */
public class ImplicitFlowTestCode {
	
	public void simpleTest() {
		String tainted = TelephonyManager.getDeviceId();
		String foo = "";
		if (tainted == "123")
			foo = "x";
		ConnectionManager cm = new ConnectionManager();
		cm.publish(foo);
	}

	public void simpleNegativeTest() {
		String tainted = TelephonyManager.getDeviceId();
		String foo = "";
		if (foo.equals("")) {
			if (tainted == "123")
				tainted = "Hello";
			foo = "x";
		}
		ConnectionManager cm = new ConnectionManager();
		cm.publish(foo);
	}

	public void simpleOverwriteTest() {
		String tainted = TelephonyManager.getDeviceId();
		String foo = "";
		if (tainted == "123") {
			tainted = "Hello";
			foo = "x";
		}
		ConnectionManager cm = new ConnectionManager();
		cm.publish(foo);
	}

	public void switchTest() {
		int secret = TelephonyManager.getIMEI();
		String foo = "";
		switch (secret) {
		case 1:
			foo = "x";
			break;
		}
		ConnectionManager cm = new ConnectionManager();
		cm.publish(foo);
	}
	
	public void convertTest() {
		int secret = TelephonyManager.getIMEI();
		String imei = Integer.toString(secret);
		ConnectionManager cm = new ConnectionManager();
		cm.publish(imei);
	}

	public void sinkTest() {
		int secret = TelephonyManager.getIMEI();
		ConnectionManager cm = new ConnectionManager();
		if (secret == 42)
			cm.publish("Secret is 42");
	}
	
	private boolean lookup(int i) {
		return i == 42;
	}

	public void returnTest() {
		int secret = TelephonyManager.getIMEI();
		ConnectionManager cm = new ConnectionManager();
		if (lookup(secret))
			cm.publish("Secret is 42");
	}
	
	private void doPublish() {
		ConnectionManager cm = new ConnectionManager();
		cm.publish("Secret is 42");
	}

	public void callTest() {
		int secret = TelephonyManager.getIMEI();
		if (secret == 42)
			doPublish();
	}
	
	private void doSomething() {
		int i = 0;
		while (i % 2 == 0)
			i++;
	}

	public void callTest2() {
		int secret = TelephonyManager.getIMEI();
		if (secret == 42) {
			doSomething();
			secret = 0;
			doPublish();
		}
	}

	public void negativeCallTest() {
		int secret = TelephonyManager.getIMEI();
		int other = 42;
		if (other == 42)
			doPublish();
		if (secret == 42)
			other = 1;
	}

}
