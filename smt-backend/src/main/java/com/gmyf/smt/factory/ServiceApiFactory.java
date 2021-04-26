package com.gmyf.smt.factory;

import com.gmyf.smt.factory.api.ServiceApi;
import com.gmyf.smt.factory.impl.SpotifyServiceApiImpl;

public class ServiceApiFactory {
    public static ServiceApi getServiceApi(long serviceId) {
        switch ((Long.toString(serviceId))) {
            case "1":
                return new SpotifyServiceApiImpl();
            default:
                return null;
        }
    }
}
