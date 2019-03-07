import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
	private static ArrayList<Request> requests;
	private static ArrayList<Zone> zones;
	private static ArrayList<Car> cars;	
	private static Request rq;
	private static Zone zone;
	private static Car car;
	private static int days;
	
	public Controller() {
		requests = new ArrayList<Request>();
		cars = new ArrayList<Car>();
		zones = new ArrayList<Zone>();
	}
	
	public static void main(String [] args) {
		FileInputStream fr;
		int line, counter = 0,ccounter=20;
		String input = "";
		
			try {
				fr = new FileInputStream("examples/100_5_14_25.csv");
				while((line = fr.read()) != -1) {				
					if((char)line == ',') {
						System.out.println(counter + ". " + input);
						processInputData(ccounter, counter, Integer.parseInt(input));
						input = ""; 
					}
					else if((char)line == ';') {
						System.out.println(counter + ". " + input);
						processInputData(ccounter, counter, Integer.parseInt(input));
						input = ""; 
						counter++;
					}
					else if((char)line == '\n') {
						System.out.println(counter + ". " + input);
						processInputData(ccounter, counter, Integer.parseInt(input));
						input = "";
						counter = 0;
					}
					else if((char)line == '+') {
						while((line = fr.read()) != '\n') {
							input += (char)line;
							if((char)line == ':') {
								input = "";
							}
						}
						System.out.println(ccounter + ". " + input);
						ccounter++;
						input = "";
					}
					else {
						if(48 <= line && line <= 57) {
							input += (char)line;
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			/*Test om te zien dat data ingelezen is*/
			System.out.println(cars.size());
			System.out.println(cars.get(5).getId());
	}
	
	static protected void processInputData(int ccounter,int counter,int input){
		switch(ccounter) {
			case 0:
				//Process requests
				switch(counter){
					case 0:
						rq = new Request();
						requests.add(rq);
						rq.setId(input);
						break;
					case 1:
						rq.setZone(input);
						break;
					case 2:
						rq.setStartday(input);
						break;
					case 3:
						rq.setStartminute(input);
						break;
					case 4:
						rq.setDuration(input);
						break;
					case 5:
						rq.addCar(input);
						break;
					case 6:
						rq.setFirstPenalty(input);
						break;
					case 7:
						rq.setSecondPenalty(input);
						break;
					default:
						System.out.println("Invalid counter");
						break;
				}
				break;
			case 1:
				//Process Zones
				switch(counter){
					case 0:
						zone = new Zone();
						zones.add(zone);
						zone.setId(input);
						break;
					case 1:
						zone.addNeighbour(input);
						break;
					default:
						System.out.println("Invalid zone");
						break;
				}
				break;
			case 2:
				//Process Vehicles
				car = new Car(input);
				cars.add(car);
				break;
			case 3:
				//Process Days
				days = input;
				break;
			default:
				//Invalid ccounter
				System.out.println("Unrecognized ccounter");
				break;
		}
	} 
}
