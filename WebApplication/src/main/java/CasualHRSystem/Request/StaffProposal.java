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
public class StaffProposal extends Request {
    public static final String PROPOSAL_ID_FIELD_NAME = "proposalID";
    @DatabaseField(generatedId=true, columnName = PROPOSAL_ID_FIELD_NAME)
    int proposalID;
}
