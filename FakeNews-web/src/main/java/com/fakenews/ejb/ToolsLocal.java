package com.fakenews.ejb;

import java.util.List;

import javax.ejb.Local;

import com.fakenews.datatypes.DTLoginCitizenRequest;
import com.fakenews.datatypes.DTLoginResponse;
import com.fakenews.datatypes.EnumRoles;
import com.fakenews.model.Citizen;
import com.fakenews.model.Hecho;

/**
	 *
	 * @author rmoreno
	 */
@Local
public interface ToolsLocal {
    
    public String getSecret();
    
    public String createAndSignToken(String username, String password);
    
    public Boolean verifyTokenGoogle(String token_id, String nombre);
    
    public EnumRoles getRolIfAllowed(String username, String password);
    
    public Boolean isUserAllowed(String username, String password);

	public void sendNotifications(Long idHecho);

	public void saveAndroidToken(DTLoginCitizenRequest request);
    
}
