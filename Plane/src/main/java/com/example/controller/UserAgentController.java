package com.example.controller;//package com.example.Controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserAgentController {
    @GetMapping("/user-info")
    public UserInfo getUserInfo(HttpServletRequest request) {
        String ipAddress = getClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        GeoLocation geoLocation = getGeoLocation(ipAddress);
        return new UserInfo(ipAddress, userAgent, geoLocation);
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private GeoLocation getGeoLocation(String ipAddress) {
        String url = "http://ip-api.com/json/" + ipAddress;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, GeoLocation.class);
    }

    @Setter
    @Getter
    public static class UserInfo {
        private String ipAddress;
        private String userAgent;
        private GeoLocation geoLocation;

        public UserInfo(String ipAddress, String userAgent, GeoLocation geoLocation) {
            this.ipAddress = ipAddress;
            this.userAgent = userAgent;
            this.geoLocation = geoLocation;
        }

        // Getters and setters

    }

    @Setter
    @Getter
    public static class GeoLocation {
        private String country;
        //地区
//        private String regionName;
        private String city;
        //经纬度
        private Double lat;
        private Double lon;
        //isp服务商
        private String isp;
        private String as;
        private String org;


        // Getters and setters

    }
}
/*
 todo 区分公网内网类型
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class UserAgentController {

    @GetMapping("/user-info")
    public UserInfo getUserInfo(HttpServletRequest request) {
        String ipAddress = getClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        GeoLocation geoLocation = null;

        if (isPublicIp(ipAddress)) {
            geoLocation = getGeoLocation(ipAddress);
        }

        return new UserInfo(ipAddress, userAgent, geoLocation, isPublicIp(ipAddress) ? "Public" : "Private");
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private boolean isPublicIp(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            if (inetAddress.isAnyLocalAddress() || inetAddress.isLoopbackAddress()) {
                return false;
            }
            if (inetAddress.isSiteLocalAddress()) {
                return false;
            }
            return !Pattern.matches("^(0|10|127|169\\.254|172\\.(1[6-9]|2[0-9]|3[0-1])|192\\.168)\\.", ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    private GeoLocation getGeoLocation(String ipAddress) {
        String url = "http://ip-api.com/json/" + ipAddress;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, GeoLocation.class);
    }

    public static class UserInfo {
        private String ipAddress;
        private String userAgent;
        private GeoLocation geoLocation;
        private String ipType;

        public UserInfo(String ipAddress, String userAgent, GeoLocation geoLocation, String ipType) {
            this.ipAddress = ipAddress;
            this.userAgent = userAgent;
            this.geoLocation = geoLocation;
            this.ipType = ipType;
        }

        // Getters and setters

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public GeoLocation getGeoLocation() {
            return geoLocation;
        }

        public void setGeoLocation(GeoLocation geoLocation) {
            this.geoLocation = geoLocation;
        }

        public String getIpType() {
            return ipType;
        }

        public void setIpType(String ipType) {
            this.ipType = ipType;
        }
    }

    public static class GeoLocation {
        private String country;
        private String regionName;
        private String city;
        private Double lat;
        private Double lon;

        // Getters and setters

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }
    }
}
*/
