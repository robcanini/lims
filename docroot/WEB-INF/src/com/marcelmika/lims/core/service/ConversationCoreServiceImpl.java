/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Marcel Mika, marcelmika.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.marcelmika.lims.core.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.marcelmika.lims.api.environment.Environment;
import com.marcelmika.lims.api.events.conversation.*;
import com.marcelmika.lims.jabber.service.ConversationJabberService;
import com.marcelmika.lims.persistence.service.ConversationPersistenceService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Implementation of ConversationCoreService
 *
 * @author Ing. Marcel Mika
 * @link http://marcelmika.com
 * Date: 2/19/14
 * Time: 11:03 PM
 */
public class ConversationCoreServiceImpl implements ConversationCoreService {

    // Dependencies
    ConversationJabberService conversationJabberService;
    ConversationPersistenceService conversationPersistenceService;

    // Log
    private static Log log = LogFactoryUtil.getLog(ConversationCoreServiceImpl.class);

    /**
     * Constructor
     *
     * @param conversationJabberService jabber service
     */
    public ConversationCoreServiceImpl(final ConversationJabberService conversationJabberService,
                                       final ConversationPersistenceService conversationPersistenceService) {
        this.conversationJabberService = conversationJabberService;
        this.conversationPersistenceService = conversationPersistenceService;
    }

    /**
     * Get all conversations related to the particular buddy
     *
     * @param event request event for method
     * @return response event for  method
     */
    @Override
    public GetConversationsResponseEvent getConversations(GetConversationsRequestEvent event) {
        throw new NotImplementedException();
    }

    /**
     * Get all opened conversations related to the particular buddy
     *
     * @param event request event for method
     * @return response event for  method
     */
    @Override
    public GetOpenedConversationsResponseEvent getOpenedConversations(GetOpenedConversationsRequestEvent event) {
        // Read from persistence
        return conversationPersistenceService.getOpenedConversations(event);
    }

    /**
     * Creates new conversation
     *
     * @param event Request event for login method
     * @return Response event for login method
     */
    @Override
    public CreateConversationResponseEvent createConversation(CreateConversationRequestEvent event) {

        // Create conversation locally
        CreateConversationResponseEvent persistenceResponseEvent = conversationPersistenceService.createConversation(
                event
        );

        // Check for error
        if (!persistenceResponseEvent.isSuccess()) {
            return persistenceResponseEvent;
        }

        // If enabled create in jabber too
        if (Environment.isJabberEnabled()) {
            conversationJabberService.createConversation(event);
        }

        return persistenceResponseEvent;
    }

    /**
     * Reads messages from conversation
     *
     * @param event request event for method
     * @return response event for method
     */
    @Override
    public ReadSingleUserConversationResponseEvent readConversation(ReadSingleUserConversationRequestEvent event) {
        // Read from persistence
        return conversationPersistenceService.readConversation(event);
    }

    /**
     * Opens existing conversation
     *
     * @param event Request event for login method
     * @return Response event for login method
     */
    @Override
    public OpenConversationResponseEvent openConversation(OpenConversationRequestEvent event) {
        throw new NotImplementedException();
    }

    /**
     * Closes existing conversation. User remains in the conversation though.
     *
     * @param event Request event for login method
     * @return Response event for login method
     */
    @Override
    public CloseConversationResponseEvent closeConversation(CloseConversationRequestEvent event) {
        // Save to persistence
        return conversationPersistenceService.closeConversation(event);
    }

    /**
     * Reset counter of unread messages (usually displayed in badge) for the particular user and conversation
     *
     * @param event request event for method
     * @return response event for method
     */
    @Override
    public ResetUnreadMessagesCounterResponseEvent resetUnreadMessagesCounter(ResetUnreadMessagesCounterRequestEvent event) {
        // Save to persistence
        return conversationPersistenceService.resetUnreadMessagesCounter(event);
    }

    /**
     * Removes buddy from the conversation
     *
     * @param event Request event for login method
     * @return Response event for login method
     */
    @Override
    public LeaveConversationResponseEvent leaveConversation(LeaveConversationRequestEvent event) {
        throw new NotImplementedException();
    }

    /**
     * Adds buddies to the conversation
     *
     * @param event request event for method
     * @return response event for method
     */
    @Override
    public AddBuddiesResponseEvent addBuddies(AddBuddiesRequestEvent event) {
        throw new NotImplementedException();
    }

    /**
     * Sends message to conversation
     *
     * @param event request event for method
     * @return response event for method
     */
    @Override
    public SendMessageResponseEvent sendMessage(SendMessageRequestEvent event) {

        // Send message locally
        SendMessageResponseEvent persistenceResponseEvent = conversationPersistenceService.sendMessage(event);

        // Check for error
        if (!persistenceResponseEvent.isSuccess()) {
            return persistenceResponseEvent;
        }

        // If enabled send message via jabber as well
        if (Environment.isJabberEnabled()) {
            SendMessageResponseEvent jabberResponseEvent = conversationJabberService.sendMessage(event);
            if (!jabberResponseEvent.isSuccess()) {
                log.error(jabberResponseEvent.getStatus());
                log.error(jabberResponseEvent.getException());
            }
        }

        // Return persistence event
        return persistenceResponseEvent;
    }
}
