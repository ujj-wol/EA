package edu.mum.component.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.component.MessageDisplay;
import edu.mum.component.MessageOrigin;

@Component
public class FileMessageDisplay implements MessageDisplay {
     
	@Autowired
	public MessageOrigin messageOrigin;
	
    public void display() {
        if (messageOrigin == null) {
            throw new RuntimeException(
                "You must set the property messageSource of class:"
                + FileMessageDisplay.class.getName());
        }

        System.out.println(messageOrigin.getMessage());
        
         writeMessageToFile(messageOrigin.getMessage());
    }

    
    public MessageOrigin getMessageSource() {
        return this.messageOrigin;
    }
    
    private void writeMessageToFile(String message) {
    	String fileName = "test.txt";
  
    	String path = this.getClass().getClassLoader().getResource(".").getFile();
    	
    	//the following way should probably be better. but file created has filename of the whole path
//    	path = path.replaceAll("%20", " ");
    	
//    	try {
//			path = URLEncoder.encode(path, "UTF-8");
//		} catch (UnsupportedEncodingException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//    	System.out.println(path);
    	

//    	//create file called "test.txt" at location of path variable
//    	File file = new File(path + fileName);
    	
    	//create a file
    	File file = new File(fileName);
    	
    	//path of the user directory
    	System.out.println(System.getProperty("user.dir"));
    	
    	try {
			if (file.createNewFile()) {
			    System.out.println("File is created!");
			} else {
			    System.out.println("File already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(message);
			bufferedWriter.close();
		    System.out.println("Message written to File in target/classes " + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	return  ;
    }
}
