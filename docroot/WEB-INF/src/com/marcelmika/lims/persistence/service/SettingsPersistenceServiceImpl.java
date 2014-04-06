package com.marcelmika.lims.persistence.service;

import com.marcelmika.lims.api.entity.BuddyDetails;
import com.marcelmika.lims.api.entity.SettingsDetails;
import com.marcelmika.lims.events.settings.*;
import com.marcelmika.lims.model.Settings;
import com.marcelmika.lims.service.SettingsLocalServiceUtil;

/**
 * @author Ing. Marcel Mika
 * @link http://marcelmika.com
 * Date: 3/30/14
 * Time: 8:29 PM
 */
public class SettingsPersistenceServiceImpl implements SettingsPersistenceService {


    /**
     * Update buddy's active panel (panel which is open)
     *
     * @param event Request event for logout method
     * @return Response event for logout method
     */
    @Override
    public UpdateActivePanelResponseEvent updateActivePanel(UpdateActivePanelRequestEvent event) {
        try {
            // Get settings
            Settings settings = SettingsLocalServiceUtil.getSettings(event.getBuddyId());
            // Set values
            settings.setActivePanelId(event.getActivePanel());
            // Save settings
            SettingsLocalServiceUtil.updateSettings(settings, false);

            return UpdateActivePanelResponseEvent.updateActivePanelSuccess(
                    "Active Panel" + event.getActivePanel() + " saved to persistence layer for user "
                            + event.getBuddyId()
            );

        } catch (Exception exception) {
            return UpdateActivePanelResponseEvent.updateActivePanelFailure(
                    "Cannot update Active Panel to a persistence layer", exception
            );
        }
    }

    /**
     * Update buddy's active room type (i.e. public or private)
     *
     * @param event Request event for logout method
     * @return Response event for logout method
     */
    @Override
    public UpdateActiveRoomTypeResponseEvent updateActiveRoomType(UpdateActiveRoomTypeRequestEvent event) {
        try {
            // Change Active Room type
            SettingsLocalServiceUtil.changeActiveRoomType(event.getBuddyId(), event.getActiveRoomType());

            return UpdateActiveRoomTypeResponseEvent.updateActiveRoomTypeSuccess(
                    "Active Room Type " + event.getActiveRoomType() + " saved to persistence layer for user "
                            + event.getBuddyId()
            );
        } catch (Exception exception) {
            return UpdateActiveRoomTypeResponseEvent.updateActiveRoomTypeFailure(
                    "Cannot update Active Room type to a persistence layer", exception
            );
        }
    }

    /**
     * Update buddy's settings
     *
     * @param event Request event for logout method
     * @return Response event for logout method
     */
    @Override
    public UpdateSettingsResponseEvent updateSettings(UpdateSettingsRequestEvent event) {
        SettingsDetails details = event.getSettingsDetails();
        try {
            // Get settings
            Settings settings = SettingsLocalServiceUtil.getSettings(event.getBuddyId());
            // Set new values
            settings.setMute(details.isMute());
            // Save
            SettingsLocalServiceUtil.updateSettings(settings, false);

            return UpdateSettingsResponseEvent.updateSettingsSuccess(
                    "Settings saved to persistence layer for user " + event.getBuddyId(), details
            );

        } catch (Exception exception) {
            return UpdateSettingsResponseEvent.updateSettingsFailure(
                    "Cannot update Settings to a persistence layer", details, exception
            );
        }
    }

    /**
     * Enables chat for buddy
     *
     * @param event Request event for logout method
     * @return Response event for logout method
     */
    @Override
    public EnableChatResponseEvent enableChat(EnableChatRequestEvent event) {
        // Get buddy
        BuddyDetails buddy = event.getBuddyDetails();

        try {
            // Save
            SettingsLocalServiceUtil.setChatEnabled(buddy.getBuddyId(), true);
            // Success
            return EnableChatResponseEvent.enableChatSuccess("User chat enabled");

        } catch (Exception e) {
            // Failure
            return EnableChatResponseEvent.enableChatFailure("Cannot enable chat", e);
        }
    }

    /**
     * Disables chat for buddy
     *
     * @param event Request event for logout method
     * @return Response event for logout method
     */
    @Override
    public DisableChatResponseEvent disableChat(DisableChatRequestEvent event) {
        // Get buddy
        BuddyDetails buddy = event.getBuddyDetails();

        try {
            // Save
            SettingsLocalServiceUtil.setChatEnabled(buddy.getBuddyId(), false);
            // Success
            return DisableChatResponseEvent.disableChatSuccess("Chat disabled");

        } catch (Exception e) {
            // Failure
            return DisableChatResponseEvent.disableChatFailure("Cannot disable chat", e);
        }
    }
}
