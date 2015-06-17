package main.webapp.ociofe.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        
    }
	
}
