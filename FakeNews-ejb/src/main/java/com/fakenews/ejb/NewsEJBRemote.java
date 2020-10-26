package com.fakenews.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.fakenews.datatypes.DTRespuesta;
import com.fakenews.datatypes.EnumRoles;
import com.fakenews.model.Hecho;

@Remote
public interface NewsEJBRemote {
	public List<Hecho> getAllHechos();
	public void cancelaHechosDia();
}
