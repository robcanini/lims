/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.marcelmika.lims.persistence.generated.model;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Participant service. Represents a row in the &quot;Lims_Participant&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.marcelmika.lims.persistence.generated.model.impl.ParticipantModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.marcelmika.lims.persistence.generated.model.impl.ParticipantImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Participant
 * @see com.marcelmika.lims.persistence.generated.model.impl.ParticipantImpl
 * @see com.marcelmika.lims.persistence.generated.model.impl.ParticipantModelImpl
 * @generated
 */
public interface ParticipantModel extends BaseModel<Participant> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a participant model instance should use the {@link Participant} interface instead.
	 */

	/**
	 * Returns the primary key of this participant.
	 *
	 * @return the primary key of this participant
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this participant.
	 *
	 * @param primaryKey the primary key of this participant
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the pid of this participant.
	 *
	 * @return the pid of this participant
	 */
	public long getPid();

	/**
	 * Sets the pid of this participant.
	 *
	 * @param pid the pid of this participant
	 */
	public void setPid(long pid);

	/**
	 * Returns the cid of this participant.
	 *
	 * @return the cid of this participant
	 */
	public long getCid();

	/**
	 * Sets the cid of this participant.
	 *
	 * @param cid the cid of this participant
	 */
	public void setCid(long cid);

	/**
	 * Returns the participant ID of this participant.
	 *
	 * @return the participant ID of this participant
	 */
	public long getParticipantId();

	/**
	 * Sets the participant ID of this participant.
	 *
	 * @param participantId the participant ID of this participant
	 */
	public void setParticipantId(long participantId);

	/**
	 * Returns the unread messages count of this participant.
	 *
	 * @return the unread messages count of this participant
	 */
	public int getUnreadMessagesCount();

	/**
	 * Sets the unread messages count of this participant.
	 *
	 * @param unreadMessagesCount the unread messages count of this participant
	 */
	public void setUnreadMessagesCount(int unreadMessagesCount);

	/**
	 * Returns the is opened of this participant.
	 *
	 * @return the is opened of this participant
	 */
	public boolean getIsOpened();

	/**
	 * Returns <code>true</code> if this participant is is opened.
	 *
	 * @return <code>true</code> if this participant is is opened; <code>false</code> otherwise
	 */
	public boolean isIsOpened();

	/**
	 * Sets whether this participant is is opened.
	 *
	 * @param isOpened the is opened of this participant
	 */
	public void setIsOpened(boolean isOpened);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Participant participant);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Participant> toCacheModel();

	@Override
	public Participant toEscapedModel();

	@Override
	public Participant toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}