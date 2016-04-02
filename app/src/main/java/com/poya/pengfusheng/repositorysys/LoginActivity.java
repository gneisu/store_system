package com.poya.pengfusheng.repositorysys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.poya.pengfusheng.repositorysys.base.conf.DotNetWebservice;
import com.poya.pengfusheng.repositorysys.base.conf.FileConstants;
import com.poya.pengfusheng.repositorysys.base.utils.FileUtil;
import com.poya.pengfusheng.repositorysys.base.utils.WebserviceClientUtil;
import com.poya.pengfusheng.repositorysys.fragment.MainFragment;
import com.poya.pengfusheng.repositorysys.pojo.LoginInfo;
import com.poya.pengfusheng.repositorysys.pojo.User;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends FragmentActivity {

    private static final String TAG = "LoginActivity";


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
//        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

//        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }





    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
//        else if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
//        return password.length() > 4;
        return true;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "sysLogin";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("loginname", mEmail);
            soapObject.addProperty("loginpwd", mPassword);
            soapObject.addProperty("corpcode", DotNetWebservice.ORGCODE);
            boolean flag = false;
            try {
                SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
                SoapObject detail = (SoapObject) result.getProperty("sysLoginResult");
                int count = detail.getPropertyCount();
                flag = (count == 0);
                User user = null;
                if (flag) {//登录成功
                    List<User> users = WebserviceClientUtil.parseSoapResultSet((SoapObject) result.getProperty("dauser"), User.class);
                    user = (users == null || users.isEmpty()) ? null : users.get(0);
                    Log.i(TAG, "login user : " + user);
                    LoginInfo loginInfo = LoginInfo.getInstance();
                    loginInfo.setLoginFlag(true);
                    loginInfo.setCname(user.getCNAME());
                    loginInfo.setEname(user.getENAME());
                    loginInfo.setUserName(user.getUSERNAME());
                    loginInfo.setOrgName(user.getORGNAME());
                    loginInfo.setTel(user.getTEL());
                    loginInfo.setPhone(user.getMPHONE());
                    loginInfo.setAtCity(user.getATCITY());
                    loginInfo.setSto(user.getSTO());

                    FileUtil.saveFile(FileConstants.PASSWD_FILE, loginInfo.getLoginInfoJson().toString());

                }
            } catch (Exception e) {
                Log.e(TAG, "error login ", e);
            }
//            Log.i(TAG, "login result : " + result);
            return flag;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
//            showProgress(false);

            if (success) {
                Intent intent = new Intent();
                intent.putExtra(MainFragment.LOGIN_USER_NAME, mEmail);
                intent.putExtra(MainFragment.LOGIN_FLAG, success);
                setResult(MainFragment.REQUEST_CODE_LONIN, intent);
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
//            showProgress(false);
        }
    }

}

