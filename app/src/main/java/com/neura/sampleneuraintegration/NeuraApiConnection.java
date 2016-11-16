package com.neura.sampleneuraintegration;

/**
 * Created by ojussave on 11/15/16.
 */

import com.neura.resources.authentication.AuthenticateCallback;
import com.neura.resources.authentication.AuthenticateData;
import com.neura.sdk.object.AuthenticationRequest;
import com.neura.sdk.object.Permission;
import com.neura.sdk.service.SubscriptionRequestCallbacks;
import com.neura.standalonesdk.service.NeuraApiClient;
import com.neura.standalonesdk.util.Builder;
import com.neura.standalonesdk.util.SDKUtils;
import android.content.Context;

public class NeuraApiConnection {

    private static NeuraApiConnection instance = new NeuraApiConnection();

    private NeuraApiClient mNeuraApiClient;

    private NeuraApiConnection() {}

    static NeuraApiConnection getInstance () {return instance;}
    public NeuraApiClient getClient() {return mNeuraApiClient;}

    public void initNeura( Context context){
        Builder builder = new Builder(context);
        mNeuraApiClient = builder.build();
        mNeuraApiClient.setAppUid(context.getResources().getString(R.string.app_uid));
        mNeuraApiClient.setAppSecret(context.getResources().getString(R.string.app_secret));
        mNeuraApiClient.connect();
    }
}
