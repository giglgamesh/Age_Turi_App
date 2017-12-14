package pe.oranch.agenciaturismo.utilities;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Panacea-Soft on 8/11/16.
 * Contact Email : teamps.is.cool@gmail.com
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Utils.psLog("token : " + token);

    }

}
