package com.gmyf.smt.factory;

import com.gmyf.smt.factory.api.ServiceApi;
import com.gmyf.smt.factory.impl.SpotifyServiceApiImpl;
import com.gmyf.smt.factory.impl.YoutubeServiceApiImpl;
import com.gmyf.smt.service.dto.ServiceDto;

public class ServiceApiFactory {
    public static ServiceApi getServiceApi(long serviceId) {
        switch ((Long.toString(serviceId))) {
            case "1":
                return new SpotifyServiceApiImpl();
            case "2":
                return new YoutubeServiceApiImpl();
            default:
                return null;
        }
    }
}
