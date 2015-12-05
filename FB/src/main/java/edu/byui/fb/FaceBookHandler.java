/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.fb;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Media;
import facebook4j.PhotoUpdate;
import java.io.InputStream;

/**
 *
 * @author Grant
 */
public class FaceBookHandler {
    private static final FaceBookHandler FBH_INSTANCE = new FaceBookHandler();

    private String errorMessage;
    private Facebook facebook;
    private final String APP_ID      = "450092371846757";
    private final String APP_SECRET  = "34d0d6d10639df7ba75b3c847a94dd22";
    private final String PERMISSIONS = "";

    private FaceBookHandler() {
        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(APP_ID, APP_SECRET);
//        facebook.setOAuthPermissions(commaSeparetedPermissions);
    }

    public static FaceBookHandler getInstance() {
        return FBH_INSTANCE;
    }

    String getLoginURL() {
        return facebook.getOAuthAuthorizationURL(facebook.getOAuthCallbackURL());
    }

    void setCallbackURL(String url) {
        facebook.setOAuthCallbackURL(url);
    }

    boolean shareImage(String imageName, InputStream imageInput, String message) throws FacebookException {
        Media image = new Media(imageName, imageInput);
        PhotoUpdate statusUpdate = new PhotoUpdate(image);
        statusUpdate.setMessage(message);
        facebook.postPhoto(statusUpdate);
        return true;
    }

    String getErrorMessage() {
        return errorMessage;
    }
}
