package com.h3ll3m4;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.colour.Transforms;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;

import java.io.IOException;

import java.net.URL;

import java.util.List;


import org.json.simple.JSONObject;


/**
 * The function accepts a jpeg, gif, or png file of varying dimensions. 
 * The function identify the number of faces in that image. It creates a
 * copy of the image with the faces visually identified with a surrounding 
 * box. The function returns a JSON file with two fields, count faces and image url.  
 * There may not be any faces, in which case the function should return zero.
 *
 */
public class App {
    public static void main( String[] args ) {
    	try {
        	findingFacesPhoto("https://fanart.tv/fanart/music/c5998351-be49-49d8-8593-3e96f129c1fc/albumcover/ce-que-lon-sme-4f67c7c949d96.jpg");
    	} catch (IOException e) {
			e.printStackTrace();
		}

    }  
    
	static JSONObject findingFacesPhoto(String url) throws  IOException {
    	int nbFaces=0;
        JSONObject obj = new JSONObject();
    	MBFImage frame = ImageUtilities.readMBF(new URL(url));
    	//Method HaarCascadeDetector
    	FaceDetector<DetectedFace,FImage> fd = new HaarCascadeDetector(40);
    	List<DetectedFace> faces = fd.detectFaces( Transforms.calculateIntensity(frame));
    	for( DetectedFace face : faces ) {
    	    frame.drawShape(face.getBounds(), RGBColour.RED);
    	    nbFaces++;
    	}
    	DisplayUtilities.display(frame);
        System.out.printf("We found %d faces", nbFaces);
        obj.put("Number of Faces", nbFaces);
        obj.put("Location", url);
        System.out.print(obj);
        return obj;
    }
        
}
