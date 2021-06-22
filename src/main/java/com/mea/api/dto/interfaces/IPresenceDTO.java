package com.mea.api.dto.interfaces;

import java.time.LocalDateTime;


public interface IPresenceDTO {
	
	   public Long getId();
		
		
		public LocalDateTime getDate();
		
		public float getTemperature();
		
		public Long getBeliver_id();
		
		public String getName_register();
		
		public String getName_cult();

}

