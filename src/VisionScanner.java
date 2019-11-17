// Imports the Google Cloud client library



import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

import java.awt.Dimension;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class VisionScanner {
  public static ArrayList<String> scan() throws Exception {
    // Instantiates a client
    try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

      String fileName = "hello-world.png";

      // Reads the image file into memory
      Path path = Paths.get(fileName);
      byte[] data = Files.readAllBytes(path);
      ByteString imgBytes = ByteString.copyFrom(data);

      // Builds the image annotation request
      List<AnnotateImageRequest> requests = new ArrayList<>();
      Image img = Image.newBuilder().setContent(imgBytes).build();
      Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
      AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
          .addFeatures(feat)
          .setImage(img)
          .build();
      requests.add(request);

      // Performs label detection on the image file
      BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
      List<AnnotateImageResponse> responses = response.getResponsesList();

      
     //Checks for errors in annotation response 
      for (AnnotateImageResponse res : responses) {
    	  if (res.hasError()) {
        	System.out.printf("Error: %s\n", res.getError().getMessage());
          return null;
        }
        

    	  ArrayList<String> labelData = new ArrayList<>();
    	  
        for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
          annotation.getAllFields().forEach((k, v) ->
              labelData.add((v.toString())));
        }
        
        return labelData;
        
        
      }

      }
	return null;
    }
  }