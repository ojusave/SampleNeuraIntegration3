package com.neura.sampleneuraintegration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import com.neura.resources.authentication.AuthenticateCallback;
import com.neura.resources.authentication.AuthenticateData;
import com.neura.sdk.object.AuthenticationRequest;
import com.neura.sdk.object.Permission;
import java.util.ArrayList;
import android.util.Log;
import java.lang.String;
import com.neura.sdk.service.SubscriptionRequestCallbacks;
import com.neura.standalonesdk.service.NeuraApiClient;
import com.neura.standalonesdk.util.Builder;
import com.neura.standalonesdk.util.SDKUtils;

public class MainActivity extends AppCompatActivity {

    Button button;
    Context context;

    private ArrayList<Permission> mPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context =this;
        button = (Button) findViewById(R.id.button);
        setAction();
    }

    private void NeuraAuthentication() {
        mPermissions = Permission.list(new String[]{"userLeftWork"});
        AuthenticationRequest request = new AuthenticationRequest(mPermissions);
        NeuraApiConnection.getInstance().getClient().authenticate(request, new AuthenticateCallback() {
            @Override
            public void onSuccess(AuthenticateData authenticateData) {
                Log.i(getClass().getSimpleName(), "Successfully authenticate with neura. "
                        + "NeuraUserId = " + authenticateData.getNeuraUserId() + " "
                        + "AccessToken = " + authenticateData.getAccessToken());
            }

            @Override
            public void onFailure(int i) {
                Log.e(getClass().getSimpleName(), "Failed to authenticate with neura. "
                        + "Reason : " + SDKUtils.errorCodeToString(i));
            }
        });
    }

    private void setAction() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NeuraApiConnection.getInstance().initNeura(context);
                NeuraAuthentication();
            }
        });
    }
}