package a2;

public class TrainNetwork {
	final int swapFreq = 2;
	TrainLine[] networkLines;

	public TrainNetwork(int nLines) {
		this.networkLines = new TrainLine[nLines];
	}

	public void addLines(TrainLine[] lines) {
		this.networkLines = lines;
	}

	public TrainLine[] getLines() {
		return this.networkLines;
	}

	public void dance() {
		System.out.println("The tracks are moving!");
		for (int i = 0; i < networkLines.length; i++) {
			networkLines[i].shuffleLine();
		}
	}

	public void undance() {
		for (int i = 0; i < networkLines.length; i++) {
			networkLines[i].sortLine();
		}
	}

	public int travel(String startStation, String startLine, String endStation, String endLine) {

		TrainLine curLine = getLineByName(startLine); // use this variable to store the current line.
		TrainStation curStation;
		try {
			curStation = curLine.findStation(startStation); // use this variable to store the current station.
		}catch(StationNotFoundException e) {
			System.out.println("startStation: " + startStation + " cannot be found");
			return 168;
		}
		TrainStation preStation = curStation;
		int hoursCount = 0;
		System.out.println("Departing from " + startStation);

		boolean done = false;
		while (!done) {

			if (hoursCount == 168) {
				System.out.println("Jumped off after spending a full week on the train. Might as well walk.");
				return hoursCount;
			}
			// reach the destination
			if (curStation.getName().equals(endStation)) {
				break;
			}
			TrainStation next = curLine.travelOneStation(curStation, preStation);
			preStation = curStation;
			curStation = next;
			curLine = getLineByName(curStation.getLine().getName());
			
			hoursCount += 1;
			if (hoursCount % 2 == 0) {
				dance();
			}
			// prints an update on your current location in the network.
			System.out.println("Traveling on line " + curLine.getName() + ":" + curLine.toString());
			System.out.println("Hour " + hoursCount + ". Current station: " + curStation.getName() + " on line "
					+ curLine.getName());
			System.out.println("=============================================");
			
		}

		System.out.println("Arrived at destination after " + hoursCount + " hours!");
		return hoursCount;
	}

	// you can extend the method header if needed to include an exception. You
	// cannot make any other change to the header.
	public TrainLine getLineByName(String lineName) {
		for (int i = 0; i < networkLines.length; i++) {
			if (networkLines[i].getName().equals(lineName)) {
				return networkLines[i];
			}
		}

		throw new LineNotFoundException("getLineByName method error: lineName " + lineName + " not found");
	}

	// prints a plan of the network for you.
	public void printPlan() {
		System.out.println("CURRENT TRAIN NETWORK PLAN");
		System.out.println("----------------------------");
		for (int i = 0; i < this.networkLines.length; i++) {
			System.out.println(this.networkLines[i].getName() + ":" + this.networkLines[i].toString());
		}
		System.out.println("----------------------------");
	}
}

//exception when searching a network for a LineName and not finding any matching Line object.
class LineNotFoundException extends RuntimeException {
	String name;

	public LineNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "LineNotFoundException[" + name + "]";
	}
}