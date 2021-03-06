package com.thealmostengineer.kdenlivetoyoutube;

import java.io.File;
import java.util.Properties;

import com.thealmostengineer.kdenlivetoyoutube.filesystem.FileOperations;
import com.thealmostengineer.kdenlivetoyoutube.filesystem.ProcessPendingFile;

/**
 * Application to render videos with Kdenlive and upload them to YouTube
 * 
 * @author almostengr, Kenny Robinson
 * 
 */
public class App {
	public static Properties appProperty = null;
	
    public static void main( String[] args )    {
    	int exitCode = 1; // default the exit code to failure
    	
    	try {
    		if (args[0].isEmpty()) {
    			exitCode = 2;
    			throw new RuntimeException("Properties file not provided.");
    		} // end if
        	 
        	appProperty = LoadProperties.loadProperties(args[0]);

        	PreventDupeProcess.checkForDuplicateProcess();
//        	CheckFreeSpace.checkFreeSpace();
    		
			File[] pendingFiles = FileOperations.getFilesInFolder(appProperty.getProperty("pendingDirectory"));
			
			for (int i = 0; i < pendingFiles.length; i++) {
				ProcessPendingFile.processPendingFile(pendingFiles[i]);
			}

			File[] videoOutputFiles = FileOperations.getFilesInFolder(appProperty.getProperty("outputDirectory"));
			
			// limit the number of files uploaded to prevent hitting the quota
			int videoFileCounter = videoOutputFiles.length;
			if (videoFileCounter > 3) {
				videoFileCounter = 2;
			} // end if
			
			// for (int i = 0; i < videoFileCounter; i++) {
				// UploadOutputFile.uploadOutputFile(videoOutputFiles[i]);
			// } // end for
			 
			exitCode = 0;
		} catch (Exception e) {
			Logging.error("Error Detail: " + e.getMessage());
			e.printStackTrace();
		} // end catch
    
    	Logging.info("Exiting, " + exitCode);
    	System.exit(exitCode);
    } // end function
}
 