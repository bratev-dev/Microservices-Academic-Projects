/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionproyectosacademicos.services;
public class SessionContext {
    private static String accessToken;
    private static String refreshToken;
    private static long expirationTime;

    public static void initialize(String access, String refresh, int expiresInSeconds) {
        accessToken = access;
        refreshToken = refresh;
        expirationTime = System.currentTimeMillis() + (expiresInSeconds * 1000L);
    }

    public static boolean isTokenExpired() {
        return System.currentTimeMillis() >= expirationTime;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static void updateAccessToken(String newAccess, String newRefresh, int expiresInSeconds) {
        initialize(newAccess, newRefresh, expiresInSeconds);
    }

    public static void clear() {
        accessToken = null;
        refreshToken = null;
        expirationTime = 0;
    }
}

