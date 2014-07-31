/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.marcelmika.lims.persistence.generated.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.marcelmika.lims.persistence.generated.NoSuchConversationException;
import com.marcelmika.lims.persistence.generated.model.Conversation;
import com.marcelmika.lims.persistence.generated.service.base.ConversationLocalServiceBaseImpl;

import java.util.Date;

/**
 * The implementation of the conversation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.marcelmika.lims.persistence.generated.service.ConversationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.marcelmika.lims.persistence.generated.service.base.ConversationLocalServiceBaseImpl
 * @see com.marcelmika.lims.persistence.generated.service.ConversationLocalServiceUtil
 */
public class ConversationLocalServiceImpl
	extends ConversationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.marcelmika.lims.persistence.generated.service.ConversationLocalServiceUtil} to access the conversation local service.
	 */

    public Conversation addConversation(String conversationId, String conversationType) throws SystemException {
        // Fetch possible existing conversation
        Conversation conversationModel = conversationPersistence.fetchByConversationId(conversationId);

        if (conversationModel == null) {
            conversationModel = conversationPersistence.create(counterLocalService.increment());
            conversationModel.setConversationId(conversationId);
            conversationModel.setConversationType(conversationType);
            conversationModel.setUpdatedAt(new Date());
            conversationModel = conversationPersistence.update(conversationModel, false);
        }

        return conversationModel;
    }

    public Conversation getConversation(String conversationId) throws SystemException {
        try {
            return conversationPersistence.findByConversationId(conversationId);
        } catch (NoSuchConversationException e) {
            return null;
        }
    }

    public void updateConversationTimestamp(long cid) throws Exception {
        Conversation conversation = conversationPersistence.findByPrimaryKey(cid);
        conversation.setUpdatedAt(new Date());
        conversationPersistence.update(conversation, false);

    }
}