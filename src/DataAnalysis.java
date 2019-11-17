import java.util.ArrayList;

public class DataAnalysis {
	
	/*return array
	 * 
	 * 0 - Recycle Type
	 * 1 - Label(1)
	 * 2 - Confidence(2)
	 * 
	 */
	
	public static ArrayList<String> analyzeLabels(ArrayList<String> labelData){
	 //Metals Word Dictionary
		String[] metals = new String[10];
		metals[0] = "Beverage can";
		metals[1] = "Aluminum can";
		metals[2] = "Soft drink";
		metals[3] = "Tin can";
		metals[4] = "Carbonated soft drinks";
    
		//Plastics Word Dictionary
		String[] plastics = new String[10];
		plastics[0] = "Bottled water";
		plastics[1] = "Water bottle";
		plastics[2] = "Bottle";
		plastics[3] = "Plastic bottle";
		//plastics[4] = "Carbonated soft drinks";
    
    
		//Paper Word Dictionary
		
		ArrayList<String> returnArray = new ArrayList();  
		int typeIndex = 0;
    //Check to see if its a metal!
		for(int i = 0; i<metals.length; i++) {
			
			if(labelData.contains(metals[i])){
				
				typeIndex = labelData.indexOf(metals[i]);
				
				if(Double.parseDouble((labelData.get(typeIndex+1)))*100 >= 50) {
					returnArray.add(0,"Metal");
					returnArray.add(metals[i]);
					returnArray.add(labelData.get(typeIndex+1));
				}
				
				//System.out.println("This is a valid metal recyclable.");
				//System.out.println("TYPE: " + metals[i]);
				//System.out.println("CONFIDENCE: " + Double.parseDouble((labelData.get(typeIndex+1)))*100 + "%");
    		
			}
		}
		
		typeIndex = 0;
	    //Check to see if its a Plastic!
			for(int i = 0; i<plastics.length; i++) {
				
				if(labelData.contains(plastics[i])){
					
					typeIndex = labelData.indexOf(plastics[i]);
					
					if(Double.parseDouble((labelData.get(typeIndex+1)))*100 >= 50) {
						returnArray.add(0,"Plastic");
						returnArray.add(plastics[i]);
						returnArray.add(labelData.get(typeIndex+1));
					}
					
				}
			}
		
		
		
	return returnArray;
	
	}
}
