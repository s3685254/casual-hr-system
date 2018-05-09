/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Request;

import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author jye
 */
public class StaffPlacement {
    public static final String PROPOSAL_ID_FIELD_NAME = "proposalID";
    public static final String PLACEMENT_FIELD_NAME = "placementID";
    public static final String STAFF_ID_FIELD_NAME = "staffID";
    public static final String TASK_ID_FIELD_NAME = "taskID";
    public static final String ACTIVITY_ID_FIELD_NAME = "activityID";
    @DatabaseField(generatedId=true, columnName = PROPOSAL_ID_FIELD_NAME)
    int proposalID;
    @DatabaseField(canBeNull = false, columnName = PLACEMENT_FIELD_NAME)
    int placementID;
    @DatabaseField(canBeNull = false, columnName = STAFF_ID_FIELD_NAME)
    int staffID;
    @DatabaseField(canBeNull = false, columnName = TASK_ID_FIELD_NAME)
    int taskID;
    @DatabaseField(canBeNull = false, columnName = ACTIVITY_ID_FIELD_NAME)
    int activityID;
}
