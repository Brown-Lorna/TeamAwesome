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
    // Only one!
    private static final FaceBookHandler FBH_INSTANCE = new FaceBookHandler();

    private Facebook facebook;
    private final String APP_ID = "450092371846757";
    private final String APP_SECRET = "34d0d6d10639df7ba75b3c847a94dd22";
    private final String PERMISSIONS = "publish_actions";

    /**
     * Constructor that sets up the Facebook instance.
     */
    private FaceBookHandler() {
        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(APP_ID, APP_SECRET);
        facebook.setOAuthPermissions(PERMISSIONS);
    }

    /**
     * getInstance
     *     Method to call the only existing object of FaceBookHandler
     * @return 
     */
    public static FaceBookHandler getInstance() {
        return FBH_INSTANCE;
    }

    /**
     * getLoginURL
     *     Gets the login dialog authorization url, given a callback url.
     * @return 
     */
    String getLoginURL() {
        return facebook.getOAuthAuthorizationURL(facebook.getOAuthCallbackURL());
    }

    /**
     * setCallbackURL
     *     Set the callback url (Call this before calling getLoginURL())
     * @param url 
     */
    void setCallbackURL(String url) {
        facebook.setOAuthCallbackURL(url);
    }

    /**
     * getAccessToken
     *     Given a code, the authorization access token is grabbed
     * @param oauthCode
     * @throws FacebookException 
     */
    void getAccessToken(String oauthCode) throws FacebookException {
        facebook.getOAuthAccessToken(oauthCode);
    }

    /**
     * shareImage
     *    Share the image (imageInput), it's name (imageName), and a message (message)
     * to the currently logged in Facebook's timeline.
     * @param imageName
     * @param imageInput
     * @param message
     * @return
     * @throws FacebookException 
     */
    boolean shareImage(String imageName, InputStream imageInput, String message) throws FacebookException {
        Media image = new Media(imageName, imageInput);
        PhotoUpdate statusUpdate = new PhotoUpdate(image);
        statusUpdate.setMessage(message);
        facebook.postPhoto(statusUpdate);
        return true;
    }
}
