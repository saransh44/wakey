package guptagaming.com.myo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.XDirection;
import com.thalmic.myo.scanner.ScanActivity;
import com.thalmic.myo.Arm;
import com.thalmic.myo.Quaternion;

public class MyoActivity extends Activity {

    private Button scan;
    private Button start;
    private static final String TAG = "MyOActivity";
    private TextView axis;
    private TextView mLockStateView;
    private TextView mTextView;
    private TextView X;
    private TextView Y;
    private TextView Z;
    private TextView time;
    float roll;
    float pitch;
    float yaw;
    float roll2 = 0;
    float pitch2 = 0;
    float yaw2 = 0;
    int steps;
    //String sleeps?
    // Classes that inherit from AbstractDeviceListener can be used to receive events from Myo devices.
    // If you do not override an event, the default behavior is to do nothing.

    double speedOfX;
    double speedOfY;
    double speedOfZ;
    Boolean awake = true;
    int timeCounter = 0;
    //double
    private DeviceListener mListener = new AbstractDeviceListener() {
        // onConnect() is called whenever a Myo has been connected.
        @Override
        public void onConnect(Myo myo, long timestamp) {
            // Set the text color of the text view to cyan when a Myo connects.
            mTextView.setTextColor(Color.CYAN);
        }
        // onDisconnect() is called whenever a Myo has been disconnected.
        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            // Set the text color of the text view to red when a Myo disconnects.
            mTextView.setTextColor(Color.RED);
        }
        // onArmSync() is called whenever Myo has recognized a Sync Gesture after someone has put it on their
        // arm. This lets Myo know which arm it's on and which way it's facing.
        @Override
        public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection) {
            mTextView.setText(myo.getArm() == Arm.LEFT ? R.string.arm_left : R.string.arm_right);
        }
        // onArmUnsync() is called whenever Myo has detected that it was moved from a stable position on a person's arm after
        // it recognized the arm. Typically this happens when someone takes Myo off of their arm, but it can also happen
        // when Myo is moved around on the arm.
        @Override
        public void onArmUnsync(Myo myo, long timestamp) {
            mTextView.setText(R.string.hello_world);
        }
        // onUnlock() is called whenever a synced Myo has been unlocked. Under the standard locking
        // policy, that means poses will now be delivered to the listener.
        @Override
        public void onUnlock(Myo myo, long timestamp) {
            mLockStateView.setText(R.string.unlocked);
        }
        // onLock() is called whenever a synced Myo has been locked. Under the standard locking
        // policy, that means poses will no longer be delivered to the listener.
        @Override
        public void onLock(Myo myo, long timestamp) {
            mLockStateView.setText(R.string.locked);
        }
        // onOrientationData() is called whenever a Myo provides its current orientation,
        // represented as a quaternion.
        @Override
        public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
            // Calculate Euler angles (roll, pitch, and yaw) from the quaternion.
            roll = (float) Math.toDegrees(Quaternion.roll(rotation));
            pitch = (float) Math.toDegrees(Quaternion.pitch(rotation));
            yaw = (float) Math.toDegrees(Quaternion.yaw(rotation));
            // Adjust roll and pitch for the orientation of the Myo on the arm.
            if (myo.getXDirection() == XDirection.TOWARD_ELBOW) {
                roll *= -1;
                pitch *= -1;
                //yaw = ((yaw*-1) + 25);
                //yaw = yaw * -1;
            }

            mTextView.setRotation(roll);
            mTextView.setRotationX(pitch);
            mTextView.setRotationY(yaw);


            // Next, we apply a rotation to the text view using the roll, pitch, and yaw.
            if ((Math.abs(roll) - Math.abs(roll2)) > 1 || (Math.abs(pitch) - Math.abs(pitch2)) > 1 || ((Math.abs(yaw) - Math.abs(yaw2))  > 1))
            {
                /*X.setText(Float.toString(Math.abs(roll) - Math.abs(roll2)));
                Y.setText(Float.toString(Math.abs(pitch) - Math.abs(pitch2)));
                Z.setText(Float.toString(Math.abs(yaw) - Math.abs(yaw2)));*/

                X.setText(Float.toString(Math.abs(roll)));
                Y.setText(Float.toString(Math.abs(pitch)));
                Z.setText(Float.toString(Math.abs(yaw)));

                awake = true;
                timeCounter = 0;
            }
            else
            {
                timeCounter +=1;
                if(timeCounter > 500){
                    awake = false;
                }
            }

            time.setText(Integer.toString(timeCounter));

            roll2 = (float) Math.toDegrees(Quaternion.roll(rotation));
            pitch2 = (float) Math.toDegrees(Quaternion.pitch(rotation));
            yaw2 = (float) Math.toDegrees(Quaternion.yaw(rotation));
            //yaw2 = ((yaw2*-1) + 25);
            //yaw2 *= -1;
            pitch2 *= -1;
            roll2 *= -1;
        }
        // onPose() is called whenever a Myo provides a new pose.
        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            // Handle the cases of the Pose enumeration, and change the text of the text view
            // based on the pose we receive.
            switch (pose) {
                case UNKNOWN:
                    mTextView.setText(getString(R.string.hello_world));
                    break;
                case REST:
                case DOUBLE_TAP:
                    int restTextId = R.string.hello_world;
                    switch (myo.getArm()) {
                        case LEFT:
                            restTextId = R.string.arm_left;
                            break;
                        case RIGHT:
                            restTextId = R.string.arm_right;
                            break;
                    }
                    mTextView.setText(getString(restTextId));
                    break;
                case FIST:
                    mTextView.setText(getString(R.string.pose_fist));
                    break;
                case WAVE_IN:
                    mTextView.setText(getString(R.string.pose_wavein));
                    break;
                case WAVE_OUT:
                    mTextView.setText(getString(R.string.pose_waveout));
                    break;
                case FINGERS_SPREAD:
                    mTextView.setText(getString(R.string.pose_fingersspread));
                    break;
            }
            if (pose != Pose.UNKNOWN && pose != Pose.REST) {
                // Tell the Myo to stay unlocked until told otherwise. We do that here so you can
                // hold the poses without the Myo becoming locked.
                myo.unlock(Myo.UnlockType.HOLD);
                // Notify the Myo that the pose has resulted in an action, in this case changing
                // the text on the screen. The Myo will vibrate.
                myo.notifyUserAction();
            } else {
                // Tell the Myo to stay unlocked only for a short period. This allows the Myo to
                // stay unlocked while poses are being performed, but lock after inactivity.
                myo.unlock(Myo.UnlockType.TIMED);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myo);
        Hub hub = Hub.getInstance();
        if (!hub.init(this)) {
            Log.e(TAG, "Could not initialize the Hub.");
            finish();
            return;
        }

        hub.addListener(mListener);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://burning-inferno-2014.firebaseio.com/");
        scan = (Button) findViewById(R.id.scanButton);
        mTextView = (TextView) findViewById(R.id.ball);
        if(awake == false) {
            myFirebaseRef.child("message").setValue("False");
        }

        X = (TextView) findViewById(R.id.x);
        Y = (TextView) findViewById(R.id.y);
        Z = (TextView) findViewById(R.id.z);
        //Myo myo = null;
        //String d = myo.getXDirection().toString();
        axis = (TextView) findViewById(R.id.AxisValue);

        time = (TextView) findViewById(R.id.debug);
        //axis.setText(d);

        scan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            change();
            //Intent intent = new Intent(this, ScanActivity.class);
            //this.startActivity(intent);
            // Use this instead to connect with a Myo that is very near (ie. almost touching) the device
            //Hub.getInstance().attachToAdjacentMyo();
        }});



    }

    public void change(){
        Intent intent = new Intent(this, ScanActivity.class);
        this.startActivity(intent);
    }

    public void messages(){
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_myo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
